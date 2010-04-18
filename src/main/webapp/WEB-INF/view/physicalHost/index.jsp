<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="物理サーバ"/>
<tiles:put name="content" type="string">

<script type="text/javascript" src="../js/jquery-1.4.1.min.js"> </script>
<h1>物理サーバ</h1>
<html:errors/>
<hh:notify/>

<p>
<a href="create">新規登録</a>
</p>

<hh:listTable config="${listConfig}" />

<script type="text/javascript">
$("table.std").tableFilter({imagePath:'../images/icons', paging:false, pageLength:9999, sortOnLoad:0, sort:true});
</script>

</tiles:put>
</tiles:insert>


