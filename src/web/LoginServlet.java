package web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.jdbc.ManagerDaoJDBCImpl;
import domain.Manager;
import domain.UserInfo;
import service.ManagerService;
import service.UserInfoService;

/**
 * ��¼ģ���Servlet
 * @author lxq 
 */
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request,response);}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		/*
		 * ���ñ����ʽ
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 *	��ȡ��¼������
		 */
		String username=request.getParameter("username");	// �û���
		String password=request.getParameter("password");	// ����
		String userType=request.getParameter("userType");	// �û�����
		
		/*
		 * ���ڵ�¼ʧ��ʱ����Ϣ����
		 */
		request.setAttribute("username", username);
		request.setAttribute("userType", userType);
		
		/*
		 *	�������˵��û�������Ϊ�ռ���
		 */
		String msgString = "";	// ������Ϣ
		if("".equals(username) || username==null){
			msgString = "���������ֻ�/����/�û���";
		}else if("".equals(password) || password==null){
			msgString = "������������";
		}
		
		if(!"".equals(msgString)){	// ���û���������Ϊ��ʱ�����ط�����Ϣ
			request.setAttribute("msg", msgString);
			request.getRequestDispatcher("login.jsp").forward(request, response);	
			return;	// �����ظ�ת�����쳣
		}
		
		/*
		 * �û��������붼��Ϊ��ʱ
		 */
		if("manager".equals(userType)){			// Manager��¼
			Manager manager = new Manager();	// ���û���������������
			manager.setM_name(username);
			manager.setM_pwd(password);
			
			ManagerService managerService = new ManagerService();
			String[] message = managerService.checkPwdAndLoginId(manager);	// ��ȡ�û�������ƥ�����ķ�����Ϣ
			
			if("true".equals(message[0])){	// ��½�ɹ���������ϢΪ��Ҫ��ת·��
				manager = managerService.findManangerInfo(manager);	// ��ȡ����Ա��ϸ��Ϣ
				session.setAttribute("manager", manager);
				managerService.checkInOrOut(manager, "1");	// ��¼��¼������1����¼��2���˳���
				switch (Integer.parseInt(message[1])) {	// �����������ͷ�����ҳ·��
				case 1:
					response.sendRedirect("page/adA_index.jsp");// 1:A��Manager
					break;
				case 2:
					response.sendRedirect("page/adB_index.jsp");// 2:B��Manager
					break;
				default:
					System.out.println("Manager�����Ҳ�������תʧ�ܣ�");
					break;
				}
							// ��ת����Ӧ��ҳ
			}else{	// ��¼ʧ�ܣ�������Ϣ��ʾ�û������������
				request.setAttribute("msg", message[1]);	
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		else if("user".equals(userType)){		// User��¼
			UserInfo userInfo = new UserInfo();	// ���û���������������
			userInfo.setU_id(username);
			userInfo.setU_pwd(password);
			
			UserInfoService userInfoService = new UserInfoService();
			String[] message = userInfoService.checkPwdAndLoginId(userInfo);	// ��ȡ�û�������ƥ�����ķ�����Ϣ
			userInfo = userInfoService.findUserInfo(userInfo);	// ��ȡ�û���ϸ��Ϣ
			// ���û���Ϣ����Session��
			session.setAttribute("userInfo", userInfo);	
			
			String pathString="";
			if("true".equals(message[0])){	// ��½�ɹ���������ϢΪ��Ҫ��ת·��
			
				switch (Integer.parseInt(message[1])) {				// �����������ͷ�����ҳ·��
				case 1:
					pathString = "doctor_index.jsp";	// 1:����û�(ҽ��)
					break;
				case 2:
					pathString = "editor_index.jsp";	// 2:�༭�û�(��ͨ������Ա)
					break;
				case 3:
					pathString = "expert_index.jsp";	// 3:������ȡ�û�(�߼�������Ա)
					break;
				case 4:
					pathString = "user_index.jsp";		// 4:�ϴ��û�(����)
					break;
				default:
					pathString = "error.jsp";
					break;
				}
				
				response.sendRedirect("page/"+pathString);					// ��ת��ҳ
			}else{	// ��¼ʧ�ܣ�������Ϣ��ʾ�û������������
				request.setAttribute("msg", message[1]);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}
}
