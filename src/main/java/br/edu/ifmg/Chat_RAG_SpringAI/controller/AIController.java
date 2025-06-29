package br.edu.ifmg.Chat_RAG_SpringAI.controller;

import br.edu.ifmg.Chat_RAG_SpringAI.service.AIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    AIService aiService;

    @Autowired
    ChatClient chatClient;

    @PostMapping("/response")
    public Map<String, String> getResponse(@RequestParam String message) {

        VectorStore vectorStore = aiService.loadDataInVectorStore();

        return Map.of("Response", chatClient.prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults))
                .user(message)
                .call()
                .content());
    }

}
