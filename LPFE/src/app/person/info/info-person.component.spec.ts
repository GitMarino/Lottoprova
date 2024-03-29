import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoPersonComponent } from './info-person.component';

describe('InfoPersonSkillsComponent', () => {
  let component: InfoPersonComponent;
  let fixture: ComponentFixture<InfoPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InfoPersonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfoPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
