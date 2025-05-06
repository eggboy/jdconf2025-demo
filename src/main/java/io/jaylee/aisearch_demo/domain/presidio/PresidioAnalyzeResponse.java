package io.jaylee.aisearch_demo.domain.presidio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PresidioAnalyzeResponse {
    private String analysisExplanation;
    private int end;
    @JsonProperty("entity_type")
    private String entityType;
    private RecognitionMetadata recognitionMetadata;
    private double score;
    private int start;
}

@Data
@ToString
class RecognitionMetadata {
    private String recognizerIdentifier;
    private String recognizerName;
}