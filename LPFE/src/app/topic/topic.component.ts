import { Component } from '@angular/core';
import { Link } from '../model/objects/link';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent 
{ link: Link = new Link();
  iconName: string = "book";

  constructor()
  { this.link.name = "Aggiungi una tematica";
    this.link.path =  "/topic/add";
  }
}