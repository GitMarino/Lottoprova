import { Injectable } from '@angular/core';
import { KeyValue } from '../objects/key-value';

@Injectable({ providedIn: 'root' })
export class FEutilityService {
  constructor() { }

  public getMapBySelected(map: KeyValue[][], areaId?: number, topicId?: number, skillId?: number): KeyValue[][] {
    var resultMap: KeyValue[][] = [];
    for (var row of map) {
      if ((areaId === undefined || row[0].id === areaId) && (topicId === undefined || row[1].id === topicId) && (skillId === undefined || row[2].id === skillId)) {
        resultMap.push(row);
      }
    }
    return resultMap;
  }
}