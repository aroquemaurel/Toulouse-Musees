<h3>Retrouver ma demande de visite</h3>
<g:form name="askingVisit" url="[controller: 'askingVisit', action:'askingList', params:params]">
    <div class="row">
        <div class="form-group">
            <input required="required"
                   name="code"
                   id="code"
                   class="form-control input-lg"
                   placeholder="Code de la visite" tabindex="1" value="" type="number" min="0">
        </div>
    </div>
    <div class="form-group">
        <button class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;Chercher une demande de visite</button>
    </div>

</g:form>