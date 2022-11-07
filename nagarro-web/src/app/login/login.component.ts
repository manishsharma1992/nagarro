import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/_services/auth.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  returnUrl!: string;

  constructor(
    private formbuilder: FormBuilder,
    private authService: AuthService,
    private tokenService: TokenStorageService,
    private router: Router) { 
      const user = this.tokenService.getUser();
      if(user) {
        this.router.navigate(['/home']);
      }
    }

  ngOnInit(): void {
    this.initForm();
    this.returnUrl = this.router.routerState.snapshot.root.queryParams['returnUrl'] || '';
  }

  initForm() {
    this.loginForm = this.formbuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  onSubmit() {
    this.authService.authenticateLogin(this.loginForm.value).subscribe((response: any) => {
      this.tokenService.saveToken(response.token);
      this.tokenService.saveUser(response);
      console.log(this.tokenService.getUser());
      this.isLoginFailed = false;
      this.isLoggedIn = true;
      this.roles = this.tokenService.getUser().roles;
      this.router.navigateByUrl('/');
      // this.reloadPage();
    })

    if(this.isLoggedIn) {
      this.authService.isLoggedIn = true;
    }
    else {
      this.authService.isLoggedIn = false;
    }
  }

  reloadPage() {
    window.location.reload();
  }

}
