import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Skill } from '../../model/objects/Skill';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-skill',
  templateUrl: './add-skill.component.html',
  styleUrls: ['./add-skill.component.css']
})
export class AddSkillComponent {

  constructor(private httpCalls: HttpCallsService) {}

  skill: Skill = new Skill();
  name = new FormControl();
  description = new FormControl();
  topicId = new FormControl();

  error: boolean = false;
  success: boolean = false;

  addSkill()
  { this.httpCalls.createSkill(this.name.value, this.description.value, this.topicId.value)
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