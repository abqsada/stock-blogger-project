import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TwitterSearchComponent } from './twitter-search.component';

describe('TwitterSearchComponent', () => {
  let component: TwitterSearchComponent;
  let fixture: ComponentFixture<TwitterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TwitterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TwitterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
