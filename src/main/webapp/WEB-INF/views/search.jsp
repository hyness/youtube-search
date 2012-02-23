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
		var params = { allowScriptAccess: "always", allowFullScreen: "true", rel: 0, modestbranding: 1, theme: "light" };
		var atts = { id: "myytplayer" };
		swfobject.embedSWF("http://www.youtube.com/v/8tPnX7OPo0Q?enablejsapi=1&playerapiid=ytplayer&version=3",
		                   "ytapiplayer", "640", "360", "8", null, null, params, atts);
		
		function executeSearch(query, page) {
			$.ajax({
				type: "GET",
				url: "/api/search/" + query + "/" + page,
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
		
		$(document).ready(function(){
			$("#loader").ajaxStart(function(){
				$(this).show();
			}).ajaxStop(function(){
				$(this).hide();
			}).hide();
			
			$("#search").click(function(){
				executeSearch($("#term").val(), 1);
			});
			
			$("#term").keypress(function(event){
				if (event.keyCode == 13) {
					executeSearch($("#term").val(), 1);
				}
			}).focus();
			
			$(document).on("click", "a.dynamiclink", function(event){
				$("#myytplayer").get(0).loadVideoById(this.id);
				event.preventDefault();
			}).on("click", "a.page", function(event){
				executeSearch($("#term").val(), this.id);
				event.preventDefault();
			});
		});
    </script>
    
	<div id="ytapiplayer">
	  You need Flash player 8+ and JavaScript enabled to view this video.
	</div>

	<div id="searchForm">
		<input type="text" id="term" />    
	    <input type="submit" id="search" value="search" />
	    <img id="loader" alt="loader" src="img/ajax-loader.gif" />
	</div>	
    
    <div id="results" ></div>
</body>
</html>