<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="物理サーバ情報の修正"/>
<tiles:put name="content" type="string">

<h1>物理サーバ情報の修正</h1>
<p>注: パスワードを空欄にした場合には以前の値が保持されます</p>

<html:errors/>

<hh:form config="${formConfig}"/>

</tiles:put>
</tiles:insert>