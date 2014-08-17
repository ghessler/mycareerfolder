<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Person List Page">
  <s:layout-component name="body">
    <h1>Persons</h1>
    <s:link beanclass="org.cosug.action.WelcomeActionBean" event="welcome">Home</s:link>
    <s:link beanclass="org.cosug.action.PersonFormActionBean" event="createPerson">Create Person</s:link>
    <br/>
    <d:table name="${actionBean.persons}" id="person" requestURI="" defaultsort="1">
      <d:column title="Name" property="name" sortable="true"/>
      <d:column title="Age" property="age" sortable="true"/>
       <d:column title="Action">
         <s:link beanclass="org.cosug.action.PersonFormActionBean" event="editPerson">Edit
           <s:param name="id" value="${person.id}"/>
         </s:link> |
         <s:link beanclass="org.cosug.action.PersonFormActionBean" event="deletePerson" onclick="return confirm('Delete ${person.name}?')">Delete
           <s:param name="id" value="${person.id}"/>
         </s:link>
       </d:column>
    </d:table>
  </s:layout-component>
</s:layout-render>