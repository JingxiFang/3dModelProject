package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.jdbc.ManagerDaoJDBCImpl;
import domain.Manager;
import service.ManagerService;

/**
 * �˳�ģ���Servlet
 * @author lxq
 *
 */
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request, response);}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		Manager manager = (Manager) session.getAttribute("manager");	// ��ȡSession��Manager����Ϣ
		
		session.removeAttribute("userInfo");			// ����Session��UserInfo����Ϣ
		if(manager != null){	// Manager�˳�
			ManagerService managerService = new ManagerService();
			managerService.checkInOrOut(manager, "2");	// ��¼�˳�������1����¼��2���˳���
			session.removeAttribute("manager");			// ����Session��Manager����Ϣ
		}
		response.sendRedirect("login.jsp");		//�˳���ת
	}
}
