import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LecturersComponent } from './lecturers/lecturers.component';
import { StudentsComponent } from './students/students.component';
import { LecturerDetailsComponent } from './lecturer-details/lecturer-details.component';

@NgModule({
  declarations: [
    AppComponent,
    LecturersComponent,
    StudentsComponent,
    LecturerDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
