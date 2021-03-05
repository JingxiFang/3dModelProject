package dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.SqlHelper;

/**
 * 工具类-通过T-SQL语句操作远程服务器上的表
 * @author lxq
 */
public class UtilDaoJDBCImpl {
	
	/**
	 * 工具方法(修改Manager)
	 */
	public void updateTableManager(String m_name,int m_state){
		
		System.out.println("m_name:"+m_name+",m_state:"+m_state);
		String sql="update manager set m_state=? where m_name=?";
		List<Object> params=new ArrayList<Object>();
		//params.add(m_pwd);	// 密码，暂无要求
		params.add(m_state);	// 用户类型
		params.add(m_name);		// 用户名，主键，字母+数字
		boolean s = SqlHelper.Execute(sql, params);
		System.out.println(s);
	}
	
	/**
	 * 工具方法(查询Manager)
	 */
	public void selectTableManager() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql="select * from manager";
		List<Object> params=new ArrayList<Object>();
		list=SqlHelper.returnMultipleResult(sql, params);	// 调用SqlHelper
		if(list.size()>0){
			System.out.println("m_name	m_pwd	m_state");
			for(Map<String,Object> map : list){
				String m_name = map.get("m_name").toString();
				String m_pwd = map.get("m_pwd").toString();
				String m_state = map.get("m_state").toString();
				System.out.println(m_name+"	"+m_pwd+"	"+m_state);
			}
		}
	}
	
	/**
	 * 工具方法(查询loginrecord)
	 */
	public void selectTableLoginrecord(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql="select * from loginrecord";
		List<Object> params=new ArrayList<Object>();
		list=SqlHelper.returnMultipleResult(sql, params);	// 调用SqlHelper
		if(list.size()>0){
			System.out.println("id	m_name	time	_action");
			for(Map<String,Object> map : list){
				String id = map.get("id").toString();
				String m_name = map.get("m_name").toString();
				String time = map.get("time").toString();
				String _action = map.get("action").toString();
				System.out.println(id+"	"+m_name+"	"+time+"	"+_action);
			}
		}
	}
	
	/*public void insertTest(){
		String sql="insert into manager() VALUES('333','111',1)";
		List<Object> params=new ArrayList<Object>();
		SqlHelper.Execute(sql, params);	// 调用SqlHelper
	}*/
	
	/**
	 * 工具方法(查询userinfo)
	 */
	public void selectTableUserinfo(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql="select u_id,u_name,u_pwd,u_state from userinfo";
		List<Object> params=new ArrayList<Object>();
		list=SqlHelper.returnMultipleResult(sql, params);	// 调用SqlHelper
		if(list.size()>0){
			System.out.println("u_id	u_name	u_pwd	u_state");
			for(Map<String,Object> map : list){
				String u_id = map.get("u_id").toString();
				String u_name = map.get("u_name").toString();
				String u_pwd = map.get("u_pwd").toString();
				String u_state = map.get("u_state").toString();
				System.out.println(u_id+"	"+u_name+"	"+u_pwd+"	"+u_state);
			}
		}
	}
	
	/**
	 * 工具方法(添加userinfo)
	 */
	public void insertTableUserinfo(){
		String sql="insert into userinfo(u_id,u_name,u_pwd,u_state) VALUES(?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add("");	// 用户名，主键，字母+数字
		params.add("");	// 姓名
		params.add("");	// 密码，暂无要求
		params.add(1);	// 用户类型
		SqlHelper.Execute(sql, params);
	}
	
	/**
	 * 工具方法(删除userinfo)
	 */
	public void deleteTableUserinfo(String u_id){
		String sql="delete from userinfo where u_id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(u_id);	// 用户名，主键，字母+数字
		/*params.add("");	// 姓名
		params.add("");	// 密码，暂无要求
		params.add(1);	// 用户类型*/
		SqlHelper.Execute(sql, params);
	}
	
	/**
	 * 工具方法(修改userinfo)
	 */
	public void updateTableUserinfo(String u_id,int u_state){
		String sql="update userinfo set u_state=? where u_id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(u_id);	// 用户名，主键，字母+数字
		params.add(u_state);	// 用户类型
		
		/*params.add(u_pwd);	// 密码，暂无要求
		params.add(u_name);	// 姓名*/
		SqlHelper.Execute(sql, params);
	}
}
