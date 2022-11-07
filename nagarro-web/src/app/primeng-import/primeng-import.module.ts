import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { PanelModule } from 'primeng/panel';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { PasswordModule } from "primeng/password";
import { TableModule } from "primeng/table"
import { CalendarModule } from "primeng/calendar";
import {InputNumberModule} from 'primeng/inputnumber';
import {SliderModule} from 'primeng/slider';
import {MenubarModule} from 'primeng/menubar';

import { AgGridModule } from 'ag-grid-angular';



@NgModule({
  declarations: [],
  imports: [
    CommonModule, AgGridModule,
    CardModule, PanelModule, InputTextModule, ButtonModule,
    PasswordModule, TableModule, CalendarModule, InputNumberModule,
    SliderModule, MenubarModule
    
  ],
  exports: [
    CardModule, AgGridModule, PanelModule, InputTextModule, ButtonModule,
    PasswordModule, TableModule, CalendarModule, InputNumberModule,
    SliderModule, MenubarModule
  ]
})
export class PrimengImportModule { }
