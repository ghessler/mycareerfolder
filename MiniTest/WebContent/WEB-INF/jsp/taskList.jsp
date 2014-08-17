<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Task List Page">
  <s:layout-component name="body">
    <h1>Tasks</h1>
    <s:link beanclass="org.cosug.action.WelcomeActionBean" event="welcome">Home</s:link>
    <s:link beanclass="org.cosug.action.TaskFormActionBean" event="createTask">Create Task</s:link>
    <br/>
    <d:table name="${actionBean.tasks}" id="task" requestURI="" defaultsort="1">
       <d:column title="Title" property="title" sortable="true"/>
       <d:column title="Description" property="description" sortable="true"/>
       <d:column title="Contact" property="contact" sortable="true"/>
       <d:column title="Starting Date" property="startDate" sortable="true"/>
       <d:column title="Ending Date" property="endDate" sortable="true"/>
       <d:column title="Action">
         <s:link beanclass="org.cosug.action.TaskFormActionBean" event="editTask">Edit
           <s:param name="id" value="${task.id}"/>
         </s:link> |
         <s:link beanclass="org.cosug.action.TaskFormActionBean" event="deleteTask" onclick="return confirm('Delete ${task.title}?')">Delete
           <s:param name="id" value="${task.id}"/>
         </s:link>
       </d:column>
    </d:table>
  </s:layout-component>
</s:layout-render>