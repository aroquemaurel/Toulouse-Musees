
<%@ page import="aderfber.museumtoulouse.AskingVisit" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'askingVisit.label', default: 'AskingVisit')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-askingVisit" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-askingVisit" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list askingVisit">
			
				<g:if test="${askingVisitInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="askingVisit.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${askingVisitInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${askingVisitInstance?.beginPeriodDate}">
				<li class="fieldcontain">
					<span id="beginPeriodDate-label" class="property-label"><g:message code="askingVisit.beginPeriodDate.label" default="Begin Period Date" /></span>
					
						<span class="property-value" aria-labelledby="beginPeriodDate-label"><g:formatDate date="${askingVisitInstance?.beginPeriodDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${askingVisitInstance?.endPeriodDate}">
				<li class="fieldcontain">
					<span id="endPeriodDate-label" class="property-label"><g:message code="askingVisit.endPeriodDate.label" default="End Period Date" /></span>
					
						<span class="property-value" aria-labelledby="endPeriodDate-label"><g:formatDate date="${askingVisitInstance?.endPeriodDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${askingVisitInstance?.nbPeople}">
				<li class="fieldcontain">
					<span id="nbPeople-label" class="property-label"><g:message code="askingVisit.nbPeople.label" default="Nb People" /></span>
					
						<span class="property-value" aria-labelledby="nbPeople-label"><g:fieldValue bean="${askingVisitInstance}" field="nbPeople"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${askingVisitInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="askingVisit.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${askingVisitInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:askingVisitInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${askingVisitInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
