import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-another_tab',
  templateUrl: 'another_tab.html'
})
export class AnotherTabPage {

  constructor(public navCtrl: NavController) {


  }

  itemTapped(event, item) {
    this.navCtrl.push(ListPage, {item: item});
  }

}
