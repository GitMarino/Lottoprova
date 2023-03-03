import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-area-topic-connection',
  templateUrl: './add-area-topic-connection.component.html',
  styleUrls: ['./add-area-topic-connection.component.css']
})
export class AddAreaTopicConnectionComponent {

  constructor(private httpCalls: HttpCallsService) {}

  topicId = new FormControl();
  areaId = new FormControl();

  error: boolean = false;
  success: boolean = false;

  addAreaTopicConnection()
  { this.httpCalls.createAreaTopicConnection(this.areaId.value, this.topicId.value)
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