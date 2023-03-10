import { Component } from '@angular/core';
import { Button } from 'src/app/model/objects/button';
import { KeyValue } from 'src/app/model/objects/key-value';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-person-skill-connection',
  templateUrl: './add-person-skill-connection.component.html',
  styleUrls: ['./add-person-skill-connection.component.css']
})
export class AddPersonSkillConnectionComponent {

  iconName: string = "git-commit";
  buttons: Button[] = [];

  selectedPerson?: number;
  selectedSkill?: number;
  mark?: number;

  people: KeyValue[] = [];
  skills: KeyValue[] = [];

  error: boolean = false;
  success: boolean = false;

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
          this.success = true;
          this.error = false;
        },
        error: error => {
          console.error(error);
          this.error = true;
          this.success = false;
        }
      })
  }

}