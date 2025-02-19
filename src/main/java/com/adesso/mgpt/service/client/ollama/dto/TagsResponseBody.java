package com.adesso.mgpt.service.client.ollama.dto;

import java.util.List;

// For JSON deserialization
public class TagsResponseBody {
    public List<Model> models;

    public static class Model {
        public String model;

        public String getModel() {
            return model;
        }
    }
}

