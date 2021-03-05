package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.FileOfModelService;
import service.Model_3dService;
import service.UserInfoService;
import domain.FileOfModel;
import domain.Manager;
import domain.Model_3d;
import domain.UserInfo;

/**
 * 查找图像文件的Servlet
 * @author xjy
 */
public class SearchModel_3d extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//获取用户权限
		HttpSession session=request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if(userInfo!=null){
			//用户	
			
			String type = String.valueOf(userInfo.getU_state());
			
			if(type.equals("1")){
				getModelList(request);
		    	response.sendRedirect("page/doctor_viewPic.jsp");
			}else if(type.equals("2")){
				//编辑人员
				getModelList(request);
				response.sendRedirect("page/editor_viewPic.jsp");
			}else if(type.equals("3")){
				//特征提取人员
				getModelList(request);
				response.sendRedirect("page/expert_viewPic.jsp");			
			}else if(type.equals("4")){
				Model_3dService modelService=new Model_3dService();
				List<Model_3d> modelList=new ArrayList<Model_3d>();
				modelList= modelService.gerModelInfo(userInfo.getU_id());
			    session.setAttribute("modelList", modelList);
				response.sendRedirect("page/user_viewPic.jsp");
			}
		}
		else{
			//管理员
			//获取管理员信息
			Manager managerInfo = (Manager) session.getAttribute("manager");
			int state=managerInfo.getM_state();
			//获取管理员状态
			if(state==2){
				//b类管理员有权管理文件
				getModelList(request);
				response.sendRedirect("page/adB_fileManage.jsp");	
			}
		}
	}

	private void getModelList(HttpServletRequest request) {
		
		String userName=request.getParameter("username");
	
		Model_3dService modelService=new Model_3dService();
		List<Model_3d> modelList=new ArrayList<Model_3d>();
		
		if("".equals(userName)){
			modelList= modelService.gerModelInfo(null);
		}
		else{
			//查找某用户上传的图片
			//先根据username获取该用户的用户id
			UserInfoService userService = new UserInfoService();
			List<UserInfo> userList = userService.findUser(userName);
			if(userList.size()>0){
				//再通过id获取用户上传的模型
				for(UserInfo user:userList){
					modelList.addAll(modelService.gerModelInfo(user.getU_id()));
				}
			}
		}
		//医生  编辑  特征提取人员 需要 查看模型所属用户信息
		if(modelList.size()>0){
			UserInfoService userService =new UserInfoService();
			for(Model_3d model:modelList ){
				model.setUpload_user_id_obj(userService.findUserById(model.getUpload_user_id()));
			}
		}
		
		request.getSession().setAttribute("modelList", modelList);		
	}
}
