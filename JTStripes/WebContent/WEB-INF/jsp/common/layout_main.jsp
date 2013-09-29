<%@page contentType="text/html;charset=ISO-8859-1" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-definition>
  <!DOCTYPE html>

  <html>
    <head>
      <title>${title}</title>
      <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css">
    </head>
    <body>
	  <div id="header">
		<span class="title">${title}</span>
	  </div>
	  <div id="body">
		<s:layout-component name="body" />
	  </div>
    </body>
  </html>  
  
</s:layout-definition>