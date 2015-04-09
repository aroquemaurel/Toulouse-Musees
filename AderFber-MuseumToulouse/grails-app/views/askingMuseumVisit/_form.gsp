<%@ page import="aderfber.museumtoulouse.AskingMuseumVisit" %>



<div class="fieldcontain ${hasErrors(bean: askingMuseumVisitInstance, field: 'askingDate', 'error')} required">
	<label for="askingDate">
		<g:message code="askingMuseumVisit.askingDate.label" default="Asking Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="askingDate" precision="day"  value="${askingMuseumVisitInstance?.askingDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: askingMuseumVisitInstance, field: 'museum', 'error')} required">
	<label for="museum">
		<g:message code="askingMuseumVisit.museum.label" default="Museum" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="museum" name="museum.id" from="${aderfber.museumtoulouse.Museum.list()}" optionKey="id" required="" value="${askingMuseumVisitInstance?.museum?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: askingMuseumVisitInstance, field: 'askingVisit', 'error')} required">
	<label for="askingVisit">
		<g:message code="askingMuseumVisit.askingVisit.label" default="Asking Visit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="askingVisit" name="askingVisit.id" from="${aderfber.museumtoulouse.AskingVisit.list()}" optionKey="id" required="" value="${askingMuseumVisitInstance?.askingVisit?.id}" class="many-to-one"/>

</div>

