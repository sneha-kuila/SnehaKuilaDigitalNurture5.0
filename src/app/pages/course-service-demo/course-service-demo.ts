import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CourseService, Course } from '../../services/course';

@Component({
  selector: 'app-course-service-demo',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './course-service-demo.html',
  styleUrl: './course-service-demo.css'
})
export class CourseServiceDemo implements OnInit {

  courses: Course[] = [];

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {

    this.courseService.getCourses().subscribe({
      next: (courses) => {
        this.courses = courses;
      },
      error: (err) => {
        console.error(err);
      }
    });

  }

}