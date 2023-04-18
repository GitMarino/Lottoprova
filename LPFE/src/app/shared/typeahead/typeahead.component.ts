import { Component, Input, Output, forwardRef } from '@angular/core';
import { ControlValueAccessor, NG_VALIDATORS, NG_VALUE_ACCESSOR } from '@angular/forms';
import { KeyValue } from '../objects/key-value';
import { Observable, OperatorFunction, debounceTime, distinctUntilChanged, map } from 'rxjs';

@Component({
  selector: 'app-typeahead',
  templateUrl: './typeahead.component.html',
  styleUrls: ['./typeahead.component.scss'],
  providers: [
    { provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => TypeaheadComponent),
      multi: true
    }
  ]
})
export class TypeaheadComponent implements ControlValueAccessor {
  
  @Input() label: string = '';
  @Input() list: KeyValue[] = [];
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

  content?: any;
  
  onSelect(event: any)
  { 
    console.log(event.target.value) ;
    if(this.list.find((item)=> item.value=== event.target.value))
    { this.onChange(event.target.value);
    }
    else
    { this.onChange(null);
    }
  }
  

  writeValue(value: any): void {
    this.content = value;
    this.onChange(null);
  }

  //when something is been writing
  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  onChange= (value: any) =>
  {}
  
  onTouched() 
  {}

}
