<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="taskId" />

	<acme:form-textbox code="manag.task.label.title" path="title" />
	<acme:form-textbox code="manag.task.label.description"
		path="description" />
	<acme:form-textbox code="manag.task.label.workload" path="workload" />
	<acme:form-textbox code="manag.task.start" path="start" />
	<acme:form-textbox code="manag.task.end" path="end" />
	<acme:form-textbox code="manag.task.label.link" path="link" />
	<acme:form-checkbox code="manag.task.label.publica" path="publica"/>
	<acme:form-submit test="${command == 'create'}" code="manag.task.form.button.create" action="/manag/task/create"/>
	<acme:form-submit test="${command == 'delete'}" code="manag.task.form.button.delete" action="/manag/task/create"/>		
		
	<acme:form-return code="manag.task.button.return" />


</acme:form>

