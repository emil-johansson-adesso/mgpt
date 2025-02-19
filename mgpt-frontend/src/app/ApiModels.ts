export interface GenerateResponseBody {
  response: string
}

export interface GetModelsResponseBody {
   modelFamilies: ModelFamily[]
}

export interface ModelFamily {
   familyId: string,
   familyName: string,
   models: Model[]
}

export interface Model {
  modelId: string,
  modelName: string
}
