<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Search YouTube</title>
		<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/redmond/jquery-ui.css" rel="stylesheet" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
		<script src="js/search.js"></script>
	</head>
	<body>
		<div id="searchForm">
			<input type="text" id="term" />    
		    <input type="submit" id="search" value="search" />
		    <img id="loader" alt="loader" src="img/ajax-loader.gif" />
		</div>	
    
		<div id="results" ></div>
	</body>
</html>