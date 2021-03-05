package dao;

import domain.Manager;

/**
 * Manager��Dao
 */
public interface ManagerDao {
	
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
	 * ���Manager���û����������Ƿ�ƥ��
	 * @param manager Manager��Ϣ
	 * @return ������Ϣ
	 * @author lxq
	 */
	public Manager checkPwdAndLoginId(Manager manager);
	
	/**
	 * ��¼Manager��¼���˳�ϵͳʱ��
	 * @param manager Manager��Ϣ
	 * @return �Ƿ���Ӽ�¼�ɹ�
	 * @author lxq
	 */
	public boolean checkInOrOut(Manager manager,String action);

	/**
	 * ��ѯManager����Ϣ
	 * @param manager Manager��Ϣ
	 * @return Manager
	 * @author lxq
	 */
	public Manager findUserInfo(Manager manager);
}
