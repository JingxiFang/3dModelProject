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
					//String fileName = item.getName(); //�ϴ��ļ�������

					String fileContentType = item.getContentType(); //�ϴ��ļ�������
					String fileType=fileContentType.substring(fileContentType.indexOf("/")+1);
					
					//�ļ�����  
					/**
					 * ��ǰ��������  ��α�֤�û��ϴ���ͼƬ�ļ���˳���Ǻ����
					 * �ļ���ʹ��UUID�Ƿ����
					 */
					fileName = UUID.randomUUID().toString()+"."+fileType;
					
					
					
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
