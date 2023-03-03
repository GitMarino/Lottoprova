import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-person-area-connection',
  templateUrl: './add-person-area-connection.component.html',
  styleUrls: ['./add-person-area-connection.component.css']
})
export class AddPersonAreaConnectionComponent {

  constructor(private httpCalls: HttpCallsService) {}

  personId = new FormControl();
  areaId = new FormControl();

  error: boolean = false;
  success: boolean = false;

  addPersonAreaConnection()
  { this.httpCalls.createPersonAreaConnection(this.personId.value, this.areaId.value)
      .subscribe({
        next: (response: void) => {
          this.success = true;
          this.error = false;
        },
        error : error => {
          console.error(error);
          this.error = true;
          this.success = false;
        }
      })
  }

}