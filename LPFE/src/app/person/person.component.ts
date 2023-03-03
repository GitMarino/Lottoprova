import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Person } from '../model/objects/Person';
import { HttpCallsService } from '../model/service/http-calls.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit
{ 
  allPeople: Person[] = [];
  searchAllError: boolean = false;

  areaId = new FormControl();
  skillId = new FormControl();
  topicId = new FormControl();
  peopleSearched: Person[] = [];
  searchSuccess: boolean = false;
  searchError: boolean = false;

  constructor(private httpCalls: HttpCallsService) {}

  ngOnInit(): void
  { this.httpCalls.getAllPeople()
      .subscribe({
        next: (response: Person[]) => {
          this.allPeople = response as Person[]
        },
        error : error => {
          this.searchAllError = true;
          this.allPeople = [];
        }
      });
  }

  searchPeople()
  { this.httpCalls.searchPeopleByBeans(this.areaId.value, this.skillId.value , this.topicId.value)
      .subscribe({
        next: (response: Person[]) => {
          this.peopleSearched = response as Person[];
          this.searchSuccess = true;
        },
        error : error => {
          this.searchError = true;
          this.searchSuccess = false;
          this.peopleSearched = [];
        }
      })

  }
}