import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Button } from '../objects/button';
import { Link } from '../objects/link';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.scss']
})
export class PageComponent {

  @Input() iconName: string = '';
  @Input() pageTitle: string = '';
  //@Input() link: Link = new Link;
  @Input() cardTitle: string = '';
  @Input() buttons: Button[] = [];
  
  @Output() selection = new EventEmitter<string>();
  onClick(button: Button) {
    if( button.action ) {
      button.action!();
    } 
    else if (button.identifier ) {
      this.selection.emit(button.identifier);
    }
  }
}