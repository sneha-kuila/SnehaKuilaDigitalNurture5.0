import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
  AbstractControl,
  ValidationErrors
} from '@angular/forms';

import { CanComponentDeactivate } from '../../guards/unsaved-changes-guard';

@Component({
  selector: 'app-reactive-enrollment-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './reactive-enrollment-form.html',
  styleUrl: './reactive-enrollment-form.css'
})
export class ReactiveEnrollmentForm
  implements OnInit, CanComponentDeactivate {

  enrollForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {

    this.enrollForm = this.fb.group({

      studentName: [
        '',
        [Validators.required, Validators.minLength(3)]
      ],

      studentEmail: [
        '',
        [Validators.required, Validators.email]
      ],

      courseId: [
        '',
        [
          Validators.required,
          this.noCourseCode
        ]
      ],

      preferredSemester: [
        'Odd',
        Validators.required
      ],

      agreeToTerms: [
        false,
        Validators.requiredTrue
      ]

    });

  }

  canDeactivate(): boolean {

    if (this.enrollForm.dirty) {
      return window.confirm('You have unsaved changes. Leave?');
    }

    return true;

  }

  noCourseCode(control: AbstractControl): ValidationErrors | null {

    const value = control.value;

    if (value && value.toString().startsWith('XX')) {
      return {
        noCourseCode: true
      };
    }

    return null;

  }

  onSubmit(): void {

    console.log('Form Value:', this.enrollForm.value);

    console.log(
      'Raw Value:',
      this.enrollForm.getRawValue()
    );

  }

}