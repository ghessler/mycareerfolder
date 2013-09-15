<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Contact Information">
	<s:layout-component name="body">

		<s:form beanclass="stripesbook.action.ContactFormActionBean">
			<div>
				<s:hidden name="contact.id" />
			</div>

			<table class="form">
				<tr>
					<td>Email:</td>
					<td><s:text name="contact.email" /></td>
				</tr>
				<tr>
					<td>First name:</td>
					<td><s:text name="contact.firstName" /></td>
				</tr>
				<tr>
					<td>Last name:</td>
					<td><s:text name="contact.lastName" /></td>
				</tr>
				<tr>
					<td>Phone number:</td>
					<td><s:text name="contact.phoneNumber" /></td>
				</tr>
				<tr>
					<td>Birth date:</td>
					<td><s:text name="contact.birthDate" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					  <s:submit name="save" value="Save" /> 
					  <s:submit name="cancel" value="Cancel" />
					</td>
				</tr>
			</table>
		</s:form>

	</s:layout-component>
</s:layout-render>
