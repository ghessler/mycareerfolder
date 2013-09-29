<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Contact List"> 
  <s:layout-component name="body"> 
    <s:messages />  
    
    <s:link beanclass="org.springsTeam.action.ContactFormActionBean">
      Create a New Contact
    </s:link>
    <br/>
    <d:table name="${actionBean.contacts}" id="contact" requestURI="" defaultsort="1"> 
      <d:column title="Last name" property="lastName" sortable="true" />  
      <d:column title="First name" property="firstName" sortable="true" />  
      <d:column title="Email" property="email" sortable="true" />  
      <d:column title="Action"> 
        <s:link beanclass="org.springsTeam.action.ContactFormActionBean" event="view"> 
          <s:param name="contactId" value="${contact.id}" />  
          View 
        </s:link> | 
        <s:link beanclass="org.springsTeam.action.ContactFormActionBean" event="form">
          <s:param name="contactId" value="${contact.id}"/>
          Update 
        </s:link> |
        <s:link beanclass="org.springsTeam.action.ContactListActionBean" event="delete" onclick="return confirm('Delete ${contact}?');">
          <s:param name="contactId" value="${contact.id}"/> 
          Delete 
        </s:link>
      </d:column> 
    </d:table>
  </s:layout-component> 
</s:layout-render>                                                                                                                                                                                                                                                                                                                    
