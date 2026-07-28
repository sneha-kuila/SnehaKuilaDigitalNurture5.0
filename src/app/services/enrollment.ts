import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, of } from 'rxjs';
import { switchMap } from 'rxjs/operators';

import { CourseService, Course } from './course';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {

  private enrolledCourseIds: number[] = [];

  private studentsApi = 'http://localhost:3000/students';

  constructor(
    private courseService: CourseService,
    private http: HttpClient
  ) {}

  enroll(courseId: number): void {

    if (!this.enrolledCourseIds.includes(courseId)) {
      this.enrolledCourseIds.push(courseId);
    }

  }

  unenroll(courseId: number): void {

    this.enrolledCourseIds =
      this.enrolledCourseIds.filter(id => id !== courseId);

  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCourseIds.includes(courseId);
  }

  getEnrolledCourses(): Observable<Course[]> {

    if (this.enrolledCourseIds.length === 0) {
      return of([]);
    }

    return forkJoin(
      this.enrolledCourseIds.map(id =>
        this.courseService.getCourseById(id)
      )
    );

  }

  // Step 87
  // Loads students enrolled in a selected course.
  // switchMap ensures that if a different course is selected before
  // the previous request finishes, the previous request is cancelled.

  getStudentsByCourse(courseId: number): Observable<any[]> {

    return this.courseService.getCourseById(courseId).pipe(

      switchMap(() =>
        this.http.get<any[]>(
          `${this.studentsApi}?courseId=${courseId}`
        )
      )

    );

  }

}