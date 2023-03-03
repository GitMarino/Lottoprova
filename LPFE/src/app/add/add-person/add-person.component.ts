import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Person } from '../../model/objects/Person';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent {

  constructor(private httpCalls: HttpCallsService) {}

  person : Person = new Person();
  username = new FormControl();
  name = new FormControl();
  surname = new FormControl();

  error: boolean = false;
  success: boolean = false;

  addPerson()
  { this.person.username = this.username.value;
    this.person.name = this.name.value;
    this.person.surname = this.surname.value;
    this.httpCalls.createPerson(this.person)
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