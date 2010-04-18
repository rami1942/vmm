<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="物理サーバ情報"/>
<tiles:put name="content" type="string">

<h1>物理サーバ情報</h1>
<html:errors/>
<hh:showBean config="${formConfig}"/>
<p>
<a href="edit?id=${id}">サーバ情報の編集</a>
</p>
<h1>関連仮想マシン</h1>
<hh:listTable config="${childConfig}"/>

</tiles:put>
</tiles:insert>