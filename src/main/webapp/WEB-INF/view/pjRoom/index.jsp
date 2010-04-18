<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="PJルーム"/>
<tiles:put name="content" type="string">
<h1>PJルーム</h1>
<html:errors/>
<hh:notify/>

<html:link href="create">新規登録</html:link>
<hh:listTable config="${indexConfig}"/>

</tiles:put>
</tiles:insert>
