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

		//����ҳ�治����
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		//�����ϴ�״̬��Ϣ
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
		//��ȡ�ϴ�ͼƬ�û����û���
		HttpSession session=request.getSession();
		Object userObj=session.getAttribute("userInfo");
		if(userObj==null)
		{
			//�û���Ϊ�� �˳�servlet
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			//����ҳ��
			return;
		}
		//else  �û������ǿ�
		UserInfo user=(UserInfo)userObj;
		String u_id=user.getU_id();
		
		String modelId="";
		//�ϴ��ļ�����·���� pathΪ�ļ��е�·�� u_id+modelidΪ�ļ��е�����
		String path="";
		//���ڴ���û��ϴ��ļ���·��
		List<String> fileNameList=new ArrayList<String>();
		
		if(user.getU_state()==4){
			//��ͨ�û������ˣ�
			
			//��ȡ��ǰʱ�����System.currentTimeMillis()  ��Ϊmodelid
			modelId=String.valueOf(System.currentTimeMillis());
			path="upload/ModelImg/"+"/"+u_id+modelId;
			fileNameList = uploadFile(request, session, path);
			
			//����ģ�Ͳ��뵽���ݿ���   �������²������ݵ�id
			if(!AddModel(u_id, path,modelId)){
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				//����ҳ��
				return; 	
			}
			boolean flag = addFile(fileService, u_id, modelId, path, fileNameList);
			if(flag){
				//�����ʾ�ɹ���
				response.setStatus(HttpServletResponse.SC_OK);
				response.sendRedirect("page/user_index.jsp");
			}
			else{
				//��ʾʧ�ܣ�
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				//ͣ���ڵ�ǰҳ��
			}
		}
		else if(user.getU_state()==2){
			//�༭��Ա
			modelId=request.getParameter("modelId");
			path="upload/ModelImg/"+"/"+u_id+modelId;
			fileNameList= uploadFile(request, session, path);
			boolean flag = addFile(fileService, u_id, modelId, path, fileNameList);
			if(flag){
				response.setStatus(HttpServletResponse.SC_OK);
				
				response.sendRedirect("page/editor_index.jsp");
			}
			else{
				//��ʾʧ�ܣ�
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
		}
		else if(user.getU_state()==1){
			//ҽ��
			//�ù�����δ���
			response.sendRedirect("page/messagePage.jsp");
			
		}
		else if(user.getU_state()==3){
			//������ȡ��Ա
			response.sendRedirect("page/messagePage.jsp");
			//�ù�����δ���
			
			
		}
		
		
	}

	private boolean addFile(FileOfModelService fileService, String u_id,
			String modelId, String path, List<String> fileNameList) {
		//���ļ�������ļ��б�   
		List<FileOfModel> fileList = getFileOfModelList(path,fileNameList, modelId,u_id);  
		//���ļ���Ϣ��ŵ����ݿ���  �����Ƿ�ɹ�
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
	 * ��ȡһ���ļ�����
	 * @param dirs  δ�����
	 * @param fileNameList
	 * @param modelId
	 * @return
	 */
	private List<FileOfModel> getFileOfModelList(String path,List<String> fileNameList, String modelId,String uid) {
		List<FileOfModel> fileList=new ArrayList<FileOfModel>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		for(String filename:fileNameList){
			FileOfModel file=new FileOfModel();
			 //1.�ļ�id����
			 
			//2.ģ��id
			 file.setModel_id(modelId);
			 //3.�ļ����·��
			 file.setFile_root(path+"//"+filename);
			 //ʱ�䣬��֤�ļ��ϴ�ʱ��һ�� ��forѭ����߻�ȡʱ�䣬���е��ļ�ʹ��ͬһ��ʱ��
			 file.setUpload_time(date);
			 //�ϴ���
			 file.setUpload_user_id(uid);
			 
			 fileList.add(file);	
		 }
		return fileList;
	}

	/**
	 * �����ݿ������һ��ģ��
	 * @param u_id �ϴ����û���
	 * @param dirs ģ���ļ��е�·��
	 * @return  ��ģ�������ݿ��еı��
	 */
	private boolean AddModel(String u_id, String path,String modelID) {
		//���ɹ��ϴ����ļ�Ԫ���ݷŵ����ݿ���
		Model_3d model=new Model_3d();
		
		//1.��ȡ��ǰʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String date = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��		
		model.setUpload_time(date);
		//1.ģ��id
		model.setModel_id(modelID);
		//2.�û������ϴ��ߣ�
		model.setUpload_user_id(u_id);
		//3.�ļ���·��
		model.setModel_root(path);
		//4.�ļ�������
		model.setModel_name(u_id+modelID); 
		//5.ģ��id����
		//6.����ģ��id  
		Model_3dService modelService=new Model_3dService();
		return modelService.addModel(model);
		
		
		 
	}

	
	
	/***
	 * �ϴ��ļ�������ȡ
	 * @param request
	 * @param session
	 * @param path ���ϴ����ļ���·��
	 * @return �ļ����·���б�
	 */
	public  List<String> uploadFile(HttpServletRequest request,
			HttpSession session, String path) {
		//1.�ļ��ϴ�Ŀ¼����ʱ�ļ�Ŀ¼
		File uploadFile;
		ServletContext sc = getServletContext();
		
		String dirs=sc.getRealPath(path);
		uploadFile = new File(dirs);//�û�һ���ϴ����ļ�����һ���ļ�����  
		
		if(!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempFile = new File(sc.getRealPath("temp"));
		if(!tempFile.exists()) {
			tempFile.mkdirs();
		}

		//2.�����ļ��ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1*1024*1024);//�趨��ֵ������1M�Ľ���д�뵽��ʱ�ļ�
		factory.setRepository(tempFile);//������ʱĿ¼

		//3.�����ϴ��ļ�������
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");//ָ����ȡʱʹ�õ��ַ���
		upload.setSizeMax(100 * 1024 * 1024);//ָ�������ϴ�����
		upload.setFileSizeMax(20*1024*1024);//�趨�����ļ�����ϴ�����

		//����ϴ����ȼ�����
		UploadStatus status = new UploadStatus();
		status.setCode(UploadStatus.STATUS_UPLOADING);
		//HttpSession session = request.getSession();
		session.setAttribute("status", status);
		UploadListener listener = new UploadListener(status);
		upload.setProgressListener(listener);
		
		
		List<String> fileNameList =new ArrayList<String>();
		String fileName ="";
		//4.��������
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item : items) {
				if(item.isFormField()) { //��ͨ����
					String name = item.getFieldName(); //������
					String value = item.getString("UTF-8"); //��Ҫָ���ַ�����Ӧ�����ύʱһ��
					System.out.println("[��ͨ����] " + name + "=" + value);
				} else {
					//�ϴ��ļ�
					

					String fileContentType = item.getContentType(); //�ϴ��ļ�������
					String fileType=fileContentType.substring(fileContentType.indexOf("/")+1);
					
					//�ļ�����  
					
					fileName = item.getName(); //�ϴ��ļ�������
					
					fileNameList.add(fileName);
					long fileSize = item.getSize(); //�ϴ��ļ��Ĵ�С
					System.out.println("[�ϴ��ļ�]" + fileName + ",������" + fileContentType + ",��СΪ" + fileSize + "��");

					//�����ļ�
					//TODO: 1. �����ļ����� �� 2.�ļ��Ĵ�ţ�����ͬһĿ¼�´�Ų�Ҫ����500���ļ�
					File target = new File(uploadFile, fileName);
					item.write(target);
					//�ϴ��ɹ�
					status.setCode(UploadStatus.STATUS_UPLOAD_SUCCESS);
					

				}
			}
			
		} catch (Exception e) {
			status.setCode(UploadStatus.STATUS_UPLOAD_ERROR);
			status.addMessage(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("�ϴ��ļ�ʧ�ܣ�" + e.getMessage());
		}
		return fileNameList;
	}
}
