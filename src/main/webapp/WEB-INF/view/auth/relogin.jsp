<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="Content-Style-Type" content="text/css" />
  <meta http-equiv="Content-Script-Type" content="text/JavaScript" />
  <link href="../css/site.css" rel="stylesheet" type="text/css" media="screen,projection" charset="utf-8" />
  <title>ログイン</title>

</head>
<body>
<h1>ログイン</h1>
<p>
セッションがタイムアウトしたか、未認証状態でページにアクセスしようとしました。ログインしてください。
</p>
<html:errors/>
<s:form method="POST" action="doLogin">
<table>
<tr><th>ユーザID</th><td><html:text property="userid"/></td></tr>
<tr><th>パスワード</th><td><html:password property="password"/></td></tr>
</table>
<input type="submit" name="submit" value="ログイン"/>
</s:form>

<hr/>

</body>
</html>
