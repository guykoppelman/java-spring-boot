package com.example.messageworker.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Message {
    @Id
    private Integer id;
    private String body;

    protected Message() { }
    public Message(Integer id, String body) { this.id = id; this.body = body; }
    public void update(String body) { this.body = body; }
}