package web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Model_3dService;

public class FileDownServlet extends HttpServlet {

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

		
		
		doPost(request, response);
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
		
		//1.��ȡģ��id
		String modelId=request.getParameter("modelId");
		//2.����ģ��id���ģ��ͼƬ�����ļ��� ������root
		Model_3dService modelService=new Model_3dService();
		
		
		String root=modelService.getModelDir(modelId);
		ServletContext sc = getServletContext();
		
		String realRoot=sc.getRealPath(root);
		
		recursion(realRoot,response,request);
	}
	
	/**
	 * ���������ļ�
	 * @param root
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
    public boolean recursion(String root,HttpServletResponse response,HttpServletRequest request) throws IOException{
        //����·������һ���ļ�
       
    	Vector<File> vecFile=new Vector<File>();
    	
    	
    	//root="D:\\Java\\TomCat\\apache-tomcat-7.0.76\\webapps\\324\\upload\\ModelImg\\a11563191077080";
        java.io.File file = new java.io.File(root);
        java.io.File[] subFile = file.listFiles();
        //�����ļ�����������ļ�
        if(subFile==null){
        	return false;
        }
        for (int i = 0; i < subFile.length; i++) {
            /*������ļ��У���ݹ���ȥѰ���ļ���������ļ�*/
            if (subFile[i].isDirectory()) {
                //recursion(subFile[i].getAbsolutePath(), vecFile);
            } else {
            //��������ļ��еĻ���ֱ����ӵ�vector��������ȥ
            //�������������ļ�����ѹ�������أ�
//            String filename = subFile[i].getName();
//            vecFile.add(filename);
                vecFile.add(subFile[i]);
            }
        }
        //���������ļ�������
        String fileName = "temp1.zip";
        response.setContentType("text/html; charset=UTF-8"); // ���ñ����ַ�
        response.setContentType("application/x-msdownload"); // ������������Ϊ��������
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);// �������ص��ļ�����
        OutputStream out = response.getOutputStream(); // ����ҳ�淵�ط�ʽΪ����������Զ��������ؿ�
        //����ѹ���ļ���Ҫ�Ŀյ�zip��
        String zipBasePath = request.getSession().getServletContext().getRealPath("/logs/");
        /* ���basePath��·����������� */
        System.out.println(zipBasePath);
        /* ����ѹ���ļ���Ҫ�Ŀյ�zip�� ���������Զ����ɵģ����������Լ�ȥ���� */
        String zipFilePath = zipBasePath + "temp.zip";
        System.out.println("create the empty zip file successfully??????");
        //������ʱ��zipѹ����·��������zip�ļ�
        java.io.File directory = new java.io.File(zipBasePath);
        java.io.File zip = new java.io.File(zipFilePath);
        if (!zip.exists()) {
        	directory.mkdirs();
            zip.createNewFile();
        }
        System.out.println("create the  zip file successfully");
        // ����zip�ļ������
        FileOutputStream fos = new FileOutputStream(zip);
        ZipOutputStream zos = new ZipOutputStream(fos);
        System.out.println("create the empty zip stream successfully");
        //ѭ����ȡ�ļ�·�����ϣ���ȡÿһ���ļ���·�������ļ�һ��һ������ѹ����
        for (java.io.File fp : vecFile) {
            zipFile(fp, zos); // ��ÿһ���ļ�д��zip�ļ����ڣ������д��
        }
        System.out.println("get the path successfully");
        // fos.close();//������������εĻ��ᱨ��java.io.IOException: Stream closed
        zos.close();
        System.out.println("ok??");
        //���������ļ�д���ͻ���,ʹ�û��������
        InputStream fis = new BufferedInputStream(new FileInputStream(zipFilePath));
        byte[] buff = new byte[4096];
        int size = 0;
        while ((size = fis.read(buff)) != -1) {
            out.write(buff, 0, size);
        }
        System.out.println("package is packed successfully");
        
        //�ͷź͹ر����������
        out.flush();//�������
        out.close();
        fis.close();
        return true;

    }

    /**
     * �ļ�ѹ���ķ���
     * @param inputFile
     * @param zipoutputStream
     */
    public void zipFile(java.io.File inputFile, ZipOutputStream zipoutputStream) {
        try {
            if (inputFile.exists()) { // �ж��ļ��Ƿ����
                if (inputFile.isFile()) { // �ж��Ƿ������ļ��������ļ���

                    // ������������ȡ�ļ�
                    FileInputStream fis = new FileInputStream(inputFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    // ���ļ�д��zip�ڣ������ļ����д��
                    ZipEntry ze = new ZipEntry(inputFile.getName()); // ��ȡ�ļ���
                    zipoutputStream.putNextEntry(ze);

                    // д���ļ��ķ�����ͬ��
                    byte[] b = new byte[1024];
                    long l = 0;
                    while (l < inputFile.length()) {
                        int j = bis.read(b, 0, 1024);
                        l += j;
                        zipoutputStream.write(b, 0, j);
                    }
                    // �ر����������
                    bis.close();
                    fis.close();
                } else { // ������ļ��У���ʹ����ٵķ�����ȡ�ļ���д��zip
                    try {
                        java.io.File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], zipoutputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

}
