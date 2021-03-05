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
	 * ������
	 * @return
	 */
	private static Connection getConnection(){
		try
		{
			//���ݿ�����
			Class.forName(duixiang);
			//���ݿ�Ķ�������
			return DriverManager.getConnection(dizhi,user,pass);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("�������ݿ�ʧ��");
		}
	}

	/***
	 * �ر�����
	 */
	public static void closeConnection(){
		try {
			if(resultset!=null){
				resultset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ResultSet�ر�ʧ��");
		}finally{
			resultset=null;
			try{
				if(pStatement!=null){
					pStatement.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException("PreparedStatement�ر�ʧ��");
			}finally{
				pStatement=null;
				try {
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("Connection�ر�ʧ��");
				}finally{
					conn=null;
				}//finally conn end
			}//finally pStatement end
		}//finally resultset end
	} //method end

	/**
	 * ��ɶ����ݿ����ɾ�Ĳ���
	 * @param sql���
	 * @param �����ռλ����List����
	 * @return SQL���ִ�гɹ�����true,���򷵻�false
	 * @throws SQLException
	 */
	public static boolean Execute(String sql,List<Object>params) 
	{
		int result = -1;//����Ϊ
		try {
			conn=getConnection();
			pStatement = conn.prepareStatement(sql);
			//���ռλ��
			int index = 1; //�ӵ�һ����ʼ���
			if(params != null && !params.isEmpty())
			{
				for(int i = 0;i<params.size();i++)
				{
					pStatement.setObject(index++,params.get(i));//���ռλ��
				}
			}
			result = pStatement.executeUpdate();//ִ�гɹ������ش���0����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection();
		}	
		return  result>-1 ? true : false;
	}

	/**
	 * ����һ�����ݲ�������������ʱʹ��
	 * @param sql sql���
	 * @param params �����ռλ����List����
	 * @param op ��ȡ�����Ĳ���
	 * @return   ���ظ����ɹ��������ֵ
	 * ����������MySql���������ݿ��ܹ�ʹ��δ֪��
	 * 
	 */
	public static Object Execute(String sql,List<Object>params,int op) 
	{
		int result = -1;//����Ϊ
		ResultSet  set;
		int rValue=-1;
		try {
			conn=getConnection();
			pStatement = conn.prepareStatement(sql,op);
			//���ռλ��
			int index = 1; //�ӵ�һ����ʼ���
			if(params != null && !params.isEmpty())
			{
				for(int i = 0;i<params.size();i++)
				{
					pStatement.setObject(index++,params.get(i));//���ռλ��
				}
			}
			result=pStatement.executeUpdate();//ִ�гɹ������ش���0����
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
	 * ���ݿ��ѯ���������ص�����¼
	 * @param sql
	 * @param params
	 * @param sql���
	 * @param �����ռλ��
	 * @return ����Map�������ͣ�������ѯ�Ľ��
	 * @throws SQLException
	 */
	public static Map<String,Object> returnSimpleResult(String sql,List<Object> params)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		int index = 1;//��1��ʼ����ռλ��
		try {
			conn=getConnection();
			//System.out.print("���ӳɹ�");
			pStatement = conn.prepareStatement(sql);
			if(params != null && !params.isEmpty()) /*�жϲ����Ƿ�Ϊ��*/
			{ 
				for(int i = 0;i<params.size();i++) /*ѭ�����ռλ��*/
				{
					//System.out.println(conn);
					pStatement.setString(index++, params.get(i).toString());
				}
			}
			//resultset = pStatement.executeQuery(sql);
			resultset = pStatement.executeQuery();
			/*  ����ѯ�����װ��map����*/
			ResultSetMetaData metaDate = resultset.getMetaData();//��ȡresultSet�е���Ϣ
			int columnLength = metaDate.getColumnCount();//����еĳ���
			while(resultset.next())
			{
				for(int i = 0;i<columnLength;i++)
				{
					String metaDateKey = metaDate.getColumnName(i+1);//�������
					Object resultsetValue = resultset.getObject(metaDateKey);//ͨ���������ֵ

					if(resultsetValue == null)
					{
						resultsetValue = "";//ת��String����
					}
					map.put(metaDateKey, resultsetValue);//��ӵ�map���ϣ����ϴ�����Ϊ�˽������ݿⷵ�ص�ֵת����map��key��value��
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
	 * ��ѯ���ݿ⣬���ض�����¼
	 * @param sql
	 * @param params
	 * @param sql���
	 * @param ռλ��
	 * @return list���ϣ�������ѯ�Ľ��
	 * @throws SQLException 
	 */
	public static List<Map<String,Object>> returnMultipleResult(String sql,List<Object> params) 
	{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//���ռλ��
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
		//ִ��SQL���
		resultset = pStatement.executeQuery();
		//��װresultset��map����
		ResultSetMetaData metaDate = resultset.getMetaData();//��ȡ����Ϣ,����metaDate
		int columnlength = metaDate.getColumnCount();
		while(resultset.next())
		{
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i = 0;i<columnlength;i++)
			{
				String metaDateKey = metaDate.getColumnName(i+1);//��ȡ����
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
	
	
	//ִ������sql���
	public static int Execute(String sql,List<List<Object>>paramsList) throws SQLException 
	{
		//int result = -1;//����Ϊ
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
					//���ռλ��
					index = 1; //�ӵ�һ����ʼ���
					if(params != null && !params.isEmpty())
					{
						for(int i = 0;i<params.size();i++)
						{
							pStatement.setObject(index++,params.get(i));//���ռλ��
						}
						pStatement.addBatch();
						
					}
				}
			}
			
			int[] rows = pStatement.executeBatch();
			row = rows.length;
			
			conn.commit(); //�����ύ	 
			conn.setAutoCommit(true);//�ָ��Զ��ύģʽ
			 
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


