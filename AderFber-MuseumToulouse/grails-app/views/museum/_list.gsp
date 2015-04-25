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
    <div class="pagination">
        <g:paginate total="${museumsCount}" action="doResearch" params="${params}"/>
    </div>
</g:if>
