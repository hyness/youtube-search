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

function executeSearch(query, page) {
	$.ajax({
		type: "GET",
		url: "api/search/" + query + "/" + page,
		success: function(response) {
			var results = $("#results").empty();
			
			if (response.data.totalItems != 0) {
				var itemsPerPage = response.data.itemsPerPage;
				var totalItems = response.data.totalItems;
				var prevPage = parseInt(page) - 1;
				var nextPage = parseInt(page) + 1;
				var startIndex = response.data.startIndex;
				var endIndex = page * itemsPerPage < totalItems ? page * itemsPerPage : totalItems;
				var prevLink = prevPage > 0 ? $('<a class="page" href="#">prev</a>').attr('id', prevPage) : 'prev';
				var nextLink = endIndex < totalItems ? $('<a class="page" href="#">next</a>').attr('id', nextPage) : 'next';
				var nav = $('<span class="nav"></span>').append(prevLink).append(' | ').append(nextLink);
				
				results.append($('<span></span>').append('Showing ' + startIndex + '-' + endIndex + ' of ' + totalItems + ' videos'))
					.append(nav.clone());
				var links = $('<ol></ol>').attr('start', startIndex);
				$(response.data.items).each(function(){
					links.append($('<li></li>').append(
						$('<a class="dynamiclink" href="#"></a>').attr('id', this.id).html(this.title)
					));
				});
				results.append(links).append(nav.clone());
			} else {
				results.append("<p>No Results</p>");
			}
			
			results.show();
		}, 
		error: function(event, status, xhr) {
			$("#errors").append('<span class="error">Error performing search: ' + this.url + '</span>').
				append('<span class="error">Return code: ' + event.status + "</span>").show();
		}
	});
}

$(document).on("click", "a.dynamiclink", function(event){
	showVideo(this.id, $(this).html());
	event.preventDefault();
}).on("click", "a.page", function(event){
	executeSearch($("#term").val(), this.id);
	event.preventDefault();
});

$(document).ready(function(){
	$("#search").click(function(){
		executeSearch($("#term").val(), 1);
	});

	$("#loader").ajaxStart(function(){
		$(this).show();
	}).ajaxStop(function(){
		$(this).hide();
	}).hide();
	
	$("#errors").ajaxStart(function(){
		$(this).empty().hide();
	});
	
	$("#term").keypress(function(event){
		if (event.keyCode == 13) {
			executeSearch($("#term").val(), 1);
		}
	}).focus();
	
	$("#results").hide();
});
