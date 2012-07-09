
<%@ page import="com.winse.zookeeper.dashboard.ZKSource" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ZKSource.label', default: 'ZKServer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ZKSource" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<g:render template="/navi"/>
				<li><g:link class="list" controller="ZKStatus">4LetterWord</g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ZKSource" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<td>${message(code: 'ZKSource.servers.label', default: 'Servers')}</td>
						<td>${message(code: 'ZKSource.timeOut.label', default: 'Time Out')}</td>
						<td>Op</td>
					</tr>
				</thead>
				<tbody>
				<g:each in="${ZKSourceInstanceList}" status="i" var="ZKSourceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${ZKSourceInstance.id}">${fieldValue(bean: ZKSourceInstance, field: "servers")}</g:link></td>
						<td>${fieldValue(bean: ZKSourceInstance, field: "timeOut")}</td>
						<td><g:link action="connection" id="${ZKSourceInstance.id}">Conn...</g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ZKSourceInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
