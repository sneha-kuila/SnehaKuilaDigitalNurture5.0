import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EnrollmentForm } from '../../components/enrollment-form/enrollment-form';
import { ReactiveEnrollmentForm } from '../../components/reactive-enrollment-form/reactive-enrollment-form';

const routes: Routes = [
  {
    path: '',
    component: EnrollmentForm
  },
  {
    path: 'reactive',
    component: ReactiveEnrollmentForm
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnrollmentRoutingModule { }