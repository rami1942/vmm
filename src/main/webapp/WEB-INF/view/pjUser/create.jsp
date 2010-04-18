<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="利用者の新規登録"/>
<tiles:put name="content" type="string">

<h1>利用者の新規登録</h1>
<html:errors/>
<hh:notify/>

<hh:form config="${createConfig}"/>

</tiles:put>
</tiles:insert>