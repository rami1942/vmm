<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="データストア"/>
<tiles:put name="content" type="string">
<h1>データストア</h1>
<style>
table.graph {
	border: solid 1px #000000;
	border-collapse: collapse;
}

table.graph td {
	height: 1ex;
}

table.graph td.bar {
	background-color: #0000ff;
}

</style>

<html:errors/>
<hh:notify/>
<p>
取得対象物理マシン : ${f:h(targetHost.name)}
</p>
<hh:listTable config="${config}" />

</tiles:put>
</tiles:insert>
