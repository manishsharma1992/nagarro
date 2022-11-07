import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';

import { AuthGuard } from './auth.guard';
import { AuthService } from './auth.service';
import { TokenStorageService } from './token-storage.service';

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let routerSpy: jasmine.SpyObj<Router>;
  let serviceStub: Partial<AuthService>;
  beforeEach(() => {
    routerSpy = jasmine.createSpyObj<Router>('Router', ['navigate']); // [1]
    serviceStub = {}; // [2]
    guard = new AuthGuard(serviceStub as TokenStorageService, routerSpy);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
