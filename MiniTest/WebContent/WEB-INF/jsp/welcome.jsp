<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Welcome Page">
  <s:layout-component name="body">
    <h1>Welcome Ye!</h1>
    <s:link beanclass="org.cosug.action.PersonListActionBean" event="showNames">Persons</s:link><br>
    <s:link beanclass="org.cosug.action.DocumentListActionBean" event="showDocuments">Documents</s:link><br>
    <s:link beanclass="org.cosug.action.TaskListActionBean" event="showTasks">Tasks</s:link><br>
  </s:layout-component>
</s:layout-render>