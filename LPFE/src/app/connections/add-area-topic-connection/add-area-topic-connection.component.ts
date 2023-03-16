import { Component, OnInit } from '@angular/core';
import { Button } from 'src/app/model/objects/button';
import { KeyValue } from 'src/app/model/objects/key-value';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-area-topic-connection',
  templateUrl: './add-area-topic-connection.component.html',
  styleUrls: ['./add-area-topic-connection.component.css']
})
export class AddAreaTopicConnectionComponent implements OnInit {

  iconName: string = "git-commit";
  buttons: Button[] = [];

  selectedTopic?: number;
  selectedArea?: number;

  topics: KeyValue[] = [];
  areas: KeyValue[] = [];

  error: boolean = false;
  success: boolean = false;

  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addAreaTopicConnection
      }
    ];
  }

  ngOnInit(): void
  { this.httpCalls.getAllTopics()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.topics = response as KeyValue[];
        }
      });
    
    this.httpCalls.getAllAreas()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.areas = response as KeyValue[];
        },
      });
  }

  addAreaTopicConnection = () =>
  { this.httpCalls.createAreaTopicConnection(this.selectedArea!, this.selectedTopic!)
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