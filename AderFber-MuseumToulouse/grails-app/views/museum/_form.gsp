<%@ page import="aderfber.museumtoulouse.Museum" %>



<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="museum.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${museumInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'openingHours', 'error')} required">
	<label for="openingHours">
		<g:message code="museum.openingHours.label" default="Opening Hours" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="openingHours" required="" value="${museumInstance?.openingHours}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="museum.phone.label" default="Phone" />
		
	</label>
	<g:textField name="phone" value="${museumInstance?.phone}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'subwayAccess', 'error')} ">
	<label for="subwayAccess">
		<g:message code="museum.subwayAccess.label" default="Subway Access" />
		
	</label>
	<g:textField name="subwayAccess" value="${museumInstance?.subwayAccess}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'busAccess', 'error')} ">
	<label for="busAccess">
		<g:message code="museum.busAccess.label" default="Bus Access" />
		
	</label>
	<g:textField name="busAccess" value="${museumInstance?.busAccess}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="museum.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="address" name="address.id" from="${aderfber.museumtoulouse.Address.list()}" optionKey="id" required="" value="${museumInstance?.address?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'manager', 'error')} required">
	<label for="manager">
		<g:message code="museum.manager.label" default="Manager" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="manager" name="manager.id" from="${aderfber.museumtoulouse.Manager.list()}" optionKey="id" required="" value="${museumInstance?.manager?.id}" class="many-to-one"/>

</div>

