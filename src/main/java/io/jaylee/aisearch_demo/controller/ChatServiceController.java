package io.jaylee.aisearch_demo.controller;

import io.jaylee.aisearch_demo.service.AzureOpenAIChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatServiceController {

    private final AzureOpenAIChatService azureOpenAIChatService;

    @PostMapping("/chat")
    public String chatCompletion(@RequestBody String userPrompt) {
        return azureOpenAIChatService.chatCompletion("gpt-4o", userPrompt);
    }

    @PostMapping("/chatwithdata")
    public String chatCompletionWithSearch(@RequestBody String userPrompt) {
        return azureOpenAIChatService.chatCompletionWithSearch("gpt-4o", userPrompt);
    }
}
