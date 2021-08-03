<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.list.label.author" path="author"/>
	<acme:form-textbox code="anonymous.shout.list.label.text" path="text"/>
	<acme:form-textbox code="anonymous.shout.list.label.info" path="info"/>

	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
  	<acme:form-return code="anonymous.shout.form.button.return"/>

</acme:form>