<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Document Form">
    <h3>Our Guy: ${actionBean.getDocument().getTitle()} with id ${actionBean.document.getId()}</h3>
	<s:layout-component name="body">
		<h1>Document</h1>
		<s:form beanclass="org.cosug.action.DocumentFormActionBean">
			<s:hidden name="document.id" />
			<table class="form">
				<tr>
					<td>Title:</td>
					<td><s:text name="document.title" /></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><s:text name="document.description" /></td>
				</tr>
				<tr>
					<td>File Name:</td>
					<td><s:text name="document.fileName" /></td>
				</tr>
				<tr>
					<td>File Type:</td>
					<td><s:text name="document.fileType" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:submit name="saveDocument" value="Save" />
					    <s:submit name="cancel" value="Cancel" />
					</td>
				</tr>
			</table>
		</s:form>
	</s:layout-component>
</s:layout-render>