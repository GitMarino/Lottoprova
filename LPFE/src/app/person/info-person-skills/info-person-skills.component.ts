import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Person } from 'src/app/model/objects/person';
import { SkillMark } from 'src/app/model/objects/skill-marks';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-info-person-skills',
  templateUrl: './info-person-skills.component.html',
  styleUrls: ['./info-person-skills.component.scss']
})
export class InfoPersonSkillsComponent implements OnInit {
  
  personId?: number;

  iconName: string = "file-text";
  person?: Person;
  skillMarks?: SkillMark[];

  constructor(private activatedRoute: ActivatedRoute, private httpCalls: HttpCallsService)
  {}

  ngOnInit(): void
  { this.activatedRoute.params.subscribe(params => {
      this.personId = params['id'];
      if(this.personId != undefined)
      { this.httpCalls.getPerson(this.personId!)
          .subscribe({
            next: (response: Person) => {
              this.person = response as Person;
            }
          });
  
        this.httpCalls.getSkillMarks(this.personId!)
          .subscribe({
            next: (response: SkillMark[]) => {
              this.skillMarks = response as SkillMark[];
            }
          });
      }
    });   
  }
}