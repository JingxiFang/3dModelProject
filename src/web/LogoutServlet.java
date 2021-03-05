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
 * 退出模块的Servlet
 * @author lxq
 *
 */
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request, response);}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		Manager manager = (Manager) session.getAttribute("manager");	// 获取Session中Manager的信息
		
		session.removeAttribute("userInfo");			// 销毁Session中UserInfo的信息
		if(manager != null){	// Manager退出
			ManagerService managerService = new ManagerService();
			managerService.checkInOrOut(manager, "2");	// 记录退出操作（1：登录；2：退出）
			session.removeAttribute("manager");			// 销毁Session中Manager的信息
		}
		response.sendRedirect("login.jsp");		//退出跳转
	}
}
