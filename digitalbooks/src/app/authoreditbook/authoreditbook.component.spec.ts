import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthoreditbookComponent } from './authoreditbook.component';

describe('AuthoreditbookComponent', () => {
  let component: AuthoreditbookComponent;
  let fixture: ComponentFixture<AuthoreditbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthoreditbookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthoreditbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
