import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-person-skill-connection',
  templateUrl: './add-person-skill-connection.component.html',
  styleUrls: ['./add-person-skill-connection.component.css']
})
export class AddPersonSkillConnectionComponent {

  constructor(private httpCalls: HttpCallsService) {}

  personId = new FormControl();
  skillId = new FormControl();
  mark = new FormControl();

  error: boolean = false;
  success: boolean = false;

  addPersonSkillConnection()
  { this.httpCalls.createPersonSkillConnection(this.personId.value, this.skillId.value, this.mark.value)
      .subscribe({
        next: (response: void) => {
          this.success = true;
          this.error = false;
        },
        error: error => {
          console.error(error);
          this.error = true;
          this.success = false;
        }
      })
  }

}