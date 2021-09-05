<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
    <acme:message code="administrator.personalization.form.title.censored-words"/>
</h2> 

<acme:form>
    <acme:form-textbox code="administrator.personalization.form.label.censored-words" path="censoredWords"/>
    <acme:form-submit test="${command == 'create'}"  code="administrator.personalization.form.button.create" action="/administrator/spam/create" />
    	<acme:form-submit test="${command == 'show'}" code="manag.task.button.delete" action="/administrator/spam/delete"/>		
    
</acme:form>