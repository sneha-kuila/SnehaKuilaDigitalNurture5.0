import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {

  return next(req).pipe(

    catchError((error: HttpErrorResponse) => {

      if (error.status === 401) {
        console.error('Unauthorized! Redirect to login/home.');
      }

      if (error.status === 500) {
        console.error('Internal Server Error!');
      }

      return throwError(() => error);

    })

  );

};