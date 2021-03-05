package web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.FileOfModel;
import domain.Model_3d;
import domain.UserInfo;
import service.FileOfModelService;
import service.Model_3dService;
import util.FileOperateHelper;
import util.UploadListener;
import util.UploadStatus;

public class FileUploadServlet extends HttpServlet {

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

		//设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		//返回上传状态信息
		String message = "";
		HttpSession session = request.getSession();
		UploadStatus status = (UploadStatus) session.getAttribute("status");
		if(status != null) {
			message = status.getCode() + ";" + status.getDescript() + ";" + status.getProgress();		
		}

		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
		out.close();
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
		
		
		FileOfModelService fileService=new FileOfModelService()	;
		//获取上传图片用户的用户名
		HttpSession session=request.getSession();
		Object userObj=session.getAttribute("userInfo");
		if(userObj==null)
		{
			//用户名为空 退出servlet
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			//错误页面
			return;
		}
		//else  用户名不是空
		UserInfo user=(UserInfo)userObj;
		String u_id=user.getU_id();
		
		String modelId="";
		//上传文件到该路径下 path为文件夹的路径 u_id+modelid为文件夹的名字
		String path="";
		//用于存放用户上传文件的路径
		List<String> fileNameList=new ArrayList<String>();
		
		if(user.getU_state()==4){
			//普通用户（病人）
			
			//获取当前时间戳；System.currentTimeMillis()  作为modelid
			modelId=String.valueOf(System.currentTimeMillis());
			path="upload/ModelImg/"+"/"+u_id+modelId;
			fileNameList = uploadFile(request, session, path);
			
			//将该模型插入到数据库中   并返回新插入数据的id
			if(!AddModel(u_id, path,modelId)){
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				//错误页面
				return; 	
			}
			boolean flag = addFile(fileService, u_id, modelId, path, fileNameList);
			if(flag){
				//如何提示成功？
				response.setStatus(HttpServletResponse.SC_OK);
				response.sendRedirect("page/user_index.jsp");
			}
			else{
				//提示失败；
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				//停留在当前页面
			}
		}
		else if(user.getU_state()==2){
			//编辑人员
			modelId=request.getParameter("modelId");
			path="upload/ModelImg/"+"/"+u_id+modelId;
			fileNameList= uploadFile(request, session, path);
			boolean flag = addFile(fileService, u_id, modelId, path, fileNameList);
			if(flag){
				response.setStatus(HttpServletResponse.SC_OK);
				
				response.sendRedirect("page/editor_index.jsp");
			}
			else{
				//提示失败；
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
		}
		else if(user.getU_state()==1){
			//医生
			//该功能尚未完成
			response.sendRedirect("page/messagePage.jsp");
			
		}
		else if(user.getU_state()==3){
			//特征提取人员
			response.sendRedirect("page/messagePage.jsp");
			//该功能尚未完成
			
			
		}
		
		
	}

