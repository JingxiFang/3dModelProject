package dao;

import domain.Manager;

/**
 * Manager的Dao
 */
public interface ManagerDao {
	
	/**
	 * 修改密码之前检查用户旧密码输入是否正确
	 * @param id 用户名
	 * @param oldpwd 旧密码
	 * @return 旧密码是否正确
	 * @author xjy
	 */
	public boolean updatePwdcheck(String id,String oldpwd);
	
	/**
	 * 修改用户密码
	 * @param id 用户名
	 * @param newpwd 新密码
	 * @return 修改是否成功
	 * @author xjy
	 */
	public boolean updatePwd(String id,String newpwd);
	
	/**
	 * 检查Manager的用户名和密码是否匹配
	 * @param manager Manager信息
	 * @return 反馈信息
	 * @author lxq
	 */
	public Manager checkPwdAndLoginId(Manager manager);
	
	/**
	 * 记录Manager登录和退出系统时间
	 * @param manager Manager信息
	 * @return 是否添加记录成功
	 * @author lxq
	 */
	public boolean checkInOrOut(Manager manager,String action);

	/**
	 * 查询Manager的信息
	 * @param manager Manager信息
	 * @return Manager
	 * @author lxq
	 */
	public Manager findUserInfo(Manager manager);
}
