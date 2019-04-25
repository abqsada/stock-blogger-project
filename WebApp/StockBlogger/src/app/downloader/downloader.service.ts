import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class DownloaderService {

  constructor(private http: HttpClient) { }
  // reads a text file from the server and logs the file contents,
  // before returning those contents to the caller as an Observable<string>.
  getTextFile(filename: string) {
    // The Observable returned by get() is of type Observable<string>
    // because a text response was specified.
    // There's no need to pass a <string> type parameter to get().
    return this.http.get(filename, {responseType: 'text'})
      .pipe(
        tap( // Log the result or error
          data => this.log(filename, data),
          error => this.logError(filename, error)
        )
      );
  }
  logError(filename: string, error: any): void {
    console.log(filename + ' ' + error)
  }
  log(filename: string, data: string): void {
    console.log(filename + ' ' + data)
  }
}
