package com.bot.faq.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("faq")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faq {

    @Id
    private Long id;

    @Column("question")
    private String question;

    @Column("answer")
    private String answer;
}
