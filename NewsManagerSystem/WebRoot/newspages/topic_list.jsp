<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理后台</title>
<link href="../CSS/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
<script type="text/javascript">
	$(function() {
		$.get('findtopic.do?page=${page}',function(data) {
							$('#topiclist').empty();
							for (var i = 0; i < data.length; i++) {
									$('#topiclist').append("<li>"+ data[i].tname+"&nbsp;&nbsp;&nbsp;&nbsp;"
											+"<b>该主题下共有新闻："+data[i].newscount+"条</b>"
											+ "<span><a href='topic_modify.jsp?tid="+data[i].tid+"&tname="+data[i].tname
											+"'>修改</a>&nbsp;&nbsp;&nbsp;<a href='topicDel.do?tid="+ data[i].tid
											+ "'onclick='return clickdel();'>删除</a></span></li>");
							}
						}, 'json');
	});
	function clickdel() {
		return confirm("该操作会将分类下的所有新闻一起删除，确定删除吗");
	}
</script>
</head>


<body>
	<div id="header">
		<div id="welcome">欢迎使用新闻管理系统！</div>
		<div id="nav">
			<div id="logo">
				<img src="../Images/logo.jpg" alt="新闻中国" />
			</div>
			<div id="a_b01">
				<img src="../Images/a_b01.gif" alt="" />
			</div>
		</div>
	</div>

	<div id="admin_bar">
		<div id="status">
			管理员 &#160;&#160;&#160;&#160; <a href="../loginnews.do?ntid=null&page=1">退出</a>
		</div>
		<div id="channel"></div>
	</div>
	<div id="main">
		<div id="opt_list">
			<ul>
				<li><a href="news_add.jsp">添加新闻</a></li>
            <li><a href="../admin.do?page=1">查找新闻</a></li>
            <li><a href="topic_add.jsp">添加主题</a></li>
            <li><a href="topicpage.do?page=1">编辑主题</a></li>
			</ul>
		</div>
		<div id="opt_area">
			<ul class="classlist" id="topiclist">

			</ul>
			<p align="right">
					当前页数:[${page}/${totalpage}]&nbsp;
					<a href="topicpage.do?page=${page-1}">上一页</a>
					<a href="topicpage.do?page=${page+1}">下一页</a>
					<a href="topicpage.do?page=${totalpage}">末页</a>
			</p>
		</div>
	</div>
	<div id="site_link">
		<a href="#">关于我们</a><span>|</span> <a href="#">Aboue Us</a><span>|</span>
		<a href="#">联系我们</a><span>|</span> <a href="#">广告服务</a><span>|</span>
		<a href="#">供稿服务</a><span>|</span> <a href="#">法律声明</a><span>|</span>
		<a href="#">招聘信息</a><span>|</span> <a href="#">网站地图</a><span>|</span>
		<a href="#">留言反馈</a>
	</div>
	<div id="footer">
		<p class="">
			24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a>
			&#160;&#160;&#160;&#160; 新闻热线：010-627488888<br />
			文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱：<a href="#">jubao@jb-aptech.com.cn</a>
		</p>
		<p class="copyright">
			Copyright &copy; 1999-2009 News China gov, All Right Reserver<br />
			新闻中国 版权所有
		</p>
	</div>
</body>
</html>

