package io.jaylee.aisearch_demo.service;

import com.azure.ai.openai.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
public class AISearchChatExtension {

    private final String azureSearchEndpoint;
    private final String azureSearchKey;
    private final String azureSearchIndexName;

    public AzureSearchChatExtensionConfiguration getAzureSearchChatExtensionConfiguration() {
        AzureSearchChatExtensionConfiguration searchConfiguration =
                new AzureSearchChatExtensionConfiguration(
                        new AzureSearchChatExtensionParameters(azureSearchEndpoint, azureSearchIndexName)
                                .setAuthentication(new OnYourDataApiKeyAuthenticationOptions(azureSearchKey))
                                .setFieldsMapping(
                                        new AzureSearchIndexFieldMappingOptions()
                                                .setContentFields(Arrays.asList("content"))
                                                .setVectorFields(Arrays.asList("content_vector"))
                                                .setTitleField("title")
                                                .setUrlField("metadata")
                                                .setFilepathField("filepath")
                                )
                                .setInScope(true)
                                .setTopNDocuments(5)
                                .setEmbeddingDependency(new OnYourDataDeploymentNameVectorizationSource("text-embedding-ada-002"))
                                .setQueryType(AzureSearchQueryType.VECTOR_SEMANTIC_HYBRID)
                                .setSemanticConfiguration("default")
                                .setInScope(true)

                );
        //AZURE_OPENAI_SYSTEM_MESSAGE=You are an AI assistant that helps people find information.
        return searchConfiguration;
    }
}
