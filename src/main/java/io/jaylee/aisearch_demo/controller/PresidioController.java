package io.jaylee.aisearch_demo.controller;

import io.jaylee.aisearch_demo.service.PresidioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PresidioController {

    private final PresidioService presidioService;

    @PostMapping("/presidio/anonymize")
    public String processPrompt(@RequestBody String prompt) {
        return presidioService.processPrompt(prompt);
    }
}