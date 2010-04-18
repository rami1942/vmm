<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="物理サーバの新規登録"/>
<tiles:put name="content" type="string">

<h1>物理サーバの新規登録</h1>
<html:errors/>

<hh:form config="${formConfig}"/>

<% /*
<br/>

<s:form method="POST" action="doCreate">
<table class="std">
<tr>
	<th>サーバ名</th>
	<td><html:text property="name"/></td>
</tr>
<tr>
	<th>IPアドレス</th>
	<td><html:text property="ipAddress"/></td>
</tr>
<tr>
	<th>ユーザ名</th>
	<td><html:text property="userName"/></td>
</tr>
<tr>
	<th>パスワード</th>
	<td><html:password property="password"/></td>
</tr>
<tr>
	<th>パスワード(確認)</th>
	<td><html:password property="passwordConfirm"/></td>
</tr>
<tr>
	<th>コア数</th>
	<td><html:text property="core"/></td>
</tr>
<tr>
	<th>メモリ(MB)</th>
	<td><html:text property="memory"/></td>
</tr>
<tr>
	<th>備考</th>
	<td><html:textarea property="description" /></td>
</tr>
</table>
<p>
<input type="submit" name="submit" value="登録"/>
</p>
</s:form>
*/ %>

</tiles:put>
</tiles:insert>