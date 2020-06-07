var express = require('express');
	const apiRouter = express.Router();

	var allGames = [];

	//allGames.push({ code : 'EUR' , nom : 'Euro' , change : 1.0 });
	//allGames.push({ code : 'USD' , nom : 'Dollar' , change : 1.1 });
   
   
	//exemple URL: http://localhost:8282/game-api/public/games (returning all games)
	apiRouter.route('/game-api/public/games')
	.get( function(req , res  , next ) {
		res.send(allGames);
    });
    
    //exemple URL: http://localhost:8282/game-api/public/game/6666 (returning game 6666)
    apiRouter.route('/game-api/public/game/:id')
	.get( function(req , res  , next ) {
        var id = req.params.id; 
        for (i=0; i<allGames.length;i++) 
            {
                if (allGames[i].id = id)  res.send(allGames[i]);
            }
	});

	exports.apiRouter = apiRouter;