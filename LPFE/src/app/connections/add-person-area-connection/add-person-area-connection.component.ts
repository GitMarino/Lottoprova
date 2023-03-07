import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { KeyValue } from 'src/app/model/objects/KeyValue';
import { HttpCallsService } from '../../model/service/http-calls.service';

@Component({
  selector: 'app-add-person-area-connection',
  templateUrl: './add-person-area-connection.component.html',
  styleUrls: ['./add-person-area-connection.component.css']
})
export class AddPersonAreaConnectionComponent {

  constructor(private httpCalls: HttpCallsService) {}

  selectedPerson: KeyValue = new KeyValue();
  selectedArea: KeyValue = new KeyValue();

  people: KeyValue[] = [];
  areas: KeyValue[] = [];

  error: boolean = false;
  success: boolean = false;

  ngOnInit(): void
  { this.httpCalls.getAllPeople()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.people = response as KeyValue[];
        }
      });
    
    this.httpCalls.getAllAreas()
      .subscribe({
        next: (response: KeyValue[]) => {
          this.areas = response as KeyValue[];
        },
      });
  }

  addPersonAreaConnection()
  { this.httpCalls.createPersonAreaConnection(this.selectedPerson.id, this.selectedArea.id)
      .subscribe({
        next: (response: void) => {
          this.success = true;
          this.error = false;
        },
        error : error => {
          console.error(error);
          this.error = true;
          this.success = false;
        }
      })
  }

}