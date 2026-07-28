import { FormsModule } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { Observable } from 'rxjs';

import { Store } from '@ngrx/store';

import { CourseCard } from '../../components/course-card/course-card';
import { Course } from '../../services/course';

import { loadCourses } from '../../store/course/course.actions';
import { selectAllCourses } from '../../store/course/course.selectors';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [
  CommonModule,
  FormsModule,
  CourseCard
],
  templateUrl: './course-list.html',
  styleUrl: './course-list.css'
}
)
export class CourseList implements OnInit {

  courses$: Observable<Course[]>;

  courses: Course[] = [];
  filteredCourses: Course[] = [];
  searchTerm: string = '';

  isLoading = true;
  errorMessage = '';

  constructor(
    private store: Store,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.courses$ = this.store.select(selectAllCourses);
  }

  ngOnInit(): void {

    this.searchTerm =
      this.route.snapshot.queryParamMap.get('search') || '';

    this.store.dispatch(loadCourses());

    this.courses$.subscribe(courses => {
      this.courses = courses;
      this.filterCourses();
      this.isLoading = false;
    });

  }

  viewCourse(course: Course): void {
    this.router.navigate(['courses', course.id]);
  }

  onSearch(): void {

    this.router.navigate(
      ['courses'],
      {
        queryParams: {
          search: this.searchTerm
        }
      }
    );

    this.filterCourses();

  }

  filterCourses(): void {

    if (!this.searchTerm) {
      this.filteredCourses = this.courses;
      return;
    }

    this.filteredCourses = this.courses.filter(course =>
      course.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );

  }

}