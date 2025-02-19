package com.adesso.mgpt.service.client.openai.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class ChatCompletionsResponseBody {
    public List<Choice> choices;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    public static class Choice {
        public Message message;

        public String toString() {
            return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
        }
    }

    public static class Message {
        public String role;
        public String content;

        public String toString() {
            return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
        }
    }
}
