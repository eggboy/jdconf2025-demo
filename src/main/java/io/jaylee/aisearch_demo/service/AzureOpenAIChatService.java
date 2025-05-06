package io.jaylee.aisearch_demo.service;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AzureOpenAIChatService {

    @Autowired
    private OpenAIClient openAIClient;

    @Value("${AZURE_SEARCH_ENDPOINT}")
    private String azureSearchEndpoint;

    @Value("${AZURE_SEARCH_KEY}")
    private String azureSearchKey;

    public String chatCompletion(String deploymentOrModelId, String userPrompt) {
        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestUserMessage(userPrompt));

        ChatCompletions chatCompletions = openAIClient.getChatCompletions(
                deploymentOrModelId, new ChatCompletionsOptions(chatMessages)
                        .setTemperature(0.7)
                        .setMaxTokens(4096)
                        .setTopP(1.0)
                        .setFrequencyPenalty(0.0)
                        .setPresencePenalty(0.0)
        );

        return chatCompletions.getChoices().get(0).getMessage().getContent();
    }

    public String chatCompletionWithSearch(String deploymentOrModelId, String userPrompt) {
        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        AISearchChatExtension aisearchChatExtension = new AISearchChatExtension(azureSearchEndpoint, azureSearchKey, "sharepointkbindex");

        chatMessages.add(new ChatRequestUserMessage(userPrompt));

        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages)
                .setTemperature(0.2)
                .setMaxTokens(4096)
                .setTopP(1.0)
                .setDataSources(Arrays.asList(aisearchChatExtension.getAzureSearchChatExtensionConfiguration()));

        ChatCompletions chatCompletions = openAIClient.getChatCompletions(deploymentOrModelId, chatCompletionsOptions);

        StringBuilder responseBuilder = new StringBuilder();
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            responseBuilder.append(message.getContent()).append("\n");
        }

        return responseBuilder.toString();
    }
}
