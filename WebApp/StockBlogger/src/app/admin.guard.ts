import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  constructor() {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    alert('YOOOOUUUUUUU...... SHAAAAALLLLL NNNOOOOOOOTTTT...... PPPPAAAAAAAAAASSSSSSS!!!!!');


    // Returns false to demonstrate rejecting a user for permissions
    return false;
  }
}
