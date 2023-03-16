import { Component, OnInit } from '@angular/core';
import { Button } from 'src/app/model/objects/button';
import { KeyValue } from 'src/app/model/objects/key-value';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-skill',
  templateUrl: './add-skill.component.html',
  styleUrls: ['./add-skill.component.css']
})
export class AddSkillComponent implements OnInit {

  iconName: string = "plus-square"
  buttons: Button[] = [];

  name: string = "";
  description: string = "";
  selectedTopic?: number;
  topics: KeyValue[] = [];

  error: boolean = false;
  success: boolean = false;

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
  { this.httpCalls.createSkill(this.name, this.description, this.selectedTopic!)
      .subscribe({
        next: (response: void) => {
          this.success = true;
          this.error = false;
        },
        error: error => {
          this.error = true;
          this.success = false;
        }
      })
  }

}