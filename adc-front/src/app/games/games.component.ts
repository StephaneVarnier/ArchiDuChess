import { Component, OnInit } from '@angular/core';
import { Game } from '../common/data/game';
import { GamesService } from '../common/services/games.service';
import { FullMove } from './fullMove';
//import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
//import 'chess-board';


//import { ChessBoard } from 'chessboardjs';
//import * as ChessBoard from 'chessboardjs';
declare var ChessBoard: any;
//const chessBoard : ChessBoardFactory;

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.scss']
})
export class GamesComponent implements OnInit {

  myBoard: any;
  games = [];
  selectedGame: Game;
  gameLabel: string = "";
  gamePGN: string = "";
  gameId: number;
  moveNumber: number;
  fens = [];
  sans = [];
  
  fullMoves : Array<FullMove> = new Array<FullMove>();

  constructor (public gamesService : GamesService) {}
  

  onChangeSelectedGame() {
    this.gameLabel = this.selectedGame.playerWhite + " - " + this.selectedGame.playerBlack + " : " + this.selectedGame.resultat;
    this.gamePGN = this.selectedGame.pgn
    this.gameId = this.selectedGame.id

    this.gamesService.getFens(this.gameId)
      .subscribe(
        (data) => {
          this.fens = data;
         // console.log("<-- " + this.gameId + " -->");
        },
        (error) => { console.log(error) }
      );

      this.gamesService.getSans(this.gameId)
      .subscribe(
        (data) => {this.sans = data; this.displayMovesInTable()},
        (error) => { console.log(error) }
      );

    this.startGame();

  }

  ngOnInit(): void {
    this.gamesService.getGames()
      .subscribe(
        (data) => { this.games = data },
        (error) => { console.log(error) }
      );

    this.startGame();
  }

  startGame(): void {
    this.myBoard = ChessBoard(
      'board1', "start"
    );
    this.moveNumber = 0;
  }

  advanceGame(): void {
    if (this.moveNumber < this.fens.length-1) {
      this.myBoard = ChessBoard(
        'board1', this.fens[++this.moveNumber]
      );
    }

  }

  withdrawGame(): void {
    if (this.moveNumber > 0) {
      this.myBoard = ChessBoard(
        'board1', this.fens[--this.moveNumber]
      );
    }
  }

  lastMoveGame(): void {
    if (this.fens.length > 0) {
      this.myBoard = ChessBoard(
        'board1', this.fens[this.fens.length-1]
      );
      this.moveNumber= this.fens.length-1;
    }
  }

  displayMovesInTable() : void {
    this.fullMoves.splice(0, this.fullMoves.length);
    if (this.sans.length>0) 
    {
      let n = Math.trunc(this.sans.length/2)
      for (let i =0; i<=n;i++) 
       {
         let fm = new FullMove(this.sans[2*i], this.sans[2*i+1])             
         this.fullMoves.push(fm);

         console.log (this.fullMoves[i].white + " .." + this.fullMoves[i].black)
      }
    }
    
  }

}
