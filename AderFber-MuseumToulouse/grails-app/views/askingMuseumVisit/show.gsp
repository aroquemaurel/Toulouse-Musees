
<%@ page import="aderfber.museumtoulouse.AskingMuseumVisit" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'askingMuseumVisit.label', default: 'AskingMuseumVisit')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-askingMuseumVisit" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-askingMuseumVisit" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list askingMuseumVisit">
			
				<g:if test="${askingMuseumVisitInstance?.askingDate}">
				<li class="fieldcontain">
					<span id="askingDate-label" class="property-label"><g:message code="askingMuseumVisit.askingDate.label" default="Asking Date" /></span>
					
						<span class="property-value" aria-labelledby="askingDate-label"><g:formatDate date="${askingMuseumVisitInstance?.askingDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${askingMuseumVisitInstance?.museum}">
				<li class="fieldcontain">
					<span id="museum-label" class="property-label"><g:message code="askingMuseumVisit.museum.label" default="Museum" /></span>
					
						<span class="property-value" aria-labelledby="museum-label"><g:link controller="museum" action="show" id="${askingMuseumVisitInstance?.museum?.id}">${askingMuseumVisitInstance?.museum?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${askingMuseumVisitInstance?.askingVisit}">
				<li class="fieldcontain">
					<span id="askingVisit-label" class="property-label"><g:message code="askingMuseumVisit.askingVisit.label" default="Asking Visit" /></span>
					
						<span class="property-value" aria-labelledby="askingVisit-label"><g:link controller="askingVisit" action="show" id="${askingMuseumVisitInstance?.askingVisit?.id}">${askingMuseumVisitInstance?.askingVisit?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:askingMuseumVisitInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${askingMuseumVisitInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
