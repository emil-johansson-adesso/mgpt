import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

import { HttpClientModule } from '@angular/common/http';
import { MarkdownModule } from 'ngx-markdown';


@NgModule({
    imports:  [
        BrowserModule,
        AppComponent,
        HttpClientModule,
        MarkdownModule.forRoot()
    ],
    declarations: [
    ],
    bootstrap: [AppComponent],
    providers: [
      provideAnimationsAsync()
    ]
})
export class AppModule { };
