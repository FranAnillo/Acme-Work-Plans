<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="taskId" />

	<acme:form-textbox code="manager.task.label.title" path="title" />
	<acme:form-textbox code="manager.task.label.description"
		path="description" />
	<acme:form-textarea code="manager.task.label.workload" path="workload" />
	<acme:form-textarea code="manager.task.start" path="start" />
	<acme:form-textarea code="manager.task.end" path="end" />
	<acme:form-textarea code="manager.task.label.link" path="link" />
	<acme:form-checkbox code="manager.task.label.publica" path="publica"/>
	<acme:form-submit code="manager.task.form.button.create"
		action="/manager/task/create" />
	<acme:form-return code="manager.task.button.return" />

	<acme:form-submit code="manager.task.button.delete" action="/manager/task/delete"/>

</acme:form>

