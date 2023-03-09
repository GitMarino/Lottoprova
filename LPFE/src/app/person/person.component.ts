import { Component } from '@angular/core';
import { Button } from '../model/objects/button';
import { KeyValue } from '../model/objects/key-value';
import { Link } from '../model/objects/link';
import { Person } from '../model/objects/person';
import { HttpCallsService } from '../model/service/http-calls.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent
{ 
  selectedArea?: number;
  selectedSkill?: number;
  selectedTopic?: number;

  areas: KeyValue[] = [];
  skills: KeyValue[] = [];
  topics: KeyValue[] = [];


  link: Link = new Link();
  iconName: string = "search"
  buttons: Button[] = [];

  people: Person[] = [];
  success: boolean = false;
  error: boolean = false;

  constructor(private httpCalls: HttpCallsService) {
    this.link.name = "Aggiungi una persona";
    this.link.path =  "/person/add";
    
    this.buttons = [
      {
        name: 'cerca',
        action: this.searchPeople
      },
      {
        name: 'reset',
        action: this.reset
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

  searchPeople()
  { this.httpCalls.searchPeopleByBeans(this.selectedArea!, this.selectedSkill!, this.selectedTopic!)
      .subscribe({
        next: (response: Person[]) => {
          this.people = response as Person[];
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
  { this.selectedArea = undefined;
    this.selectedSkill = undefined;
    this.selectedTopic = undefined;
  }
}