import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoPersonSkillsComponent } from './info-person-skills.component';

describe('InfoPersonSkillsComponent', () => {
  let component: InfoPersonSkillsComponent;
  let fixture: ComponentFixture<InfoPersonSkillsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InfoPersonSkillsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfoPersonSkillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
