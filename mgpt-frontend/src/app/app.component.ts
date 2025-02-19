import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from "@angular/common/http";
import { NgFor } from '@angular/common';
import { Conversation, LlmModelFamily, LlmModel } from './UIModels';
import { GenerateResponseBody, GetModelsResponseBody } from './ApiModels'; // API calls should be moved to separate file and imports removed fron this file
import { MarkdownService } from 'ngx-markdown';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [FormsModule, NgFor],
  styleUrl: './app.component.css'
})
export class AppComponent {
  conversations: Conversation[] = []
  availableModels: LlmModelFamily[] = []
  availableModelsMap = new Map<string, LlmModel[]>()
  nextPrompt: string =''

  ngOnInit() {
    this.http.get<GetModelsResponseBody>('http://localhost:8080/llm/models').subscribe(responseBody => {
      debugger
      responseBody.modelFamilies.forEach(modelFamily => {
        const llmModels: LlmModel[] = modelFamily.models.map(model => {
          return {
            id: model.modelId,
            name: model.modelName,
            selected: false
          }
        })
        this.availableModels.push({id: modelFamily.familyId, name: modelFamily.familyName, models: llmModels})
        this.availableModelsMap.set(modelFamily.familyId, llmModels)
      })
    })
  }

  constructor(private http: HttpClient, private markdownService: MarkdownService) {
  }

  getAvailableModels(modelFamilyId: string): LlmModel[] {
    debugger
    if (this.availableModelsMap.has(modelFamilyId))
      return this.availableModelsMap.get(modelFamilyId) as LlmModel[]
    else
      return []
  }

  // modelSelectionChanged() {
  addRemoveModel(familyId: string, model: LlmModel){
    if (model.selected)
      this.addModel(familyId, model)
    else
      this.removeModel(model)
  }

  addModel(familyId: string, model: LlmModel) {
    this.conversations.push({
      familyId: familyId,
      modelName: model.name,
      modelId: model.id,
      promptsAndResponses: []
    })
  }

  removeModel(modelToRemove: LlmModel) {
    debugger
    const modelIds: string[] = this.conversations.map(a => a.modelId)
    const index: number = modelIds.indexOf(modelToRemove.id)
    if (index >= 0)
      this.conversations.splice(index, 1)
  }


  getConversationColumnWidth() {
    return ((100 / this.conversations.length)-(1 / this.conversations.length)).toString() + '%'
  }

  postNewPrompt() {

    // Add new prompt with response ... for each model
    this.conversations.forEach((conversation) => {
      conversation.promptsAndResponses.unshift(
        {
          prompt: this.nextPrompt,
          response: '...'
        }
      )
    })

    // Then also post the question/prompt to each model and replace ... when done
    this.conversations.forEach((conversation) => {
      this.http.post<GenerateResponseBody>('http://localhost:8080/llm/generate', {familyId: conversation.familyId, modelId: conversation.modelId, prompt: this.nextPrompt})
        .subscribe(responseBody => {
          // Responses from Ollama are formatted with markdown. Convert to HTML with ngx-markdown to be able to display in browser
          let promiseResponse = this.markdownService.parse(responseBody.response.toString()) as string
          conversation.promptsAndResponses[0].response = promiseResponse
        })
    })
  }
}
