import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnrollmentRoutingModule } from './enrollment-routing.module';

import { EnrollmentForm } from '../../components/enrollment-form/enrollment-form';
import { ReactiveEnrollmentForm } from '../../components/reactive-enrollment-form/reactive-enrollment-form';

@NgModule({
  imports: [
    CommonModule,
    EnrollmentRoutingModule,
    EnrollmentForm,
    ReactiveEnrollmentForm
  ]
})
export class EnrollmentModule { }