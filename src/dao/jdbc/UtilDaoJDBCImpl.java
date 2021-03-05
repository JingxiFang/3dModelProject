package dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.SqlHelper;

/**
 * ������-ͨ��T-SQL������Զ�̷������ϵı�
 * @author lxq
 */
public class UtilDaoJDBCImpl {
	
	/**
	 * ���߷���(�޸�Manager)
	 */
	public void updateTableManager(String m_name,int m_state){
		
		System.out.println("m_name:"+m_name+",m_state:"+m_state);
		String sql="update manager set m_state=? where m_name=?";
		List<Object> params=new ArrayList<Object>();
		//params.add(m_pwd);	// ���룬����Ҫ��
		params.add(m_state);	// �û�����
		params.add(m_name);		// �û�������������ĸ+����
		boolean s = SqlHelper.Execute(sql, params);
		System.out.println(s);
	}
	
	/**
	 * ���߷���(��ѯManager)
	 */
	public void selectTableManager() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql="select * from manager";
		List<Object> params=new ArrayList<Object>();
		list=SqlHelper.returnMultipleResult(sql, params);	// ����SqlHelper
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
	 * ���߷���(��ѯloginrecord)
	 */
	public void selectTableLoginrecord(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql="select * from loginrecord";
		List<Object> params=new ArrayList<Object>();
		list=SqlHelper.returnMultipleResult(sql, params);	// ����SqlHelper
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
		SqlHelper.Execute(sql, params);	// ����SqlHelper
	}*/
	
	/**
	 * ���߷���(��ѯuserinfo)
	 */
	public void selectTableUserinfo(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql="select u_id,u_name,u_pwd,u_state from userinfo";
		List<Object> params=new ArrayList<Object>();
		list=SqlHelper.returnMultipleResult(sql, params);	// ����SqlHelper
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
	 * ���߷���(���userinfo)
	 */
	public void insertTableUserinfo(){
		String sql="insert into userinfo(u_id,u_name,u_pwd,u_state) VALUES(?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add("");	// �û�������������ĸ+����
		params.add("");	// ����
		params.add("");	// ���룬����Ҫ��
		params.add(1);	// �û�����
		SqlHelper.Execute(sql, params);
	}
	
	/**
	 * ���߷���(ɾ��userinfo)
	 */
	public void deleteTableUserinfo(String u_id){
		String sql="delete from userinfo where u_id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(u_id);	// �û�������������ĸ+����
		/*params.add("");	// ����
		params.add("");	// ���룬����Ҫ��
		params.add(1);	// �û�����*/
		SqlHelper.Execute(sql, params);
	}
	
	/**
	 * ���߷���(�޸�userinfo)
	 */
	public void updateTableUserinfo(String u_id,int u_state){
		String sql="update userinfo set u_state=? where u_id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(u_id);	// �û�������������ĸ+����
		params.add(u_state);	// �û�����
		
		/*params.add(u_pwd);	// ���룬����Ҫ��
		params.add(u_name);	// ����*/
		SqlHelper.Execute(sql, params);
	}
}
