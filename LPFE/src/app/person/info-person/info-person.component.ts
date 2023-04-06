import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Person } from 'src/app/model/objects/person';
import { HttpCallsService } from '../../model/service/http-calls.service';
import { Observable, OperatorFunction, debounceTime, distinctUntilChanged, forkJoin, map } from 'rxjs';
import { KeyValue } from 'src/app/model/objects/key-value';
import { Location } from '@angular/common';
import { Button } from 'src/app/model/objects/button';
import { TopicSkills } from 'src/app/model/objects/topic-skills';

@Component({
  selector: 'app-info-person',
  templateUrl: './info-person.component.html',
  styleUrls: ['./info-person.component.scss']
})
export class InfoPersonComponent implements OnInit {

  personId?: number;
  iconName: string = 'file-text';
  person?: Person;
  areas?: KeyValue[];

  topicsSkills?: TopicSkills[];
  notCollapsed: boolean[] = [];
  
  public topicSearched?: any;
  inputFormatter = (selected: TopicSkills) => selected.topicName;
  resultsFormatter = (result: TopicSkills) => result.topicName.toUpperCase();
  search: OperatorFunction<string, readonly TopicSkills[]> = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map((topicWritten) =>
				topicWritten.length < 2 ? [] : this.topicsSkills!.filter((t) => t.topicName.toLowerCase().indexOf(topicWritten.toLowerCase()) > -1).slice(0, 5),
			),
		);
  notCollapsedSelected: boolean = true;

  buttons: Button[] = [];

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
        responseAreas: this.httpCalls.getAreasByPerson(this.personId!),
        responseTopicsSkills: this.httpCalls.getTopicsSkillsByPerson(this.personId!),
      })
      .subscribe(({ responsePerson, responseAreas, responseTopicsSkills}) => {
        this.person = responsePerson as Person;
        this.areas = responseAreas as KeyValue[];
        this.topicsSkills = responseTopicsSkills as TopicSkills[];
        
        for(let i of this.topicsSkills!)
        { this.notCollapsed.push(true);
        }
      })
    }
  }

  pageBack = () => {
    this.location.back();
  }

  reset()
  { this.topicSearched = undefined;
  }

}