import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {NoopAnimationsModule} from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { PersonComponent } from './person/manage/person.component';
import { InfoPersonComponent } from './person/info/info-person.component';
import { TopicComponent } from './topic/manage/topic.component';
import { AreaComponent } from './area/manage/area.component';
import { AddPersonComponent } from './person/add/add-person.component';
import { AddTopicComponent } from './topic/add/add-topic.component';
import { AddAreaComponent } from './area/add/add-area.component';
import { SkillComponent } from './skill/manage/skill.component';
import { AddSkillComponent } from './skill/add/add-skill.component';
import { AddPersonAreaConnectionComponent } from './connections/add-person-area-connection/add-person-area-connection.component';
import { AddPersonTopicConnectionComponent } from './connections/add-person-topic-connection/add-person-topic-connection.component';
import { AddPersonSkillConnectionComponent } from './connections/add-person-skill-connection/add-person-skill-connection.component';
import { AddAreaTopicConnectionComponent } from './connections/add-area-topic-connection/add-area-topic-connection.component';
import { NgbAccordionModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FeatherModule } from 'angular-feather';
import { PageComponent } from './shared/page/page.component';
import { IconsModule } from './icons/icons.module';
import { PopupComponent } from './shared/popup/popup.component';
import { CommonModule } from '@angular/common';
import { MiniCardComponent } from './shared/mini-card/mini-card.component';

const routes: Routes = [
  { path: '', component: AppComponent},

  { path: 'area', component: AreaComponent},
  { path: 'addArea', component: AddAreaComponent},

  { path: 'person', component: PersonComponent},
  { path: 'person/:id', component: InfoPersonComponent},
  { path: 'addPerson', component:AddPersonComponent},

  { path: 'skill', component: SkillComponent},
  { path: 'addSkill', component: AddSkillComponent},

  { path: 'topic', component: TopicComponent},
  { path: 'addTopic', component: AddTopicComponent},

  { path: 'personArea', component: AddPersonAreaConnectionComponent},
  { path: 'personTopic', component: AddPersonTopicConnectionComponent},
  { path: 'personSkill', component: AddPersonSkillConnectionComponent},
  { path: 'areaTopic', component: AddAreaTopicConnectionComponent},
];


@NgModule({
  declarations: 
  [ PageComponent,
    AppComponent,
    PopupComponent,

    AreaComponent, 
    PersonComponent,
    InfoPersonComponent, 
    SkillComponent,
    TopicComponent,
    
    AddAreaComponent,
    AddPersonComponent,
    AddSkillComponent,
    AddTopicComponent,

    AddPersonAreaConnectionComponent,
    AddPersonTopicConnectionComponent,
    AddPersonSkillConnectionComponent,
    AddAreaTopicConnectionComponent,
    MiniCardComponent,
  ],
  imports: 
  [ BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    NoopAnimationsModule,
    NgbModule,

    IconsModule,
    NgbAccordionModule,
    CommonModule,
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }