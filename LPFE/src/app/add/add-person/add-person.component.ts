import { Component } from '@angular/core';
import { Button } from 'src/app/model/objects/button';
import { Person } from '../../model/objects/person';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent {

  iconName: string = "plus-square"
  buttons: Button[] = [];

  person : Person = new Person();

  error: boolean = false;
  success: boolean = false;

  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addPerson
      }
    ];
  }

  addPerson = () =>
  { this.httpCalls.createPerson(this.person)
      .subscribe({
        next: (response: Person) => {
          this.success = true;
          this.error = false;
        },
        error : error => {
          this.error = true;
          this.success = false;
        }
      });
  }

}