# Overview

Goal of this project is to create a web application for having parallel conversation with different 
AI/LLM models at same time. Usage is comparison of quality in responses from different models.

LLM model deployment will most likely be outside this git repository, see technical description below.

## Goal

End goal is to have web application, backend and LLM models deployed in Microsoft Azure. Open source
LLM:s will be deployed through Ollama and virtual machines. Goal is to also include Chat GPT models, 
but since they are not available deployable units the Azure OpenAI services. 

It remains to be seen how far the project gets. It will be done incrementally, see sprint plan below.
There is also a concern about cost, both for virtual machines and Azure OpenAI prompts.

## Background

There are a couple of reason for starting this project, all revolving around personal development:
<ul>
    <li>Improve my Angular knowledge by starting a project from scratch.</li>
    <li>Get some simple hands-on experience from setting up service in Microsoft Azure.</li>
    <li>Do something fun/cool with AI that can potentially be used as reference project.</li>
    <li>Show colleagues how open-source LLM models and Chat GPT can be used programmatically.</li>
</ul>

## Status

To be written...

# Architecture

To be written...

# Sprint Plan

Overall plan is to do one sprint per week. This might fail since sales activities will have higher priority.

## Sprint 1

Goals:
<ul>
    <li>Setup Ollama locally and do test prompts to see how/if they differ (proof of value).</li>
    <li>Check what other alternatives there is to do same thing (proof of value)</li>
    <li>Create backend with dummy endpoint that returns fixed response. To setup infrastructure.</li>
    <li>Create frontend with mocked data.</li>
    <li>Make draft architecture and sprint plan.</li>
</ul>

Lessons learned:
<ul>
    <li>Response differ widely between models, both with regard to correctness and verbosity.</li>
    <li>Since the responses differ that much it makes sense to be able to ask single questions to 
        multiple models. This could be good first step to work more iterative.</li>
    <li>It is totally possible to run at least llama-3.2, deepseek-r1 and qwen:0.5b locally at same 
        time time (memory/CPU wise). This is also a good match since they differ alot in behaviour.</li>
    <li>Stream mode would be nice since especially deepseek have long conversions with itself 
        and takes minutes to give complete answer.</li>
    <li>Angular is for single-page application. There is no support for opening a new tab and rendering
        different pages with different URLs.</li>
</ul>

Status: completed.

## Sprint 2

Goals:
<ul>
    <li>Make workflow work end-2-end with local LLMs.</li>
    <li>List of models shall be fetched from backend, which in turn shall fetch them from Ollama API.</li>
    <li>Questions/prompt shall be sent to all (active) LLMs </li>
    <li>Limitations:<ul>
        <li>No session and memory in backend. Follow-up question won't make any sense.</li>
        <li>No streaming. Deepseek will be very hard to use since it chats for minutes.</li>
        <li>Only local Ollama models supported.</li>
        <li>Frontend must be run through "ng serve". No packaging etc</li>
    </ul>
</ul>

Lessons learned:
<ul>
    <li>Response from Ollama is markdown formatted (with possible extensions, I have not found any 
        documentation). It cannot be directly displayed and browser. Workaround is to convert to
        HTML with third-party library and display with help of internalHTML.</li>
    <li>Response time from LLMs is a major obstacle when running everything locally. Especially
        for big models.</li>
    <li>To get a descent UI is also critical. For example, without proper formatting of responses 
        the functionality is bareley visible.</li>
    <li>Result shows that this is something that is worth more time!</li>
</ul>

Status: completed.

## Sprint 3

Goals:
<ul>
    <li>Basically wrap up and cut loose ends. Some hard decisions are needed to make the
        functionality clear, without "it is like this because I want to do that it future".</li>
    <li>Examples: add/remove models when checkboxes are selected, add a clear button and a
        submit button so that same question can be asked multiple times. And cleanup model
        names to separate models "family" from size.</li>
    <li>Discuss with students if it is something that they can use as inspiration.</li>
    <li>Stretch: create Open AI free tier account and add as separate "backend".</li>
</ul>

Lessons learned:
<ul>
    <li>Nested div tags is the way to go when designing a UI with space in correct places.</li>
    <li>It still feels like a lottery when trying to design buttons etc and catching events.
        Sometimes Angular specific names can be used, and sometimes names shall have 
        parenthesis (), sometimes both brackets and parenthesis [()]...</li>
</ul>

Status: ongoing.

