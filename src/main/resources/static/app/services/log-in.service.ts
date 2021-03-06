import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import "rxjs/Rx";


@Injectable()
export class LoginService {

    constructor(private http:Http) {}

    login(email, password):Observable<Response> {
        console.log(email);
        let loginRequest = JSON.stringify({email: email, password: password});
        let headers = new Headers({'Content-Type': 'application/json', 'Accept': 'application/json'});

        return this.http.post('http://localhost:8080/api/test', loginRequest, { headers: headers })
                        .do(resp => {
                           console.log(resp.text().data);
                            localStorage.setItem('jwt', resp.text().data);
                        })
                        ;
    }

    logout():void {
        localStorage.removeItem('jwt');
    }

    private handleError(error:Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }


     isSignedIn():boolean {
        return localStorage.getItem('jwt') !== null;
    }


}
