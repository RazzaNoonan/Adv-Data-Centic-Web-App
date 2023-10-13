import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LecturerService } from '../lecturer.service';
import { Lecturer } from '../lecturer';

@Component({
  selector: 'app-lecturer-details',
  templateUrl: './lecturer-details.component.html',
  styleUrls: ['./lecturer-details.component.css'],
})
export class LecturerDetailsComponent implements OnInit {
  lecturer!: Lecturer;

  constructor(
    private route: ActivatedRoute,
    private lecturerService: LecturerService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.lecturerService.getLecturerById(id).subscribe((lecturer) => {
      this.lecturer = lecturer;
    });
  }
}
