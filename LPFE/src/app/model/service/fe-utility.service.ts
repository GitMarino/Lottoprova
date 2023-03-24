import { Injectable } from '@angular/core';
import { KeyValue } from '../objects/key-value';

@Injectable({providedIn: 'root'})
export class FEutilityService 
{ 
  constructor() {}

  public getMapBySelected(id: number, column: number, map: KeyValue[][]): KeyValue[][]
  { var resultMap: KeyValue[][] = [];
    for(var row of map)
    { if(id===undefined || row[column].id===id)
      { resultMap.push(row);
      }
    }
    return resultMap;
  }
}