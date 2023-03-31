import { Component, OnInit, ViewChild } from '@angular/core';
import { Button } from 'src/app/model/objects/button';
import { KeyValue } from 'src/app/model/objects/key-value';
import { HttpCallsService } from '../../model/service/http-calls.service';
import { PopupComponent } from 'src/app/model/popup/popup.component';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/model/constants/constants';

@Component({
  selector: 'app-add-person-skill-connection',
  templateUrl: './add-person-skill-connection.component.html',
  styleUrls: ['./add-person-skill-connection.component.css']
})
export class AddPersonSkillConnectionComponent implements OnInit {

  iconName: string = "git-commit";
  buttons: Button[] = [];

  selectedPerson?: number;
  people: KeyValue[] = [];
  selectedSkill?: number;
  skills: KeyValue[] = [];
  mark?: number;

  @ViewChild('popup') myPopup!: PopupComponent;

  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addPersonSkillConnection
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
    
    this.httpCalls.getAllSkills()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.skills = response as KeyValue[];
        },
      });
  }

  addPersonSkillConnection = () =>
  { this.httpCalls.createPersonSkillConnection(this.selectedPerson!, this.selectedSkill!, this.mark!)
      .subscribe({
        next: (response: void) => {
          this.myPopup.show(SUCCESS_TITLE, SUCCESS_BODY);

          this.selectedPerson = undefined;
          this.selectedSkill = undefined;
          this.mark = undefined;
        },
        error: error => {
          this.myPopup.show(ERROR_TITLE, ERROR_BODY);
        }
      })
  }

}