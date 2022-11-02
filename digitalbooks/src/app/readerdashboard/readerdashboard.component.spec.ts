import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReaderdashboardComponent } from './readerdashboard.component';

describe('ReaderdashboardComponent', () => {
  let component: ReaderdashboardComponent;
  let fixture: ComponentFixture<ReaderdashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReaderdashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReaderdashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
