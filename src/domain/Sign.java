package domain;

/**
 * ���
 * @author ������
 *
 */
public class Sign {
	

	/**
	 * ��ǩ����
	 */
	private Tag tag;
	private int tag_id;
	/**
	 * �����ļ��ı��
	 */
	private int fileId;
	/**
	 * ��������
	 */
	private int startX;
	/**
	 * �ߵ�������
	 */
	private int startY;
	/**
	 * �յ������
	 */
	private int endX;
	/**
	 * �յ�������
	 */
	private int endY;
	/**
	 * ͼ����״
	 */
	private int sharpType;
	/**
	 * ͼ�α߿���ɫ
	 */
	private String sharpColor;
	/**
	 * ��ע
	 */
	private String description;
	
	
	
	
	
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public int getTag_id() {
		return tag_id;
	}

	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public int getSharpType() {
		return sharpType;
	}

	public void setSharpType(int sharpType) {
		this.sharpType = sharpType;
	}

	public String getSharpColor() {
		return sharpColor;
	}

	public void setSharpColor(String sharpColor) {
		this.sharpColor = sharpColor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Sign(){
		
	}

	

	

	
}
