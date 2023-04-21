import { Component, Input, forwardRef } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { KeyValue } from '../objects/key-value';
import { Observable, OperatorFunction, debounceTime, distinctUntilChanged, map } from 'rxjs';
import { NgbTypeaheadSelectItemEvent } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-typeahead',
  templateUrl: './typeahead.component.html',
  styleUrls: ['./typeahead.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => TypeaheadComponent),
      multi: true
    },
    
    /*{
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => TypeaheadComponent),
      multi: true
    }*/
  ]
})
export class TypeaheadComponent implements ControlValueAccessor {

  @Input() label: string = '';
  @Input() list: KeyValue[] = [];
  value?: any;

  inputFormatter = (selected: KeyValue) => selected.value;
  resultsFormatter = (result: KeyValue) => result.value;
  search: OperatorFunction<string, readonly KeyValue[]> = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map((written) =>
        written.length < 3 ? []
          : this.list!.filter((o) => o.value.toLowerCase().indexOf(written.toLowerCase()) > -1).slice(0, 5),
      ),
    );

  //event particolare che permette di avere item che è l'oggetto
  onSelect(event: NgbTypeaheadSelectItemEvent) {
    this.value = event.item;
    this.onChange(event.item.id);
  }

  //event.target è il tag dove l'evento è contenuto, event.target.value è il valore del tag quindi la stringa dell'input formatter
  onFocusOut(event: any) {
    if (!event.target.value) {
      this.value = null;
      this.onChange(null);
    }
    else {
      const item = this.list.find((item) => item.value === event.target.value)
      if (item) {
        this.value = item;
        this.onChange(item.id);
      }
      else
      { this.value = null;
        this.onChange(null);
      }
    }
    this.onTouched();
  }


  //channel from page to custom component
  writeValue(idValue: number): void {
    //non trova il valore perchè la lista non è inizializzata
    const item = this.list.find((item) => item.id === idValue)
    if (item) {
      this.value = item;
    }
    else
      if (idValue) {
        this.onChange(null);
      }
  }

  //channel from custom component to page
  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  //channel from custom component to page
  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  //ngModel value
  onChange = (idValue: any) => { }

  onTouched() { }

  /*
  validate(control: FormControl): ValidationErrors | null {
    logica del validate
    if(condizione di errore) return return { notAllowedValue: true };
    else return null
  }*/

}