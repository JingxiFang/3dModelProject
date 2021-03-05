package dao;

import java.sql.SQLException;
import java.util.List;

import domain.Sign;

public interface SignDao {
	
	/**
	 * �����ݿ���������ӱ����Ϣ
	 * @param signList
	 * @return
	 * @throws SQLException 
	 * fjx
	 */
	public boolean addSigns(List<Sign> signList ) throws SQLException;
	
	/**
	 * ͨ���ļ�id���Ҹ��ļ���Ӧ�ı��
	 * @param fileId
	 * @return
	 * fjx
	 */
	public List<Sign> getSigns(int fileId);
	
	/**
	 * ͨ�����idɾ�����
	 * @param singId
	 * @return
	 */
	public boolean deleteSign(int singId);
}
