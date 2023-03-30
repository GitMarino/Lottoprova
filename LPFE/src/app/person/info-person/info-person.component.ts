import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Person } from 'src/app/model/objects/person';
import { SkillMark } from 'src/app/model/objects/skill-marks';
import { HttpCallsService } from '../../model/service/http-calls.service';
import { forkJoin } from 'rxjs';
import { KeyValue } from 'src/app/model/objects/key-value';
import { Location } from '@angular/common';
import { Button } from 'src/app/model/objects/button';

@Component({
  selector: 'app-info-person',
  templateUrl: './info-person.component.html',
  styleUrls: ['./info-person.component.scss']
})
export class InfoPersonComponent implements OnInit {

  personId?: number;

  iconName: string = "file-text";
  buttons: Button[] = [];

  person?: Person;
  topics?: KeyValue[];
  areas?: KeyValue[];
  skillMarks?: SkillMark[];

  constructor(private activatedRoute: ActivatedRoute, private httpCalls: HttpCallsService, private location: Location) {
    this.buttons = [
      {
        name: 'torna a gestione',
        action: this.pageBack
      }
    ];
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.personId = params['id'];
    });
    if (this.personId != undefined) 
    { 
      forkJoin({
        responsePerson: this.httpCalls.getPerson(this.personId!),
        responseTopics: this.httpCalls.getTopicsByPerson(this.personId!),
        responseAreas: this.httpCalls.getAreasByPerson(this.personId!)
      })
      .subscribe(({ responsePerson, responseTopics, responseAreas }) => {
        this.person = responsePerson as Person;
        this.topics = responseTopics as KeyValue[];
        this.areas = responseAreas as KeyValue[];
      }
      )
    }
  }

  pageBack = () => {
    this.location.back();
  }

}