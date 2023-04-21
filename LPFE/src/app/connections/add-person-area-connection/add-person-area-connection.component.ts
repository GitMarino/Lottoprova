import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Button } from 'src/app/shared/objects/button';
import { KeyValue } from 'src/app/shared/objects/key-value';
import { HttpCallsService } from '../../shared/service/http-calls.service';
import { PopupComponent } from 'src/app/shared/popup/popup.component';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/shared/constants/constants';
import { BehaviorSubject } from 'rxjs';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-add-person-area-connection',
  templateUrl: './add-person-area-connection.component.html',
  styleUrls: ['./add-person-area-connection.component.css']
})
export class AddPersonAreaConnectionComponent implements OnInit, AfterViewInit {

  iconName: string = 'git-commit';
  buttons: Button[] = [];

  selectedAreaId?: number;
  areas: KeyValue[] = [];

  people: KeyValue[] = [];
  personId?: number;

  @ViewChild('form') form!: NgForm;

  @ViewChild('popup') myPopup!: PopupComponent;

  constructor(private httpCalls: HttpCallsService) {
    this.buttons = [
      {
        name: 'salva',
        action: this.addPersonAreaConnection,
        disabled: new BehaviorSubject<boolean>(true),
      }
    ];
  }

  ngAfterViewInit(): void {
    this.form.statusChanges?.subscribe({
      next: status => {
        (this.buttons[0].disabled as BehaviorSubject<boolean>).next(status !== 'VALID')
      }
    })
  }

  ngOnInit(): void {
    this.httpCalls.getAllPeople()
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

  addPersonAreaConnection = () => {
    this.httpCalls.createPersonAreaConnection(this.personId!, this.selectedAreaId!)
    .subscribe({
      next: (response: void) => {
        this.myPopup.show(SUCCESS_TITLE, SUCCESS_BODY);
      },
      error: error => {
        this.myPopup.show(ERROR_TITLE, ERROR_BODY);
      }
    })
  }

}