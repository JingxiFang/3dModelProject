package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.SignService;

import domain.Sign;

import net.sf.json.JSONArray;

public class MarkServlet extends HttpServlet {

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//存放sign对象
		List<Sign> signList=new ArrayList<Sign>();
		//获取json对象
		String shapesStr=request.getParameter("shapes");
		
		//前台页面上传失败
		if("".equals(shapesStr)){
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			//错误页面
			return; 	
		}
		
		//取出每个Shape
		JSONArray jsonArray = JSONArray.fromObject(request.getParameter("shapes"));
		//取出每个shape属性的值
		for(int i=1;i<jsonArray.size();i++){
			JSONArray signJSON = JSONArray.fromObject(jsonArray.getString(i));
			//System.out.println(signJSON.get(0).toString());
			Sign signObj=new Sign();
			signObj.setSharpType(Integer.parseInt(signJSON.get(0).toString()));
			signObj.setStartX(Integer.parseInt(signJSON.get(1).toString()));
			signObj.setStartY(Integer.parseInt(signJSON.get(2).toString()));
			signObj.setEndX(Integer.parseInt(signJSON.get(3).toString()));
			signObj.setEndY(Integer.parseInt(signJSON.get(4).toString()));
			signObj.setSharpColor(signJSON.get(5).toString());
			//signObj.setTag_id(Integer.parseInt(signJSON.get(6).toString()));
			//当前默认tagId=1
			signObj.setTag_id(1);
			signObj.setDescription(signJSON.get(7).toString());
			//默认为159
			signObj.setFileId(159);
			
			
			signList.add(signObj);
			 
		}
		
		//上传到数据库中
		SignService signService = new SignService();
		try {
			if(!signService.addSigns(signList)){
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//System.out.println("array"+jsonArray.get(1));
		//Object[] obj = CommFunctions.toArrays(jsonArray);
		//System.out.println("marks"+marks.get(1));
        

		out.flush();
		out.close();
	}

}
