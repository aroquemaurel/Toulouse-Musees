<%@ page import="aderfber.museumtoulouse.Manager" %>



<div class="fieldcontain ${hasErrors(bean: managerInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="manager.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${managerInstance?.name}"/>

</div>

