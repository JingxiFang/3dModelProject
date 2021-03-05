package util;

import java.sql.*;
import java.util.*;


public class SqlHelper {

	private static Connection conn = null;
	private static ResultSet resultset=null;
	private static PreparedStatement pStatement=null;
	
	
	private static final String duixiang = "com.mysql.jdbc.Driver";
	/*private static final String dizhi = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8";
	private static final String user = "root";
	private static final String pass = "111";*/
	private static final String dizhi = "jdbc:mysql://cdb-5jg30rt3.gz.tencentcdb.com:10133/3d_model?autoReconnect=true&failOverReadOnly=false";
	private static final String user = "root";
	private static final String pass = "a123456789";

	/**
	 * 打开连接
	 * @return
	 */
	private static Connection getConnection(){
		try
		{
			//数据库连接
			Class.forName(duixiang);
			//数据库的对象连接
			return DriverManager.getConnection(dizhi,user,pass);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("连接数据库失败");
		}
	}

	/***
	 * 关闭连接
	 */
	public static void closeConnection(){
		try {
			if(resultset!=null){
				resultset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ResultSet关闭失败");
		}finally{
			resultset=null;
			try{
				if(pStatement!=null){
					pStatement.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException("PreparedStatement关闭失败");
			}finally{
				pStatement=null;
				try {
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("Connection关闭失败");
				}finally{
					conn=null;
				}//finally conn end
			}//finally pStatement end
		}//finally resultset end
	} //method end

	/**
	 * 完成对数据库的增删改操作
	 * @param sql语句
	 * @param 传入的占位符，List集合
	 * @return SQL语句执行成功返回true,否则返回false
	 * @throws SQLException
	 */
	public static boolean Execute(String sql,List<Object>params) 
	{
		int result = -1;//设置为
		try {
			conn=getConnection();
			pStatement = conn.prepareStatement(sql);
			//填充占位符
			int index = 1; //从第一个开始添加
			if(params != null && !params.isEmpty())
			{
				for(int i = 0;i<params.size();i++)
				{
					pStatement.setObject(index++,params.get(i));//填充占位符
				}
			}
			result = pStatement.executeUpdate();//执行成功将返回大于0的数
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection();
		}	
		return  result>-1 ? true : false;
	}

	/**
	 * 插入一条数据并主动返回主键时使用
	 * @param sql sql语句
	 * @param params 传入的占位符，List集合
	 * @param op 获取主键的参数
	 * @return   返回该语句成功后的主键值
	 * 新增（用于MySql，其他数据库能够使用未知）
	 * 
	 */
	public static Object Execute(String sql,List<Object>params,int op) 
	{
		int result = -1;//设置为
		ResultSet  set;
		int rValue=-1;
		try {
			conn=getConnection();
			pStatement = conn.prepareStatement(sql,op);
			//填充占位符
			int index = 1; //从第一个开始添加
			if(params != null && !params.isEmpty())
			{
				for(int i = 0;i<params.size();i++)
				{
					pStatement.setObject(index++,params.get(i));//填充占位符
				}
			}
			result=pStatement.executeUpdate();//执行成功将返回大于0的数
			if(result>0){
				set=pStatement.getGeneratedKeys();
				if (set.next()) {
					rValue = set.getInt(1);
				}

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection();
		}	
		return  rValue;
	}

	    



	/**
	 * 数据库查询操作，返回单条记录
	 * @param sql
	 * @param params
	 * @param sql语句
	 * @param 传入的占位符
	 * @return 返回Map集合类型，包含查询的结果
	 * @throws SQLException
	 */
	public static Map<String,Object> returnSimpleResult(String sql,List<Object> params)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		int index = 1;//从1开始设置占位符
		try {
			conn=getConnection();
			//System.out.print("连接成功");
			pStatement = conn.prepareStatement(sql);
			if(params != null && !params.isEmpty()) /*判断参数是否为空*/
			{ 
				for(int i = 0;i<params.size();i++) /*循环填充占位符*/
				{
					//System.out.println(conn);
					pStatement.setString(index++, params.get(i).toString());
				}
			}
			//resultset = pStatement.executeQuery(sql);
			resultset = pStatement.executeQuery();
			/*  将查询结果封装到map集合*/
			ResultSetMetaData metaDate = resultset.getMetaData();//获取resultSet列的信息
			int columnLength = metaDate.getColumnCount();//获得列的长度
			while(resultset.next())
			{
				for(int i = 0;i<columnLength;i++)
				{
					String metaDateKey = metaDate.getColumnName(i+1);//获得列名
					Object resultsetValue = resultset.getObject(metaDateKey);//通过列名获得值

					if(resultsetValue == null)
					{
						resultsetValue = "";//转成String类型
					}
					map.put(metaDateKey, resultsetValue);//添加到map集合（以上代码是为了将从数据库返回的值转换成map的key和value）
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return map;
	}
	
	/**
	 * 查询数据库，返回多条记录
	 * @param sql
	 * @param params
	 * @param sql语句
	 * @param 占位符
	 * @return list集合，包含查询的结果
	 * @throws SQLException 
	 */
	public static List<Map<String,Object>> returnMultipleResult(String sql,List<Object> params) 
	{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//填充占位符
		int index = 1;
		try {
			conn=getConnection();
			pStatement = conn.prepareStatement(sql);
		
		if(params != null && !params.isEmpty())
		{
			for(int i = 0;i<params.size();i++)
			{
				pStatement.setObject(index++, params.get(i));
			}
		}
		//执行SQL语句
		resultset = pStatement.executeQuery();
		//封装resultset成map类型
		ResultSetMetaData metaDate = resultset.getMetaData();//获取列信息,交给metaDate
		int columnlength = metaDate.getColumnCount();
		while(resultset.next())
		{
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i = 0;i<columnlength;i++)
			{
				String metaDateKey = metaDate.getColumnName(i+1);//获取列名
				Object resultsetValue = resultset.getObject(metaDateKey);
				if(resultsetValue == null)
				{
					resultsetValue = "";
				}
				map.put(metaDateKey, resultsetValue);
			}
			list.add(map);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();	
		}
		return list;
	}
	
	
	//执行批量sql语句
	public static int Execute(String sql,List<List<Object>>paramsList) throws SQLException 
	{
		//int result = -1;//设置为
		//ResultSet  set;
		//int rValue=-1;
		int index;
		int row = 0;
		try {
			conn=getConnection();
			conn.setAutoCommit(false);
			pStatement = conn.prepareStatement(sql);
			
			if(paramsList!=null)
			{
				for(List<Object> params:paramsList){
					//填充占位符
					index = 1; //从第一个开始添加
					if(params != null && !params.isEmpty())
					{
						for(int i = 0;i<params.size();i++)
						{
							pStatement.setObject(index++,params.get(i));//填充占位符
						}
						pStatement.addBatch();
						
					}
				}
			}
			
			int[] rows = pStatement.executeBatch();
			row = rows.length;
			
			conn.commit(); //事务提交	 
			conn.setAutoCommit(true);//恢复自动提交模式
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			conn.rollback();
			conn.setAutoCommit(true);
			e.printStackTrace();
			
		} finally{
			closeConnection();
		}	
		return  row;
	}


}


