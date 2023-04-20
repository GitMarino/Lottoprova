import { AfterViewChecked, AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Button } from 'src/app/shared/objects/button';
import { KeyValue } from 'src/app/shared/objects/key-value';
import { HttpCallsService } from '../../shared/service/http-calls.service';
import { PopupComponent } from 'src/app/shared/popup/popup.component';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/shared/constants/constants';
import { Topic } from 'src/app/shared/objects/topic';
import { BehaviorSubject, Observable, OperatorFunction, debounceTime, distinctUntilChanged, map } from 'rxjs';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-add-area-topic-connection',
  templateUrl: './add-area-topic-connection.component.html',
  styleUrls: ['./add-area-topic-connection.component.css']
})
export class AddAreaTopicConnectionComponent implements OnInit, AfterViewInit {

  iconName: string = 'git-commit';
  buttons: Button[] = [];

  areas: KeyValue[] = [];
  selectedAreaId?: number;

  topics: KeyValue[] = [];
  topicId?: number;

  @ViewChild('form') form!: NgForm;

  @ViewChild('popup') myPopup!: PopupComponent;

  constructor(private httpCalls: HttpCallsService) {
    this.buttons = [
      {
        name: 'salva',
        action: this.addAreaTopicConnection,
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
    this.httpCalls.getAllTopics()
    .subscribe({
      next: (response: KeyValue[]) => {
        this.topics = response as KeyValue[];
      }
    });

    this.httpCalls.getAllAreas()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.areas = response as KeyValue[];
        },
      });
  }

  addAreaTopicConnection = () => {
    this.httpCalls.createAreaTopicConnection(this.selectedAreaId!, this.topicId!)
    .subscribe({
      next: (response: void) => {
        this.myPopup.show(SUCCESS_TITLE, SUCCESS_BODY);

        this.topicId = undefined;
        this.selectedAreaId = undefined;
      },
      error: error => {
        this.myPopup.show(ERROR_TITLE, ERROR_BODY);
      }
    })
  }

}