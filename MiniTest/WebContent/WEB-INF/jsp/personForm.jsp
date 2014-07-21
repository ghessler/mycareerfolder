<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Person Form">
    <h3>Our Guy: ${actionBean.getPerson().getName()} with id ${actionBean.person.getId()}</h3>
	<s:layout-component name="body">
		<h1>Person</h1>
		<s:form beanclass="org.cosug.action.PersonFormActionBean">
			<s:hidden name="person.id" />
			<table class="form">
				<tr>
					<td>Name:</td>
					<td><s:text name="person.name" /></td>
				</tr>
				<tr>
					<td>Age:</td>
					<td><s:text name="person.age" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:submit name="savePerson" value="Save" />
					    <s:submit name="cancel" value="Cancel" />
					</td>
				</tr>
			</table>
		</s:form>
	</s:layout-component>
</s:layout-render>