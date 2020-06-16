import { Component, OnInit } from '@angular/core';
import { Game } from '../common/data/game';
import { GamesService } from '../common/services/games.service';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.scss']
})
export class GamesComponent implements OnInit {


  games = []

  selectedGame : Game ;
  gameLabel : string = "";
  gamePGN : string = "";

  constructor(public gamesService : GamesService ) {}

  onChangeSelectedGame() {
    this.gameLabel = this.selectedGame.playerWhite + " - "+this.selectedGame.playerBlack+" : "+this.selectedGame.resultat;
    this.gamePGN = this.selectedGame.pgn
  }

  ngOnInit(): void {
    this.gamesService.getGames()
      .subscribe(
        (data) => {this.games=data} , 
        (error) => {console.log(error)}
      );
  }

}
