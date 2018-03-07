package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import dao.CommentDao;
import dao.NewsDao;
import dao.TopicDao;
import dao.UserDao;
import dao.impl.CommentDaoImpl;
import dao.impl.NewsDaoImpl;
import dao.impl.TopicDaoImpl;
import dao.impl.UserDaoImpl;
import bean.Comment;
import bean.News;
import bean.Topic;
import bean.User;
/**
 * Servlet控制层
 * @author DaiJinchi
 *	2017-07-17
 */
public class ActionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf("."));
		if (action.equals("login")){//登录，查找用户
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			User user = new User(name,pwd);
			UserDao ud = new UserDaoImpl();
			boolean flag = ud.checkUser(user);
			HttpSession session = request.getSession();
			if (flag){
				session.setAttribute("uname", name);
			}
			out.print(flag);
		}else if (action.equals("admin")){//admin页面控制新闻页码
			int page = Integer.parseInt(request.getParameter("page"));//当前页数
			NewsDao nd = new NewsDaoImpl();
			int totalpage = 0;//新闻的页数
			int total = nd.findNewsCount();//新闻总条数
			int num = 5;//新闻每页条数
			if (total % num != 0){
				totalpage = total/num+1;
			}else{
				totalpage = total/num;
			}
			if (page > 1 && page <= totalpage){
				request.setAttribute("page", page);
			}else if (page <= 1){
				request.setAttribute("page", 1);
			}else{
				request.setAttribute("page", totalpage);
			}
			request.setAttribute("totalpage", totalpage);
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}else if (action.equals("quoto")){//admin页面分页查找新闻
			int page = (Integer.parseInt(request.getParameter("page"))-1)*5;
			int num = 5;
			NewsDao nd = new NewsDaoImpl();
			List<News> list = nd.findAllNews(page,num);
			JSONArray ja = JSONArray.fromObject(list);
			String str = ja.toString();
			out.print(str);
		}else if (action.equals("loginnews")){//login页面控制新闻页码
			NewsDao nd = new NewsDaoImpl();
			String ntid = request.getParameter("ntid");
			int total = nd.findNewsCount();//新闻总条数
			if (ntid.equals("null")){
				total = nd.findNewsCount();
			}
			else if (ntid != null){
				total = nd.findNewsCountBytid(ntid);
			}
			request.setAttribute("ntid", ntid);
			int page = Integer.parseInt(request.getParameter("page"));//当前页数
			int totalpage = 0;//新闻的页数
			int num = 20;//新闻每页条数
			if (total % num != 0){
				totalpage = total/num+1;
			}else{
				totalpage = total/num;
			}
			if (page > 1 && page <= totalpage){
				request.setAttribute("page", page);
			}else if (page <= 1){
				request.setAttribute("page", 1);
			}else{
				request.setAttribute("page", totalpage);
			}
			request.setAttribute("totalpage", totalpage);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if (action.equals("loginTopicNews")){//login页面按主题查找新闻
			int ntid = Integer.parseInt(request.getParameter("ntid"));
			int page = 1;
			if (request.getParameter("page") != null && request.getParameter("page") != ""){
				page = (Integer.parseInt(request.getParameter("page"))-1)*20;
			}
			int num = 20;
			NewsDao nd = new NewsDaoImpl();
			List<News> list = nd.findNewsByTid(page,num,ntid);
			JSONArray ja = JSONArray.fromObject(list);
			String str = ja.toString();
			out.print(str);
		}else if (action.equals("loginnewsfind")){//login页面分页查找新闻
			int page = 1;
			if (request.getParameter("page") != null && request.getParameter("page") != ""){
				page = (Integer.parseInt(request.getParameter("page"))-1)*20;
			}
			request.setAttribute("page", page);
			int num = 20;
			NewsDao nd = new NewsDaoImpl();
			List<News> list = nd.findAllNews(page,num);
			JSONArray ja = JSONArray.fromObject(list);
			String str = ja.toString();
			out.print(str);
		}else if (action.equals("topicpage")){//主题管理页面控制页码
			int page = Integer.parseInt(request.getParameter("page"));//当前页数
			TopicDao nd = new TopicDaoImpl();
			int totalpage = 0;//新闻的页数
			int total = nd.topicCount();//主题总条数
			int num = 5;//主题每页条数
			if (total % num != 0){
				totalpage = total/num+1;
			}else{
				totalpage = total/num;
			}
			if (page > 1 && page <= totalpage){
				request.setAttribute("page", page);
			}else if (page <= 1){
				request.setAttribute("page", 1);
			}else{
				request.setAttribute("page", totalpage);
			}
			request.setAttribute("totalpage", totalpage);
			request.getRequestDispatcher("topic_list.jsp").forward(request, response);
		}else if (action.equals("findtopic")){//分页查找所有主题
			int page = (Integer.parseInt(request.getParameter("page"))-1)*5;
			int num = 5;
			TopicDao td = new TopicDaoImpl();
			List<Topic> list = td.findAllTopic(page,num);
			JSONArray ja = JSONArray.fromObject(list);
			String str = ja.toString();
			out.print(str);
		}else if (action.equals("findAlltopic")){//查找所有主题
			TopicDao td = new TopicDaoImpl();
			List<Topic> list = td.findAllTopic();
			JSONArray ja = JSONArray.fromObject(list);
			String str = ja.toString();
			out.print(str);
		}
		else if (action.equals("addnew")){//添加新闻
			String ntid = request.getParameter("ntid");
			String ntitle = request.getParameter("ntitle");
			String nauthor = request.getParameter("nauthor");
			String ncontent = request.getParameter("ncontent");
			String nsummary = request.getParameter("nsummary");
			News news = new News("", ntid, ntitle, nauthor, "", "", ncontent, "", nsummary);
			NewsDao nd = new NewsDaoImpl();
			boolean flag = nd.addNews(news);
			System.out.println(flag);
			if (flag){
				//重定向,客户的行为
				response.sendRedirect("../admin.do?page=1");
			}
		}else if (action.equals("delete")){//删除新闻
			int nid = Integer.parseInt(request.getParameter("nid"));
			NewsDao nd = new NewsDaoImpl();
			boolean flag = nd.deleteNews(nid);
			if (flag){
				response.sendRedirect("admin.do?page=1");
			}
		}else if (action.equals("update")){//根据id查找新闻并写入页面
			int nid = Integer.parseInt(request.getParameter("nid"));
			NewsDao nd = new NewsDaoImpl();
			News news = nd.findNewsById(nid);
			request.setAttribute("news", news);//${news.ntid}
			if (news != null){
				//转发，服务器行为
				request.getRequestDispatcher("newspages/news_modify.jsp").forward(request, response);
			}
		}else if (action.equals("modify")){//修改新闻
			String ntid = request.getParameter("ntid");
			String nid = request.getParameter("nid");
			String ntitle = request.getParameter("ntitle");
			String nauthor = request.getParameter("nauthor");
			String nsummary = request.getParameter("nsummary");
			String ncontent = request.getParameter("ncontent");
			News news = new News
			(nid, ntid, ntitle, nauthor, null, null, ncontent, null, nsummary);
			NewsDao nd = new NewsDaoImpl();
			boolean flag = nd.modifyNews(news);
			if (flag){
				response.sendRedirect("admin.do?page=1");
			}
		}else if (action.equals("addTopic")){//添加主题
			String tname = new String(request.getParameter("tname").getBytes("ISO-8859-1"),"UTF-8");
			TopicDao td = new TopicDaoImpl();
			boolean flag = td.addTopic(tname);
			if (flag){
				response.sendRedirect("topicpage.do?page=1");
			}
		}else if (action.equals("topicDel")){//删除主题
			int tid = Integer.parseInt(request.getParameter("tid"));
			TopicDao td = new TopicDaoImpl();
			boolean flag = td.delTopic(tid);
			if (flag){
				response.sendRedirect("topicpage.do?page=1");
			}
		}else if (action.equals("updateTopic")){//修改主题
			int tid = Integer.parseInt(request.getParameter("tid"));
			String tname = request.getParameter("tname");
			TopicDao td = new TopicDaoImpl();
			boolean flag = td.updateTopic(tid, tname);
			if (flag){
				response.sendRedirect("topicpage.do?page=1");
			}
		}else if (action.equals("findcomment")){//根据新闻id找到评论
			String cnid = request.getParameter("cnid");
			CommentDao cm = new CommentDaoImpl();
			List<Comment> list = cm.findCommentById(cnid);
			JSONArray ja = JSONArray.fromObject(list);
			String str = ja.toString();
			out.print(str);
		}else if (action.equals("delComment")){//删除评论
			String cid = request.getParameter("cid");
			String cnid = request.getParameter("cnid");
			CommentDao cd = new CommentDaoImpl();
			boolean flag = cd.delComment(cid);
			if (flag){
				response.sendRedirect("update.do?nid="+cnid);
			}
		}else if (action.equals("guoneiFind")){//查找国内新闻
			NewsDao nd = new NewsDaoImpl();
			List<News> list = nd.findNewsByTid(0,5,42);
			JSONArray ja = JSONArray.fromObject(list);
			String str = ja.toString();
			out.print(str);
		}else if (action.equals("guojiFind")){//查找国际新闻
			NewsDao nd = new NewsDaoImpl();
			List<News> list = nd.findNewsByTid(0,5,20);
			JSONArray ja = JSONArray.fromObject(list);
			String str = ja.toString();
			out.print(str);
		}else if (action.equals("yuleFind")){//查找娱乐新闻
			NewsDao nd = new NewsDaoImpl();
			List<News> list = nd.findNewsByTid(0,5,17);
			JSONArray ja = JSONArray.fromObject(list);
			String str = ja.toString();
			out.print(str);
		}
		out.close();
	}

}
