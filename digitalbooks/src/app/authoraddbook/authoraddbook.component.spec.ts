import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthoraddbookComponent } from './authoraddbook.component';

describe('AuthoraddbookComponent', () => {
  let component: AuthoraddbookComponent;
  let fixture: ComponentFixture<AuthoraddbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthoraddbookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthoraddbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
