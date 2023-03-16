import { Component, OnInit } from '@angular/core';
import { Button } from '../model/objects/button';
import { KeyValue } from '../model/objects/key-value';
import { Person } from '../model/objects/person';
import { HttpCallsService } from '../model/service/http-calls.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit
{ 
  selectedArea?: number;
  selectedSkill?: number;
  selectedTopic?: number;

  areas: KeyValue[] = [];
  skills: KeyValue[] = [];
  topics: KeyValue[] = [];

  iconName: string = "users";
  buttons: Button[] = [];

  people?: Person[];
  currentPage: number = 1;
  pageSize: number = 5;
  collectionSize?: number;
  success: boolean = false;
  error: boolean = false;

  constructor(private httpCalls: HttpCallsService) 
  { 
    this.buttons = [
      {
        name: 'cerca',
        identifier: 'search'
      },
      {
        name: 'reset',
        identifier: 'reset'
      }
    ];
  }

  ngOnInit(): void
  { this.httpCalls.getAllAreas()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.areas = response as KeyValue[];
        }
      });
    
    this.httpCalls.getAllSkills()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.skills = response as KeyValue[];
        },
      });

    this.httpCalls.getAllTopics()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.topics = response as KeyValue[];
        },
      });
  }

  onClick(indentifier: string) {
    switch (indentifier) {
      case 'search': 
        this.searchPeople();
        break;
      case 'reset':
        this.reset();
        break;
    }
  }

  searchPeople()
  {
    this.httpCalls.searchPeopleByBeans(this.selectedArea!, this.selectedSkill!, this.selectedTopic!)
      .subscribe({
        next: (response: Person[]) => {
          this.people = response as Person[];
          this.collectionSize = this.people.length;
          console.log(this.collectionSize);
          this.success = true;
        },
        error : error => {
          this.error = true;
          this.success = false;
          this.people = [];
        }
      })
  }

  reset()
  { this.people = undefined;
    this.selectedArea = undefined;
    this.selectedSkill = undefined;
    this.selectedTopic = undefined;
  }
}