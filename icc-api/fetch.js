const fetch = require("node-fetch");
const mongoClient = require('./my_generic_mongo_client')
const RULE = "chess"

const url = "https://api.chess.com/pub/player/tiou/games/2020/05";
var tabGames = [];

const getData = async url => {
  try {
    const response = await fetch(url);
    const json = await response.json();
    //console.log(json);
    fillMongoDbWithJson(json);
  } catch (error) {
    console.log(error);
  }
};

function fillMongoDbWithJson(jsonn) {
    
    for (i in jsonn.games) {
       
        if (jsonn.games[i].rules == RULE) {
    //    jsonStr = JSON.stringify(jsonn.games[i]).toString().replace("url","_id");
    //    var jsonCorrected = JSON.parse(jsonStr);
    //     console.log(i + " --> " +jsonCorrected._id.toString());
        let jsonCorrected = prepareJson(jsonn.games[i])
       
        mongoClient.genericInsertOne
        (
            'OnlineGame',
            jsonCorrected,
             function(err,gameId) {
                 if (err) console.log(`error on game : ${gameId} --> ${err}`)
                }
        );}
    }

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
        splittedPgn =  (jsonCorrected.pgn).toString().split(" ")
        jsonCorrected.resultat = splittedPgn[splittedPgn.length-1]
        jsonCorrected.opening = (((jsonCorrected.pgn).toString().split("\n"))[8].split("\""))[1].substr(31)
        jsonCorrected.date = ((jsonCorrected.pgn).toString().split("\n"))[2].substr(7,10)
        return jsonCorrected
}

getData(url);