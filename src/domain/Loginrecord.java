package domain;
import domain.Manager;

/**
 * ���߼�¼
 * @author admin
 *
 */
public class Loginrecord {
	/**
	 * ������ʶ��
	 */
	private String id;
	
	/**
	 * �û���
	 */
	private Manager m_name;
	
	/**
	 * ������ʱ��
	 */
	private String time;
	
	/**
	 * ����
	 * action�����߼�¼���У��򲻷���java�����淶��������Ŀ�е��д����_action
	 */
	private String _action;

	public Loginrecord() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Manager getM_name() {
		return m_name;
	}

	public void setM_name(Manager m_name) {
		this.m_name = m_name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String get_action() {
		return _action;
	}

	public void set_action(String _action) {
		this._action = _action;
	}
}
