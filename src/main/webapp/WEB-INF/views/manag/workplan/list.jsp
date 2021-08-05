<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="workplan.list.label.title" path="title" width="20%"/>
	<acme:list-column code="workplan.list.label.workload" path="workload" width="20%"/>
	<acme:list-column code="workplan.list.label.start" path="start" width="20%"/>
	<acme:list-column code="workplan.list.label.end" path="end" width="20%"/>
</acme:list>