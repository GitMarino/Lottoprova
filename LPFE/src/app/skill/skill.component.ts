import { Component } from '@angular/core';
import { Skill } from '../model/objects/Skill';
import { HttpCallsService } from '../model/service/http-calls.service';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css']
})
export class SkillComponent {

  skills: Skill[] = [];
  error: boolean = false;

  constructor(private httpCalls: HttpCallsService) {}

  ngOnInit(): void
  { this.httpCalls.getAllSkills()
      .subscribe({
        next: (response: Skill[]) => {
          this.skills = response as Skill[]
        },
        error : error => {
          this.error = true;
          this.skills = [];
        }
      });
  }
  
}