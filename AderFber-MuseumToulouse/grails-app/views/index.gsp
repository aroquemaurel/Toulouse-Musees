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
                    <h3>Rechercher un musée</h3>

                    <g:form name="search" url="[controller: 'museum', action:'doResearch']">
                    <div class="row">
                        <div class="form-group">
                            <input name="name" id="name" class="form-control input-lg" placeholder="Nom ou partie du nom" tabindex="1" value="" type="text">
                        </div>
                    </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <!-- TODO put good postal codes. -->
                            <select style="display: none;" tabindex="2" class="selectpicker form-control input-lg" id="postalCode" name="postalCode">
                                <option disabled="" selected="">Code postal</option>
                            <g:each var="postalCode" in="${postalCodes}">
                                <option value="${postalCode}">${postalCode}</option>
                            </g:each>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input name="address" id="address" class="form-control input-lg" placeholder="Adresse" tabindex="3" value="" type="text">
                        </div>
                    </div>
                </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="form-group">
                        <button class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;Rechercher</button>
                    </div>
                </g:form>
                </div>
            </div>
                <div class="col-md-offset-1 col-md-4">
                    <g:if test="${stars}">
                    <h3>Musées favoris  <span class="badge">${stars.size()}</span></h3>
                    <ul class="list-group">
                    <g:each var="star" in="${stars}">
                        <li class="list-group-item"><g:link  controller="museum" action="doRsearchById" params="[museumId: star.id]">
                            <p class="glyphicon glyphicon-star"> &nbsp;${star.name}</p>
                        </g:link></li>
                    </g:each>
                    </ul>
                    </g:if>
                </div>

            </div>
                <g:if test="${museums}">

                        <div class="pagination">
                        <g:paginate total="${museumsCount}" action="doResearch" params="${params}"/>
                        </div>
                        <table class="table table-striped">
                            <tr>
                                <th class="col-md-3">Nom du musée</th>
                                <th class="col-md-2">Horaires d'ouverture</th>
                                <th class="col-md-2">Téléphone</th>
                                <th class="col-md-2">Adresse</th>
                                <th class="col-md-1">Accès bus</th>
                                <th class="col-md-2">Accès métro</th>
                                <th class="col-md-2">Gestionnaire</th>
                                <th class="col-md-2">Favoris</th>
                            </tr>
                            <g:each var="museum" in="${museums}" params="${params}">
                                <tr>
                                    <td>${museum.name}</td>
                                    <td>${museum.openingHours}</td>
                                    <td>${museum.phone}</td>
                                    <td>${museum.address.street} <br/>
                                        ${museum.address.postalCode} ${museum.address.city}
                                    </td>
                                    <td>${museum.busAccess}</td>
                                    <td>${museum.subwayAccess}</td>
                                    <td>${museum.manager.name}</td>
                                    <td>
                                        <g:if test="${!stars*.id.contains(museum.id)}">
                                        <g:link controller="star" action="addToStars" params="[museumId:museum.id]">
                                            <button class="btn btn-primary">
                                                <i class="glyphicon glyphicon-star"></i>&nbsp;Ajouter
                                            </button>
                                        </g:link>
                                        </g:if>
                                        <g:else>
                                        <g:link controller="star" action="removeToStars" params="[museumId:museum.id]">
                                            <button class="btn btn-default">
                                                <i class="glyphicon glyphicon-star-empty"></i>&nbsp;Retirer
                                            </button>
                                        </g:link>
                                        </g:else>
                                    </td>
                                </tr>
                            </g:each>
                        </table>
                    </div>
                    <div class="pagination">
                    <g:paginate total="${museumsCount}" action="doResearch" params="${params}"/>
                    </div>
                </g:if>
            </g>
	</body>
</html>

