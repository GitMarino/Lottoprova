import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Area } from '../../model/objects/Area';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-area',
  templateUrl: './add-area.component.html',
  styleUrls: ['./add-area.component.css']
})
export class AddAreaComponent {

  constructor(private httpCalls: HttpCallsService) {}
  
  area: Area = new Area();
  name = new FormControl();
  areaManager = new FormControl();

  error: boolean = false;
  success: boolean = false;

  addArea()
  { this.area.name = this.name.value;
    this.area.areaManager = this.areaManager.value;
    this.httpCalls.createArea(this.area)
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