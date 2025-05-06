package io.jaylee.aisearch_demo.domain.presidio;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Getter
@Setter
public class PresidioAnonymizeRequest {
    public String text;
    public List<PresidioAnalyzerResult> analyzer_results = new ArrayList<>();
}

