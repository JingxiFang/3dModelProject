package domain;

/**
 * �ӽ�
 * @author ������
 *
 */
public class Gview {
	/**
	 * �ӽ�id
	 */
	private String gview_id;
	
	/**
	 * ģ��id
	 */
	private Model_3d model_id;
	
	/**
	 * �ӽ�ֵ
	 */
	private String gview_value;
	
	public Gview(){
		
	}

	public String getGview_id() {
		return gview_id;
	}

	public void setGview_id(String gview_id) {
		this.gview_id = gview_id;
	}

	public Model_3d getModel_id() {
		return model_id;
	}

	public void setModel_id(Model_3d model_id) {
		this.model_id = model_id;
	}

	public String getGview_value() {
		return gview_value;
	}

	public void setGview_value(String gview_value) {
		this.gview_value = gview_value;
	}
	
	

}
