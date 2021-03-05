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
 * 登录模块的Servlet
 * @author lxq 
 */
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request,response);}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		/*
		 * 设置编码格式
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 *	获取登录表单数据
		 */
		String username=request.getParameter("username");	// 用户名
		String password=request.getParameter("password");	// 密码
		String userType=request.getParameter("userType");	// 用户类型
		
		/*
		 * 用于登录失败时，信息回显
		 */
		request.setAttribute("username", username);
		request.setAttribute("userType", userType);
		
		/*
		 *	服务器端的用户名密码为空检验
		 */
		String msgString = "";	// 反馈信息
		if("".equals(username) || username==null){
			msgString = "请您输入手机/邮箱/用户名";
		}else if("".equals(password) || password==null){
			msgString = "请您输入密码";
		}
		
		if(!"".equals(msgString)){	// 当用户名或密码为空时，返回反馈信息
			request.setAttribute("msg", msgString);
			request.getRequestDispatcher("login.jsp").forward(request, response);	
			return;	// 避免重复转发报异常
		}
		
		/*
		 * 用户名或密码都不为空时
		 */
		if("manager".equals(userType)){			// Manager登录
			Manager manager = new Manager();	// 将用户名密码存入对象中
			manager.setM_name(username);
			manager.setM_pwd(password);
			
			ManagerService managerService = new ManagerService();
			String[] message = managerService.checkPwdAndLoginId(manager);	// 获取用户名密码匹配结果的反馈信息
			
			if("true".equals(message[0])){	// 登陆成功，反馈信息为将要跳转路径
				manager = managerService.findManangerInfo(manager);	// 获取管理员详细信息
				session.setAttribute("manager", manager);
				managerService.checkInOrOut(manager, "1");	// 记录登录操作（1：登录；2：退出）
				switch (Integer.parseInt(message[1])) {	// 根据所属类型返回主页路径
				case 1:
					response.sendRedirect("page/adA_index.jsp");// 1:A类Manager
					break;
				case 2:
					response.sendRedirect("page/adB_index.jsp");// 2:B类Manager
					break;
				default:
					System.out.println("Manager类型找不到，跳转失败！");
					break;
				}
							// 跳转到相应主页
			}else{	// 登录失败，反馈信息提示用户名或密码错误
				request.setAttribute("msg", message[1]);	
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		else if("user".equals(userType)){		// User登录
			UserInfo userInfo = new UserInfo();	// 将用户名密码存入对象中
			userInfo.setU_id(username);
			userInfo.setU_pwd(password);
			
			UserInfoService userInfoService = new UserInfoService();
			String[] message = userInfoService.checkPwdAndLoginId(userInfo);	// 获取用户名密码匹配结果的反馈信息
			userInfo = userInfoService.findUserInfo(userInfo);	// 获取用户详细信息
			// 将用户信息存入Session中
			session.setAttribute("userInfo", userInfo);	
			
			String pathString="";
			if("true".equals(message[0])){	// 登陆成功，反馈信息为将要跳转路径
			
				switch (Integer.parseInt(message[1])) {				// 根据所属类型返回主页路径
				case 1:
					pathString = "doctor_index.jsp";	// 1:标记用户(医生)
					break;
				case 2:
					pathString = "editor_index.jsp";	// 2:编辑用户(普通技术人员)
					break;
				case 3:
					pathString = "expert_index.jsp";	// 3:特征提取用户(高级技术人员)
					break;
				case 4:
					pathString = "user_index.jsp";		// 4:上传用户(病人)
					break;
				default:
					pathString = "error.jsp";
					break;
				}
				
				response.sendRedirect("page/"+pathString);					// 跳转主页
			}else{	// 登录失败，反馈信息提示用户名或密码错误
				request.setAttribute("msg", message[1]);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}
}
