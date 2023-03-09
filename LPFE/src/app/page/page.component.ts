import { Component, Input } from '@angular/core';
import { Button } from '../model/objects/button';
import { Link } from '../model/objects/link';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.scss']
})
export class PageComponent {

  @Input() pageTitle: string = '';
  @Input() link: Link = new Link;
  @Input() iconName: string = '';
  @Input() cardTitle: string = '';
  @Input() buttons: Button[] = [];

}