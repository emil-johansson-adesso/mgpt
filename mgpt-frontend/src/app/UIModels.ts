
export interface LlmModelFamily {
  name: string,
  id: string,
  models: LlmModel[]
}

export interface LlmModel {
  name: string,
  id: string,
  selected: boolean
}

export interface Conversation {
  familyId: string,
  modelName: string,
  modelId: string,
  promptsAndResponses: PromptResponse[]
}

export interface PromptResponse {
  prompt: string,
  response: string
}


