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
<title>添加主题--管理后台</title>
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
<link href="CSS/admin.css" rel="stylesheet" type="text/css" />
<%
	//Session验证
	Object name = session.getAttribute("uname");
	if (name == null) {
		response.sendRedirect("login.jsp");
	}
%>
<script type="text/javascript">
	$(function quoto() {
		$
				.post(
						'quoto.do?page=${page}',
						function(data) {
							$('#news').empty();
							for (var i = 0; i < data.length; i++) {
								$('#news')
										.append(
												"<li>"
														+ data[i].ntitle
														+ "<span>作者:"
														+ data[i].nauthor
														+ "&#160;&#160;&#160;&#160; <a href='update.do?nid="
														+ data[i].nid
														+ "'>修改</a>"
														+ "&#160;&#160;&#160;&#160;"
														+ "<a href='delete.do?nid="
														+ data[i].nid
														+ "' onclick='return clickdel()'>删除</a></span>"
														+ "</li>");
							}
						}, 'json');
	});
</script>
</head>
<body>
	<div id="header">
		<div id="welcome">欢迎使用新闻管理系统！</div>
		<div id="nav">
			<div id="logo">
				<img src="Images/logo.jpg" alt="新闻中国" />
			</div>
			<div id="a_b01">
				<img src="Images/a_b01.gif" alt="" />
			</div>
		</div>
	</div>

	<div id="admin_bar">
		<div id="status">
			管理员 &#160;&#160;&#160;&#160; <a href="loginnews.do?ntid=null&page=1">退出</a>
		</div>
		<div id="channel"></div>
	</div>
	<div id="main">
		<div id="opt_list">
			<ul>
				<li><a href="newspages/news_add.jsp">添加新闻</a></li>
				<li><a href="admin.do?page=1">查找新闻</a></li>
				<li><a href="newspages/topic_add.jsp">添加主题</a></li>
				<li><a href="newspages/topicpage.do?page=1">编辑主题</a></li>
			</ul>
		</div>
		<div id="opt_area">
			<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
			<script language="javascript">
				function clickdel(tid) {
					return confirm("删除请点击确认");
				}
			</script>
			<ul class="classlist" id="news">
				
			</ul>
			<p align="right">
					当前页数:[${page}/${totalpage}]&nbsp;
					<a href="admin.do?page=${page-1}">上一页</a>
					<a href="admin.do?page=${page+1}">下一页</a>
					<a href="admin.do?page=${totalpage}">末页</a>
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

