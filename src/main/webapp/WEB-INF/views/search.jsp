<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Search YouTube</title>
   </head>
  <body>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/swfobject/2.2/swfobject.js"></script>
    <script>
    	// Embed the YouTube player
		var params = { allowScriptAccess: "always" };
		var atts = { id: "myytplayer" };
		swfobject.embedSWF("http://www.youtube.com/v/8tPnX7OPo0Q?enablejsapi=1&playerapiid=ytplayer&version=3",
		                   "ytapiplayer", "425", "356", "8", null, null, params, atts);
		
		function executeSearch(query, page) {
			$.ajax({
				type: "GET",
				url: "/api/search/" + query + "/" + page,
			}).done(function(msg) {
				results = $("#results");
				results.empty();
				
				if (msg.data.totalItems != 0) {
					itemsPerPage = msg.data.itemsPerPage;
					totalItems = msg.data.totalItems;
					prevPage = parseInt(page) - 1;
					nextPage = parseInt(page) + 1;
					startIndex = msg.data.startIndex;
					endIndex = page * itemsPerPage < totalItems ? page * itemsPerPage : totalItems;
					prevLink = prevPage > 0 ? '<a id="'+prevPage+'" class="page" href="#">prev</a>' : 'prev';
					nextLink = nextPage * itemsPerPage < totalItems ? '<a id="'+nextPage+'" class="page" href="#">next</a>' : 'next';
					results.append('<p>' + startIndex + '-' + endIndex + ' of ' + totalItems + '</p>');
					results.append('<p>'+prevLink+' | '+nextLink+'</p>');
					$(msg.data.items).each(function(){
						results.append('<a id="'+this.id+'" class="dynamiclink" href="#">'+this.title+'</a><br />');
					});
				} else {
					results.append("<p>No Results</p>");
				}
			});
		}
		
		$(document).ready(function(){
			$("#search").click(function(){
				executeSearch($("#term").val(), 1);
			});
			
			term = $("#term");
			term.focus();
			term.keypress(function(e){
				if (e.keyCode == 13) {
					executeSearch($("#term").val(), 1);
				}
			});
			
			$(document).on("click", "a.dynamiclink", function(){
				player = $("#myytplayer").get(0);
				player.loadVideoById(this.id);
			});
			
			$(document).on("click", "a.page", function(){
				executeSearch($("#term").val(), this.id);
			});
		});
    </script>
    
	<div id="ytapiplayer">
	  You need Flash player 8+ and JavaScript enabled to view this video.
	</div>

	<div id="searchForm">
		<input type="text" id="term" />    
	    <input type="submit" id="search" value="search" />
	</div>	
    
    <div id="results"></div>
</body>
</html>