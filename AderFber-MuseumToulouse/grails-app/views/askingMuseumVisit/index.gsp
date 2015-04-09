
<%@ page import="aderfber.museumtoulouse.AskingMuseumVisit" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'askingMuseumVisit.label', default: 'AskingMuseumVisit')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-askingMuseumVisit" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-askingMuseumVisit" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="askingDate" title="${message(code: 'askingMuseumVisit.askingDate.label', default: 'Asking Date')}" />
					
						<th><g:message code="askingMuseumVisit.museum.label" default="Museum" /></th>
					
						<th><g:message code="askingMuseumVisit.askingVisit.label" default="Asking Visit" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${askingMuseumVisitInstanceList}" status="i" var="askingMuseumVisitInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${askingMuseumVisitInstance.id}">${fieldValue(bean: askingMuseumVisitInstance, field: "askingDate")}</g:link></td>
					
						<td>${fieldValue(bean: askingMuseumVisitInstance, field: "museum")}</td>
					
						<td>${fieldValue(bean: askingMuseumVisitInstance, field: "askingVisit")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${askingMuseumVisitInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
