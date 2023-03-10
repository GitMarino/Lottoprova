import { Component } from '@angular/core';
import { Link } from '../model/objects/link';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css']
})
export class SkillComponent 
{ link: Link = new Link();
  iconName: string = "file-text";

  constructor()
  { this.link.name = "Aggiungi una competenza";
    this.link.path =  "/skill/add";
  }
}