<%@ page import="com.winse.zookeeper.dashboard.ZKSource" %>

<div class="fieldcontain ${hasErrors(bean: ZKSourceInstance, field: 'servers', 'error')} ">
	<label for="servers">
		<g:message code="ZKSource.servers.label" default="Servers" />
		
	</label>
	<g:textField name="servers" value="${ZKSourceInstance?.servers}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ZKSourceInstance, field: 'timeOut', 'error')} required">
	<label for="timeOut">
		<g:message code="ZKSource.timeOut.label" default="Time Out" />
	</label>
	<g:textField name="timeOut" required="" value="${ZKSourceInstance?.timeOut}"/> <span class="required-indicator">数字类型</span>
</div>

