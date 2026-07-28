import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Header } from '../../components/header/header';
import { CourseService, Course } from '../../services/course';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    Header
  ],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit, OnDestroy {

  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';

  courses: Course[] = [];

  coursesAvailable = 0;
  enrolledCourses = 0;
  gpa = 8.67;

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {

    this.courseService.getCourses().subscribe({
      next: (courses) => {
        this.courses = courses;
        this.coursesAvailable = courses.length;

        // Until enrollment service is integrated
        this.enrolledCourses = 0;
      },
      error: (err) => {
        console.error(err);
      }
    });

    console.log('Home Component initialized');

  }

  ngOnDestroy(): void {
    console.log('Home Component destroyed');
  }

  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }

}