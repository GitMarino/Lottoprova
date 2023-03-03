import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPersonAreaConnectionComponent } from './add-person-area-connection.component';

describe('AddPersonAreaConnectionComponent', () => {
  let component: AddPersonAreaConnectionComponent;
  let fixture: ComponentFixture<AddPersonAreaConnectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPersonAreaConnectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPersonAreaConnectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
