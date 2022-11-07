import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule],
      declarations: [ HomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    component.user = JSON.parse('{"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY2NzgzMzUxMywiZXhwIjoxNjY3ODMzNTE4fQ.UKHQIelSzeXSR1Lb-hR9bTrml3t7L9D9N8L_En8XODEnltc7lXEjAmN5iEz6DjgqPLAW3c51V0_pxcAYzWybNQ","type":"Bearer","username":"admin","roles":["ROLE_ADMIN"]}');
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
