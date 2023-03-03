import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPersonSkillConnectionComponent } from './add-person-skill-connection.component';

describe('AddPersonSkillConnectionComponent', () => {
  let component: AddPersonSkillConnectionComponent;
  let fixture: ComponentFixture<AddPersonSkillConnectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPersonSkillConnectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPersonSkillConnectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
