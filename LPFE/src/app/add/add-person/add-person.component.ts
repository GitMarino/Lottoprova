import { Component, ViewChild } from '@angular/core';
import { Button } from 'src/app/model/objects/button';
import { Person } from '../../model/objects/person';
import { HttpCallsService } from '../../model/service/http-calls.service';
import { PopupComponent } from 'src/app/model/popup/popup.component';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/model/constants/constants';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent {

  iconName: string = "plus-square"
  buttons: Button[] = [];

  person : Person = new Person();

  @ViewChild('popup') myPopup!: PopupComponent;
  
  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addPerson
      }
    ];
  }

  addPerson = () =>
  { this.httpCalls.createPerson(this.person)
      .subscribe({
        next: (response: Person) => {
          this.myPopup.show(SUCCESS_TITLE, SUCCESS_BODY);

          this.person = new Person();
        },
        error : error => {
          this.myPopup.show(ERROR_TITLE, ERROR_BODY);
        }
      });
  }

}