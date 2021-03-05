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
 * ����Ա�����޸ĵ�Servlet
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
		 * �޸�����
		 * @param type����Ա���� id����Ա�˺�  oldpwd��������� newpwd����������
		 */
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		String id = manager.getM_name();
		String type = String.valueOf(manager.getM_state());
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		
		String msgString ="";
		ManagerService man = new ManagerService();
		if(man.updatePwdcheck(id, oldpwd)){	// ��֤�������Ƿ���ȷ
			if(man.updatePwd(id, newpwd)){	// �޸�����
				msgString = "�޸�����ɹ���";
			}else{
				msgString = "�޸�����ʧ�ܣ�";	
			}
		}else{
			msgString ="�������������";
		}
		
		if(type.equals("1")){
			out.print("<script>alert(\'"+msgString+"\');window.location.href='../324/page/adA_changePwd.jsp'</script>");
		}else if(type.equals("2")){
        	out.print("<script>alert(\'"+msgString+"\');window.location.href='../324/page/adB_changePwd.jsp'</script>");
		}
	}
}
