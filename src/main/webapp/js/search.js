function getPlayer(id, width, height) {
    var url = "http://www.youtube.com/embed/" + id + "?rel=0&autohide=1&autoplay=1&controls=1&fs=1";
    var player = '<iframe title="YouTube video player" style="margin:0; padding:0;" width="' + width + '" ';
    player += 'height="' + height + '" src="' + url + '" frameborder="0" allowfullscreen></iframe>';
    return player;
}

function showVideo(id, title) {
	var width = 640, height = 360;
	var player = $("<div></div>");
	$("body").append(player);
	player.dialog({autoOpen: false, modal: true, resizable: false, position: 'center',  
		width: 'auto', height: 'auto', title: title, 
		close: function(){
			player.html("");		
		}
	});
	player.html(getPlayer(id, width, height));
	player.dialog("open");
}

function executeSearch(query, page) {
	$.ajax({
		type: "GET",
		url: "api/search/" + query + "/" + page,
	}).done(function(msg) {
		if (msg.data.totalItems != 0) {
			itemsPerPage = msg.data.itemsPerPage;
			totalItems = msg.data.totalItems;
			prevPage = parseInt(page) - 1;
			nextPage = parseInt(page) + 1;
			startIndex = msg.data.startIndex;
			endIndex = page * itemsPerPage < totalItems ? page * itemsPerPage : totalItems;
			prevLink = prevPage > 0 ? '<a id="'+prevPage+'" class="page" href="#">prev</a>' : 'prev';
			nextLink = nextPage * itemsPerPage < totalItems ? '<a id="'+nextPage+'" class="page" href="#">next</a>' : 'next';
			response = '<p>' + startIndex + '-' + endIndex + ' of ' + totalItems + '</p>';
			response += '<p>'+prevLink+' | '+nextLink+'</p>';
			$(msg.data.items).each(function(){
				response += '<a id="'+this.id+'" class="dynamiclink" href="#">'+this.title+'</a><br />';
			});
		} else {
			response = "<p>No Results</p>";
		}
		
		$("#results").empty().append(response);
	});
}

$("#search").click(function(){
	executeSearch($("#term").val(), 1);
});

$(document).on("click", "a.dynamiclink", function(event){
	showVideo(this.id, $(this).html());
	event.preventDefault();
}).on("click", "a.page", function(event){
	executeSearch($("#term").val(), this.id);
	event.preventDefault();
});

$(document).ready(function(){
	$("#loader").ajaxStart(function(){
		$(this).show();
	}).ajaxStop(function(){
		$(this).hide();
	}).hide();
	
	$("#term").keypress(function(event){
		if (event.keyCode == 13) {
			executeSearch($("#term").val(), 1);
		}
	}).focus();
});
