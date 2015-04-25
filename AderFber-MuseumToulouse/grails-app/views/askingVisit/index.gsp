<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>Toulouse-Musees</title>
</head>
<body>
<div class="jumbotron">
	<h2>Demander à visiter un musée</h2>
	<p style="font-size: 12pt">Vous pouvez visiter les musées que vous souhaitez, il suffit de remplir le formulaire !
	</p>
</div>

<g id="controller-list" role="navigation">
	<div class="row">
		<div class="col-md-7 ">
			<g:render template="askingVisitForm" model="[ museum:museum  ]" />
		</div>
		</div>
		<div class="col-md-offset-1 col-md-4">
			<g:render template="/stars/list" model="[ stars : stars ]" />
		</div>
	</div>

</g>
</body>
</html>

