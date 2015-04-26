<%@ page import="aderfber.museumtoulouse.AskingVisit" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Toulouse-Musees</title>
</head>
<body>
<div class="jumbotron">
    <h2>Bienvenue sur Toulouse-Musees</h2>
    <p style="font-size: 12pt">Ce site à pour but de faciliter musées dans Toulouse,
    de gérer une liste de musées favoris, ainsi que de pouvoir demander la visite d'un
    musée.
    </p>
</div>

<g id="controller-list" role="navigation">
    <div class="row">
        <g:if test="${successes}">
            <div class="alert alert-success">
                <g:each in="${successes}" var="${success}">
                    <g:if test="${success instanceof AskingVisit}">
                        <b>Succès</b> Votre demande de visite à bien été prise en compte votre demande sera traitée prochainement.<br/>
                        Voici le code de votre demande : ${(success as AskingVisit).code}
                    </g:if>
                </g:each>
            </div>
        </g:if>
        <g:if test="${successToStars}">
            <div class="alert alert-success">
                        <b>Succès</b> Ce musée à bien été ajouté à vos favoris.
            </div>
        </g:if>
        <g:if test="${successUnstars}">
            <div class="alert alert-success">
                <b>Succès</b> Ce musée à bien été retiré de vos favoris.
            </div>
        </g:if>

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

