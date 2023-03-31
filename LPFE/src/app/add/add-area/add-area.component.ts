import { Component, OnInit, ViewChild } from '@angular/core';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/model/constants/constants';
import { Button } from 'src/app/model/objects/button';
import { KeyValue } from 'src/app/model/objects/key-value';
import { PopupComponent } from 'src/app/model/popup/popup.component';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-area',
  templateUrl: './add-area.component.html',
  styleUrls: ['./add-area.component.css']
})
export class AddAreaComponent implements OnInit {
  
  iconName: string = 'plus-square';
  buttons: Button[] = [];

  name?: string;
  selectedPerson?: number;
  people: KeyValue[] = [];
  selectedTopic?: number;
  topics: KeyValue[] = [];

  @ViewChild('popup') myPopup!: PopupComponent;
  
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
  { this.httpCalls.createArea(this.name!, this.selectedPerson!, this.selectedTopic!)
      .subscribe({
        next: (response: void) => {
          this.myPopup.show(SUCCESS_TITLE, SUCCESS_BODY);

          this.name = undefined;
          this.selectedPerson = undefined;
          this.selectedTopic = undefined;
        },
        error : error => {
          this.myPopup.show(ERROR_TITLE, ERROR_BODY);
        }
      });
  }
}