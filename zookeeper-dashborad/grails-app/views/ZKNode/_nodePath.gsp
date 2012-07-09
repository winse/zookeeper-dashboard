<g:each in="${com.winse.zookeeper.dashboard.ZKNodeController.getParentAndName(ZKSourceNodePath)}" status="i" var="path">
<g:if test="${i==0}"><g:link action="list" params="${[id:ZKSourceInstance?.id, path:path]}">${path}</g:link></g:if><g:if test="${i!=0}">${path}</g:if>
</g:each>