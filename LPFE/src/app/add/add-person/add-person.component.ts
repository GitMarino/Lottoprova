import { Component } from '@angular/core';
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

  error: boolean = false;
  success: boolean = false;

  addPerson()
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