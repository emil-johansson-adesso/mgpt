package com.adesso.mgpt.service.client.openai;

import com.adesso.mgpt.service.client.ollama.dto.GenerateResponseBody;
import com.adesso.mgpt.service.client.openai.dto.ChatCompletionsRequestBody;
import com.adesso.mgpt.service.client.openai.dto.ChatCompletionsResponseBody;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class OpenAIClient {

    private static final String BASE_URL = "https://api.openai.com/v1";

    private static final String API_KEY = null; // Replace with your API key

    private HttpClient httpClient = HttpClient.newBuilder().build();

    private ObjectMapper jsonDeserializer = new ObjectMapper().configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private ObjectMapper jsonSerializer = new ObjectMapper();

    // Implementation is very stupid. It returns first choice without checking if there is 0 more than 1
    public String getCompletion(String model, String prompt) throws Exception {
        List<ChatCompletionsRequestBody.Message> messages = Arrays.asList(new ChatCompletionsRequestBody.Message("user", prompt));
        ChatCompletionsRequestBody requestBody = new ChatCompletionsRequestBody(model, true, messages);
        String requestBodyString = jsonSerializer.writeValueAsString(requestBody);
        System.out.println("requestBodyString: "+requestBodyString);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyString))
                .uri(URI.create(BASE_URL + "/chat/completions"))
                .header("Authorization", "Bearer "+API_KEY)
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("chat/completions - Status code: " + response.statusCode() + ". Response body: "+response.body());
        if (response.statusCode() == 200) {
            ChatCompletionsResponseBody responseBody = jsonDeserializer.readValue(response.body(), ChatCompletionsResponseBody.class);
            System.out.println("chat/completions - Parsed response body: " + responseBody);
            return responseBody.choices.get(0).message.content;
        } else {
            System.out.println("Failed to retrieve data. Status code: " + response.statusCode());
            throw new RuntimeException();
        }
    }

}
