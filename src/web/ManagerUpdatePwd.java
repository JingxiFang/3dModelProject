package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Manager;
import service.ManagerService;

/**
 * 管理员密码修改的Servlet
 * @author xjy
 */
public class ManagerUpdatePwd extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request, response);}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		/*
		 * 修改密码
		 * @param type管理员类型 id管理员账号  oldpwd输入旧密码 newpwd输入新密码
		 */
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		String id = manager.getM_name();
		String type = String.valueOf(manager.getM_state());
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		
		String msgString ="";
		ManagerService man = new ManagerService();
		if(man.updatePwdcheck(id, oldpwd)){	// 验证旧密码是否正确
			if(man.updatePwd(id, newpwd)){	// 修改密码
				msgString = "修改密码成功！";
			}else{
				msgString = "修改密码失败！";	
			}
		}else{
			msgString ="旧密码输入错误！";
		}
		
		if(type.equals("1")){
			out.print("<script>alert(\'"+msgString+"\');window.location.href='../324/page/adA_changePwd.jsp'</script>");
		}else if(type.equals("2")){
        	out.print("<script>alert(\'"+msgString+"\');window.location.href='../324/page/adB_changePwd.jsp'</script>");
		}
	}
}
