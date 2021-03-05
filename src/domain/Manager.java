package domain;
/**
 * 管理员
 * @author lxq
 *
 */
public class Manager {
	/**
	 * 用户名
	 */
	private String m_name;
	
	/**
	 * 密码
	 */
	private String m_pwd;
	
	/**
	 * 类别
	 */
	private int m_state;

	public Manager() {
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_pwd() {
		return m_pwd;
	}

	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}

	public int getM_state() {
		return m_state;
	}

	public void setM_state(int m_state) {
		this.m_state = m_state;
	}
}
