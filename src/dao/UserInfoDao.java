package dao;

import java.util.List;

import domain.UserInfo;

/**
 * UserInfo的Dao
 */
public interface UserInfoDao {
	
	/**
	 * 根据条件查询用户集合（拓展：多个条件（未开发））
	 * @param u_name 姓名
	 * @return 用户集合
	 * @author hy
	 */
	public List<UserInfo> findUser(String u_name);
	
	/**
	 * 通过用户名查询用户信息
	 * @param u_id 用户名
	 * @return 用户信息
	 * @author hy
	 */
	public List<UserInfo> findUserByid(String u_id);
	
	/**
	 * 修改用户信息
	 * @param user 用户
	 * @return 修改是否成功
	 * @author hy
	 */
	public boolean updateUserByid(UserInfo user);
	
	/**
	 * 删除用户
	 * @param u_id 用户名
	 * @return 删除是否成功
	 * @author hy
	 */
	public boolean deleteUserByid(String u_id);
	
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
	 * 检查用户名是否已存在
	 * @param u_id 用户名
	 * @return 用户名是否已存在
	 * @author zh
	 */
	public boolean isExist(String u_id);
	
	/**
	 * 用户注册
	 * @param userinfo
	 * @return 注册是否成功
	 * @author zh
	 */
	public Boolean insertUserExec(UserInfo userinfo);
	
	/**
	 * 检查UserInfo的用户名和密码是否匹配
	 * @param user UserInfo信息
	 * @return 反馈信息
	 * @author lxq
	 */
	public UserInfo checkPwdAndLoginId(UserInfo user);
	
	/**
	 * 查询UserInfo的信息
	 * @param user UserInfo信息
	 * @return UserInfo
	 * @author lxq
	 */
	public UserInfo findUserInfo(UserInfo user);
	
	/**
	 * 查询UserInfo的信息
	 * @param user userid
	 * @return UserInfo
	 * @author fjx  
	 * 搞不懂lxq写的findUserInfo方法为什么传 user对象 故加之 
	 */
	public UserInfo findUserById(String userId) ;
}
