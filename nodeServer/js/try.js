var url = "https://api.chess.com/pub/player/tiou/games/2020/05"

//variables globales:

var tabGames = []; // A synchroniser (via api REST) avec le coté serveur

function makeAjaxGetRequest(url,callback) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
		    callback(xhr.responseText);
		}
	};
	xhr.open("GET", url, true);
	xhr.send(null);
}


function loadGamessWithAjax(){
	makeAjaxGetRequest(url ,  function(texteReponse){
		tabGames = JSON.parse(texteReponse /* au format json string */);
		/* //old simulated values:
		tabDevises.push({code:'EUR' , nom : 'Euro' , change : 1})
	    tabDevises.push({code:'USD' , nom : 'Dollar' , change : 1.1})
		*/
		for(i=0;i<tabGames.length;i++){
			console.log(tabGames[i]);
		}
	});
}

loadGamessWithAjax();








