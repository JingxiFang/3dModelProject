package dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.SqlHelper;
import dao.UserInfoDao;
import domain.UserInfo;

/**
 * UserInfo的Service
 */
public class UserInfoDaoJDBCImpl implements UserInfoDao {
	/**
	 * 根据条件查询用户集合（拓展：多个条件（未开发））
	 * @param u_name 姓名
	 * @return 用户集合
	 * @author hy
	 */
	public List<UserInfo> findUser(String u_name) {
 		List<UserInfo> userList=new ArrayList<UserInfo>();
 		String sql="select u_id,u_name,u_email,u_phone from userinfo ";
 		List<Object> params=new ArrayList<Object>();
 		if(u_name != null){
 			sql=sql+" where u_name like ?";
 			params.add("%"+u_name+"%");
 		}

 		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
 		list=SqlHelper.returnMultipleResult(sql, params);
 		if(list.size()>0){
 			for(Map<String,Object> map:list){
 				UserInfo user=new UserInfo();
 				user.setU_id(map.get("u_id").toString());
 				user.setU_name(map.get("u_name").toString());
 				user.setU_email(map.get("u_email").toString());
 				user.setU_phone(map.get("u_phone").toString());
 				userList.add(user);
 			}
 		}
		return userList;
	}

	/**
	 * 通过用户名查询用户信息
	 * @param u_id 用户名
	 * @return 用户信息
	 * @author hy
	 */
	public List<UserInfo> findUserByid(String u_id) {
		List<UserInfo> userList=new ArrayList<UserInfo>();
 		String sql="select u_id,u_name,u_email,u_phone,u_state from userinfo where u_id=?";
 		List<Object> params=new ArrayList<Object>();
 		params.add(u_id);	
 		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
 		list=SqlHelper.returnMultipleResult(sql, params);
 		if(list.size()>0){
 			for(Map<String,Object> map:list){
 				UserInfo user=new UserInfo();
 				user.setU_id(map.get("u_id").toString());
 				user.setU_name(map.get("u_name").toString());
 				user.setU_email(map.get("u_email").toString());
 				user.setU_phone(map.get("u_phone").toString());
 				user.setU_state(Integer.valueOf( map.get("u_state").toString()));
 				userList.add(user);
 			}
 		}
		return userList;
	}

	/**
	 * 修改用户信息
	 * @param user 用户
	 * @return 修改是否成功
	 * @author hy
	 */
	public boolean updateUserByid(UserInfo user) {
		String sql="update userinfo set u_name=?,u_email=?,u_phone=?, u_state=? where u_id=?";
		List<Object> params =new ArrayList<Object>();
		params.add(user.getU_name());
		params.add(user.getU_email());
		params.add(user.getU_phone());
		params.add(user.getU_state());
		params.add(user.getU_id());
		return SqlHelper.Execute(sql, params);
	}

	/**
	 * 删除用户
	 * @param u_id 用户名
	 * @return 删除是否成功
	 * @author hy
	 */
	public boolean deleteUserByid(String u_id) {
		String sql="delete from userinfo where u_id=? ";
		List<Object> params =new ArrayList<Object>();
		params.add(u_id);
		return SqlHelper.Execute(sql, params);
	}
	
	/**
	 * 修改密码之前检查用户旧密码输入是否正确
	 * @param id 用户名
	 * @param oldpwd 旧密码
	 * @return 旧密码是否正确
	 * @author xjy
	 */
	public boolean updatePwdcheck(String id, String oldpwd) {
		boolean flag = false;
		String sql="select u_pwd from userinfo where u_id = ?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		Map<String,Object> pwd = SqlHelper.returnSimpleResult(sql, params);
		if(pwd.size()>0){
			if(pwd.get("u_pwd").toString().equals(oldpwd)){
				flag=true;
			}
        }
		return flag;
	}

