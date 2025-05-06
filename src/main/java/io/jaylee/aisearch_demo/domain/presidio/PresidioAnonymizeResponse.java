package io.jaylee.aisearch_demo.domain.presidio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PresidioAnonymizeResponse {
    private String text;
    private List<AnonymizeItem> items;

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AnonymizeItem> getItems() {
        return items;
    }

    public void setItems(List<AnonymizeItem> items) {
        this.items = items;
    }
}

class AnonymizeItem {
    private int start;
    private int end;
    private String entityType;
    private String text;
    private String operator;

    // Getters and setters
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}