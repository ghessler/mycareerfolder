<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Task Form">
    <h3>Our Task: ${actionBean.getTask().getTitle()} with id ${actionBean.task.getId()}</h3>
	<s:layout-component name="body">
		<h1>Task</h1>
		<s:form beanclass="org.cosug.action.TaskFormActionBean">
			<s:hidden name="task.id" />
			<table class="form">
				<tr>
					<td>Title:</td>
					<td><s:text name="task.title" /></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><s:text name="task.description" /></td>
				</tr>
				<tr>
					<td>Contact:</td>
					<td><s:text name="task.contact" /></td>
				</tr>
				<tr>
					<td>Start Date:</td>
					<td><s:text name="task.startDate" /></td>
				</tr>
				<tr>
					<td>End Date:</td>
					<td><s:text name="task.endDate" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:submit name="saveTask" value="Save" />
					    <s:submit name="cancel" value="Cancel" />
					</td>
				</tr>
			</table>
		</s:form>
	</s:layout-component>
</s:layout-render>