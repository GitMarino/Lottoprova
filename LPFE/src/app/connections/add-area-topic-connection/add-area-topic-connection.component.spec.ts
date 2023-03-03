import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAreaTopicConnectionComponent } from './add-area-topic-connection.component';

describe('AddAreaTopicConnectionComponent', () => {
  let component: AddAreaTopicConnectionComponent;
  let fixture: ComponentFixture<AddAreaTopicConnectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAreaTopicConnectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAreaTopicConnectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
