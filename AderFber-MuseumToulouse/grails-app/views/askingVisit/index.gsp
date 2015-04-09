
<%@ page import="aderfber.museumtoulouse.AskingVisit" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'askingVisit.label', default: 'AskingVisit')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-askingVisit" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-askingVisit" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'askingVisit.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="beginPeriodDate" title="${message(code: 'askingVisit.beginPeriodDate.label', default: 'Begin Period Date')}" />
					
						<g:sortableColumn property="endPeriodDate" title="${message(code: 'askingVisit.endPeriodDate.label', default: 'End Period Date')}" />
					
						<g:sortableColumn property="nbPeople" title="${message(code: 'askingVisit.nbPeople.label', default: 'Nb People')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'askingVisit.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${askingVisitInstanceList}" status="i" var="askingVisitInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${askingVisitInstance.id}">${fieldValue(bean: askingVisitInstance, field: "code")}</g:link></td>
					
						<td><g:formatDate date="${askingVisitInstance.beginPeriodDate}" /></td>
					
						<td><g:formatDate date="${askingVisitInstance.endPeriodDate}" /></td>
					
						<td>${fieldValue(bean: askingVisitInstance, field: "nbPeople")}</td>
					
						<td>${fieldValue(bean: askingVisitInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${askingVisitInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
