package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.FileOfModelService;
import service.Model_3dService;
import dao.jdbc.ManagerDaoJDBCImpl;
import dao.jdbc.UtilDaoJDBCImpl;
import domain.FileOfModel;
import domain.Model_3d;
import domain.UserInfo;

/**
 * 用来测试的工具Servlet
 * @author lxq
 */
public class UtilServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		//获取模型
		String modelId=request.getParameter("modelId");
		FileOfModelService fileService = new FileOfModelService();
		List<FileOfModel> fileList = fileService.selectFile(modelId);
		
		session.setAttribute("fileList",fileList);
		//获取用户名
		
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		String type = String.valueOf(userInfo.getU_state());
		
	    if(type.equals("1")){
	    	//医生
	    	//response.sendRedirect("page/doctor_viewPicDetail.jsp");
	    	request.setAttribute("fileList",fileList);
	    	request.getRequestDispatcher("page/doctor_viewPicDetail.jsp").forward(request, response);
		}else if(type.equals("2")){
			request.setAttribute("fileList",fileList);
		    request.getRequestDispatcher("page/editor_viewPicDetail.jsp").forward(request, response);
		}else if(type.equals("3")){
			request.setAttribute("fileList",fileList);
			request.getRequestDispatcher("page/expert_viewPicDetail.jsp").forward(request, response);
		}else if(type.equals("4")){
			//病人
			request.setAttribute("fileList",fileList);
		    request.getRequestDispatcher("page/user_viewPicDetail.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		
	}
}
