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
 * 用户密码修改的Servlet
 * @author xjy
 */
public class UserUpdatePwd extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request, response);}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		/*
		 * 修改密码
		 * @param type用户类型 id用户账号  oldpwd输入旧密码 newpwd输入新密码
		 */
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String id = userInfo.getU_id();
		String type = String.valueOf(userInfo.getU_state());
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		
		String msgString ="";
		UserInfoService user = new UserInfoService();
		if(user.updatePwdcheck(id, oldpwd)){	// 验证旧密码是否正确
			if(user.updatePwd(id, newpwd)){		// 修改密码
				msgString = "修改密码成功！";
			}else {
				msgString = "修改密码失败！";
			}
		}else{
			msgString ="旧密码输入错误！";
		}
		
		if(type.equals("1")){
			out.print("<script>alert(\'"+msgString+"\');window.location.href='../324/page/doctor_changePwd.jsp'</script>");
		}else if(type.equals("2")){
			out.print("<script>alert(\'"+msgString+"\');window.location.href='../324/page/editor_changePwd.jsp'</script>");
		}else if(type.equals("3")){
			out.print("<script>alert(\'"+msgString+"\');window.location.href='../324/page/expert_changePwd.jsp'</script>");
		}else if(type.equals("4")){
			out.print("<script>alert(\'"+msgString+"\');window.location.href='../324/page/user_changePwd.jsp'</script>");
		}
	}
}
