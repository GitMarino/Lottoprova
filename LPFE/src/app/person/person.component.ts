import { Component } from '@angular/core';
import { Person } from '../model/objects/Person';
import { HttpCallsService } from '../model/service/http-calls.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent
{ 
  areaId: number = 0;
  skillId: number = 0;
  topicId: number = 0;

  people: Person[] = [];
  success: boolean = false;
  error: boolean = false;

  constructor(private httpCalls: HttpCallsService) {}

  searchPeople()
  { this.httpCalls.searchPeopleByBeans(this.areaId, this.skillId , this.topicId)
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
}