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
 * Manager的Service
 */
public class ManagerDaoJDBCImpl implements ManagerDao {
	
	/**
	 * 修改密码之前检查用户旧密码输入是否正确
	 * @param id 用户名
	 * @param oldpwd 旧密码
	 * @return 旧密码是否正确
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
	 * 修改管理员密码
	 * @param id 用户名
	 * @param newpwd 新密码
	 * @return 修改是否成功
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
	 * 检查Manager的用户名和密码是否匹配
	 * @param manager Manager信息
	 * @return 反馈信息
	 * @author lxq
	 */
	public Manager checkPwdAndLoginId(Manager manager) {
		Manager mgr = null;
		String sql="select m_state from manager where m_name=? and m_pwd=?";
		List<Object> params =new ArrayList<Object>();
		params.add(manager.getM_name());
		params.add(manager.getM_pwd());
		Map<String,Object> map = SqlHelper.returnSimpleResult(sql, params);	// 调用SqlHelper
		if(map.size()>0){	// 存在结果集时
			mgr = new Manager();
			mgr.setM_state((Integer)map.get("m_state"));
		}
		return mgr;
	}

	/**
	 * 记录Manager登录和退出系统时间
	 * @param manager Manager信息
	 * @return 是否添加记录成功
	 * @author lxq
	 */
	public boolean checkInOrOut(Manager manager, String action) {
		String sql="insert into loginrecord(m_name,time,action) VALUES(?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(manager.getM_name());
		params.add(new Date());
		params.add(action);
		return SqlHelper.Execute(sql, params);	// 调用SqlHelper
	}
	
	/**
	 * 查询Manager的信息
	 * @param manager Manager信息
	 * @return Manager
	 * @author lxq
	 */
	public Manager findUserInfo(Manager manager){
		Manager m = null;
		String sql="select m_name,m_state from manager where m_name=?";
		List<Object> params =new ArrayList<Object>();
		params.add(manager.getM_name());
		Map<String,Object> map = SqlHelper.returnSimpleResult(sql, params);	// 调用SqlHelper
		if(map.size()>0){	// 存在结果集时
			m = new Manager();
			m.setM_name(map.get("m_name").toString());
			m.setM_state((Integer)map.get("m_state"));
		}
		return m;
	}
}
