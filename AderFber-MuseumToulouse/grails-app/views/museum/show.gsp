<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>Toulouse-Musees</title>
</head>
<body>
<div class="jumbotron">
	<h2>Voir un musée</h2>
	<p style="font-size: 12pt">Vous pouvez voir les informations du musée, et si vous le souhaitez demander à le visiter !
	</p>
</div>

<g id="controller-list" role="navigation">
	<div class="row">
		<div class="col-md-7 ">
			<g:render template="/museum/searchForm" model="[ postalCodes: postalCodes]" />
		</div>
		</div>
		<div class="col-md-offset-1 col-md-4">
			<g:render template="/stars/list" model="[ stars : stars ]" />
		</div>
	</div>
	<g:render template="/museum/show" model="[ museum : museum, isStared: stars*.id.contains(museum.id)]" />

</g>
</body>
</html>

