<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="taskId"/>

		<acme:form-textbox code="authenticated.task.label.title" path="title" readonly="true"/>
		<acme:form-textbox code="authenticated.task.label.description" path="description" readonly="true"/>
	<acme:form-textarea code="authenticated.task.label.workload" path="workload" readonly="true"/>
	<acme:form-textarea code="authenticated.task.start" path="start" readonly="true"/>
	<acme:form-textarea code="authenticated.task.label.end" path="end" readonly="true"/>	
	<acme:form-textarea code="authenticated.task.label.link" path="link" readonly="true"/>			
		
	<acme:form-return code="authenticated.task.button.return"/>	
</acme:form>

