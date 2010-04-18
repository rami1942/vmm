<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="PJルーム"/>
<tiles:put name="content" type="string">
<h1>PJルーム</h1>
<html:errors/>
<hh:notify/>
<hh:showBean config="${editConfig}"/>
<html:link href="edit?id=${id}">編集</html:link>

<h1>所属仮想マシン</h1>
<hh:listTable config="${vmListConfig}"/>
</tiles:put>
</tiles:insert>