	/**
	 * 修改用户密码
	 * @param id 用户名
	 * @param newpwd 新密码
	 * @return 修改是否成功
	 * @author xjy
	 */
	public boolean updatePwd(String id, String newpwd) {
		String sql="update userinfo set u_pwd = ? where u_id = ?";
		List<Object> params =new ArrayList<Object>();
		params.add(newpwd);
		params.add(id);
        return SqlHelper.Execute(sql, params);
	}
	
	/**
	 * 检查用户名是否已存在
	 * @param u_id 用户名
	 * @return 用户名是否已存在
	 * @author zh
	 */
	public boolean isExist(String u_id) {
		boolean flag = false;
		String sql = "select count(*) as COUNT from userinfo where u_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(u_id);
		Map<String, Object> count = SqlHelper.returnSimpleResult(sql, params);
		if (count.size() > 0) {
			if (Integer.parseInt(count.get("COUNT").toString()) == 1) {
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 用户注册
	 * @param userinfo
	 * @return 注册是否成功
	 * @author zh
	 */
	public Boolean insertUserExec(UserInfo userinfo) {
		String sql = "insert into userinfo(u_id,u_pwd,u_name,u_email) VALUES(?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(userinfo.getU_id());
		params.add(userinfo.getU_pwd());
		params.add(userinfo.getU_name());
		params.add(userinfo.getU_email());
		return SqlHelper.Execute(sql, params);
	}

	/**
	 * 检查UserInfo的用户名和密码是否匹配
	 * @param 用户信息
	 * @return 反馈信息
	 * @author lxq
	 */
	public UserInfo checkPwdAndLoginId(UserInfo user) {
		UserInfo u = null;
		String sql="select u_state from userinfo where u_id=? and u_pwd=?";
		List<Object> params =new ArrayList<Object>();
		params.add(user.getU_id());
		params.add(user.getU_pwd());
		Map<String,Object> map = SqlHelper.returnSimpleResult(sql, params);	// 调用SqlHelper
		if(map.size()>0){	// 存在结果集时
			u = new UserInfo();
			u.setU_state((Integer)map.get("u_state"));
		}
		return u;
	}
	
	/**
	 * 查询UserInfo的信息
	 * @param user UserInfo信息
	 * @return UserInfo
	 * @author lxq
	 */
	public UserInfo findUserInfo(UserInfo user) {
		UserInfo u = null;
		String sql="select u_id,u_name,u_state from userinfo where u_id=?";
		List<Object> params =new ArrayList<Object>();
		params.add(user.getU_id());
		Map<String,Object> map = SqlHelper.returnSimpleResult(sql, params);	// 调用SqlHelper
		if(map.size()>0){	// 存在结果集时
			u = new UserInfo();
			u.setU_id(map.get("u_id").toString());
			u.setU_name(map.get("u_name").toString());
			u.setU_state((Integer)map.get("u_state"));
		}
		return u;
	}
	
	/**
	 * 查询UserInfo的信息
	 * @param user userid
	 * @return UserInfo
	 * @author fjx  
	 * 搞不懂lxq写的findUserInfo方法为什么传 user对象 故加之 
	 */
	public UserInfo findUserById(String userId) {
		UserInfo u = null;
		String sql="select u_id,u_name,u_age,u_sex,u_email,u_phone from userinfo where u_id=?";
		List<Object> params =new ArrayList<Object>();
		params.add(userId);
		Map<String,Object> map = SqlHelper.returnSimpleResult(sql, params);	// 调用SqlHelper
		if(map.size()>0){	// 存在结果集时
		
			u = new UserInfo();
			u.setU_id(map.get("u_id").toString());
			u.setU_name(map.get("u_name").toString());
			u.setU_age(map.get("u_age").toString());
			u.setU_phone(map.get("u_phone").toString());
			u.setU_sex(map.get("u_sex").toString());
			u.setU_email(map.get("u_email").toString());
			
		}
		return u;
	}
}
