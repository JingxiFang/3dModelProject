package domain;
import domain.Manager;

/**
 * 在线记录
 * @author admin
 *
 */
public class Loginrecord {
	/**
	 * 自增标识列
	 */
	private String id;
	
	/**
	 * 用户名
	 */
	private Manager m_name;
	
	/**
	 * 上下线时间
	 */
	private String time;
	
	/**
	 * 动作
	 * action（在线记录表中）因不符合java命名规范所以在项目中当中处理成_action
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
