package br.edu.ifmg.Chat_RAG_SpringAI.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;

@Configuration
public class AIConfig {

    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel){
        return new SimpleVectorStore(embeddingModel);
    }

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }

}
