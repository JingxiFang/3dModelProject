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
 * 修改用户信息的Servlet
 * @author hy
 */
public class UpdateUserInfoByid extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request,response);}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
	
		String u_id=request.getParameter("u_id");
		String u_name=request.getParameter("u_name");
		String u_email=request.getParameter("u_email");
		String u_phone=request.getParameter("u_phone");
		String u_state_str=request.getParameter("u_state");
		int u_state=Integer.parseInt(u_state_str);
		
		UserInfo user =new UserInfo();
		user.setU_id(u_id);
		user.setU_name(u_name);
		user.setU_email(u_email);
		user.setU_phone(u_phone);
		user.setU_state(u_state);
		
		UserInfoService userService = new UserInfoService();
		userService.updateUserByid(user);
		out.print("修改成功");
		response.sendRedirect("page/adA_userManage.jsp");
	}
}
