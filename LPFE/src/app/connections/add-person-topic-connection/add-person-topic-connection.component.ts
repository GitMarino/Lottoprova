import { Component, OnInit } from '@angular/core';
import { Button } from 'src/app/model/objects/button';
import { KeyValue } from 'src/app/model/objects/key-value';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-person-topic-connection',
  templateUrl: './add-person-topic-connection.component.html',
  styleUrls: ['./add-person-topic-connection.component.css']
})
export class AddPersonTopicConnectionComponent implements OnInit {

  iconName: string = "git-commit";
  buttons: Button[] = [];

  selectedPerson?: number;
  selectedTopic?: number;

  people: KeyValue[] = [];
  topics: KeyValue[] = [];

  error: boolean = false;
  success: boolean = false;

  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addPersonTopicConnection
      }
    ];
  }

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

  addPersonTopicConnection = () =>
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