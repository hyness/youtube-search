function getPlayer(id, width, height) {
    var url = "http://www.youtube.com/embed/" + id + "?rel=0&autohide=1&autoplay=1&controls=1&fs=1";
    return '<iframe title="YouTube video player" style="margin:0; padding:0;" width="' + width + '" ' + 
    	'height="' + height + '" src="' + url + '" frameborder="0" allowfullscreen></iframe>';
}

function showVideo(id, title) {
	var width = 640, height = 360;
	var player = $("<div></div>");
	$("body").append(player);
	player.dialog({
		autoOpen: false, modal: true, resizable: false, position: 'center',  
		width: 'auto', height: 'auto', title: title, 
		close: function(){
			player.remove();		
		}
	});
	player.append(getPlayer(id, width, height));
	player.dialog("open");
}

function executeSearch(query, hd, page, token) {
	$.ajax({
		type: "GET",
		url: "search/" + query + "/" + hd + (token != null ? "/" + token : ""),
	}).done(function(msg) {
		var response = $("#results").empty();
		
		if (msg.totalResults != 0) {
			page = parseInt(page);
			var itemsPerPage = msg.resultsPerPage;
			var totalItems = msg.totalResults;
			var prevPage = msg.prevPageToken;
			var nextPage = msg.nextPageToken;
			var startIndex = ((page - 1) * itemsPerPage) + 1;
			var endIndex = page * itemsPerPage < totalItems ? page * itemsPerPage : totalItems;
			var prevLink = prevPage != null ? $('<a class="page" href="#">prev</a>').attr('pageNo', page - 1).attr('id', prevPage) : 'prev';
			var nextLink = nextPage != null ? $('<a class="page" href="#">next</a>').attr('pageNo', page + 1).attr('id', nextPage) : 'next';
			var nav = $('<span class="nav"></span>').append(prevLink).append(' | ').append(nextLink);
			
			response.append($('<span></span>').append('Showing ' + startIndex + '-' + endIndex + ' of ' + totalItems + ' videos'))
				.append(nav.clone());
			var links = $('<ol></ol>').attr('start', startIndex);
			$(msg.items).each(function(){
				links.append($('<li></li>').append(
					$('<a class="dynamiclink" href="#"></a>').attr('id', this.id).html(this.title)
				));
			});
			response.append(links).append(nav.clone());
		} else {
			response.append("<p>No Results</p>");
		}
		
		response.show();
	});
}

$(document).on("click", "a.dynamiclink", function(event){
	showVideo(this.id, $(this).html());
	event.preventDefault();
}).on("click", "a.page", function(event){
	executeSearch($("#term").val(), $("#hd").prop('checked') ? 'HIGH' : 'ANY', $(this).attr("pageno"), this.id);
	event.preventDefault();
});

$(document).ready(function(){
	$("#search").click(function(){
		executeSearch($("#term").val(), $("#hd").prop('checked') ? 'HIGH' : 'ANY', 1, '');
	});

	$("#loader").ajaxStart(function(){
		$(this).show();
	}).ajaxStop(function(){
		$(this).hide();
	}).hide();
	
	$("#term").keypress(function(event){
		if (event.keyCode == 13) {
			executeSearch($("#term").val(), $("#hd").prop('checked') ? 'HIGH' : 'ANY', 1, '');
		}
	}).focus();
	
	$("#results").hide();
});
