import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Button } from 'src/app/shared/objects/button';
import { KeyValue } from 'src/app/shared/objects/key-value';
import { HttpCallsService } from '../../shared/service/http-calls.service';
import { PopupComponent } from 'src/app/shared/popup/popup.component';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/shared/constants/constants';
import { BehaviorSubject, Observable, OperatorFunction, debounceTime, distinctUntilChanged, map } from 'rxjs';
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
  public selectedPerson?: any;
  inputFormatter = (selected: KeyValue) => selected.value;
  resultsFormatter = (result: KeyValue) => result.value;
  searchPerson: OperatorFunction<string, readonly KeyValue[]> = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map((personWritten) =>
        personWritten.length < 3 ? []
          : this.people!.filter((p) => p.value.toLowerCase().indexOf(personWritten.toLowerCase()) > -1).slice(0, 5),
      ),
    );

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
    this.httpCalls.createPersonAreaConnection(this.selectedPerson!.id, this.selectedAreaId!)
    .subscribe({
      next: (response: void) => {
        this.myPopup.show(SUCCESS_TITLE, SUCCESS_BODY);

        this.selectedPerson = undefined;
        this.selectedAreaId = undefined;
      },
      error: error => {
        this.myPopup.show(ERROR_TITLE, ERROR_BODY);
      }
    })
  }

}