











<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="taskId" />

	<acme:form-textbox code="manag.task.label.title" path="title" />
	<acme:form-textarea code="manag.task.label.description"
		path="description" />
	<acme:form-workload code="manag.task.label.workload" path="workload" />
	<acme:form-moment code="manag.task.start" path="start" />
	<acme:form-moment code="manag.task.end" path="end" />
	<acme:form-url code="manag.task.label.link" path="link" />
	<acme:form-submit test="${command == 'create'}" code="manag.task.form.button.create" action="/manag/task/create"/>
	<acme:form-submit test="${command == 'show' && publica == 'false'}" code="manag.task.button.delete" action="/manag/task/delete"/>		
	<acme:form-submit test="${command == 'show' && publica == 'false'}" code="manag.task.button.update" action="/manag/task/update"/>	
	<acme:form-submit test="${command == 'show' && publica == 'false'}" code="manag.task.button.publish" action="/manag/task/publish"/>
	<acme:form-submit test="${command == 'delete'}" code="manag.task.button.delete" action="/manag/task/delete"/>		
	<acme:form-submit test="${command == 'update'}" code="manag.task.button.update" action="/manag/task/update"/>	
	<acme:form-submit test="${command == 'publish'}" code="manag.task.form.button.publish" action="/manag/task/publish"/>
	<acme:form-return code="manag.task.button.return" />


</acme:form>

