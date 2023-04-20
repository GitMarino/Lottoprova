import { Component, ElementRef, ViewChild } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap'

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.scss'],
  providers: [NgbModalConfig, NgbModal],
})
export class PopupComponent {

  popupTitle?: string;
  popupBody?: string;

  @ViewChild('content', { static: true }) content!: ElementRef;

  constructor(config: NgbModalConfig, private modalService: NgbModal) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
  }

  show(title: string, body: string) {
    this.popupTitle = title;
    this.popupBody = body;
    this.modalService.open(this.content);
  }

}