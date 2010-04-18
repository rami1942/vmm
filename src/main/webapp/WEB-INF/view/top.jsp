<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="Content-Style-Type" content="text/css" />
  <meta http-equiv="Content-Script-Type" content="text/JavaScript" />
  <link href="css/site.css" rel="stylesheet" type="text/css" media="screen,projection" charset="utf-8" />
  <title>トップページ</title>

</head>
<body>
<h1>トップメニュー</h1>
<ul>
<li><a href="physicalHost">物理サーバ</a></li>
<li><a href="hvStorage">データストア</a></li>
<li><a href="virtualMachine">仮想マシン</a></li>
</ul>

<ul>
<li><a href="physicalHost/allocation">サーバ割り当て状況</a></li>
</ul>

<ul>
<li><a href="pjRoom">プロジェクト情報</a></li>
<li><a href="pjUser">利用者情報</a></li>
</ul>

<ul>
<li><a href="auth/logout">ログアウト</a></li>
</ul>

<h1>履歴</h1>
<ul>
<li>03/17 PJ情報入力フォームを追加</li>
<li>02/26 使用率を簡易グラフ表示に変更</li>
<li>02/26 物理マシンを選択した時にその上で動いているマシンを表示する用に修正</li>
<li>02/24 データストアの最終更新時刻が表示されるようになった</li>
<li>02/24 負荷状況でVM割り当てのない物理マシンも表示するようにした</li>
<li>02/24 物理マシンのコア数・メモリはハイパバイザーから取得するようにした</li>
</ul>


<hr />

</body>
</html>
