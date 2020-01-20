function getPlayer(id) {
    var url = "//www.youtube.com/embed/" + id + "?rel=0&autoplay=1";
    return '<iframe class="embed-responsive-item" src="' + url + '" ' +
    	'webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>'
}

function showVideo(id, title) {
	$('#mediaModal .embed-responsive').html(getPlayer(id));
	$('#mediaModal').modal();
	$('#mediaModal').on('hidden.bs.modal', function () {
		$('#mediaModal .embed-responsive').html('');
	});
}

function executeSearch(page, token) {
	$.ajax({
		type: "GET",
		url: "search/" + $("#term").val() + "/" 
			+ ($("#hd").prop('checked') ? 'HIGH' : 'ANY') 
			+ (typeof token !== 'undefined' ? '/' + token : '')
	}).done(function(msg) {
		var response = $("#results").empty();
		
		if (msg.pageInfo.totalResults != 0) {
			page = typeof page !== 'undefined' ?  parseInt(page) : 1;
			var startIndex = ((page - 1) * msg.pageInfo.resultsPerPage) + 1;
			var endIndex = Math.min(page * msg.pageInfo.resultsPerPage, msg.pageInfo.totalResults);
			var prevLink = msg.prevPageToken != null ? $('<a class="page" href="#">prev</a>').attr('pageNo', page - 1).attr('id', msg.prevPageToken) : 'prev';
			var nextLink = msg.nextPageToken != null ? $('<a class="page" href="#">next</a>').attr('pageNo', page + 1).attr('id', msg.nextPageToken) : 'next';
			var nav = $('<span class="nav"></span>').append(prevLink).append(' | ').append(nextLink);
			
			response.append($('<span></span>').append('Showing ' + startIndex + '-' + endIndex + ' of ' + msg.pageInfo.totalResults + ' videos'))
				.append(nav.clone());
			var links = $('<ol></ol>').attr('start', startIndex);
			$(msg.items).each(function(){
				links.append($('<li></li>').append(
					$('<a class="dynamiclink" href="#"></a>').attr('id', this.id.id).html(this.snippet.title)
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
