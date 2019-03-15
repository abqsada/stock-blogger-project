import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    console.log('Entered resetpassword.component.ts ngOnInit method!');
  }

  clickHandler() {
    const confirm = prompt('Are you sure you want to go here?');

    if(confirm === 'yes' || 'Yes' || 'YES' || 'y' || 'Y') {
      this.router.navigate(['']);
    }
  }

}
