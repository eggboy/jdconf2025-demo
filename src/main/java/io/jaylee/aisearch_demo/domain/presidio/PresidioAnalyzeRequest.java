package io.jaylee.aisearch_demo.domain.presidio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PresidioAnalyzeRequest {
    private String text;
    private String language;
    @JsonProperty("score_threshold")
    private double scoreThreshold = 0.7;
}
