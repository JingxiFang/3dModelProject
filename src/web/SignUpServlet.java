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
 * �û�ע��Servlet
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
		 * ���ñ����ʽ
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();	// һ��Ҫ���������д������
		
		/*
		 * ��ȡע�������
		 */
		String method = request.getParameter("method");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String u_name = request.getParameter("realname");
		String email = request.getParameter("email");

		/*
		 * ���û���Ϣ���������
		 */
		UserInfo userinfo = new UserInfo();
		userinfo.setU_id(username);
		userinfo.setU_pwd(password);
		userinfo.setU_name(u_name);
		
		/*
		 *����ע������תҳ�� 
		 */
		UserInfoService userInfoService = new UserInfoService();
		if("ajax".equals(method)){	// ajax����
			if(userInfoService.isExist(userinfo.getU_id())){
				out.write("* ���û����ѱ�ע��");// �û����ظ������ط�����Ϣ
			}
		}else{	// post����
			String registinfo = userInfoService.register(userinfo);	// �û�ע��
			if ("ע��ɹ�".equals(registinfo)) {
				response.sendRedirect("login.jsp");			// ע��ɹ�����ת����¼ҳ��
			}else {
				response.sendRedirect("signup.jsp");		// ��չ����תϵͳ����ҳ�棨δ������
			}
		}
	}
}
