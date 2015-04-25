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