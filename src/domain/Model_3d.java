package domain;

import java.util.List;

/**
 * ͼ���ļ�
 * @author yzc
 * �޸���fangjingx �޸�ʱ�� 2019-07-13 19��17 
 */
public class Model_3d {
	/**
	 * ģ��id
	 */
	private String model_id;
	/**
	 * �ϴ���id
	 */
	private String upload_user_id;
	/**
	 * �ϴ��߶��� 
	 */
	private UserInfo upload_user_id_obj;
	/**
	 * �ϴ�ʱ��
	 */
	private String upload_time;
	
	/**
	 * ·��
	 */
	private String model_root;
	/**
	 * ģ������
	 */
	private String model_name;
	/**
	 * ģ���������Ķ�άͼ���ļ�
	 */
	private List<FileOfModel> file;

	
	public UserInfo getUpload_user_id_obj() {
		return upload_user_id_obj;
	}

	public void setUpload_user_id_obj(UserInfo upload_user_id_obj) {
		this.upload_user_id_obj = upload_user_id_obj;
	}

	public  List<FileOfModel> getFile() {
		return file;
	}

	public void setFile( List<FileOfModel> file) {
		this.file = file;
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}
	
	
	public String getUpload_user_id() {
		return upload_user_id;
	}

	public void setUpload_user_id(String upload_user_id) {
		this.upload_user_id = upload_user_id;
	}

	
	public String getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(String upload_time) {
		this.upload_time = upload_time;
	}
	
	
	public String getModel_root() {
		return model_root;
	}

	public void setModel_root(String model_root) {
		this.model_root = model_root;
	}

	
	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	
	
	public Model_3d(){
		
	}

	
}
