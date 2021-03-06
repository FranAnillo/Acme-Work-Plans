<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="manag.task.list.label.title" path="title" width="20%"/>
	<acme:list-column code="manag.task.list.label.workload" path="workload" width="20%"/>
	<acme:list-column code="manag.task.list.label.description" path="description" width="20%"/>
	<acme:list-column code="manag.task.list.label.start" path="start" width="20%"/>
	<acme:list-column code="manag.task.list.label.end" path="end" width="20%"/>
</acme:list>