import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Topic } from '../objects/Topic';
import { AREA, BACKEND_ENDPOINT, PERSON, SEARCH, SKILL, STANDARD_JSON_HEADERS, TOPIC } from '../constants/constants';
import { Area } from '../objects/Area';
import { Person } from '../objects/Person';
import { Skill } from '../objects/Skill';

@Injectable({providedIn: 'root'})
export class HttpCallsService
{ 
  constructor(private http: HttpClient)
  {}

  //AREA

  public getAllAreas(): Observable<Area[]>
  { return this.http.get<Area[]>(BACKEND_ENDPOINT+AREA);
  }

  public createArea(area: Area): Observable<Area>
  { return this.http.post<Area>(BACKEND_ENDPOINT+AREA, area, {'headers': STANDARD_JSON_HEADERS});
  }

  //PERSON

  public getAllPeople(): Observable<Person[]>
  { return this.http.get<Person[]>(BACKEND_ENDPOINT+PERSON);
  }

  public createPerson(person: Person): Observable<Person>
  { return this.http.post<Person>(BACKEND_ENDPOINT+PERSON, person, {'headers': STANDARD_JSON_HEADERS});
  }

  public searchPeopleByBeans(areaId: number, skillId: number, topicId: number): Observable<Person[]>
  { 
    let params = new HttpParams();
    if( areaId )
      params = params.set('areaId', areaId);
    if( skillId )
      params = params.set('skillId', skillId);
    if( topicId)
      params = params.set('topicId', topicId);
    
    return this.http.get<Person[]>(BACKEND_ENDPOINT+PERSON+SEARCH, { params });
  }

  //SKILL

  public getAllSkills(): Observable<Skill[]>
  { return this.http.get<Skill[]>(BACKEND_ENDPOINT+SKILL);
  }

  public createSkill(name: string, description: string, skillTopicBeanId: number): Observable<void>
  { return this.http.post<void>(BACKEND_ENDPOINT+SKILL+"?name="+name+"&description="+description+"&skillTopicBeanId="+skillTopicBeanId,  {'headers': STANDARD_JSON_HEADERS})
  }
  
  //TOPIC

  public getAllTopics(): Observable<Topic[]>
  { return this.http.get<Topic[]>(BACKEND_ENDPOINT+TOPIC);
  }
  
  public createTopic(topic: Topic): Observable<Topic>
  { return this.http.post<Topic>(BACKEND_ENDPOINT+TOPIC, topic, {'headers': STANDARD_JSON_HEADERS});
  }

  //CONNECTIONS

  public createPersonAreaConnection(personId: number, areaId: number): Observable<void>
  { return this.http.put<void>(BACKEND_ENDPOINT+PERSON+"/"+personId+AREA+"?areaId="+areaId,  {'headers': STANDARD_JSON_HEADERS});
  }

  public createPersonSkillConnection(personId: number, skillId: number, mark: number): Observable<void>
  { return this.http.put<void>(BACKEND_ENDPOINT+PERSON+"/"+personId+SKILL+"?skillId="+skillId+"&mark="+mark,  {'headers': STANDARD_JSON_HEADERS});
  }

  public createPersonTopicConnection(personId: number, topicId: number): Observable<void>
  { return this.http.put<void>(BACKEND_ENDPOINT+PERSON+"/"+personId+TOPIC+"?topicId="+topicId,  {'headers': STANDARD_JSON_HEADERS});
  }

  public createAreaTopicConnection(areaId: number, topicId: number): Observable<void>
  { return this.http.put<void>(BACKEND_ENDPOINT+AREA+"/"+areaId+TOPIC+"?topicId="+topicId,  {'headers': STANDARD_JSON_HEADERS});
  }

}