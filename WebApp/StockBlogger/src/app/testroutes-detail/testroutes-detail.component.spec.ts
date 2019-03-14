import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestroutesDetailComponent } from './testroutes-detail.component';

describe('TestroutesDetailComponent', () => {
  let component: TestroutesDetailComponent;
  let fixture: ComponentFixture<TestroutesDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestroutesDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestroutesDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
