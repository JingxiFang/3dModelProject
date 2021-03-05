package service;

import dao.ManagerDao;
import dao.jdbc.ManagerDaoJDBCImpl;
import domain.Manager;

/**
 * Manager的Service
 */
public class ManagerService {
	ManagerDao managerDao = new ManagerDaoJDBCImpl();

	/**
	 * 修改密码之前检查用户旧密码输入是否正确
	 * @param id 用户名
	 * @param oldpwd 旧密码
	 * @return 旧密码是否正确
	 * @author xjy
	 */
	public boolean updatePwdcheck(String id,String oldpwd){
		return managerDao.updatePwdcheck(id, oldpwd);
	}
	
	/**
	 * 修改用户密码
	 * @param id 用户名
	 * @param newpwd 新密码
	 * @return 修改是否成功
	 * @author xjy
	 */
	public boolean updatePwd(String id,String newpwd){
		return managerDao.updatePwd(id, newpwd);
	}
	
	/**
	 * 检查Manager的用户名和密码是否匹配
	 * @param manager Manager信息
	 * @return 反馈信息
	 * @author lxq  
	 * 修改 fjx
	 */
	public String[] checkPwdAndLoginId(Manager manager) {
		String[] message = new String[2];	// 第1位：是否匹配；第2位：返回信息
		Manager mgr = managerDao.checkPwdAndLoginId(manager);

		if (mgr != null) {	// 匹配成功
			
			message[0] = "true";
			message[1] =String.valueOf( mgr.getM_state());
		} else {	// 匹配失败
			String errorMsg = "用户名或密码有误，请重新输入或<a href='#'>找回密码</a>";	// 返回错误信息
			message[0] = "false";
			message[1] = errorMsg;
		}
		return message;
	}
	
	/**
	 * 记录Manager登录和退出系统时间
	 * @param manager Manager信息
	 * @return 是否添加记录成功
	 * @author lxq
	 */
	public boolean checkInOrOut(Manager manager,String action) {
		boolean b = managerDao.checkInOrOut(manager,action);
		if(b){
			System.out.println("记录操作成功！");
		}else{
			System.out.println("记录操作失败!");
		}
		return b;
	}

	/**
	 * 查询Manager的信息
	 * @param manager Manager信息
	 * @return Manager
	 * @author lxq
	 */
	public Manager findManangerInfo(Manager manager) {
		return managerDao.findUserInfo(manager);
	}
}
