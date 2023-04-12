import { Component, OnInit, ViewChild } from '@angular/core';
import { Button } from 'src/app/shared/objects/button';
import { KeyValue } from 'src/app/shared/objects/key-value';
import { HttpCallsService } from '../../shared/service/http-calls.service';
import { PopupComponent } from 'src/app/shared/popup/popup.component';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/shared/constants/constants';

@Component({
  selector: 'app-add-person-area-connection',
  templateUrl: './add-person-area-connection.component.html',
  styleUrls: ['./add-person-area-connection.component.css']
})
export class AddPersonAreaConnectionComponent implements OnInit {

  iconName: string = 'git-commit';
  buttons: Button[] = [];

  selectedPerson?: number;
  people: KeyValue[] = [];
  selectedArea?: number;
  areas: KeyValue[] = [];

  @ViewChild('popup') myPopup!: PopupComponent;

  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addPersonAreaConnection
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
    
    this.httpCalls.getAllAreas()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.areas = response as KeyValue[];
        },
      });
  }

  addPersonAreaConnection = () =>
  { this.httpCalls.createPersonAreaConnection(this.selectedPerson!, this.selectedArea!)
      .subscribe({
        next: (response: void) => {
          this.myPopup.show(SUCCESS_TITLE, SUCCESS_BODY);

          this.selectedPerson = undefined;
          this.selectedArea = undefined;
        },
        error : error => {
          this.myPopup.show(ERROR_TITLE, ERROR_BODY);
        }
      })
  }

}