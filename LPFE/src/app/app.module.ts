import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {NoopAnimationsModule} from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { PersonComponent } from './person/person.component';
import { InfoPersonSkillsComponent } from './person/info-person-skills/info-person-skills.component';
import { TopicComponent } from './topic/topic.component';
import { AreaComponent } from './area/area.component';
import { AddPersonComponent } from './add/add-person/add-person.component';
import { AddTopicComponent } from './add/add-topic/add-topic.component';
import { AddAreaComponent } from './add/add-area/add-area.component';
import { SkillComponent } from './skill/skill.component';
import { AddSkillComponent } from './add/add-skill/add-skill.component';
import { AddPersonAreaConnectionComponent } from './connections/add-person-area-connection/add-person-area-connection.component';
import { AddPersonTopicConnectionComponent } from './connections/add-person-topic-connection/add-person-topic-connection.component';
import { AddPersonSkillConnectionComponent } from './connections/add-person-skill-connection/add-person-skill-connection.component';
import { AddAreaTopicConnectionComponent } from './connections/add-area-topic-connection/add-area-topic-connection.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { FeatherModule } from 'angular-feather';
import { PageComponent } from './page/page.component';
import { IconsModule } from './icons/icons.module';

const routes: Routes = [
  { path: '', component: AppComponent},
  { path: 'area', component: AreaComponent},
  { path: 'addArea', component: AddAreaComponent},
  { path: 'person', component: PersonComponent},
  { path: 'infoPerson/:id', component: InfoPersonSkillsComponent},
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

    AreaComponent, 
    PersonComponent,
    InfoPersonSkillsComponent, 
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
  ],
  imports: 
  [ BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    NoopAnimationsModule,
    NgbModule,

    IconsModule
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }