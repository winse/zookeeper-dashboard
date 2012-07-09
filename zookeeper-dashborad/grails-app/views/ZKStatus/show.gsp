
<%@ page import="com.winse.zookeeper.dashboard.ZKStatus" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ZKStatus.label', default: 'ZKStatus')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ZKStatus" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<g:render template="/navi"/>
			</ul>
		</div>
		<div class="nav">
			<ul>
				<g:each in="${com.winse.zookeeper.dashboard.ZKStatusController.CMDS}" status="i" var="cmd">
					<li><g:link action="show" id="${cmd}">${cmd}</g:link></li>
				</g:each>
			</ul>
		</div>
		<div id="show-ZKStatus" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /> : ${cmd}</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:if test="${ZKStatusInstance?.stat}">
			<div class="property-list ZKSource">
				<span class="property-value" aria-labelledby="stat-label">${ZKStatusInstance.stat}</span>
			</div>
			</g:if>
		</div>
	</body>
</html>
