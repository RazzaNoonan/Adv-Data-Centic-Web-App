import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LecturerService } from '../lecturer.service';

@Component({
  selector: 'app-lecturers',
  templateUrl: './lecturers.component.html',
  styleUrls: ['./lecturers.component.css'],
})
export class LecturersComponent implements OnInit {
  lecturers: any[] = [];

  constructor(private lecturerService: LecturerService, private router: Router) {}

  ngOnInit(): void {
    this.fetchLecturers();
  }

  fetchLecturers(): void {
    this.lecturerService.getLecturers().subscribe((data) => {
      this.lecturers = data;
    });
  }

  updateLecturer(lid: string): void {
    this.router.navigateByUrl(`/lecturer-details/${lid}`);
  }
}
