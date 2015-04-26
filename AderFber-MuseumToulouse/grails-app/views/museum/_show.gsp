<div>
    <h3>
        <g:if test="${isStared}"><i class="glyphicon glyphicon-star"></i></g:if>
        <g:else><i class="glyphicon glyphicon-star-empty"></i></g:else>
        &nbsp;${museum.name}
    </h3>

    <g:if test="${isStared}">
    <g:link controller="askingVisit" action="askingVisit" params="[museumId:museum.id]">
        <button class="btn btn-primary">
            <i class="glyphicon glyphicon-plus"></i>&nbsp;Demander à visiter ce musée
        </button>
    </g:link>
    </g:if>
    <h4>Contact</h4>
    <ul style="list-style-type: none">
    <li><b><i class="glyphicon glyphicon-phone-alt"></i>&nbsp;Téléphone</b> ${museum.phone}</li>
    <li><b>Gestionnaire</b> ${museum.manager.name}</li>
    </ul>

    <h4>S'y rendre</h4>
    <ul style="list-style-type: none">
    <li><b>Horaire d'ouverture</b> ${museum.openingHours}</li>
    <li><b>Adresse</b> ${museum.address.street} ${museum.address.postalCode} ${museum.address.city}</li>
    <li><b>Accès Bus</b> ${museum.busAccess}</li>
    <li><b>Accès Métro</b> ${museum.subwayAccess}</li>
    </ul>

</div>