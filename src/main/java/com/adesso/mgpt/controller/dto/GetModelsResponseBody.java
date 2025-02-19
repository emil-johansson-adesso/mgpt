package com.adesso.mgpt.controller.dto;

import java.util.List;

public class GetModelsResponseBody {
    public List<ModelFamily> modelFamilies;

    public GetModelsResponseBody(List<ModelFamily> modelFamilies) {
        this.modelFamilies = modelFamilies;
    }

    public static class ModelFamily {
        public String familyId;
        public String familyName;
        public List<Model> models;

        public ModelFamily(String familyId, String familyName, List<Model> models) {
            this.familyId = familyId;
            this.familyName = familyName;
            this.models = models;
        }
    }

    public static class Model {
        public String modelId;
        public String modelName;

        public Model(String modelId, String modelName) {
            this.modelId = modelId;
            this.modelName = modelName;
        }
    }
}
