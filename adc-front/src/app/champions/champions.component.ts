import { Component, OnInit } from '@angular/core';
import { ChampionsService } from '../common/services/champions.service';
import { Champion } from '../common/data/champion';

@Component({
  selector: 'app-champions',
  templateUrl: './champions.component.html',
  styleUrls: ['./champions.component.scss']
})
export class ChampionsComponent implements OnInit {

  champions : Array<Champion> = new Array<Champion>();

  constructor(public championsService : ChampionsService) { }

  ngOnInit(): void {

    this.championsService.getChampions()
    .subscribe(
      (data) => {this.champions = data},
      (error) => { console.log(error)}
    )
  }

}
