<%@ page import="aderfber.museumtoulouse.Manager" %>



<div class="fieldcontain ${hasErrors(bean: managerInstance, field: 'firstname', 'error')} required">
	<label for="firstname">
		<g:message code="manager.firstname.label" default="Firstname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstname" required="" value="${managerInstance?.firstname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: managerInstance, field: 'lastname', 'error')} required">
	<label for="lastname">
		<g:message code="manager.lastname.label" default="Lastname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastname" required="" value="${managerInstance?.lastname}"/>

</div>

