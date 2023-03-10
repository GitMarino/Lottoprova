import { Component } from '@angular/core';
import { Button } from 'src/app/model/objects/button';
import { Area } from '../../model/objects/area';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-area',
  templateUrl: './add-area.component.html',
  styleUrls: ['./add-area.component.css']
})
export class AddAreaComponent {
  
  iconName: string = "plus-square"
  buttons: Button[] = [];

  area: Area = new Area();

  error: boolean = false;
  success: boolean = false;

  constructor(private httpCalls: HttpCallsService) 
  { this.buttons = [
      {
        name: 'salva',
        action: this.addArea
      }
    ];
  }

  addArea = () =>
  { this.httpCalls.createArea(this.area)
      .subscribe({
        next: (response: Area) => {
          this.success = true;
          this.error = false;
        },
        error : error => {
          this.error = true;
          this.success = false;
        }
      });
  }

}