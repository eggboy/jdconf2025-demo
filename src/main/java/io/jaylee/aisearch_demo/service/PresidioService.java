package io.jaylee.aisearch_demo.service;

import io.jaylee.aisearch_demo.domain.presidio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class PresidioService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${PRESIDIO_ANALYZER_ENDPOINT}")
    private String presidioAnalyzerUrl;

    @Value("${PRESIDIO_ANONYMIZER_ENDPOINT}")
    private String presidioAnonymizerUrl;

    public List<PresidioAnalyzeResponse> anlayze(String prompt, String language) {
        PresidioAnalyzeRequest presidioAnalyzeRequest = new PresidioAnalyzeRequest();
        presidioAnalyzeRequest.setText(prompt);
        presidioAnalyzeRequest.setLanguage(language);
        log.info("presidio analyzer request: {}", presidioAnalyzeRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PresidioAnalyzeRequest> request = new HttpEntity<>(presidioAnalyzeRequest, headers);

        PresidioAnalyzeResponse[] response = restTemplate.postForObject(presidioAnalyzerUrl, request, PresidioAnalyzeResponse[].class);

        return response != null ? List.of(response) : List.of();
    }

    public String anonymize(List<PresidioAnalyzeResponse> analyzeEntities, String prompt) {
        PresidioAnonymizeRequest presidioAnonymizeRequest = new PresidioAnonymizeRequest();
        presidioAnonymizeRequest.setText(prompt);

        for (PresidioAnalyzeResponse entity : analyzeEntities) {
            presidioAnonymizeRequest.getAnalyzer_results()
                                    .add(new PresidioAnalyzerResult(entity.getStart(), entity.getEnd(), entity.getScore(), entity.getEntityType()));
        }

        log.info("presidio anonymize request: {}", presidioAnonymizeRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PresidioAnonymizeRequest> request = new HttpEntity<>(presidioAnonymizeRequest, headers);

        PresidioAnonymizeResponse response = restTemplate.postForObject(presidioAnonymizerUrl, request, PresidioAnonymizeResponse.class);

        return response != null ? response.getText() : "";
    }

    public String processPrompt(String prompt) {
        List<PresidioAnalyzeResponse> analyzeEntities = anlayze(prompt, "en");
        return anonymize(analyzeEntities, prompt);
    }
}
