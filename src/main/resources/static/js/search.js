function getPlayer(id, width, height) {
    var url = "//www.youtube.com/embed/" + id + "?rel=0&autohide=1&autoplay=1&controls=1&fs=1";
    return '<iframe title="YouTube video player" style="margin:0; padding:0;" width="' + width + '" ' + 
    	'height="' + height + '" src="' + url + '" frameborder="0" allowfullscreen></iframe>';
}

function showVideo(id, title) {
	var width = 640, height = 360;
	var player = $("<div></div>");
	$("body").append(player);
	player.dialog({
		autoOpen: false, modal: true, resizable: false, position: {my: 'center'},  
		width: 'auto', height: 'auto', title: title, 
		close: function(){
			player.remove();		
		}
	});
	player.append(getPlayer(id, width, height));
	player.dialog("open");
}

function executeSearch(page, token) {
	$.ajax({
		type: "GET",
		url: "search/" + $("#term").val() + "/" 
			+ ($("#hd").prop('checked') ? 'HIGH' : 'ANY') 
			+ (typeof token !== 'undefined' ? '/' + token : '')
	}).done(function(msg) {
		var response = $("#results").empty();
		
		if (msg.totalResults != 0) {
			page = typeof page !== 'undefined' ?  parseInt(page) : 1;
			var startIndex = ((page - 1) * msg.resultsPerPage) + 1;
			var endIndex = Math.min(page * msg.resultsPerPage, msg.totalResults);
			var prevLink = msg.prevPageToken != null ? $('<a class="page" href="#">prev</a>').attr('pageNo', page - 1).attr('id', msg.prevPageToken) : 'prev';
			var nextLink = msg.nextPageToken != null ? $('<a class="page" href="#">next</a>').attr('pageNo', page + 1).attr('id', msg.nextPageToken) : 'next';
			var nav = $('<span class="nav"></span>').append(prevLink).append(' | ').append(nextLink);
			
			response.append($('<span></span>').append('Showing ' + startIndex + '-' + endIndex + ' of ' + msg.totalResults + ' videos'))
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
	executeSearch($(this).attr("pageno"), this.id);
	event.preventDefault();
}).ajaxStart(function(){
	$("#loader").show();
}).ajaxStop(function(){
	$("#loader").hide();
}).ready(function(){
	$("#search").click(function(){
		executeSearch();
	});

	$("#loader").hide();
	
	$("#term").keypress(function(event){
		if (event.keyCode == 13) {
			executeSearch();
		}
	}).focus();
	
	$("#results").hide();
});
