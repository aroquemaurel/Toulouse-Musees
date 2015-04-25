<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Toulouse-Musees</title>
</head>
<body>
<div class="jumbotron">
    <h2>Bienvenue sur Toulouse-Musees</h2>
    <p style="font-size: 12pt">Ce site à pour but de faciliter  musées dans Toulouse,
    de gérer une liste de musées favoris, ainsi que de pouvoir demander la visite d'un
    musée.
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
    <g:render template="/museum/list" model="[ museums : museums, museumsCount: museumsCount ]" />

</g>
</body>
</html>

