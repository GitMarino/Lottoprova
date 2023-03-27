import { Component, OnInit } from '@angular/core';
import { Button } from '../model/objects/button';
import { KeyValue } from '../model/objects/key-value';
import { Person } from '../model/objects/person';
import { HttpCallsService } from '../model/service/http-calls.service';
import { FEutilityService } from '../model/service/fe-utility.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {
  ATSmapBE?: KeyValue[][] = [];
  ATSsubMap?: KeyValue[][] = [];

  selectedArea?: number;
  selectedTopic?: number;
  selectedSkill?: number;

  areas: KeyValue[] = [];
  topics: KeyValue[] = [];
  skills: KeyValue[] = [];

  iconName: string = "users";
  buttons: Button[] = [];

  people?: Person[];
  currentPage: number = 1;
  pageSize: number = 5;
  collectionSize?: number;
  success: boolean = false;
  error: boolean = false;

  constructor(private httpCalls: HttpCallsService, private feUtilityService: FEutilityService) {
    this.buttons = [
      {
        name: 'cerca',
        identifier: 'search'
      },
      {
        name: 'reset',
        identifier: 'reset'
      }
    ];
  }

  ngOnInit(): void {
    this.httpCalls.getAreaTopicSkill()
      .subscribe({
        next: (response: KeyValue[][]) => {
          this.ATSmapBE = response as KeyValue[][];
          this.ATSsubMap = this.ATSmapBE;
          this.populateSelects(this.ATSsubMap);
        }
      })
  }

  onClick(indentifier: string) {
    switch (indentifier) {
      case 'search':
        this.searchPeople();
        break;
      case 'reset':
        this.reset();
        break;
    }
  }

  setSelects(column: number) {
    var resultMap: KeyValue[][] = this.feUtilityService.getMapBySelected(this.ATSmapBE!, this.selectedArea, this.selectedTopic, this.selectedSkill);
    this.ATSsubMap = resultMap;
    this.populateSelects(this.ATSsubMap);
  }

  searchPeople() {
    this.httpCalls.searchPeopleByBeans(this.selectedArea!, this.selectedSkill!, this.selectedTopic!)
      .subscribe({
        next: (response: Person[]) => {
          this.people = response as Person[];
          this.collectionSize = this.people.length;
          console.log(this.collectionSize);
          this.success = true;
        },
        error: error => {
          this.error = true;
          this.success = false;
          this.people = [];
        }
      })
  }

  reset() {
    this.people = undefined;
    this.selectedArea = undefined;
    this.selectedSkill = undefined;
    this.selectedTopic = undefined;

    this.ATSsubMap = this.ATSmapBE;
    this.populateSelects(this.ATSsubMap!);
  }

  populateSelects(map: KeyValue[][]) {
    this.areas = [];
    this.topics = [];
    this.skills = [];

    var area: KeyValue;
    var topic: KeyValue;
    var skill: KeyValue;

    for (var row of map) {
      area = row[0];
      if (area.id != null && !this.areas.find(listArea => listArea.id === area.id)) {
        this.areas.push(area);
      }

      topic = row[1];
      if (topic.id != null && !this.topics.find(listTopic => listTopic.id === topic.id)) {
        this.topics.push(topic);
      }

      skill = row[2];
      if (skill.id != null && !this.skills.find(listSkill => listSkill.id === skill.id)) {
        this.skills.push(skill);
      }
    }

    this.areas.sort( (n1,n2) => {
      if(n1.value>n2.value) {
        return 1;
      }
      if(n1.value<n2.value) {
        return -1;
      }
      return 0;
    });

    this.topics.sort( (n1,n2) => {
      if(n1.value>n2.value) {
        return 1;
      }
      if(n1.value<n2.value) {
        return -1;
      }
      return 0;
    });

    this.skills.sort( (n1,n2) => {
      if(n1.value>n2.value) {
        return 1;
      }
      if(n1.value<n2.value) {
        return -1;
      }
      return 0;
    });
  }
}