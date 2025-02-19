package com.adesso.mgpt.service.client.ollama;

import com.adesso.mgpt.service.client.ollama.dto.GenerateRequestBody;
import com.adesso.mgpt.service.client.ollama.dto.GenerateResponseBody;
import com.adesso.mgpt.service.client.ollama.dto.TagsResponseBody;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
public class OllamaClient {

    private static final String BASE_URL = "http://localhost:11434";

    private HttpClient httpClient = HttpClient.newBuilder().build();

    private ObjectMapper jsonDeserializer = new ObjectMapper().configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private ObjectMapper jsonSerializer = new ObjectMapper();

    public List<String> getAvailableModels() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/tags"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("api/tags - status code: " + response.statusCode());
        if (response.statusCode() == 200) {
            System.out.println("api/tags - response body: " + response.body());
            TagsResponseBody responseBody = jsonDeserializer.readValue(response.body(), TagsResponseBody.class);
            return getModelsFromTagsResponseBody(responseBody);
        } else {
            System.out.println("Failed to retrieve data. Status code: " + response.statusCode());
            throw new RuntimeException();
        }
    }

    public String generate(String model, String prompt) throws Exception {
        GenerateRequestBody requestBody = new GenerateRequestBody(model, prompt);
        String requestBodyString = jsonSerializer.writeValueAsString(requestBody);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyString))
                .uri(URI.create(BASE_URL + "/api/generate"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("api/generate - status code: " + response.statusCode());
        if (response.statusCode() == 200) {
            System.out.println("/api/generate - response body: " + response.body());
            GenerateResponseBody responseBody = jsonDeserializer.readValue(response.body(), GenerateResponseBody.class);
            return responseBody.response;
        } else {
            System.out.println("Failed to retrieve data. Status code: " + response.statusCode());
            throw new RuntimeException();
        }
    }

    private List<String> getModelsFromTagsResponseBody(TagsResponseBody responseBody) {
        return responseBody.models.stream().map(TagsResponseBody.Model::getModel).toList();
    }
}
