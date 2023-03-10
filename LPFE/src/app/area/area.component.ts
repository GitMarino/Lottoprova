import { Component } from '@angular/core';
import { Link } from '../model/objects/link';

@Component({
  selector: 'app-area',
  templateUrl: './area.component.html',
  styleUrls: ['./area.component.css']
})
export class AreaComponent 
{ link: Link = new Link();
  iconName: string = "box";

  constructor()
  { this.link.name = "Aggiungi un'area";
    this.link.path =  "/area/add";
  }
}