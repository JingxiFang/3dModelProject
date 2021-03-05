package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserInfoService;
import dao.UserInfoDao;
import dao.jdbc.UserInfoDaoJDBCImpl;
import domain.UserInfo;

/**
 * 根据条件查询用户的Servlet
 * @author hy
 */
public class SearchUserInfo extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request,response);}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String u_name = request.getParameter("u_name");
		if(u_name==null){
			u_name = (String) request.getSession().getAttribute("content");
		}else{
			request.getSession().setAttribute("content", u_name); // 将查询条件放入到session中	
		}
		
		UserInfoService userService = new UserInfoService();
		List<UserInfo> userList = new ArrayList<UserInfo>();
		userList = userService.findUser(u_name);
		request.setAttribute("userList", userList);

		//转发
		RequestDispatcher dispatcher =request.getRequestDispatcher("page/adA_userManage.jsp");
	    dispatcher.forward(request, response);
	}
}
