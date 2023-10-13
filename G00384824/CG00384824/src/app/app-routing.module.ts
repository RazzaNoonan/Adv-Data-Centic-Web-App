import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LecturersComponent } from './lecturers/lecturers.component';
import { StudentsComponent } from './students/students.component';
import { LecturerDetailsComponent } from './lecturer-details/lecturer-details.component';

const routes: Routes = [
  { path: '', redirectTo: '/lecturers', pathMatch: 'full' },
  { path: 'lecturers', component: LecturersComponent },
  { path: 'students', component: StudentsComponent },
  { path: 'lecturer-details/:id', component: LecturerDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
