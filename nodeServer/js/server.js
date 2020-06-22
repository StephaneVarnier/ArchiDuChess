var express = require('express');
	var gameApiRoutes = require('./games-api-routes');
	var app = express();

	app.use(function(req, res, next) {  
		res.header("Access-Control-Allow-Origin", "*"); //"*" ou "xy.com , ..."  
		res.header("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS"); //default: GET, ...  
		res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept , Authorization"); 
		next(); 
	});
	

	var bodyParser = require('body-parser');
	var jsonParser = bodyParser.json() ;
    app.use(jsonParser);

	app.use(gameApiRoutes.apiRouter); //delegate REST API routes to apiRouter(s)

	app.use('/html', express.static(__dirname+"/html")); 

	app.listen(8282 , function () {
		console.log("http://localhost:8282");
	});

	app.get('/', function(req , res  ) 
	{
		  res.redirect('/html/index.html'); 
	});