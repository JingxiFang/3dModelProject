package web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Manager;

import service.FileOfModelService;
import service.Model_3dService;

public class FileDeleteServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		//��֤�û����
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		if(manager==null){
			return;
		}
		if(manager.getM_state()!=2){
			return;
		}
		
		//�û������֤�ɹ� �������²���
		
		//��ȡmodelId
		String modelId=request.getParameter("modelId");
		//���ݿ����������ɾ�������������Ҳ�����ɾ��
		//ɾ��Model
		//1.���model�ĸ��ļ���ַ
		FileOfModelService fileService=new FileOfModelService();
		List<String> rootList=fileService.selectFileRoot(modelId);
		//2.ɾ���ļ�
		for(String root:rootList){
			delFile(root);
		}
		//3.ɾ�����ݿ��е�����
		Model_3dService modelService=new Model_3dService();
		modelService.delModel(modelId);
		
		//4.ɾ���ɹ�����ʾ�ɹ�
		
		
	}
	/**
	 * ����ɾ���ļ�
	 * @param path
	 */
	private  void delFile(String path) {
			
			File fileTemp = new File(path);
			// �ж��ļ��Ƿ����
			boolean falg = false;
			falg = fileTemp.exists();
			if(falg){
				File file = new File(path);
				if (true==file.isFile()) {
					boolean flag = false;
					flag = file.delete();
					
				}
			}
			else {
				System.out.print("ʧ��");
				}
			
	}

}
