package com.bot;

import com.bot.faq.entity.Faq;
import com.bot.faq.repository.FaqRepository;
import com.bot.faq.service.FaqService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class BotApplicationTests {
    @Autowired
    private FaqRepository faqRepository;
    @Autowired
    private FaqService faqService;

    @Test
    @DisplayName("FAQ 게시글 초기화 테스트")
    void t1(){
        // given
        String keyword = "배송";

        // when
        List<Faq> results = faqService.searchFaq(keyword);

        // then
        assertFalse(results.isEmpty());
        assertTrue(results.stream().anyMatch(f ->
                f.getQuestion().contains("배송") || f.getAnswer().contains("배송")));
        System.out.println("FTS 검색 결과:");
        results.forEach(System.out::println);
    }

    @Test
    @DisplayName("PGroonga FTS 검색 테스트")
    void t2() {
        List<Faq> results = faqService.searchFaq("배송");
        assertFalse(results.isEmpty());
        assertTrue(results.stream()
                .anyMatch(faq -> faq.getQuestion().contains("배송") || faq.getAnswer().contains("배송")));
        System.out.println("FTS 검색 결과:");
        results.forEach(System.out::println);
    }

}
