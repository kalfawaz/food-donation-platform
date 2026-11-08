import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Role } from '../models';

// Blocks a page if the user is not logged in, or does not have one of the roles in route data
export const roleGuard: CanActivateFn = (route) => {
  const auth = inject(AuthService);
  const router = inject(Router);
  const roles = (route.data['roles'] ?? []) as Role[];

  if (!auth.currentUser()) {
    return router.createUrlTree(['/login']);
  }
  if (roles.length > 0 && !auth.hasRole(...roles)) {
    return router.createUrlTree(['/']);
  }
  return true;
};
