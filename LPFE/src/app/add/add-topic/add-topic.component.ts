import { Component } from '@angular/core';
import { Button } from 'src/app/model/objects/button';
import { Topic } from '../../model/objects/topic';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-topic',
  templateUrl: './add-topic.component.html',
  styleUrls: ['./add-topic.component.css']
})
export class AddTopicComponent {

  iconName: string = "plus-square"
  buttons: Button[] = [];

  topic: Topic = new Topic();

  error: boolean = false;
  success: boolean = false;

  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addTopic
      }
    ];
  }

  addTopic = () =>
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