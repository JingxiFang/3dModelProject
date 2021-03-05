package service;

import dao.ManagerDao;
import dao.jdbc.ManagerDaoJDBCImpl;
import domain.Manager;

/**
 * Manager��Service
 */
public class ManagerService {
	ManagerDao managerDao = new ManagerDaoJDBCImpl();

	/**
	 * �޸�����֮ǰ����û������������Ƿ���ȷ
	 * @param id �û���
	 * @param oldpwd ������
	 * @return �������Ƿ���ȷ
	 * @author xjy
	 */
	public boolean updatePwdcheck(String id,String oldpwd){
		return managerDao.updatePwdcheck(id, oldpwd);
	}
	
	/**
	 * �޸��û�����
	 * @param id �û���
	 * @param newpwd ������
	 * @return �޸��Ƿ�ɹ�
	 * @author xjy
	 */
	public boolean updatePwd(String id,String newpwd){
		return managerDao.updatePwd(id, newpwd);
	}
	
	/**
	 * ���Manager���û����������Ƿ�ƥ��
	 * @param manager Manager��Ϣ
	 * @return ������Ϣ
	 * @author lxq  
	 * �޸� fjx
	 */
	public String[] checkPwdAndLoginId(Manager manager) {
		String[] message = new String[2];	// ��1λ���Ƿ�ƥ�䣻��2λ��������Ϣ
		Manager mgr = managerDao.checkPwdAndLoginId(manager);

		if (mgr != null) {	// ƥ��ɹ�
			
			message[0] = "true";
			message[1] =String.valueOf( mgr.getM_state());
		} else {	// ƥ��ʧ��
			String errorMsg = "�û������������������������<a href='#'>�һ�����</a>";	// ���ش�����Ϣ
			message[0] = "false";
			message[1] = errorMsg;
		}
		return message;
	}
	
	/**
	 * ��¼Manager��¼���˳�ϵͳʱ��
	 * @param manager Manager��Ϣ
	 * @return �Ƿ���Ӽ�¼�ɹ�
	 * @author lxq
	 */
	public boolean checkInOrOut(Manager manager,String action) {
		boolean b = managerDao.checkInOrOut(manager,action);
		if(b){
			System.out.println("��¼�����ɹ���");
		}else{
			System.out.println("��¼����ʧ��!");
		}
		return b;
	}

	/**
	 * ��ѯManager����Ϣ
	 * @param manager Manager��Ϣ
	 * @return Manager
	 * @author lxq
	 */
	public Manager findManangerInfo(Manager manager) {
		return managerDao.findUserInfo(manager);
	}
}
