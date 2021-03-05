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
 * �û������޸ĵ�Servlet
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
		 * �޸�����
		 * @param type�û����� id�û��˺�  oldpwd��������� newpwd����������
		 */
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String id = userInfo.getU_id();
		String type = String.valueOf(userInfo.getU_state());
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		
		String msgString ="";
		UserInfoService user = new UserInfoService();
		if(user.updatePwdcheck(id, oldpwd)){	// ��֤�������Ƿ���ȷ
			if(user.updatePwd(id, newpwd)){		// �޸�����
				msgString = "�޸�����ɹ���";
			}else {
				msgString = "�޸�����ʧ�ܣ�";
			}
		}else{
			msgString ="�������������";
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
