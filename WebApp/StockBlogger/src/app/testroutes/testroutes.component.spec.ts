import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestroutesComponent } from './testroutes.component';

describe('TestroutesComponent', () => {
  let component: TestroutesComponent;
  let fixture: ComponentFixture<TestroutesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestroutesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestroutesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
