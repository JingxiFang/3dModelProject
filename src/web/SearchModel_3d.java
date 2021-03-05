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
 * ����ͼ���ļ���Servlet
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
		//��ȡ�û�Ȩ��
		HttpSession session=request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if(userInfo!=null){
			//�û�	
			
			String type = String.valueOf(userInfo.getU_state());
			
			if(type.equals("1")){
				getModelList(request);
		    	response.sendRedirect("page/doctor_viewPic.jsp");
			}else if(type.equals("2")){
				//�༭��Ա
				getModelList(request);
				response.sendRedirect("page/editor_viewPic.jsp");
			}else if(type.equals("3")){
				//������ȡ��Ա
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
			//����Ա
			//��ȡ����Ա��Ϣ
			Manager managerInfo = (Manager) session.getAttribute("manager");
			int state=managerInfo.getM_state();
			//��ȡ����Ա״̬
			if(state==2){
				//b�����Ա��Ȩ�����ļ�
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
			//����ĳ�û��ϴ���ͼƬ
			//�ȸ���username��ȡ���û����û�id
			UserInfoService userService = new UserInfoService();
			List<UserInfo> userList = userService.findUser(userName);
			if(userList.size()>0){
				//��ͨ��id��ȡ�û��ϴ���ģ��
				for(UserInfo user:userList){
					modelList.addAll(modelService.gerModelInfo(user.getU_id()));
				}
			}
		}
		//ҽ��  �༭  ������ȡ��Ա ��Ҫ �鿴ģ�������û���Ϣ
		if(modelList.size()>0){
			UserInfoService userService =new UserInfoService();
			for(Model_3d model:modelList ){
				model.setUpload_user_id_obj(userService.findUserById(model.getUpload_user_id()));
			}
		}
		
		request.getSession().setAttribute("modelList", modelList);		
	}
}
