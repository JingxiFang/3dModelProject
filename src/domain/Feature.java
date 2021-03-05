package domain;

/**
 * 特征
 * @author admin
 *
 */
public class Feature {
	/**
	 * 模型id
	 */
	private String model_id;
	
	/**
	 * 特征名
	 */
	private String feature_name;
	
	/**
	 * 特征值
	 */
	private String feature_value;

	public Feature() {
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public String getFeature_name() {
		return feature_name;
	}

	public void setFeature_name(String feature_name) {
		this.feature_name = feature_name;
	}

	public String getFeature_value() {
		return feature_value;
	}

	public void setFeature_value(String feature_value) {
		this.feature_value = feature_value;
	}
}
