import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Button } from 'src/app/shared/objects/button';
import { KeyValue } from 'src/app/shared/objects/key-value';
import { HttpCallsService } from '../../shared/service/http-calls.service';
import { PopupComponent } from 'src/app/shared/popup/popup.component';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/shared/constants/constants';
import { BehaviorSubject, Observable, OperatorFunction, debounceTime, distinctUntilChanged, map } from 'rxjs';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-add-person-topic-connection',
  templateUrl: './add-person-topic-connection.component.html',
  styleUrls: ['./add-person-topic-connection.component.css']
})
export class AddPersonTopicConnectionComponent implements OnInit, AfterViewInit {

  iconName: string = 'git-commit';
  buttons: Button[] = [];

  inputFormatter = (selected: KeyValue) => selected.value;
  resultsFormatter = (result: KeyValue) => result.value;

  people: KeyValue[] = [];
  public selectedPerson?: any;
  searchPerson: OperatorFunction<string, readonly KeyValue[]> = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map((personWritten) =>
				personWritten.length < 3 ? []
        : this.people!.filter((p) => p.value.toLowerCase().indexOf(personWritten.toLowerCase()) > -1).slice(0, 5),
			),
		);
  
  topics: KeyValue[] = [];
  public selectedTopic?: any;
  searchTopic: OperatorFunction<string, readonly KeyValue[]> = (text$: Observable<string>) =>
		text$.pipe(
			debounceTime(200),
			distinctUntilChanged(),
			map((topicWritten) =>
				topicWritten.length < 3 ? []
        : this.topics!.filter((t) => t.value.toLowerCase().indexOf(topicWritten.toLowerCase()) > -1).slice(0, 5),
			),
		);
  
  @ViewChild('form') form!: NgForm;

  @ViewChild('popup') myPopup!: PopupComponent;

  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addPersonTopicConnection,
        disabled: new BehaviorSubject<boolean>(true),
      }
    ];
  }

  ngAfterViewInit(): void {
    this.form.statusChanges?.subscribe({
      next: status => {
        (this.buttons[0].disabled as BehaviorSubject<boolean>).next(status!=='VALID')
      }
    })
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
  { this.httpCalls.createPersonTopicConnection(this.selectedPerson!.id, this.selectedTopic!.id)
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