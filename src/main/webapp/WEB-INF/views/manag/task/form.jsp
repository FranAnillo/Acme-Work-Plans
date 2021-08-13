<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="taskId" />

	<acme:form-textbox code="manag.task.label.title" path="title" />
	<acme:form-textbox code="manag.task.label.description"
		path="description" />
	<acme:form-textarea code="manag.task.label.workload" path="workload" />
	<acme:form-textarea code="manag.task.start" path="start" />
	<acme:form-textarea code="manag.task.end" path="end" />
	<acme:form-textarea code="manag.task.label.link" path="link" />
	<acme:form-checkbox code="manag.task.label.publica" path="publica"/>
	<acme:form-submit code="manag.task.form.button.create"
		action="/manag/task/create" />
	<acme:form-return code="manag.task.button.return" />

	<acme:form-submit code="manag.task.button.delete" action="/manag/task/delete"/>

</acme:form>

