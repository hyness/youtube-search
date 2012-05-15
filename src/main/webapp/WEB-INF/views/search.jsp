<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Video Search</title>
		<link type="text/css" href="styles/search.css" rel="stylesheet" />
		<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/redmond/jquery-ui.css" rel="stylesheet" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
		<script src="js/search.js"></script>
	</head>
	<body>
		<section id="searchForm">
			<label for="term">Search for videos:</label>
			<input type="text" id="term" />    
		    <input type="submit" id="search" value="search" />
		    <img id="loader" alt="loader" src="img/ajax-loader.gif" />
		</section>
    
		<section id="results"></section>
	</body>
</html>