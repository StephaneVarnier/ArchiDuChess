var express = require('express');
	var gameApiRoutes = require('./games-api-routes');
	var app = express();

	app.use(gameApiRoutes.apiRouter); //delegate REST API routes to apiRouter(s)

	app.listen(8282 , function () {
		console.log("http://localhost:8282");
	});