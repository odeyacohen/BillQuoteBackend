package com.billquote.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "message")
@Getter @Setter @NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sms")
    private Long idSms;

    @Lob
    @Column(name = "content_sms", nullable = false)
    private String contentSms;

    @Column(name = "envoie_sms", nullable = false)
    private Instant envoieSms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conversation", nullable = false,
            foreignKey = @ForeignKey(name = "fk_message_conversation"))
    private Conversation conversation;
}
