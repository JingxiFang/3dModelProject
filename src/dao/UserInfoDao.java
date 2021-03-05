package dao;

import java.util.List;

import domain.UserInfo;

/**
 * UserInfo��Dao
 */
public interface UserInfoDao {
	
	/**
	 * ����������ѯ�û����ϣ���չ�����������δ��������
	 * @param u_name ����
	 * @return �û�����
	 * @author hy
	 */
	public List<UserInfo> findUser(String u_name);
	
	/**
	 * ͨ���û�����ѯ�û���Ϣ
	 * @param u_id �û���
	 * @return �û���Ϣ
	 * @author hy
	 */
	public List<UserInfo> findUserByid(String u_id);
	
	/**
	 * �޸��û���Ϣ
	 * @param user �û�
	 * @return �޸��Ƿ�ɹ�
	 * @author hy
	 */
	public boolean updateUserByid(UserInfo user);
	
	/**
	 * ɾ���û�
	 * @param u_id �û���
	 * @return ɾ���Ƿ�ɹ�
	 * @author hy
	 */
	public boolean deleteUserByid(String u_id);
	
	/**
	 * �޸�����֮ǰ����û������������Ƿ���ȷ
	 * @param id �û���
	 * @param oldpwd ������
	 * @return �������Ƿ���ȷ
	 * @author xjy
	 */
	public boolean updatePwdcheck(String id,String oldpwd);
	
	/**
	 * �޸��û�����
	 * @param id �û���
	 * @param newpwd ������
	 * @return �޸��Ƿ�ɹ�
	 * @author xjy
	 */
	public boolean updatePwd(String id,String newpwd);
	
	/**
	 * ����û����Ƿ��Ѵ���
	 * @param u_id �û���
	 * @return �û����Ƿ��Ѵ���
	 * @author zh
	 */
	public boolean isExist(String u_id);
	
	/**
	 * �û�ע��
	 * @param userinfo
	 * @return ע���Ƿ�ɹ�
	 * @author zh
	 */
	public Boolean insertUserExec(UserInfo userinfo);
	
	/**
	 * ���UserInfo���û����������Ƿ�ƥ��
	 * @param user UserInfo��Ϣ
	 * @return ������Ϣ
	 * @author lxq
	 */
	public UserInfo checkPwdAndLoginId(UserInfo user);
	
	/**
	 * ��ѯUserInfo����Ϣ
	 * @param user UserInfo��Ϣ
	 * @return UserInfo
	 * @author lxq
	 */
	public UserInfo findUserInfo(UserInfo user);
	
	/**
	 * ��ѯUserInfo����Ϣ
	 * @param user userid
	 * @return UserInfo
	 * @author fjx  
	 * �㲻��lxqд��findUserInfo����Ϊʲô�� user���� �ʼ�֮ 
	 */
	public UserInfo findUserById(String userId) ;
}
