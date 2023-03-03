import { Component } from '@angular/core';
import { Topic } from '../model/objects/Topic';
import { HttpCallsService } from '../model/service/http-calls.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent 
{
  topics: Topic[] = [];
  error: boolean = false;
  

  constructor(private httpCalls: HttpCallsService) {}

  ngOnInit(): void
  { this.httpCalls.getAllTopics()
      .subscribe({
        next: (response: Topic[]) => {this.topics = response},
        error : error => {
          this.error = true;
          this.topics = [];
        }
      });
  }

}