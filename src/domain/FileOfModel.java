package domain;

import java.util.List;

/**
 * 文件
 * @author admin
 *
 */
public class FileOfModel {
	
	/**
	 * 文件夹id
	 */
	private String file_id;

	/**
	 * 文件名
	 */
	private String file_name;
	
	/**
	 * 文件格式
	 */
	private String file_format;
	
	/**
	 * 文件路径
	 */
	private String file_root;

	/**
	 * 3d模型编号
	 */
	private String model_id;
	
	/**
	 * 上传者编号
	 */
	private String upload_user_id;
	
	/**
	 * 上传时间
	 */
	private String upload_time;
	
	/**
	 * 该二维图像上所存在的标记的集合
	 */
	private List<Sign> signList;

	

	public List<Sign> getSignList() {
		return signList;
	}

	public void setSignList(List<Sign> signList) {
		this.signList = signList;
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

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	
	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}
	
	
	public String getFile_root() {
		return file_root;
	}

	public void setFile_root(String file_root) {
		this.file_root = file_root;
	}
	

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	
	public String getFile_format() {
		return file_format;
	}

	public void setFile_format(String file_format) {
		this.file_format = file_format;
	}

	
	
	public FileOfModel() {
	
	}
	
	/*
	/**
	 * 上传者
	 */
	/*
	private String file_package_from;
	
	/**
	 * 上传时间
	 */
	/*
	private String file_package_time;
	*/
	/*
	public String getFile_package_from() {
		return file_package_from;
	}

	public void setFile_package_from(String file_package_from) {
		this.file_package_from = file_package_from;
	}

	public String getFile_package_time() {
		return file_package_time;
	}
*/
}
