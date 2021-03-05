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
		//验证用户身份
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		if(manager==null){
			return;
		}
		if(manager.getM_state()!=2){
			return;
		}
		
		//用户身份验证成功 进行以下操作
		
		//获取modelId
		String modelId=request.getParameter("modelId");
		//数据库中设置外键删除相关联的数据也会跟着删除
		//删除Model
		//1.查出model的各文件地址
		FileOfModelService fileService=new FileOfModelService();
		List<String> rootList=fileService.selectFileRoot(modelId);
		//2.删除文件
		for(String root:rootList){
			delFile(root);
		}
		//3.删除数据库中的数据
		Model_3dService modelService=new Model_3dService();
		modelService.delModel(modelId);
		
		//4.删除成功后提示成功
		
		
	}
	/**
	 * 用于删除文件
	 * @param path
	 */
	private  void delFile(String path) {
			
			File fileTemp = new File(path);
			// 判断文件是否存在
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
				System.out.print("失败");
				}
			
	}

}
