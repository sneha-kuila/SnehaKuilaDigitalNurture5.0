import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Header } from '../../components/header/header';
import { EnrollmentService } from '../../services/enrollment';
import { Course } from '../../services/course';

@Component({
  selector: 'app-student-profile',
  standalone: true,
  imports: [
    CommonModule,
    Header
  ],
  templateUrl: './student-profile.html',
  styleUrl: './student-profile.css'
})
export class StudentProfile implements OnInit {

  enrolledCourses: Course[] = [];

  constructor(
    private enrollmentService: EnrollmentService
  ) {}

  ngOnInit(): void {

    this.enrollmentService.getEnrolledCourses().subscribe({
      next: (courses) => {
        this.enrolledCourses = courses;
      },
      error: (err) => {
        console.error(err);
      }
    });

  }

}