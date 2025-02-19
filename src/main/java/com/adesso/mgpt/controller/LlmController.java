package com.adesso.mgpt.controller;

import com.adesso.mgpt.controller.dto.GenerateRequestBody;
import com.adesso.mgpt.controller.dto.GenerateResponseBody;
import com.adesso.mgpt.controller.dto.GetModelsResponseBody;
import com.adesso.mgpt.service.LlmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller()
@CrossOrigin(origins = "http://localhost:4200/")
public class LlmController {

    @Autowired
    private LlmService llmService;

    @GetMapping("/llm/models")
    public ResponseEntity<GetModelsResponseBody> getAvailableModels() {
        System.out.println("GET:/llm/models called");
        GetModelsResponseBody models = llmService.getAvailableModels();
        return ResponseEntity.ok(models);

    }

    @PostMapping("/llm/generate")
    public ResponseEntity<GenerateResponseBody> generate(@RequestBody GenerateRequestBody requestBody) {
        System.out.println("POST:/llm/models called with family "+requestBody.familyId+", model "+requestBody.modelId+" and prompt '"+requestBody.prompt + "'");
        String response = llmService.generate(requestBody.familyId, requestBody.modelId, requestBody.prompt);
        // response = response.replace("\n\n", "<br>"); // Some HTML formatting
        return ResponseEntity.ok(new GenerateResponseBody(response));
    }
}
