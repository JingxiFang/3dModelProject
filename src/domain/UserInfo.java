package domain;

/**
 * �û�
 * @author lxq
 *
 */
public class UserInfo {
	/**
	 * �û���
	 */
	private String u_id;
	/**
	 * ����
	 */
	private String u_name;
	
	/**
	 * ����
	 */
	private String u_pwd;
	
	/**
	 * �Ա�
	 */
	private String u_sex;
	
	/**
	 * ����
	 */
	private String u_age;
	
	/**
	 * ����
	 */
	private String u_email;
	
	/**
	 * �ֻ�
	 */
	private String u_phone;
	
	/**
	 * ����
	 */
	private int u_state;
	
	public UserInfo() {
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_pwd() {
		return u_pwd;
	}

	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}

	public String getU_sex() {
		return u_sex;
	}

	public void setU_sex(String u_sex) {
		this.u_sex = u_sex;
	}

	public String getU_age() {
		return u_age;
	}

	public void setU_age(String u_age) {
		this.u_age = u_age;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_phone() {
		return u_phone;
	}

	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}

	public int getU_state() {
		return u_state;
	}

	public void setU_state(int u_state) {
		this.u_state = u_state;
	}
	
}
