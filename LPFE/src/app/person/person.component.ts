import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
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
  iconName: string = 'users';
  buttons: Button[] = [];
  
  ATSmapBE?: KeyValue[][] = [];
  ATSsubMap?: KeyValue[][] = [];

  areas: KeyValue[] = [];
  selectedArea?: number;
  topics: KeyValue[] = [];
  selectedTopic?: number;
  skills: KeyValue[] = [];
  selectedSkill?: number;

  people?: Person[];
  arrow?: string;
  sortColumn: number = 3;
  ascending: number = 0;
  currentPage: number = 1;
  pageSize: number = 5;
  collectionSize?: number;

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

  onClick(identifier: string) {
    switch (identifier) {
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
        },
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

  onSortClick(column: number)
  { if(this.sortColumn !== column)
    { this.ascending = 0;
      this.sortColumn = column;
    }
    this.sortColumn = column;
    if(this.ascending===0)
    { switch (column) {
        case 1:
          this.people!.sort( (n1,n2) => {
            if(n1.serial>n2.serial) {
              return 1;
            }
            if(n1.serial<n2.serial) {
              return -1;
            }
            return 0;
          });
          break;
        case 2:
          this.people!.sort( (n1,n2) => {
            if(n1.name>n2.name) {
              return 1;
            }
            if(n1.name<n2.name) {
              return -1;
            }
            return 0;
          });
          break;
        case 3:
          this.people!.sort( (n1,n2) => {
            if(n1.surname>n2.surname) {
              return 1;
            }
            if(n1.surname<n2.surname) {
              return -1;
            }
            return 0;
          });
          break;
      } 
      this.arrow = 'arrow-up';
      this.ascending = 1;
    }
    else if(this.ascending === 1)
    { this.arrow = 'arrow-down';
      switch (column)
      { case 1:
          this.people!.sort( (n1,n2) => {
            if(n1.serial<n2.serial) {
              return 1;
            }
            if(n1.serial>n2.serial) {
              return -1;
            }
            return 0;
          });
          break;
        case 2:
          this.people!.sort( (n1,n2) => {
            if(n1.name<n2.name) {
              return 1;
            }
            if(n1.name>n2.name) {
              return -1;
            }
            return 0;
          });
          break;
        case 3:
          this.people!.sort( (n1,n2) => {
            if(n1.surname<n2.surname) {
              return 1;
            }
            if(n1.surname>n2.surname) {
              return -1;
            }
            return 0;
          });
          break;
      }
      this.arrow = 'arrow-down';
      this.ascending = 2;
    }
    else
    { this.people!.sort( (n1,n2) => {
        if(n1.surname>n2.surname) {
          return 1;
        }
        if(n1.surname<n2.surname) {
          return -1;
        }
        return 0;
      });
      this.arrow = undefined;
      this.ascending = 0;
    }
  }
}