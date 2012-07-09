
<%@ page import="com.winse.zookeeper.dashboard.ZKNode" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ZKNode.label', default: 'ZKNode')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ZKNode" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<g:render template="/navi"/>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-ZKNode" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list ZKNode">
			
				<g:if test="${ZKNodeInstance?.path}">
				<li class="fieldcontain">
					<span id="path-label" class="property-label"><g:message code="ZKNode.path.label" default="Path" /></span>
					<span class="property-value" aria-labelledby="path-label">

<g:each in="${com.winse.zookeeper.dashboard.ZKNodeController.getParentAndName(ZKNodeInstance.path)}" status="i" var="path">
<g:if test="${i==0}"><g:link action="list" params="${[id:ZKSourceInstance?.id, path:path]}">${path}</g:link></g:if><g:if test="${i!=0}">${path}</g:if>
</g:each>
					
					</span>
				</li>
				</g:if>
			
				<g:if test="${ZKNodeInstance?.data}">
				<li class="fieldcontain">
					<span id="data-label" class="property-label"><g:message code="ZKNode.data.label" default="Data" /></span>
					<span class="property-value" aria-labelledby="data-label"><g:fieldValue bean="${ZKNodeInstance}" field="data"/></span>
				</li>
				</g:if>
				
				<g:if test="${ZKNodeInstance?.createMode}">
				<li class="fieldcontain">
					<span id="createMode-label" class="property-label"><g:message code="ZKNode.createMode.label" default="Create Mode" /></span>
					<span class="property-value" aria-labelledby="createMode-label"><g:fieldValue bean="${ZKNodeInstance}" field="createMode"/></span>
				</li>
				</g:if>
			
				<g:if test="${stat}">
				<li class="fieldcontain">
					<span id="path-label" class="property-label">stat</span>
					<span class="property-value" aria-labelledby="path-label">${stat}</span>
				</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ZKNodeInstance?.id}" />
					<g:hiddenField name="path" value="${ZKNodeInstance?.path}" />
					<g:link class="edit" action="edit"  params="${[id:ZKSourceInstance?.id, path:ZKNodeInstance?.path]}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
