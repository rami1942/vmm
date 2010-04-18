<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/JavaScript" />
<link href="../css/site.css" rel="stylesheet" type="text/css" media="screen,projection" charset="utf-8" />

<style>
.offColor {
	background-color:#FDE9D9;
}
</style>

<script type="text/javascript" src="../js/jquery-packed.js"></script>
<script type="text/JavaScript" src="../js/jquery.cookies-packed.js"></script>
<script type="text/javascript" src="../js/prototypes-packed.js"></script>
<script type="text/javascript" src="../js/json-packed.js"></script>
<script type="text/javascript" src="../js/jquery.truemouseout-packed.js"></script>
<script type="text/javascript" src="../js/daemachTools-packed.js"></script>
<script type="text/javascript" src="../js/jquery.tableFilter-packed.js"></script>
<script type="text/javascript" src="../js/jquery.tableFilter.aggregator-packed.js"></script>
<script type="text/javascript" src="../js/jquery.tableFilter.columnStyle-packed.js"></script>


<title><tiles:getAsString name="title" /></title>
</head>
<body>
<table width="100%" id="layout_table">
  <tr><td colspan="2" id="layout_header"><tiles:insert page="/WEB-INF/view/common/header.jsp" /></td></tr>
  <tr>
    <td width="12%"　valign="top" id="layout_menu"><tiles:insert page="/WEB-INF/view/common/menu.jsp" /></td>
    <td id="layout_body"><tiles:insert attribute="content" /></td>
  </tr>
  <tr><td colspan="2" id="layout_footer"><tiles:insert page="/WEB-INF/view/common/footer.jsp" /></td></tr>
</table>
</body>
</html>