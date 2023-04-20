import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { ERROR_BODY, ERROR_TITLE, SUCCESS_BODY, SUCCESS_TITLE } from 'src/app/shared/constants/constants';
import { Button } from 'src/app/shared/objects/button';
import { PopupComponent } from 'src/app/shared/popup/popup.component';
import { Topic } from '../../shared/objects/topic';
import { HttpCallsService } from '../../shared/service/http-calls.service';
import { NgForm } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-add-topic',
  templateUrl: './add-topic.component.html',
  styleUrls: ['./add-topic.component.css']
})
export class AddTopicComponent implements AfterViewInit {

  iconName: string = "plus-square"
  buttons: Button[] = [];

  topic: Topic = new Topic();

  @ViewChild('form') form!: NgForm;

  @ViewChild('popup') myPopup!: PopupComponent;

  constructor(private httpCalls: HttpCallsService) {
    this.buttons = [
      {
        name: 'salva',
        action: this.addTopic,
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

  addTopic = () => {
    this.httpCalls.createTopic(this.topic)
    .subscribe({
      next: (response: Topic) => {
        this.myPopup.show(SUCCESS_TITLE, SUCCESS_BODY);

        this.topic = new Topic();
      },
      error: error => {
        this.myPopup.show(ERROR_TITLE, ERROR_BODY);
      }
    });
  }

}