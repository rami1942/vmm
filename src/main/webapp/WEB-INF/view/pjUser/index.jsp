<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="PJ利用者"/>
<tiles:put name="content" type="string">
<h1>PJ利用者</h1>
<html:errors/>
<hh:notify/>

<p>
<html:link href="create">新規作成</html:link>
</p>

<hh:listTable config="${indexConfig}"/>

</tiles:put>
</tiles:insert>
