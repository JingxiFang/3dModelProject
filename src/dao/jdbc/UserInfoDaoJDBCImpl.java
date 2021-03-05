package dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.SqlHelper;
import dao.UserInfoDao;
import domain.UserInfo;

/**
 * UserInfo��Service
 */
public class UserInfoDaoJDBCImpl implements UserInfoDao {
	/**
	 * ����������ѯ�û����ϣ���չ�����������δ��������
	 * @param u_name ����
	 * @return �û�����
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
	 * ͨ���û�����ѯ�û���Ϣ
	 * @param u_id �û���
	 * @return �û���Ϣ
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
	 * �޸��û���Ϣ
	 * @param user �û�
	 * @return �޸��Ƿ�ɹ�
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
	 * ɾ���û�
	 * @param u_id �û���
	 * @return ɾ���Ƿ�ɹ�
	 * @author hy
	 */
	public boolean deleteUserByid(String u_id) {
		String sql="delete from userinfo where u_id=? ";
		List<Object> params =new ArrayList<Object>();
		params.add(u_id);
		return SqlHelper.Execute(sql, params);
	}
	
	/**
	 * �޸�����֮ǰ����û������������Ƿ���ȷ
	 * @param id �û���
	 * @param oldpwd ������
	 * @return �������Ƿ���ȷ
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
	 * �޸��û�����
	 * @param id �û���
	 * @param newpwd ������
	 * @return �޸��Ƿ�ɹ�
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
	 * ����û����Ƿ��Ѵ���
	 * @param u_id �û���
	 * @return �û����Ƿ��Ѵ���
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
	 * �û�ע��
	 * @param userinfo
	 * @return ע���Ƿ�ɹ�
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
	 * ���UserInfo���û����������Ƿ�ƥ��
	 * @param �û���Ϣ
	 * @return ������Ϣ
	 * @author lxq
	 */
	public UserInfo checkPwdAndLoginId(UserInfo user) {
		UserInfo u = null;
		String sql="select u_state from userinfo where u_id=? and u_pwd=?";
		List<Object> params =new ArrayList<Object>();
		params.add(user.getU_id());
		params.add(user.getU_pwd());
		Map<String,Object> map = SqlHelper.returnSimpleResult(sql, params);	// ����SqlHelper
		if(map.size()>0){	// ���ڽ����ʱ
			u = new UserInfo();
			u.setU_state((Integer)map.get("u_state"));
		}
		return u;
	}
	
	/**
	 * ��ѯUserInfo����Ϣ
	 * @param user UserInfo��Ϣ
	 * @return UserInfo
	 * @author lxq
	 */
	public UserInfo findUserInfo(UserInfo user) {
		UserInfo u = null;
		String sql="select u_id,u_name,u_state from userinfo where u_id=?";
		List<Object> params =new ArrayList<Object>();
		params.add(user.getU_id());
		Map<String,Object> map = SqlHelper.returnSimpleResult(sql, params);	// ����SqlHelper
		if(map.size()>0){	// ���ڽ����ʱ
			u = new UserInfo();
			u.setU_id(map.get("u_id").toString());
			u.setU_name(map.get("u_name").toString());
			u.setU_state((Integer)map.get("u_state"));
		}
		return u;
	}
	
	/**
	 * ��ѯUserInfo����Ϣ
	 * @param user userid
	 * @return UserInfo
	 * @author fjx  
	 * �㲻��lxqд��findUserInfo����Ϊʲô�� user���� �ʼ�֮ 
	 */
	public UserInfo findUserById(String userId) {
		UserInfo u = null;
		String sql="select u_id,u_name,u_age,u_sex,u_email,u_phone from userinfo where u_id=?";
		List<Object> params =new ArrayList<Object>();
		params.add(userId);
		Map<String,Object> map = SqlHelper.returnSimpleResult(sql, params);	// ����SqlHelper
		if(map.size()>0){	// ���ڽ����ʱ
		
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
