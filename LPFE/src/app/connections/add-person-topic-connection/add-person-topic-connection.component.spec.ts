import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPersonTopicConnectionComponent } from './add-person-topic-connection.component';

describe('AddPersonTopicConnectionComponent', () => {
  let component: AddPersonTopicConnectionComponent;
  let fixture: ComponentFixture<AddPersonTopicConnectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPersonTopicConnectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPersonTopicConnectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
