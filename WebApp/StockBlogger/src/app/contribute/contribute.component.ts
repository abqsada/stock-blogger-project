import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { Router } from '@angular/router';


@Component({
  selector: 'app-contribute',
  templateUrl: './contribute.component.html',
  styleUrls: ['./contribute.component.css']
})
export class ContributeComponent implements OnInit {

  public Editor = ClassicEditor;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  // Handles navigation to the next page
  nextHandler() {
    this.router.navigate(['account']);
  }
  // Handles navigation to the previous page
  prevHandler() {
    this.router.navigate(['feed']);
  }

}