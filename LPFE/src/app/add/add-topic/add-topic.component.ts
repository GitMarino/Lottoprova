import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Topic } from '../../model/objects/Topic';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-topic',
  templateUrl: './add-topic.component.html',
  styleUrls: ['./add-topic.component.css']
})
export class AddTopicComponent {

  constructor(private httpCalls: HttpCallsService) {}

  topic: Topic = new Topic();

  error: boolean = false;
  success: boolean = false;

  addTopic()
  { this.httpCalls.createTopic(this.topic)
      .subscribe({
        next: (response: Topic) => {
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