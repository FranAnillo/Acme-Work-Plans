<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
    <acme:message code="administrator.threshold.form.title.threshold"/>
</h2> 

<acme:form>
    <acme:form-textarea code="administrator.threshold.form.label.threshold" path="thresholdword"/>
    <acme:form-submit code="administrator.threshold.form.button.update"
        action="/administrator/threshold/update" />
</acme:form>