<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="taskId"/>

		<acme:form-textbox code="anonymous.task.label.title" path="title" readonly="true"/>
		<acme:form-textbox code="anonymous.task.label.description" path="description" readonly="true"/>
	<acme:form-textarea code="anonymous.task.label.workload" path="workload" readonly="true"/>
	<acme:form-textarea code="anonymous.task.start" path="start" readonly="true"/>
	<acme:form-textarea code="anonymous.task.end" path="end" readonly="true"/>
	<acme:form-textarea code="anonymous.task.label.link" path="link" readonly="true"/>			
		
	<acme:form-return code="anonymous.task.button.return"/>	
</acme:form>

