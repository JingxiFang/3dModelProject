package service;

import java.sql.SQLException;
import java.util.List;

import dao.SignDao;
import dao.jdbc.SignDaoJDBCImpl;
import domain.Sign;

public class SignService {
	/**
	 * ������ӱ��
	 * @param signList
	 * @return
	 * @throws SQLException
	 */
	public boolean addSigns(List<Sign> signList) throws SQLException {
		
		SignDao signDao=new SignDaoJDBCImpl();		
		return signDao.addSigns(signList);
		
	}
	/**
	 * ͨ���ļ�id���Ҹ��ļ���Ӧ�ı��
	 * @param fileId
	 * @return
	 * fjx
	 */
	public List<Sign> getSigns(int fileId) {
		SignDao signDao=new SignDaoJDBCImpl();		
		return signDao.getSigns(fileId);
	}
}