	private boolean addFile(FileOfModelService fileService, String u_id,
			String modelId, String path, List<String> fileNameList) {
		//将文件打包成文件列表   
		List<FileOfModel> fileList = getFileOfModelList(path,fileNameList, modelId,u_id);  
		//将文件信息存放到数据库中  返回是否成功
		boolean flag=false;
		try {
			flag=fileService.addFiles(fileList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	
	/**
	 * 获取一个文件集合
	 * @param dirs  未完待续
	 * @param fileNameList
	 * @param modelId
	 * @return
	 */
	private List<FileOfModel> getFileOfModelList(String path,List<String> fileNameList, String modelId,String uid) {
		List<FileOfModel> fileList=new ArrayList<FileOfModel>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		for(String filename:fileNameList){
			FileOfModel file=new FileOfModel();
			 //1.文件id自增
			 
			//2.模型id
			 file.setModel_id(modelId);
			 //3.文件相对路径
			 file.setFile_root(path+"//"+filename);
			 //时间，保证文件上传时间一致 在for循坏外边获取时间，所有的文件使用同一个时间
			 file.setUpload_time(date);
			 //上传者
			 file.setUpload_user_id(uid);
			 
			 fileList.add(file);	
		 }
		return fileList;
	}

	/**
	 * 向数据库中添加一个模型
	 * @param u_id 上传者用户名
	 * @param dirs 模型文件夹的路径
	 * @return  该模型在数据库中的编号
	 */
	private boolean AddModel(String u_id, String path,String modelID) {
		//将成功上传的文件元数据放到数据库中
		Model_3d model=new Model_3d();
		
		//1.获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间		
		model.setUpload_time(date);
		//1.模型id
		model.setModel_id(modelID);
		//2.用户名（上传者）
		model.setUpload_user_id(u_id);
		//3.文件夹路径
		model.setModel_root(path);
		//4.文件夹名称
		model.setModel_name(u_id+modelID); 
		//5.模型id自增
		//6.返回模型id  
		Model_3dService modelService=new Model_3dService();
		return modelService.addModel(model);
		
		
		 
	}

	
	
	/***
	 * 上传文件方法提取
	 * @param request
	 * @param session
	 * @param path 所上传的文件夹路径
	 * @return 文件相对路径列表
	 */
	public  List<String> uploadFile(HttpServletRequest request,
			HttpSession session, String path) {
		//1.文件上传目录和临时文件目录
		File uploadFile;
		ServletContext sc = getServletContext();
		
		String dirs=sc.getRealPath(path);
		uploadFile = new File(dirs);//用户一次上传的文件放在一个文件夹中  
		
		if(!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempFile = new File(sc.getRealPath("temp"));
		if(!tempFile.exists()) {
			tempFile.mkdirs();
		}

		//2.创建文件上传工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1*1024*1024);//设定阈值，超出1M的将会写入到临时文件
		factory.setRepository(tempFile);//设置临时目录

		//3.创建上传文件处理器
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");//指定读取时使用的字符集
		upload.setSizeMax(100 * 1024 * 1024);//指定最大的上传限制
		upload.setFileSizeMax(20*1024*1024);//设定单个文件最大上传限制

		//添加上传进度监听器
		UploadStatus status = new UploadStatus();
		status.setCode(UploadStatus.STATUS_UPLOADING);
		//HttpSession session = request.getSession();
		session.setAttribute("status", status);
		UploadListener listener = new UploadListener(status);
		upload.setProgressListener(listener);
		
		
		List<String> fileNameList =new ArrayList<String>();
		String fileName ="";
		//4.处理请求
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item : items) {
				if(item.isFormField()) { //普通表单项
					String name = item.getFieldName(); //参数名
					String value = item.getString("UTF-8"); //需要指定字符集，应当于提交时一致
					System.out.println("[普通表单项] " + name + "=" + value);
				} else {
					//上传文件
					

					String fileContentType = item.getContentType(); //上传文件的类型
					String fileType=fileContentType.substring(fileContentType.indexOf("/")+1);
					
					//文件名称  
					
					fileName = item.getName(); //上传文件的名称
					
					fileNameList.add(fileName);
					long fileSize = item.getSize(); //上传文件的大小
					System.out.println("[上传文件]" + fileName + ",类型是" + fileContentType + ",大小为" + fileSize + "。");

					//保存文件
					//TODO: 1. 考虑文件重名 ； 2.文件的存放，建议同一目录下存放不要超过500个文件
					File target = new File(uploadFile, fileName);
					item.write(target);
					//上传成功
					status.setCode(UploadStatus.STATUS_UPLOAD_SUCCESS);
					

				}
			}
			
		} catch (Exception e) {
			status.setCode(UploadStatus.STATUS_UPLOAD_ERROR);
			status.addMessage(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("上传文件失败：" + e.getMessage());
		}
		return fileNameList;
	}
}
