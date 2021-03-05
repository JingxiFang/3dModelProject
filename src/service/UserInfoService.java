package service;
import java.util.List;

import dao.UserInfoDao;
import dao.jdbc.UserInfoDaoJDBCImpl;
import domain.UserInfo;

/**
 * UserInfo的Service
 */
public class UserInfoService {
	UserInfoDao userInfoDao = new UserInfoDaoJDBCImpl();
	
	/**
	 * 根据条件查询用户集合（拓展：多个条件（未开发））
	 * @param u_name 姓名
	 * @return 用户集合
	 * @author hy
	 */
	public List<UserInfo> findUser(String u_name){
		return userInfoDao.findUser(u_name);
		
	}
	
	/**
	 * 通过用户名查询用户信息
	 * @param u_id 用户名
	 * @return 用户信息
	 * @author hy
	 */
	public List<UserInfo> findUserByid(String u_id){
		return userInfoDao.findUserByid(u_id);
		
	}
	
	/**
	 * 修改用户信息
	 * @param user 用户
	 * @return 修改是否成功
	 * @author hy
	 */
	public  boolean updateUserByid(UserInfo user) {
		return userInfoDao.updateUserByid(user);
	}
	
	/**
	 * 删除用户
	 * @param u_id 用户名
	 * @return 删除是否成功
	 * @author hy
	 */
	public boolean deleteUserByid(String u_id) {
		return userInfoDao.deleteUserByid(u_id);
	}
	
	/**
	 * 修改密码之前检查用户旧密码输入是否正确
	 * @param id 用户名
	 * @param oldpwd 旧密码
	 * @return 旧密码是否正确
	 * @author xjy
	 */
	public boolean updatePwdcheck(String id,String oldpwd){
		return userInfoDao.updatePwdcheck(id, oldpwd);
	}
	
	/**
	 * 修改用户密码
	 * @param id 用户名
	 * @param newpwd 新密码
	 * @return 修改是否成功
	 * @author xjy
	 */
	public boolean updatePwd(String id,String newpwd){
		return userInfoDao.updatePwd(id, newpwd);
	}
	
	/**
	 * 检查用户名是否已存在
	 * @param u_id 用户名
	 * @return 用户名是否已存在
	 * @author zh
	 */
	public boolean isExist(String u_id){
		return userInfoDao.isExist(u_id);
	}
	

	
	/**
	 * 用户注册
	 * @param userinfo	用户名
	 * @return 相应的反馈信息
	 * @author zh
	 */
	public String register(UserInfo userinfo){
		if(userInfoDao.insertUserExec(userinfo)){
			return "注册成功";	
		}else{
			return "哎呀 系统生病了";	// 拓展：跳转系统错误页面（未开发）
		}
	}
	
	/**
	 * 检查UserInfo的用户名和密码是否匹配
	 * @param user UserInfo信息
	 * @return 反馈信息
	 * @author lxq
	 * modify：fjx 修改原因：service层中不应该出现view层中的页面等信息
	 */
	public String[] checkPwdAndLoginId(UserInfo user) {
		
		
		String[] message = new String[2];	// 第1位：是否匹配；第2位：返回信息/或权限；   
		UserInfo u = userInfoDao.checkPwdAndLoginId(user);
		
		if (u != null) {					// 匹配成功
			
			message[0] = "true";
			message[1] =Integer.toString(u.getU_state()) ;
		} else {	// 匹配失败
			String errorMsg = "用户名或密码有误，请重新输入或<a href='#'>找回密码</a>";	// 返回错误信息
			message[0] = "false";
			message[1] = errorMsg;
		}
		return message;
		
	}
	
	/**
	 * 查询UserInfo的信息
	 * @param user UserInfo信息
	 * @return UserInfo
	 * @author lxq
	 */
	public UserInfo findUserInfo(UserInfo user) {
		return userInfoDao.findUserInfo(user);
	}
	
	/**
	 * 查询UserInfo的信息
	 * @param user userid
	 * @return UserInfo
	 * @author fjx  
	 * 搞不懂lxq写的findUserInfo方法为什么传 user对象 故加之 
	 */
	public UserInfo findUserById(String userId) {
		return userInfoDao.findUserById(userId);
	}
}
