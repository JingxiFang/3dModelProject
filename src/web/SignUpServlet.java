package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.UserInfo;
import service.UserInfoService;

/**
 * 用户注册Servlet
 * @author zh
 */
public class SignUpServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * 设置编码格式
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();	// 一定要放在上两行代码后面
		
		/*
		 * 获取注册表单数据
		 */
		String method = request.getParameter("method");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String u_name = request.getParameter("realname");
		String email = request.getParameter("email");

		/*
		 * 将用户信息存入对象中
		 */
		UserInfo userinfo = new UserInfo();
		userinfo.setU_id(username);
		userinfo.setU_pwd(password);
		userinfo.setU_name(u_name);
		
		/*
		 *根据注册结果跳转页面 
		 */
		UserInfoService userInfoService = new UserInfoService();
		if("ajax".equals(method)){	// ajax请求
			if(userInfoService.isExist(userinfo.getU_id())){
				out.write("* 该用户名已被注册");// 用户名重复：返回反馈信息
			}
		}else{	// post请求
			String registinfo = userInfoService.register(userinfo);	// 用户注册
			if ("注册成功".equals(registinfo)) {
				response.sendRedirect("login.jsp");			// 注册成功：跳转到登录页面
			}else {
				response.sendRedirect("signup.jsp");		// 拓展：跳转系统错误页面（未开发）
			}
		}
	}
}
