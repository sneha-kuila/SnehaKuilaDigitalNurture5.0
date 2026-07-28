import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError, tap, retry } from 'rxjs/operators';

export interface Course {
  id: number;
  name: string;
  code: string;
  credits: number;
  gradeStatus: string;
}

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private apiUrl = 'http://localhost:3000/courses';

  constructor(private http: HttpClient) { }

  // GET all courses
  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl).pipe(

      // Step 83 - Filter courses
      map(courses => courses.filter(course => course.credits > 0)),

      // Step 85 - Logging
      tap(courses => {
        console.log('Courses loaded:', courses.length);
      }),

      // Step 86 - Retry twice before failing
      retry(2),

      // Step 84 - Error handling
      catchError(err => {
        console.error(err);
        return throwError(() =>
          new Error('Failed to load courses. Please try again.')
        );
      })

    );
  }

  // GET course by ID
  getCourseById(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`).pipe(

      retry(2),

      catchError(err => {
        console.error(err);
        return throwError(() =>
          new Error('Failed to load course.')
        );
      })

    );
  }

  // POST
  createCourse(course: Omit<Course, 'id'>): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course);
  }

  // PUT
  updateCourse(course: Course): Observable<Course> {
    return this.http.put<Course>(
      `${this.apiUrl}/${course.id}`,
      course
    );
  }

  // DELETE
  deleteCourse(id: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${id}`
    );
  }

}