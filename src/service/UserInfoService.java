package service;
import java.util.List;

import dao.UserInfoDao;
import dao.jdbc.UserInfoDaoJDBCImpl;
import domain.UserInfo;

/**
 * UserInfo��Service
 */
public class UserInfoService {
	UserInfoDao userInfoDao = new UserInfoDaoJDBCImpl();
	
	/**
	 * ����������ѯ�û����ϣ���չ�����������δ��������
	 * @param u_name ����
	 * @return �û�����
	 * @author hy
	 */
	public List<UserInfo> findUser(String u_name){
		return userInfoDao.findUser(u_name);
		
	}
	
	/**
	 * ͨ���û�����ѯ�û���Ϣ
	 * @param u_id �û���
	 * @return �û���Ϣ
	 * @author hy
	 */
	public List<UserInfo> findUserByid(String u_id){
		return userInfoDao.findUserByid(u_id);
		
	}
	
	/**
	 * �޸��û���Ϣ
	 * @param user �û�
	 * @return �޸��Ƿ�ɹ�
	 * @author hy
	 */
	public  boolean updateUserByid(UserInfo user) {
		return userInfoDao.updateUserByid(user);
	}
	
	/**
	 * ɾ���û�
	 * @param u_id �û���
	 * @return ɾ���Ƿ�ɹ�
	 * @author hy
	 */
	public boolean deleteUserByid(String u_id) {
		return userInfoDao.deleteUserByid(u_id);
	}
	
	/**
	 * �޸�����֮ǰ����û������������Ƿ���ȷ
	 * @param id �û���
	 * @param oldpwd ������
	 * @return �������Ƿ���ȷ
	 * @author xjy
	 */
	public boolean updatePwdcheck(String id,String oldpwd){
		return userInfoDao.updatePwdcheck(id, oldpwd);
	}
	
	/**
	 * �޸��û�����
	 * @param id �û���
	 * @param newpwd ������
	 * @return �޸��Ƿ�ɹ�
	 * @author xjy
	 */
	public boolean updatePwd(String id,String newpwd){
		return userInfoDao.updatePwd(id, newpwd);
	}
	
	/**
	 * ����û����Ƿ��Ѵ���
	 * @param u_id �û���
	 * @return �û����Ƿ��Ѵ���
	 * @author zh
	 */
	public boolean isExist(String u_id){
		return userInfoDao.isExist(u_id);
	}
	

	
	/**
	 * �û�ע��
	 * @param userinfo	�û���
	 * @return ��Ӧ�ķ�����Ϣ
	 * @author zh
	 */
	public String register(UserInfo userinfo){
		if(userInfoDao.insertUserExec(userinfo)){
			return "ע��ɹ�";	
		}else{
			return "��ѽ ϵͳ������";	// ��չ����תϵͳ����ҳ�棨δ������
		}
	}
	
	/**
	 * ���UserInfo���û����������Ƿ�ƥ��
	 * @param user UserInfo��Ϣ
	 * @return ������Ϣ
	 * @author lxq
	 * modify��fjx �޸�ԭ��service���в�Ӧ�ó���view���е�ҳ�����Ϣ
	 */
	public String[] checkPwdAndLoginId(UserInfo user) {
		
		
		String[] message = new String[2];	// ��1λ���Ƿ�ƥ�䣻��2λ��������Ϣ/��Ȩ�ޣ�   
		UserInfo u = userInfoDao.checkPwdAndLoginId(user);
		
		if (u != null) {					// ƥ��ɹ�
			
			message[0] = "true";
			message[1] =Integer.toString(u.getU_state()) ;
		} else {	// ƥ��ʧ��
			String errorMsg = "�û������������������������<a href='#'>�һ�����</a>";	// ���ش�����Ϣ
			message[0] = "false";
			message[1] = errorMsg;
		}
		return message;
		
	}
	
	/**
	 * ��ѯUserInfo����Ϣ
	 * @param user UserInfo��Ϣ
	 * @return UserInfo
	 * @author lxq
	 */
	public UserInfo findUserInfo(UserInfo user) {
		return userInfoDao.findUserInfo(user);
	}
	
	/**
	 * ��ѯUserInfo����Ϣ
	 * @param user userid
	 * @return UserInfo
	 * @author fjx  
	 * �㲻��lxqд��findUserInfo����Ϊʲô�� user���� �ʼ�֮ 
	 */
	public UserInfo findUserById(String userId) {
		return userInfoDao.findUserById(userId);
	}
}
