
<%@ page import="com.winse.zookeeper.dashboard.ZKNode" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ZKNode.label', default: 'ZKNode')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ZKNode" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<g:render template="/navi"/>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ZKNode" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /> : 
				<g:render template="nodePath"/>
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<th>${message(code: 'ZKNode.path.label', default: 'Path')}</th>
						<th>${message(code: 'ZKNode.data.label', default: 'Data')}</th>
						<th>${message(code: 'ZKNode.createMode.label', default: 'Create Mode')}</th>
						<th>op</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${ZKNodeInstanceList}" status="i" var="ZKNodeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>
							<g:link action="show" params="${[id:ZKSourceInstance?.id, path:ZKNodeInstance?.path]}">${fieldValue(bean: ZKNodeInstance, field: "path")}</g:link>
						</td>
						<td>${fieldValue(bean: ZKNodeInstance, field: "data")}</td>
						<td>${fieldValue(bean: ZKNodeInstance, field: "createMode")}</td>
						<td>
							<g:link class="edit" action="list" params="${[id:ZKSourceInstance?.id, path:ZKNodeInstance?.path]}">Get Children</g:link>
						</td>			
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ZKNodeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
