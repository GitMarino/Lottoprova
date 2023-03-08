import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { KeyValue } from 'src/app/model/objects/KeyValue';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-person-topic-connection',
  templateUrl: './add-person-topic-connection.component.html',
  styleUrls: ['./add-person-topic-connection.component.css']
})
export class AddPersonTopicConnectionComponent {

  constructor(private httpCalls: HttpCallsService) {}

  selectedPerson?: number;
  selectedTopic?: number;

  people: KeyValue[] = [];
  topics: KeyValue[] = [];

  error: boolean = false;
  success: boolean = false;

  ngOnInit(): void
  { this.httpCalls.getAllPeople()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.people = response as KeyValue[];
        }
      });
    
    this.httpCalls.getAllTopics()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.topics = response as KeyValue[];
        },
      });
  }

  addPersonTopicConnection()
  { this.httpCalls.createPersonTopicConnection(this.selectedPerson!, this.selectedTopic!)
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