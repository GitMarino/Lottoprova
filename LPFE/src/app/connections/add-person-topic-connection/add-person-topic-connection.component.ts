import { Component, OnInit, ViewChild } from '@angular/core';
import { Button } from 'src/app/shared/objects/button';
import { KeyValue } from 'src/app/shared/objects/key-value';
import { HttpCallsService } from '../../shared/service/http-calls.service';
import { PopupComponent } from 'src/app/shared/popup/popup.component';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/shared/constants/constants';

@Component({
  selector: 'app-add-person-topic-connection',
  templateUrl: './add-person-topic-connection.component.html',
  styleUrls: ['./add-person-topic-connection.component.css']
})
export class AddPersonTopicConnectionComponent implements OnInit {

  iconName: string = 'git-commit';
  buttons: Button[] = [];

  selectedPerson?: number;
  people: KeyValue[] = [];
  selectedTopic?: number;
  topics: KeyValue[] = [];
  
  @ViewChild('popup') myPopup!: PopupComponent;

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
          this.myPopup.show(SUCCESS_TITLE, SUCCESS_BODY);

          this.selectedPerson = undefined;
          this.selectedTopic = undefined;
        },
        error: error => {
          this.myPopup.show(ERROR_TITLE, ERROR_BODY);
        }
      })
  }

}