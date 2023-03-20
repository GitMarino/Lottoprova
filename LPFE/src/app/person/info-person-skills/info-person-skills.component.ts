import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Person } from 'src/app/model/objects/person';
import { SkillMark } from 'src/app/model/objects/skill-marks';
import { HttpCallsService } from '../../model/service/http-calls.service';
import { forkJoin } from 'rxjs';
import { KeyValue } from 'src/app/model/objects/key-value';

@Component({
  selector: 'app-info-person-skills',
  templateUrl: './info-person-skills.component.html',
  styleUrls: ['./info-person-skills.component.scss']
})
export class InfoPersonSkillsComponent implements OnInit {
  
  personId?: number;

  iconName: string = "file-text";
  person?: Person;
  topics?:  KeyValue[];
  areas?:  KeyValue[];
  skillMarks?: SkillMark[];

  constructor(private activatedRoute: ActivatedRoute, private httpCalls: HttpCallsService)
  {}

  ngOnInit(): void
  { this.activatedRoute.params.subscribe(params => {
      this.personId = params['id'];
    });
    if(this.personId != undefined)
    { forkJoin({
        responsePerson: this.httpCalls.getPerson(this.personId!),
        responseTopics: this.httpCalls.getTopicsByPerson(this.personId!),
        responseAreas: this.httpCalls.getAreasByPerson(this.personId!)
      })
      .subscribe(({responsePerson, responseTopics, responseAreas}) => 
        { this.person = responsePerson as Person;
          this.topics = responseTopics as KeyValue[];
          this.areas = responseAreas as KeyValue[];
        }
      )
    }
  }
}