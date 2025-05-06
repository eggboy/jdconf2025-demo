package io.jaylee.aisearch_demo.domain.presidio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PresidioAnalyzerResult {
    public int start;
    public int end;
    public double score;
    @JsonProperty("entity_type")
    public String entity_type;
}
