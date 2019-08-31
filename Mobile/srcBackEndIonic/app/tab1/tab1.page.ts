import { Component } from '@angular/core';
import { RestApiService } from '../rest-api.service';
import { Observable } from 'rxjs';
import { getLocaleDateFormat } from '@angular/common';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page {

  public leitura:string = "";
  private response:Observable<Object>;

  constructor(public httpService : RestApiService) {
    this.getData();
  }

  updateData():void{
    this.response = this.httpService.putData();
    this.response.subscribe( data => {
      this.getData();
    })
  }

  getData():void{
    this.response = this.httpService.getData();
    this.response.subscribe( 
      data => {
        this.leitura = data as any;
      }, error => {
        console.log(error);
      }
    );
  }
}
