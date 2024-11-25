import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function optionalDateValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const isEmpty = !control.value;
    // Add additional validation logic here if needed
    return isEmpty ? null : { invalidDate: true }; // Replace with actual validation logic
  };
}
