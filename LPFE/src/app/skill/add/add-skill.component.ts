import { Component, OnInit, ViewChild } from '@angular/core';
import { Button } from 'src/app/shared/objects/button';
import { KeyValue } from 'src/app/shared/objects/key-value';
import { HttpCallsService } from '../../shared/service/http-calls.service';
import { PopupComponent } from 'src/app/shared/popup/popup.component';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/shared/constants/constants';

@Component({
  selector: 'app-add-skill',
  templateUrl: './add-skill.component.html',
  styleUrls: ['./add-skill.component.css']
})
export class AddSkillComponent implements OnInit {

  iconName: string = "plus-square"
  buttons: Button[] = [];

  name?: string;
  description?: string;
  selectedTopic?: number;
  topics: KeyValue[] = [];

  @ViewChild('popup') myPopup!: PopupComponent;

  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addSkill
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
  }

  addSkill = () =>
  { this.httpCalls.createSkill(this.name!, this.description!, this.selectedTopic!)
      .subscribe({
        next: (response: void) => {
          this.myPopup.show(SUCCESS_TITLE, SUCCESS_BODY);

          this.name = undefined;
          this.description = undefined;
          this.selectedTopic = undefined;       
        },
        error: error => {
          this.myPopup.show(ERROR_TITLE, ERROR_BODY);
        }
      })
  }

}