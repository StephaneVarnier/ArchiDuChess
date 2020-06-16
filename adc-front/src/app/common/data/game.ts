export class Game {

    // id : number;
    // playerWhite : string;
    // playerBlack : string;
    // result : string; 

    constructor(
        public id : number,
        public playerWhite : string,
        public playerBlack : string,
        public timeControl : string,
        public resultat : string,
        public opening : string,
        public pgn : string
    ) {}
  }

//   export class GameImpl implements Game {
//       constructor
//       (
//         public id : number,
//         public playerWhite : string,
//         public  playerBlack : string,
//         public result : string
//       ) {}
//   }