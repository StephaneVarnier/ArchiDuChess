const fetch = require("node-fetch");
const mongoClient = require('./my_generic_mongo_client')
const RULE = "chess"

var user = "tiou"
var year = "2020"


const getDataAndPersist = async url => {
  try {
    const response = await fetch(url);
    const json = await response.json();
    
    fillMongoDbWithJson(json);
  } catch (error) {
    console.log(error);
  }
};

// const getData = async url => {
//   try {
//     const response = await fetch(url);
//     const json = await response.json();
    
//     return(json);

//   } catch (error) {
//     console.log(error);
//   }
// };

function getGames() {
for (let month = 6; month > 0; month-- ) {

  let url = "https://api.chess.com/pub/player/"+user+"/games/"+year+"/0"+month;
  getDataAndPersist(url);
  console.log ("archive "+url+" downloaded" )
  
}
}

function fillMongoDbWithJson(jsonn) {

  for (i in jsonn.games) {

    if (jsonn.games[i].rules == RULE) {

      let jsonCorrected = prepareJson(jsonn.games[i])
     
      mongoClient.genericUpdateOne
        (
          'OnlineGame',
          jsonCorrected._id,
          jsonCorrected,
          function (err, gameId) {
            if (err) console.log(`error on game : ${gameId} --> ${err}`)
          }
        )
    }
  }

}

function isNew(id, toReturn) {
  console.log(id)
  toReturn = false
  mongoClient.genericFindOne
    (
      'OnlineGame',
      { '_id': id },
      function (err, game) {

        if (game == null) {
          toReturn = true
          console.log(game)
        }
        else {
          console.log(game.url)
          toReturn = false
        }
        return toReturn
      }
    )
  console.log(toReturn)
  return toReturn
}

function prepareJson(jsonn) {

  let jsonCorrected = jsonn
  jsonCorrected._id = (jsonn.url).toString().substr(32)
  delete jsonCorrected.time_class;
  jsonCorrected.playerWhite = jsonCorrected.white.username
  jsonCorrected.eloWhite = jsonCorrected.white.rating
  jsonCorrected.playerBlack = jsonCorrected.black.username
  jsonCorrected.eloBlack = jsonCorrected.black.rating
  delete jsonCorrected.white;
  delete jsonCorrected.black;
  delete jsonCorrected.end_time;
  delete jsonCorrected.fen;
  delete jsonCorrected.rated;
  delete jsonCorrected.rules;
  splittedPgn = (jsonCorrected.pgn).toString().split(" ")
  jsonCorrected.resultat = splittedPgn[splittedPgn.length - 1]
  jsonCorrected.opening = (((jsonCorrected.pgn).toString().split("\n"))[8].split("\""))[1].substr(31)
  jsonCorrected.date = ((jsonCorrected.pgn).toString().split("\n"))[2].substr(7, 10)
  return jsonCorrected
}

getGames ();

