<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Document List Page">
  <s:layout-component name="body">
    <h1>Documents</h1>
    <s:link beanclass="org.cosug.action.WelcomeActionBean" event="welcome">Home</s:link>
    <s:link beanclass="org.cosug.action.DocumentFormActionBean" event="createDocument">Create Document</s:link>
    <br/>
    <d:table name="${actionBean.documents}" id="document" requestURI="" defaultsort="1">
       <d:column title="Title" property="title" sortable="true"/>
       <d:column title="Description" property="description" sortable="true"/>
       <d:column title="File Name" property="fileName" sortable="true"/>
       <d:column title="File Type" property="fileType" sortable="true"/>
       <d:column title="Action">
         <s:link beanclass="org.cosug.action.DocumentFormActionBean" event="editDocument">Edit
           <s:param name="id" value="${document.id}"/>
         </s:link> |
         <s:link beanclass="org.cosug.action.DocumentFormActionBean" event="deleteDocument" onclick="return confirm('Delete ${document.title}?')">Delete
           <s:param name="id" value="${document.id}"/>
         </s:link>
       </d:column>
    </d:table>
  </s:layout-component>
</s:layout-render>