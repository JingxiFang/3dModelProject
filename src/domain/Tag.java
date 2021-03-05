package domain;

/**
 * 标签
 * @author admin
 *
 */
public class Tag {
	/**
	 * 标签id
	 */
	private String tag_id;

	/**
	 * 父标签id
	 */
	private String tagFather_id;
	
	/**
	 * 标签名
	 */
	private String tag_name;
	
	/**
	 * 创建时间
	 */
	private String tag_createTime;
	
	/**
	 * 创建人
	 */
	private String tag_createBy;
	
	/**
	 * 修改人
	 */
	private String tag_updateBy;
	
	/**
	 * 修改时间
	 */
	private String tag_updateTime;

	public Tag() {
	}

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getTagFather_id() {
		return tagFather_id;
	}

	public void setTagFather_id(String tagFather_id) {
		this.tagFather_id = tagFather_id;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public String getTag_createTime() {
		return tag_createTime;
	}

	public void setTag_createTime(String tag_createTime) {
		this.tag_createTime = tag_createTime;
	}

	public String getTag_createBy() {
		return tag_createBy;
	}

	public void setTag_createBy(String tag_createBy) {
		this.tag_createBy = tag_createBy;
	}

	public String getTag_updateBy() {
		return tag_updateBy;
	}

	public void setTag_updateBy(String tag_updateBy) {
		this.tag_updateBy = tag_updateBy;
	}

	public String getTag_updateTime() {
		return tag_updateTime;
	}

	public void setTag_updateTime(String tag_updateTime) {
		this.tag_updateTime = tag_updateTime;
	}
}
