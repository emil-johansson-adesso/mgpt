package com.adesso.mgpt.service.client.ollama.dto;

public class GenerateRequestBody {
    public String model;
    public String prompt;
    public Boolean stream;

    public GenerateRequestBody(String model, String prompt) {
        this.model = model;
        this.prompt = prompt;
        this.stream = false;
    }
}
