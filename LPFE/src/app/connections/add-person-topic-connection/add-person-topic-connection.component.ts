import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-person-topic-connection',
  templateUrl: './add-person-topic-connection.component.html',
  styleUrls: ['./add-person-topic-connection.component.css']
})
export class AddPersonTopicConnectionComponent {

  constructor(private httpCalls: HttpCallsService) {}

  personId = new FormControl();
  topicId = new FormControl();

  error: boolean = false;
  success: boolean = false;

  addPersonTopicConnection()
  { this.httpCalls.createPersonTopicConnection(this.personId.value, this.topicId.value)
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