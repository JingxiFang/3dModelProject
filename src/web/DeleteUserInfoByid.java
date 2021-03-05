package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserInfoService;

/**
 * É¾³ýÓÃ»§µÄServlet
 * @author hy
 */
public class DeleteUserInfoByid extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String u_id=request.getParameter("u_id");
		
		UserInfoService userInfoService=new UserInfoService();
		if(userInfoService.deleteUserByid(u_id)){
			response.sendRedirect("page/adA_userManage.jsp");
		}	
	}
}
