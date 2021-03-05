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
import domain.UserInfo;

/**
 * 通过用户名查询用户的Servlet
 * @author hy
 */
public class SearchUserInfoByid extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request,response);}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String u_id=request.getParameter("u_id");
		
		UserInfoService userService = new UserInfoService();
		List<UserInfo> userList = new ArrayList<UserInfo>();
		userList = userService.findUserByid(u_id);
		
		RequestDispatcher dispatcher =request.getRequestDispatcher("page/adA_changeUserInfo.jsp");
	    dispatcher.forward(request, response);
	}
}
