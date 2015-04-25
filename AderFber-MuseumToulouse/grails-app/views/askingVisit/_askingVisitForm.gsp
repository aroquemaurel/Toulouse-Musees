<h3>Visiter le musée ${museum.name}</h3>
<g:form name="askingVisit" url="[controller: 'askingVisit', action:'askingVisit']">
    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-6">
            <div class="form-group">
                <div required="required" class="input-group date input-group-lg" id="beginDate">
                    <input data-date-format="MM/YYYY" tabindex="1" id="beginDate" name="beginDate" placeholder="Date de début" class="form-control input-lg" value="" type="text">
                    <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span>
                    </span>
                </div>
            </div>

        </div>
        <div class="col-xs-12 col-sm-6 col-md-6">
            <div class="form-group">
                <div required="required" class="input-group date input-group-lg" id="endDate">
                    <input data-date-format="MM/YYYY" tabindex="2" id="endDate" name="endDate" placeholder="Date de fin" class="form-control input-lg" value="" type="text">
                    <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span>
                    </span>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group">
            <input required="required"
                   name="numberOfPeople"
                   id="numberOfPeople"
                   class="form-control input-lg"
                   placeholder="Nombre de personnes" tabindex="2" value="" type="number" max="6" min="1">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-6">
    <div class="form-group">
        <button class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;Rechercher</button>
    </div>
</g:form>

<script type="text/javascript">
    $(document).ready(function() {
        $('#beginDate').datetimepicker({
            pickTime: false,
        });
        $('#endDate').datetimepicker({
            pickTime: false,
        });

        $('.selectpicker').selectpicker();
    });
</script>