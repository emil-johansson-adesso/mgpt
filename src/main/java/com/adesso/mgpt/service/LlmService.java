package com.adesso.mgpt.service;

import com.adesso.mgpt.controller.dto.GenerateResponseBody;
import com.adesso.mgpt.controller.dto.GetModelsResponseBody;
import com.adesso.mgpt.service.client.ollama.OllamaClient;
import com.adesso.mgpt.service.client.openai.OpenAIClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LlmService {

    private OllamaClient ollamaClient = new OllamaClient();
    private OpenAIClient openaiClient = new OpenAIClient();

    public GetModelsResponseBody getAvailableModels() {
        try {
            GetModelsResponseBody.ModelFamily ollamaFamiles = getOllamaModels();
            GetModelsResponseBody.ModelFamily openAIFamiles = getOpenAIModels();
            return new GetModelsResponseBody(Arrays.asList(ollamaFamiles, openAIFamiles));
        } catch(Exception e) {
            System.out.println("Exception when getting available models: "+e+". Will return empty list");
            return new GetModelsResponseBody(Collections.EMPTY_LIST);
        }
    }

    public String generate(String familyId, String modelId, String prompt) {
        try {
            if (familyId.equals("ollama"))
                return ollamaClient.generate(modelId, prompt);
            if (familyId.equals("openai"))
                return openaiClient.getCompletion(modelId, prompt);
            System.out.println("Unknown model family: "+familyId+". Will return dummy answer");
            return "!!! FAILURE !!!";

        } catch(Exception e) {
            System.out.println("Exception when getting available models: "+e+". Will return dummy answer");
            return "!!! FAILURE !!!";
        }
    }

    private GetModelsResponseBody.ModelFamily getOpenAIModels() throws Exception {
        // Only GTP-4O Mini is available in free-tier
        List<GetModelsResponseBody.Model> models = new ArrayList<>();
        models.add(new GetModelsResponseBody.Model("gpt-4o-mini", "GPT-4o Mini"));
        return new GetModelsResponseBody.ModelFamily("openai", "OpenAI", models);
    }

    private GetModelsResponseBody.ModelFamily getOllamaModels() throws Exception {
        List<String> modelIds = ollamaClient.getAvailableModels();
        List<String> modelIdsSorted = new ArrayList<>(modelIds);
        Collections.sort(modelIdsSorted);
        List<GetModelsResponseBody.Model> models = new ArrayList<>();
        modelIdsSorted.stream().forEach(modelId -> models.add(new GetModelsResponseBody.Model(modelId, getOllamaModelName(modelId))) );
        return new GetModelsResponseBody.ModelFamily("ollama", "Ollama - Localhost", models);
    }

    private String getOllamaModelName(String modelId) {
        if (modelId.equals("phi4:14b"))
            return "Microsoft Phi-4 (14B)";
        if (modelId.equals("deepseek-r1:7b"))
            return "DeepSeek-R1 (7B)";
        if (modelId.equals("deepseek-r1:1.5b"))
            return "DeepSeek-R1 (1.5B)";
        if (modelId.equals("llama3.2:1b"))
            return "Meta Llama 3.2 (1B)";
        if (modelId.equals("llama3.2:3b"))
            return "Meta Llama 3.2 (3B)";
        if (modelId.equals("gemma2:27b"))
            return "Google Gemma 2 (27B)";
        if (modelId.equals("gemma2:9b"))
            return "Google Gemma 2 (9B)";
        if (modelId.equals("gemma2:2b"))
            return "Google Gemma 2 (2B)";
        if (modelId.equals("qwen:0.5b"))
            return "Alibaba Qwen 2 (0.5B)";
        return modelId;

    }
}
