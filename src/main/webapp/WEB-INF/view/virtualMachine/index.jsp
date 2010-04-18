<%@ page pageEncoding="UTF-8" %>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="仮想マシン"/>
<tiles:put name="content" type="string">

<h1>仮想マシン</h1>
<html:errors/>
<div class="notice">
<html:messages id="notice" message="true">
  <bean:write name="notice" ignore="true"/>
</html:messages>
</div>

<hh:listTable config="${config}" />

<script type="text/javascript">
$("table.std").tableFilter({imagePath:'../images/icons', paging:false, pageLength:9999, sortOnLoad:0, sort:true});
</script>
</tiles:put>
</tiles:insert>
