<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Toulouse-Musees</title>
</head>
<body>
<div class="jumbotron">
    <h2>Retrouver une demande de visite</h2>
    <p style="font-size: 12pt">Il vous suffit d'avoir le code de la demande pour retrouver son avancement !
    </p>
</div>

<g id="controller-list" role="navigation">
    <div class="row">
        <g:if test="${errors}">
            <div class="alert alert-danger">
                <g:each in="${errors}" var="${error}">
                    <g:if test="${error} == 'ASKING_CODE'">
                        <b>Erreur</b> Le numéro de visite est inconnu
                    </g:if>
                </g:each>
            </div>
        </g:if>
        <g:if test="${askingVisit}">
            <h3>Demande de visite pour « ${askingVisit.museum.name} »</h3>
            <p><em>Demande du <g:formatDate format="dd/MM/yyyy à HH:mm" date="${askingVisit.askingDate}"/></em></p>
            <ul>
                <li><b>Date de début</b> <g:formatDate format="dd/MM/yyyy" date="${askingVisit.askingVisit.beginPeriodDate}"/></li>
                <li><b>Date de fin</b> <g:formatDate format="dd/MM/yyyy" date="${askingVisit.askingVisit.endPeriodDate}"/></li>
                <li><b>Nombre de personnes</b> ${askingVisit.askingVisit.nbPeople}</li>
                <li><b>Statut</b> ${askingVisit.askingVisit.statusToString()}</li>
            </ul>
        </g:if>
        <g:else>
            <div class="col-md-7 ">
                <g:render template="askingListVisitForm" />
            </div>
        </g:else>
    </div>
</div>

</g>
</body>
</html>

