<%@ page import="com.winse.zookeeper.dashboard.ZKNode" %>

<div class="fieldcontain ${hasErrors(bean: ZKNodeInstance, field: 'path', 'error')} ">
	<label for="path">
		<g:message code="ZKNode.path.label" default="Path" />
	</label>
	<g:textField name="path" value="${ZKNodeInstance?.path}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ZKNodeInstance, field: 'data', 'error')} ">
	<label for="data">
		<g:message code="ZKNode.data.label" default="Data" />
	</label>
	<g:textField name="data" value="${ZKNodeInstance?.data}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ZKNodeInstance, field: 'createMode', 'error')} required">
	<label for="createMode">
		<g:message code="ZKNode.createMode.label" default="Create Mode" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="createMode" from="${org.apache.zookeeper.CreateMode?.values()}" keys="${org.apache.zookeeper.CreateMode.values()*.name()}" required="" value="${ZKNodeInstance?.createMode?.name()}"/>
</div>

