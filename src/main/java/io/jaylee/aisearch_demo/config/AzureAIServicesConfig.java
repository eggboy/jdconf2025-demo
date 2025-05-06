package io.jaylee.aisearch_demo.config;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AzureAIServicesConfig {

    @Autowired
    OpenAIClientBuilder openAIClientBuilder;

    @Bean
    public OpenAIClient openAIClient() {
        // Create and configure the OpenAI client
        return openAIClientBuilder
                .buildClient();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
