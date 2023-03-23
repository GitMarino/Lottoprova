import { Component, OnInit } from '@angular/core';
import { Button } from 'src/app/model/objects/button';
import { KeyValue } from 'src/app/model/objects/key-value';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-area',
  templateUrl: './add-area.component.html',
  styleUrls: ['./add-area.component.css']
})
export class AddAreaComponent implements OnInit {
  
  iconName: string = "plus-square"
  buttons: Button[] = [];

  name: string = "";
  selectedPerson?: number;
  people: KeyValue[] = [];
  selectedTopic?: number;
  topics: KeyValue[] = [];

  error: boolean = false;
  success: boolean = false;

  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addArea
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
        }
      })
  }

  addArea = () =>
  { this.httpCalls.createArea(this.name, this.selectedPerson!, this.selectedTopic!)
      .subscribe({
        next: (response: void) => {
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