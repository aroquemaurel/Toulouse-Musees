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
		<g:if test="${errors}">
		<div class="alert alert-danger">
			<g:each in="${errors}" var="${error}">
				<g:if test="${error} == 'UNVALID_ASKING_VISIT'">
					<b>Erreur</b> Une erreur a eu lieu pendant l'ajout de la demande de visite : votre formulaire est invalide.
				</g:if>
			</g:each>
		</div>
		</g:if>
		<div class="col-md-7 ">
			<g:render template="askingVisitForm" model="[ museum:museum, params:params  ]" />
		</div>
		</div>
		<div class="col-md-offset-1 col-md-4">
			<g:render template="/stars/list" model="[ stars : stars ]" />
		</div>
	</div>

</g>
</body>
</html>

