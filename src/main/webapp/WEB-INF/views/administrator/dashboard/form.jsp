<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.general-indicators" />
</h2>

<acme:form readonly="true">
	<acme:form-double
		code="administrator.dashboard.form.label.number-of-public-task"
		path="numberOfPublicTask" />
	<acme:form-double
		code="administrator.dashboard.form.label.number-of-private-task"
		path="numberOfPrivateTask" />
	<acme:form-double
		code="administrator.dashboard.form.label.number-of-finish-task"
		path="numberOfFinishTask" />
	<acme:form-integer
		code="administrator.dashboard.form.label.number-of-not-finish-task"
		path="numberOfNotFinishTask" />
	<acme:form-integer
		code="administrator.dashboard.form.label.minimum-workload"
		path="minimumWorkload" />
	<acme:form-integer
		code="administrator.dashboard.form.label.maximum-workload"
		path="maximumWorkload" />
	<acme:form-double
		code="administrator.dashboard.form.label.average-workload"
		path="averageWorkload" />
	<acme:form-double
		code="administrator.dashboard.form.label.deviation-workload"
		path="deviationWorkload" />
	<acme:form-double
		code="administrator.dashboard.form.label.average-execution-periods"
		path="averageExecutionPeriods" />
</acme:form>
