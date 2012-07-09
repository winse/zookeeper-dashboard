
<%@ page import="com.winse.zookeeper.dashboard.ZKSource" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ZKSource.label', default: 'ZKSource')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ZKSource" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<g:render template="/navi"/>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-ZKSource" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list ZKSource">
				<g:if test="${ZKSourceInstance?.servers}">
				<li class="fieldcontain">
					<span id="servers-label" class="property-label"><g:message code="ZKSource.servers.label" default="Servers" /></span>
					<span class="property-value" aria-labelledby="servers-label"><g:fieldValue bean="${ZKSourceInstance}" field="servers"/></span>
				</li>
				</g:if>
			
				<g:if test="${ZKSourceInstance?.timeOut}">
				<li class="fieldcontain">
					<span id="timeOut-label" class="property-label"><g:message code="ZKSource.timeOut.label" default="Time Out" /></span>
					<span class="property-value" aria-labelledby="timeOut-label"><g:fieldValue bean="${ZKSourceInstance}" field="timeOut"/></span>
				</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ZKSourceInstance?.id}" />
					<g:link class="edit" action="edit" id="${ZKSourceInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
