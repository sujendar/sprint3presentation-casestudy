import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorbookviewComponent } from './authorbookview.component';

describe('AuthorbookviewComponent', () => {
  let component: AuthorbookviewComponent;
  let fixture: ComponentFixture<AuthorbookviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthorbookviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthorbookviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
