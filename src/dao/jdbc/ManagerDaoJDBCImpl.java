package dao.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import util.SqlHelper;
import dao.ManagerDao;
import domain.Manager;
import domain.UserInfo;

/**
 * Manager��Service
 */
public class ManagerDaoJDBCImpl implements ManagerDao {
	
	/**
	 * �޸�����֮ǰ����û������������Ƿ���ȷ
	 * @param id �û���
	 * @param oldpwd ������
	 * @return �������Ƿ���ȷ
	 * @author xjy
	 */
	public boolean updatePwdcheck(String id, String oldpwd) {
		boolean flag = false;
		String sql="select m_pwd from manager where m_name = ?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		Map<String,Object> pwd = SqlHelper.returnSimpleResult(sql, params);
		if(pwd.size()>0){
			if(pwd.get("m_pwd").toString().equals(oldpwd)){
				flag=true;
			}
        }
		return flag;
	}
	
	/**
	 * �޸Ĺ���Ա����
	 * @param id �û���
	 * @param newpwd ������
	 * @return �޸��Ƿ�ɹ�
	 * @author xjy
	 */
	public boolean updatePwd(String id, String newpwd) {
		String sql="update manager set m_pwd = ? where m_name = ?";
		List<Object> params =new ArrayList<Object>();
		params.add(newpwd);
		params.add(id);
        return SqlHelper.Execute(sql, params);
	}

	/**
	 * ���Manager���û����������Ƿ�ƥ��
	 * @param manager Manager��Ϣ
	 * @return ������Ϣ
	 * @author lxq
	 */
	public Manager checkPwdAndLoginId(Manager manager) {
		Manager mgr = null;
		String sql="select m_state from manager where m_name=? and m_pwd=?";
		List<Object> params =new ArrayList<Object>();
		params.add(manager.getM_name());
		params.add(manager.getM_pwd());
		Map<String,Object> map = SqlHelper.returnSimpleResult(sql, params);	// ����SqlHelper
		if(map.size()>0){	// ���ڽ����ʱ
			mgr = new Manager();
			mgr.setM_state((Integer)map.get("m_state"));
		}
		return mgr;
	}

	/**
	 * ��¼Manager��¼���˳�ϵͳʱ��
	 * @param manager Manager��Ϣ
	 * @return �Ƿ���Ӽ�¼�ɹ�
	 * @author lxq
	 */
	public boolean checkInOrOut(Manager manager, String action) {
		String sql="insert into loginrecord(m_name,time,action) VALUES(?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(manager.getM_name());
		params.add(new Date());
		params.add(action);
		return SqlHelper.Execute(sql, params);	// ����SqlHelper
	}
	
	/**
	 * ��ѯManager����Ϣ
	 * @param manager Manager��Ϣ
	 * @return Manager
	 * @author lxq
	 */
	public Manager findUserInfo(Manager manager){
		Manager m = null;
		String sql="select m_name,m_state from manager where m_name=?";
		List<Object> params =new ArrayList<Object>();
		params.add(manager.getM_name());
		Map<String,Object> map = SqlHelper.returnSimpleResult(sql, params);	// ����SqlHelper
		if(map.size()>0){	// ���ڽ����ʱ
			m = new Manager();
			m.setM_name(map.get("m_name").toString());
			m.setM_state((Integer)map.get("m_state"));
		}
		return m;
	}
}
