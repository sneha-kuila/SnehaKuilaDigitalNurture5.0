import { beforeEach, describe, expect, it, vi } from 'vitest';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { Store } from '@ngrx/store';
import { of } from 'rxjs';

import { CourseCard } from './course-card';
import { enrollInCourse } from '../../store/enrollment/enrollment.actions';

describe('CourseCard', () => {

  let component: CourseCard;
  let fixture: ComponentFixture<CourseCard>;
  const storeSpy = {
  dispatch: vi.fn(),
  select: vi.fn(() => of([]))
};
  beforeEach(async () => {

  vi.clearAllMocks();

  await TestBed.configureTestingModule({
      imports: [CourseCard],
      providers: [
        { provide: Store, useValue: storeSpy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseCard);
    component = fixture.componentInstance;

    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display the course name', () => {

    component.course = {
      id: 1,
      name: 'Data Structures',
      code: 'CS101',
      credits: 4,
      gradeStatus: 'passed'
    };

    fixture.detectChanges();

    const title = fixture.debugElement.query(By.css('h3')).nativeElement;

    expect(title.textContent).toContain('Data Structures');

  });

  it('should dispatch enrollInCourse when enroll() is called', () => {

    component.course = {
      id: 1,
      name: 'Data Structures',
      code: 'CS101',
      credits: 4,
      gradeStatus: 'passed'
    };

    component.enroll();

    expect(storeSpy.dispatch).toHaveBeenCalledWith(
      enrollInCourse({ courseId: 1 })
    );

  });

});