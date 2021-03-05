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
		
		//1.获取模型id
		String modelId=request.getParameter("modelId");
		//2.根据模型id查出模型图片所在文件夹 即参数root
		Model_3dService modelService=new Model_3dService();
		
		
		String root=modelService.getModelDir(modelId);
		ServletContext sc = getServletContext();
		
		String realRoot=sc.getRealPath(root);
		
		recursion(realRoot,response,request);
	}
	
	/**
	 * 批量下载文件
	 * @param root
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
    public boolean recursion(String root,HttpServletResponse response,HttpServletRequest request) throws IOException{
        //根据路径生成一个文件
       
    	Vector<File> vecFile=new Vector<File>();
    	
    	
    	//root="D:\\Java\\TomCat\\apache-tomcat-7.0.76\\webapps\\324\\upload\\ModelImg\\a11563191077080";
        java.io.File file = new java.io.File(root);
        java.io.File[] subFile = file.listFiles();
        //遍历文件里面的所有文件
        if(subFile==null){
        	return false;
        }
        for (int i = 0; i < subFile.length; i++) {
            /*如果是文件夹，则递归下去寻找文件夹里面的文件*/
            if (subFile[i].isDirectory()) {
                //recursion(subFile[i].getAbsolutePath(), vecFile);
            } else {
            //如果不是文件夹的话就直接添加到vector容器里面去
            //将遍历出来的文件进行压缩和下载：
//            String filename = subFile[i].getName();
//            vecFile.add(filename);
                vecFile.add(subFile[i]);
            }
        }
        //设置下载文件的名称
        String fileName = "temp1.zip";
        response.setContentType("text/html; charset=UTF-8"); // 设置编码字符
        response.setContentType("application/x-msdownload"); // 设置内容类型为下载类型
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);// 设置下载的文件名称
        OutputStream out = response.getOutputStream(); // 创建页面返回方式为输出流，会自动弹出下载框
        //创建压缩文件需要的空的zip包
        String zipBasePath = request.getSession().getServletContext().getRealPath("/logs/");
        /* 输出basePath的路径，方便调试 */
        System.out.println(zipBasePath);
        /* 创建压缩文件需要的空的zip包 ，这里是自动生成的，不用我们自己去生成 */
        String zipFilePath = zipBasePath + "temp.zip";
        System.out.println("create the empty zip file successfully??????");
        //根据临时的zip压缩包路径，创建zip文件
        java.io.File directory = new java.io.File(zipBasePath);
        java.io.File zip = new java.io.File(zipFilePath);
        if (!zip.exists()) {
        	directory.mkdirs();
            zip.createNewFile();
        }
        System.out.println("create the  zip file successfully");
        // 创建zip文件输出流
        FileOutputStream fos = new FileOutputStream(zip);
        ZipOutputStream zos = new ZipOutputStream(fos);
        System.out.println("create the empty zip stream successfully");
        //循环读取文件路径集合，获取每一个文件的路径（将文件一个一个进行压缩）
        for (java.io.File fp : vecFile) {
            zipFile(fp, zos); // 将每一个文件写入zip文件包内，即进行打包
        }
        System.out.println("get the path successfully");
        // fos.close();//如果这样关两次的话会报错，java.io.IOException: Stream closed
        zos.close();
        System.out.println("ok??");
        //将打包后的文件写到客户端,使用缓冲流输出
        InputStream fis = new BufferedInputStream(new FileInputStream(zipFilePath));
        byte[] buff = new byte[4096];
        int size = 0;
        while ((size = fis.read(buff)) != -1) {
            out.write(buff, 0, size);
        }
        System.out.println("package is packed successfully");
        
        //释放和关闭输入输出流
        out.flush();//清除缓存
        out.close();
        fis.close();
        return true;

    }

    /**
     * 文件压缩的方法
     * @param inputFile
     * @param zipoutputStream
     */
    public void zipFile(java.io.File inputFile, ZipOutputStream zipoutputStream) {
        try {
            if (inputFile.exists()) { // 判断文件是否存在
                if (inputFile.isFile()) { // 判断是否属于文件，还是文件夹

                    // 创建输入流读取文件
                    FileInputStream fis = new FileInputStream(inputFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    // 将文件写入zip内，即将文件进行打包
                    ZipEntry ze = new ZipEntry(inputFile.getName()); // 获取文件名
                    zipoutputStream.putNextEntry(ze);

                    // 写入文件的方法，同上
                    byte[] b = new byte[1024];
                    long l = 0;
                    while (l < inputFile.length()) {
                        int j = bis.read(b, 0, 1024);
                        l += j;
                        zipoutputStream.write(b, 0, j);
                    }
                    // 关闭输入输出流
                    bis.close();
                    fis.close();
                } else { // 如果是文件夹，则使用穷举的方法获取文件，写入zip
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
