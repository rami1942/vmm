<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="PJルーム"/>
<tiles:put name="content" type="string">
<h1>PJルーム</h1>
<html:errors/>
<hh:notify/>

<hh:form config="${createConfig}"/>

</tiles:put>
</tiles:insert>
