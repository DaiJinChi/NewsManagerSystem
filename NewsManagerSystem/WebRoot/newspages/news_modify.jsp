<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理后台</title>
<link href="CSS/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$.get('findAlltopic.do', function(data) {
			$('#tid').empty();
			for (var i = 0; i < data.length; i++) {
				if ($('#ntid').val() == data[i].tid) {
					$('#tid').append(
							"<option selected='selected' value='"+data[i].tid+"'>"
									+ data[i].tname + "</option>");
				}
				$('#tid').append(
						"<option value='"+data[i].tid+"'>" + data[i].tname
								+ "</option>");
			}
		}, 'json');
	});
	$(function() {
		$.post('findcomment.do?cnid=' + $('#nid').val(), function(data) {
			$('#comments').empty();
			if (data.length == 0){
				$('#comments').append("<tr><td colspan='6'>暂无评论</td></tr>");
			}
			for (var i = 0; i < data.length; i++) {
				$('#comments').append(
						"<tr><td>留言人：</td><td>" + data[i].cauthor
								+ "</td><td>IP：</td><td>"
								+ data[i].cip + "</td><td>留言时间：</td>"
								+ "<td>" + data[i].cdate + "</td>"
								+ "<td><a href='delComment.do?cid="+data[i].cid+"&cnid="+data[i].cnid+"'>删除</a></td></tr><tr><td colspan='6'>" + data[i].ccontent
								+ "</td></tr><tr><td colspan='6'><hr /></td></tr>");
			}
		}, 'json');
	});
</script>


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
			管理员 &#160;&#160;&#160;&#160; <a href="../loginnews.do?ntid=null&page=1">退出</a>
		</div>
		<div id="channel"></div>
	</div>

	<div id="main">
		<div id="opt_list">
			<ul>
				<li><a href="newspages/news_add.jsp">添加新闻</a></li>
				<li><a href="./admin.do?page=1">查找新闻</a></li>
				<li><a href="newspages/topic_add.jsp">添加主题</a></li>
				<li><a href="newspages/topicpage.do?page=1">编辑主题</a></li>
			</ul>
		</div>
		<div id="opt_area">
			<h1 id="opt_type">修改新闻：</h1>
			<form action="modify.do" method="post">
				<p>
					<label> 主题 </label> <select name="ntid" id="tid">

					</select> <input type="hidden" name="nid" id="nid" value="${news.nid}" /> <input
						type="hidden" name="ntid" id="ntid" value="${news.ntid}" />
				</p>
				<p>
					<label> 标题 </label> <input name="ntitle" type="text"
						class="opt_input" value="${news.ntitle}" />
				</p>
				<p>
					<label> 作者 </label> <input name="nauthor" type="text"
						class="opt_input" value="${news.nauthor}" />
				</p>
				<p>
					<label> 摘要 </label>
					<textarea name="nsummary" cols="40" rows="3">${news.nsummary}</textarea>
				</p>
				<p>
					<label> 内容 </label>
					<textarea name="ncontent" cols="70" rows="10">${news.ncontent}</textarea>
				</p>
				<p>
					<label> 上传图片 </label> <input name="file" type="file"
						class="opt_input" />
				</p>
				<input type="submit" value="提交" class="opt_sub" /> <input
					type="reset" value="重置" class="opt_sub" />
			</form>
			<h1 id="opt_type">修改新闻评论：</h1>
			<table style="width:80%;align:left;" id="comments">

			</table>
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

