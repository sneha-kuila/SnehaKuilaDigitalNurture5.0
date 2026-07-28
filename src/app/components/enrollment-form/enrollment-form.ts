import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { CourseService } from '../../services/course';

@Component({
  selector: 'app-enrollment-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './enrollment-form.html',
  styleUrl: './enrollment-form.css'
})
export class EnrollmentForm {

  student = {
    studentName: '',
    studentEmail: '',
    courseId: null as number | null,
    preferredSemester: 'Odd',
    agreeToTerms: false
  };

  submitted = false;

  constructor(private courseService: CourseService) { }

  onSubmit(form: NgForm): void {

    if (form.invalid) {
      return;
    }

    const newCourse = {
      name: this.student.studentName,
      code: `COURSE-${this.student.courseId}`,
      credits: 4,
      gradeStatus: 'Not Graded'
    };

    this.courseService.createCourse(newCourse).subscribe({
      next: (course) => {
        console.log('Course Created:', course);
        console.log('Form Value:', form.value);
        console.log('Form Valid:', form.valid);

        this.submitted = true;
      },
      error: (err) => {
        console.error('Error creating course:', err);
      }
    });
  }

  resetForm(form: NgForm): void {

    form.resetForm({
      preferredSemester: 'Odd',
      agreeToTerms: false
    });

    this.submitted = false;
  }

}