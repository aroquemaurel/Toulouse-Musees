<g:if test="${stars}">
    <h3>Musées favoris  <span class="badge">${stars.size()}</span></h3>
    <ul class="list-group">
        <g:each var="star" in="${stars}">
            <li class="list-group-item">
                <g:link  controller="museum" action="show" params="[museumId: star.id]">
                    <i class="glyphicon glyphicon-star"></i> &nbsp;${star.name}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>
