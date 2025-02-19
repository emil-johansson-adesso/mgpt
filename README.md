# Overview

Goal of this project is to create a web application for having parallel conversation with different
AI/LLM models at same time. Usage is comparison of quality in responses from different models.

It was made a side project during my first weeks of employment at adesso when I had some time to 
spare. Motivation for doing this project, instead  of something else, is to improve my Angular
knowledge and do something fun with AI. It can also be used as a reference project to show
how easy it is implement programmatic access to LLMs.

# Status

Project is now on standby due to other assignments. Code is left as is it looked when it was 
functional for the first time, in other words code is a mess. Here is a list of other known 
limitations: 
<ul>
    <li>There is no packaging or deployment of artifacts.</li>
    <li>Applications does not hold state of conversation so every question will be answered 
        without considering previous question(s). In other words there is no conversation, 
        only prompts.
    <li>There is no streaming of responses. Application will wait for complete answer and then 
        display the entire thing at once.
    <li>UI is extremely simple. For example a dummy answer ("...") is the only indication that 
        we are waiting for an answer.</li>
    <li>And many more...</li>
</ul>

Here is a screenshot of application in action:

![img.png](img.png)

# Architecture

Architecture consists of three layers:
<ul>
    <li>Frontend web application developed in Angualar. Code is in mgpt-frontend folder in this repo.</li>
    <li>Backed with simple REST API implemented as Springboot app. Code is in root folder of this project.</li>
    <li>LLM "providers". They are not part of git repo so providers can be seen as an external dependency.
        Backend supports two types of providers: Ollama and OpenAI.</li>
</ul>

No packaging or deployment of frontend, backend or LLM:s exist. Everything (except OpenAI models) is run 
on localhost.

# Setup

Here are the steps required to run the application on your dev computer:
<ul>
    <li>Install and run Ollama. Also make sure you have a number of models downloaded.</li>
    <li>Install Node, NPM and Angular CLI.</li>
    <li>Create an OpenAI account with access to at least GPT-4o mini (hard-coded as only OpenAI model). 
        Insert your API key directly in OpenAIClient.java.</li>
    <li>Make sure you have maven installed. Easiest way is to use IntelliJ since it has maven bundled.</li>
    <li>Start backend: run java class MgptApplication. Again, the easiest way is to use IntelliJ.</li>
    <li>Start frontend: in any shell, go to mgpt-frontend folder and run "npm install" + "ng serve".</li>
    <li>Start browser and go to http://localhost:4200. If everything works you should see something 
        similar to screenshot above.</li>
</ul>
