import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';

import {
  enrollInCourse,
  unenrollFromCourse
} from '../../store/enrollment/enrollment.actions';

import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

import { Course } from '../../services/course';

@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './course-card.html',
  styleUrl: './course-card.css'
})
export class CourseCard {

  @Input() course!: Course;

  enrolledIds$: Observable<number[]>;

  constructor(private store: Store) {
    this.enrolledIds$ = this.store.select(selectEnrolledIds);
  }

  enroll(): void {
    this.store.dispatch(
      enrollInCourse({ courseId: this.course.id })
    );
  }

  unenroll(): void {
    this.store.dispatch(
      unenrollFromCourse({ courseId: this.course.id })
    );
  }

}