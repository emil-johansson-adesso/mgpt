package com.adesso.mgpt.service.client.openai.dto;

import java.util.List;

public class ChatCompletionsRequestBody {
    public String model;
    public Boolean store;
    public List<Message> messages;

    public ChatCompletionsRequestBody(String model, Boolean store, List<Message> messages) {
        this.model = model;
        this.store = store;
        this.messages = messages;
    }

    public static class Message {
        public String role;
        public String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
