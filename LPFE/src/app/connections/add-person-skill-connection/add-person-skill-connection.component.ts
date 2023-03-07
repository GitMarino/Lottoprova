import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { KeyValue } from 'src/app/model/objects/KeyValue';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-person-skill-connection',
  templateUrl: './add-person-skill-connection.component.html',
  styleUrls: ['./add-person-skill-connection.component.css']
})
export class AddPersonSkillConnectionComponent {

  constructor(private httpCalls: HttpCallsService) {}

  selectedPerson: KeyValue = new KeyValue();
  selctedSkill: KeyValue = new KeyValue();
  mark: string = "";

  people: KeyValue[] = [];
  skills: KeyValue[] = [];

  error: boolean = false;
  success: boolean = false;

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

  addPersonSkillConnection()
  { this.httpCalls.createPersonSkillConnection(this.selectedPerson.value, this.selctedSkill.value, this.mark)
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