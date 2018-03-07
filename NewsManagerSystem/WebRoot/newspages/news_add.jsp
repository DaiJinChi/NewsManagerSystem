<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="console_element/top.jsp"></jsp:include>
<jsp:include page="console_element/left.jsp"></jsp:include>
<script type="text/javascript">
	$(function (){
		$.get('findAlltopic.do',function(data){
			$('#topic').empty();
			$('#topic').append("<option value='0'>选择</option>");
			for (var i=0;i<data.length;i++){
				$('#topic').append("<option value='"+data[i].tid+"'>"+data[i].tname+"</option>");
			}
		},'json');
	});
</script>

<%--指令方法导入文件 
<%@ include file="console_element/top.jsp" %>--%>

<%--@ include file="console_element/left.jsp" --%>


 <div id="opt_area">
<h1 id="opt_type">
	添加新闻：
</h1>
<form  action="addnew.do" method="post">
	<p>
		<label>
			主题
		</label>
		<select name="ntid" id="topic">
			
				 
		</select>
	</p>
	<p>
		<label>
			标题
		</label>
		<input name="ntitle" type="text" class="opt_input" />
	</p>
	<p>
		<label>
			作者
		</label>
		<input name="nauthor" type="text" class="opt_input" />
	</p>
	<p>
		<label>
			摘要
		</label>
		<textarea name="nsummary" cols="40" rows="3"></textarea>
	</p>
	<p>
		<label>
			内容
		</label>
		<textarea name="ncontent" cols="70" rows="10"></textarea>
	</p>
	
	<input name="action" type="hidden" value="addnews">
	<input type="submit" value="提交" class="opt_sub" />
	<input type="reset" value="重置" class="opt_sub" />
</form>
    	
    </div>
</div>
<%--@ include file="console_element/down.jsp" --%>
<jsp:include page="console_element/down.jsp"></jsp:include>