import { Component } from '@angular/core';
import { Area } from '../model/objects/Area';
import { HttpCallsService } from '../model/service/http-calls.service';

@Component({
  selector: 'app-area',
  templateUrl: './area.component.html',
  styleUrls: ['./area.component.css']
})
export class AreaComponent 
{ 
  areas: Area[] = [];
  error: boolean = false;

  constructor(private httpCalls: HttpCallsService) {}

  ngOnInit(): void
  { this.httpCalls.getAllAreas()
      .subscribe({
        next: (response: Area[]) => {this.areas = response},
        error : error => {
          this.error = true;
          this.areas = [];
        }
      });
  }

}