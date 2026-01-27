package com.bot.ai.service;

import com.bot.faq.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatClient chatClient;
    private final FaqService faqService;
    private static final String SYSTEM_PROMPT = """
            당신은 고객 서비스 FAQ 챗봇입니다.
            고객의 질문에 친절하고 정확하게 답변해주세요.
            
            질문에 답변하기 전에 반드시 searchFaq 도구를 사용하여 FAQ 데이터베이스를 검색하세요.
            검색 결과를 바탕으로 자연스럽게 답변을 작성해주세요.
            검색 결과가 없으면 "해당 내용에 대한 FAQ가 없습니다. 고객센터(1234-5678)로 문의해주세요."라고 안내하세요.
            """;

    public Flux<String> streamChatResponse(String userMessage){
        return chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .messages(UserMessage.builder().text(userMessage).build())
                .tools(faqService)
                .stream()
                .content();
    }

}

