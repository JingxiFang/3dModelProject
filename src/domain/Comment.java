package domain;

/**
 * ���
 * @author ������
 *
 */
public class Comment {
	/**
	 * ������ʶ��
	 */
	private int id;
	
	/**
	 * �û���
	 */
	private UserInfo a_name;
	
	/**
	 * ���ʱ��
	 */
	private String comment_time;
	
	/**
	 * �������
	 */
	private String comment_value;
	
	/**
	 * ģ��id
	 */
	private Model_3d model_id;
	
	public Comment(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getA_name() {
		return a_name;
	}

	public void setA_name(UserInfo a_name) {
		this.a_name = a_name;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public String getComment_value() {
		return comment_value;
	}

	public void setComment_value(String comment_value) {
		this.comment_value = comment_value;
	}

	public Model_3d getModel_id() {
		return model_id;
	}

	public void setModel_id(Model_3d model_id) {
		this.model_id = model_id;
	}
	
	

}
