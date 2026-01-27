package com.bot.ai.controller;

import com.bot.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ai")
public class AiController {
    private final ChatService chatService;

    @GetMapping("/chat")
    public Flux<String> chat(
            @RequestParam("message") String message
    ){
        return chatService.streamChatResponse(message);
    }
}
