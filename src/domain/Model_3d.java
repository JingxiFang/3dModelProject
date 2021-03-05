package domain;

import java.util.List;

/**
 * 图像文件
 * @author yzc
 * 修改人fangjingx 修改时间 2019-07-13 19：17 
 */
public class Model_3d {
	/**
	 * 模型id
	 */
	private String model_id;
	/**
	 * 上传者id
	 */
	private String upload_user_id;
	/**
	 * 上传者对象 
	 */
	private UserInfo upload_user_id_obj;
	/**
	 * 上传时间
	 */
	private String upload_time;
	
	/**
	 * 路径
	 */
	private String model_root;
	/**
	 * 模型名称
	 */
	private String model_name;
	/**
	 * 模型所包含的二维图像文件
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
