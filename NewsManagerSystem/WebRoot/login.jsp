<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新闻中国</title>
<link href="CSS/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
<script type="text/javascript">
	function login() {

		var name = document.getElementById("uname").value;
		var pwd = document.getElementById("upwd").value;
		if (name == "") {
			alert("用户名为空");
			return;
		} else if (pwd == "") {
			alert("密码为空");
		} else {
			doAjax();
		}
	}
	function doAjax() {

		var name = document.getElementById("uname").value;
		var pwd = document.getElementById("upwd").value;
		var xhr = getXhr();//创建ajax核心对象
		xhr.open('post', 'login.do', true);
		xhr.setRequestHeader("content-type",
				"application/x-www-form-urlencoded");
		xhr.send("name=" + name + "&pwd=" + pwd);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var str = xhr.responseText;
				if (str == "true") {
					document.getElementById("top_login").innerHTML = "欢迎"
							+ name
							+ "登录&nbsp;&nbsp;&nbsp;<a href='admin.do?page=1'>进入管理员界面</a>";
				} else {
					document.getElementById("error").innerHTML = "用户名或密码错误";
				}
			}
		};
	}
	$(function() {
		$.get('findAlltopic.do',
				function(data) {
					$('#topic').empty();
					for (var i = 0; i < data.length; i++) {
						$('#topic').append("&#160;&#160;&#160;");
						$('#topic').append(
								"<a href='loginnews.do?ntid=" + data[i].tid
										+ "&page=1'><b>" + data[i].tname
										+ "</b> </a>");
					}
					$('#topic').append("&#160;&#160;&#160;");
					$('#topic').append("<a href='loginnews.do?ntid=null&page=1'><b>所有</b></a>");
				}, 'json');
	});
	if (${ntid} != null && ${ntid} != ""){
		loginTopicNews();
	}else {
		loginNews();
	}
	function loginTopicNews() {
		$.get('loginTopicNews.do?ntid=${ntid}&page=${page}', function(data) {
			$('#news').empty();
			for (var i = 0; i < data.length; i++) {
				if (i % 5 == 0 && i != 0) {
					$('#news').append("<li class='space'></li>");
				}
				$('#news').append(
						"<li><a href='#'>" + data[i].ntitle + "</a><span>"
								+ data[i].ncreatedate + "</span>" + "</li>");
			}
		}, 'json');
	}
	function loginNews() {
		$.get('loginnewsfind.do?page=${page}', function(data) {
			$('#news').empty();
			for (var i = 0; i < data.length; i++) {
				if (i % 5 == 0 && i != 0) {
					$('#news').append("<li class='space'></li>");
				}
				$('#news').append(
						"<li><a href='#'>" + data[i].ntitle + "</a><span>"
								+ data[i].ncreatedate + "</span>" + "</li>");
			}
		}, 'json');
	}
	
	$(function() {
		$.get('guoneiFind.do',
				function(data) {
					$('#guoneiNews').empty();
					for (var i = 0; i < data.length; i++) {
						$('#guoneiNews').append("<li><a href='#'><b>"+data[i].ntitle+"</b> </a></li>");
					}
				}, 'json');
	});
	
	$(function() {
		$.get('guojiFind.do',
				function(data) {
					$('#guojiNews').empty();
					for (var i = 0; i < data.length; i++) {
						$('#guojiNews').append("<li><a href='#'><b>"+data[i].ntitle+"</b> </a></li>");
					}
				}, 'json');
	});
	
	$(function() {
		$.get('yuleFind.do',
				function(data) {
					$('#yuleNews').empty();
					for (var i = 0; i < data.length; i++) {
						$('#yuleNews').append("<li><a href='#'><b>"+data[i].ntitle+"</b> </a></li>");
					}
				}, 'json');
	});
</script>
</head>
<body>
	<div id="header">
		<div id="top_login">
			<label> 登录名 </label> <input type="text" id="uname" value=""
				class="login_input" /> <label> 密&#160;&#160;码 </label> <input
				type="password" id="upwd" value="" class="login_input" /> <input
				type="button" class="login_sub" value="登录" onclick="login()" /> <label
				id="error"> </label> <img src="Images/friend_logo.gif" alt="Google"
				id="friend_logo" />
		</div>
		<div id="nav">
			<div id="logo">
				<img src="Images/logo.jpg" alt="新闻中国" />
			</div>
			<div id="a_b01">
				<img src="Images/a_b01.gif" alt="" />
			</div>
			<!--mainnav end-->
		</div>
	</div>
	<div id="container">
		<div class="sidebar">
			<h1>
				<img src="Images/title_1.gif" alt="国内新闻" />
			</h1>
			<div class="side_list">
				<ul id="guoneiNews">
				</ul>
			</div>
			<h1>
				<img src="Images/title_2.gif" alt="国际新闻" />
			</h1>
			<div class="side_list">
				<ul id="guojiNews">
				</ul>
			</div>
			<h1>
				<img src="Images/title_3.gif" alt="娱乐新闻" />
			</h1>
			<div class="side_list">
				<ul id="yuleNews">
				</ul>
			</div>
		</div>
		<div class="main">
			<div class="class_type">
				<img src="Images/class_type.gif" alt="新闻中心" />
			</div>
			<div class="content">
				<ul class="class_date" id="topic">

				</ul>
				<ul class="classlist" id="news">

				</ul>
				当前页数:[${page}/${totalpage}]&nbsp; <a
					href="loginnews.do?ntid=${ntid}&page=${page-1}">上一页</a> <a
					href="loginnews.do?ntid=${ntid}&page=${page+1}">下一页</a> <a
					href="loginnews.do?ntid=${ntid}&page=${totalpage}">末页</a>
			</div>

			<div class="picnews">
				<ul>
					<li><a href="#"><img src="Images/Picture1.jpg" width="249"
							alt="" /> </a><a href="#">郑州99栋楼被炸药撂平</a></li>
					<li><a href="#"><img src="Images/Picture2.jpg" width="249"
							alt="" /> </a><a href="#">辽宁岫岩暴雨 村民：没见过这么大的水</a></li>
					<li><a href="#"><img src="Images/Picture3.jpg" width="249"
							alt="" /> </a><a href="#">韩国西红柿节：83吨西红柿中藏金戒指</a></li>
					<li><a href="#"><img src="Images/Picture4.jpg" width="249"
							alt="" /> </a><a href="#">安徽一黑人男子寻衅滋事 被民警拘捕</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="friend">
		<h1 class="friend_t">
			<img src="Images/friend_ico.gif" alt="合作伙伴" />
		</h1>
		<div class="friend_list">
			<ul>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
			</ul>
		</div>
	</div>
	<div id="footer">
		<p class="">
			24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a>
			&#160;&#160;&#160;&#160; 新闻热线：010-627488888 <br />
			文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱： <a href="#">jubao@jb-aptech.com.cn</a>
		</p>
		<p class="copyright">
			Copyright &copy; 1999-2009 News China gov, All Right Reserver <br />
			新闻中国 版权所有
		</p>
	</div>
</body>
</html>

