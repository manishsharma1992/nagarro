import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AccountService } from 'src/app/_services/account.service';
import {MenuItem} from 'primeng/api';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Account } from 'src/app/_models/account.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  columnDefs: any[] = [];
  rowData$: Account[] = [];
  filterForm!: FormGroup;
  items!: MenuItem[];
  user: any;
  username!: String;

  constructor(private accService: AccountService, private formBuilder: FormBuilder, private tokenService: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
    this.user = this.tokenService.getUser();
    this.username = this.user.username;
    this.initFilterForm();
    this.initGrid();
  }

  initFilterForm() {
    this.filterForm = this.formBuilder.group({
      acctId: [null],
      dateRange: [[]],
      amountRange: [[0,0]]
    })
  }

  initGrid(): void {
    this.columnDefs.push({field: 'accountNumber', header: 'Account Number'});
    this.columnDefs.push({field: 'accountType', header: 'Account Type'});
    this.columnDefs.push({field: 'amount', header: 'Amount'});
    this.columnDefs.push({field: 'datefield', header: 'Date'});

    if(!this.isAdminLoggedIn()) {
      this.getDataForUser();
    }

  }

  onSubmit() {
    this.accService.fetchStatementsByFilter(this.filterForm.value).subscribe((response) => {
      this.rowData$ = [];
      response.forEach((acct: any) => {
        acct.statements.forEach((stmt: any) => {
          let account = new Account();
          account.accountNumber = acct.accountNumber;
          account.accountType = acct.accountType;
          account.amount = stmt.amount;
          account.datefield = stmt.datefield;
          this.rowData$.push(account);
        });
      });
    })
  }

  getDataForUser() {
    this.accService.fetchAllAccounts().subscribe((response) => {
      this.rowData$ = [];
      response.forEach((acct: any) => {
        acct.statements.forEach((stmt: any) => {
          let account = new Account();
          account.accountNumber = acct.accountNumber;
          account.accountType = acct.accountType;
          account.amount = stmt.amount;
          account.datefield = stmt.datefield;
          this.rowData$.push(account);
        });
      });
    })
  }

  isAdminLoggedIn(): boolean {
    const roles = this.user.roles;
    const role = roles[0];
    if(role === 'ROLE_ADMIN') {
      return true;
    }
    else {
      return false;
    }
  }

  logout() {
    this.tokenService.signOut();
    this.router.navigateByUrl('/login');
  }

}
