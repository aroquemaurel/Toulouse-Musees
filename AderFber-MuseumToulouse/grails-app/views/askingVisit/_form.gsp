<%@ page import="aderfber.museumtoulouse.AskingVisit" %>



<div class="fieldcontain ${hasErrors(bean: askingVisitInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="askingVisit.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="code" type="number" value="${askingVisitInstance.code}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: askingVisitInstance, field: 'beginPeriodDate', 'error')} required">
	<label for="beginPeriodDate">
		<g:message code="askingVisit.beginPeriodDate.label" default="Begin Period Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="beginPeriodDate" precision="day"  value="${askingVisitInstance?.beginPeriodDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: askingVisitInstance, field: 'endPeriodDate', 'error')} required">
	<label for="endPeriodDate">
		<g:message code="askingVisit.endPeriodDate.label" default="End Period Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endPeriodDate" precision="day"  value="${askingVisitInstance?.endPeriodDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: askingVisitInstance, field: 'nbPeople', 'error')} required">
	<label for="nbPeople">
		<g:message code="askingVisit.nbPeople.label" default="Nb People" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nbPeople" type="number" min="0" value="${askingVisitInstance.nbPeople}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: askingVisitInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="askingVisit.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${askingVisitInstance.constraints.status.inList}" required="" value="${fieldValue(bean: askingVisitInstance, field: 'status')}" valueMessagePrefix="askingVisit.status"/>

</div>

