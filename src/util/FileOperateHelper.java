package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class FileOperateHelper extends HttpServlet{
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
					//String fileName = item.getName(); //上传文件的名称

					String fileContentType = item.getContentType(); //上传文件的类型
					String fileType=fileContentType.substring(fileContentType.indexOf("/")+1);
					
					//文件名称  
					/**
					 * 当前存在问题  如何保证用户上传的图片文件的顺序是合理的
					 * 文件名使用UUID是否合理？
					 */
					fileName = UUID.randomUUID().toString()+"."+fileType;
					
					
					
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
