<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="リソース割り当て状況"/>
<tiles:put name="content" type="string">

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

<script type="text/javascript" src="../js/jquery-1.4.1.min.js"> </script>
<h1>リソース割り当て状況</h1>
<html:errors/>
<hh:notify/>


<hh:listTable config="${resourceConfig}" />
<div>
※ コア数については200%割り当てで換算
</div>

</tiles:put>
</tiles:insert>


