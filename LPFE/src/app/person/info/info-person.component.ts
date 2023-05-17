import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Person } from 'src/app/shared/objects/person';
import { HttpCallsService } from '../../shared/service/http-calls.service';
import { Observable, OperatorFunction, debounceTime, distinctUntilChanged, forkJoin, map } from 'rxjs';
import { KeyValue } from 'src/app/shared/objects/key-value';
import { Location } from '@angular/common';
import { Button } from 'src/app/shared/objects/button';
import { TopicSkills } from 'src/app/shared/objects/topic-skills';
import { FileContent } from 'src/app/shared/objects/file-content';

@Component({
  selector: 'app-info-person',
  templateUrl: './info-person.component.html',
  styleUrls: ['./info-person.component.scss'],
})
export class InfoPersonComponent implements OnInit {


  personId?: number;
  iconName: string = 'file-text';
  person?: Person;
  areas?: KeyValue[];

  topicsSkillsBE?: TopicSkills[];
  topicSkillsList?: TopicSkills[];

  textWritten?: string;
  public topicSearched?: any;
  inputFormatter = (selected: TopicSkills) => selected.topicName;
  resultsFormatter = (result: TopicSkills) => result.topicName.toUpperCase();
  search: OperatorFunction<string, readonly TopicSkills[]> = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map((topicWritten) =>
        topicWritten.length < 2 ? [] : this.topicSkillsList!.filter((t) => t.topicName.toLowerCase().indexOf(topicWritten.toLowerCase()) > -1).slice(0, 5),
      ),
    );

  currentPage: number = 1;
  pageSize: number = 5;
  collectionSize?: number;

  buttons: Button[] = [];

  constructor(private activatedRoute: ActivatedRoute, private httpCalls: HttpCallsService, private location: Location) {
    this.buttons = [
      {
        name: 'torna a gestione',
        action: this.pageBack
      },
      {
        name: 'scarica cv',
        action: this.download
      }
    ];
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.personId = params['id'];
    });
    if (this.personId != undefined) {
      forkJoin({
        responsePerson: this.httpCalls.getPerson(this.personId!),
        responseAreas: this.httpCalls.getAreasByPerson(this.personId!),
        responseTopicsSkills: this.httpCalls.getTopicsSkillsByPerson(this.personId!),
      })
        .subscribe(({ responsePerson, responseAreas, responseTopicsSkills }) => {
          this.person = responsePerson as Person;
          this.areas = responseAreas as KeyValue[];
          this.topicsSkillsBE = responseTopicsSkills as TopicSkills[];

          this.topicsSkillsBE.sort((n1, n2) => {
            if (n1.topicName > n2.topicName) {
              return 1;
            }
            if (n1.topicName < n2.topicName) {
              return -1;
            }
            return 0;
          });

          this.topicSkillsList = this.topicsSkillsBE;
          this.collectionSize = this.topicSkillsList!.length;
        })
    }
  }

  reset() {
    this.topicSkillsList = this.topicsSkillsBE;
    this.collectionSize = this.topicSkillsList!.length;
  }

  onFilter(event: any) {
    this.textWritten = event.target.value.trim();
    if (this.textWritten) {
      this.topicSkillsList = this.topicsSkillsBE!.filter(topicSkills => topicSkills.topicName.toLowerCase().indexOf(this.textWritten!.toLowerCase()) > -1);
    }
    else {
      this.topicSkillsList = this.topicsSkillsBE;
    }
    this.collectionSize = this.topicSkillsList!.length;
  }

  pageBack = () => {
    this.location.back();
  }

  download = () => {
    this.httpCalls.getCV(this.personId!)
      .subscribe({
        next: (response: FileContent) => {
          //const byteArray = Buffer.from(response.content, 'base64');
          console.log(response.metatype)
          const byteArray = new Uint8Array(atob(response.content).split('').map(char => char.charCodeAt(0)));
          const blob = new Blob([byteArray], {type: response.metatype});
          const fileURL = URL.createObjectURL(blob);

          var linkToFile = document.createElement('a');
          linkToFile.download = response.name;
          linkToFile.href = fileURL;
          linkToFile.click();

          //window.open(fileURL);
        }
      })
  }

}