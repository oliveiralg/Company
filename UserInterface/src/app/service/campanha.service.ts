import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

import { Http, Response } from '@angular/http';
import { Campanha } from '../model/campanha';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

const API_URL = environment.apiUrl;
const CREATE_ACTION = 'create';
const UPDATE_ACTION = 'update';
const REMOVE_ACTION = 'delete';

@Injectable()
export class CampanhaService extends BehaviorSubject<any[]> {

  constructor(private http: Http) {
    super([]);
  }

  private data: any[] = [];

  public read() {
      if (this.data.length) {
          return super.next(this.data);
      }

      this.getTodasCampanhas()
          .do(data => {
              this.data = data;
          })
          .subscribe(data => {
              super.next(data);
          });
  }

  private handleError (error: Response | any) {
    console.error('ApiService::handleError', error);
    return Observable.throw(error);
  }

  public getTodasCampanhas(): Observable<Campanha[]> {
    return this.http
    .get(API_URL + '/campanha/read-all')
    .map(response => {
      const campanhas = response.json().campanhas;
      return campanhas.map((campanha) => new Campanha(campanha));
    })
    .catch(this.handleError);
  }

  public update(campanha: Campanha, isNew?: boolean): Observable<Campanha> {
    const action = isNew ? CREATE_ACTION : UPDATE_ACTION;

    return this.http
      .put(API_URL + '/' + action, campanha)
      .map(response => {
        return new Campanha(response.json());
      })
      .catch(this.handleError);
  }

  public delete(campanha: Campanha): Observable<null> {
    const action = REMOVE_ACTION;
    return this.http
      .delete(API_URL + '/' + action + '?campanhaId=' + campanha.id)
      .map(response => null)
      .catch(this.handleError);
  }
}
