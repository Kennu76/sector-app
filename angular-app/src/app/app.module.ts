import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from "@angular/material/autocomplete";
import { MatButtonModule } from "@angular/material/button";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatRadioModule } from "@angular/material/radio";
import { MatSelectModule } from "@angular/material/select";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatTreeModule } from "@angular/material/tree";
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AppComponent } from './app.component';
import { SectorTreeComponent } from './sector-tree/sector-tree.component';
import { SectorService } from './sector.service';
import { UserFormComponent } from './user-form/user-form.component';
import { UserService } from './user.service';




@NgModule({
  declarations: [
    AppComponent,
    UserFormComponent,
    SectorTreeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,

    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatSelectModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    MatTreeModule,
    MatIconModule,
    MatCheckboxModule,
    MatAutocompleteModule,
    ReactiveFormsModule
  ],
  providers: [UserService, SectorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
