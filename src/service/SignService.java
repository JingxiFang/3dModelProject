package service;

import java.sql.SQLException;
import java.util.List;

import dao.SignDao;
import dao.jdbc.SignDaoJDBCImpl;
import domain.Sign;

public class SignService {
	/**
	 * 批量添加标记
	 * @param signList
	 * @return
	 * @throws SQLException
	 */
	public boolean addSigns(List<Sign> signList) throws SQLException {
		
		SignDao signDao=new SignDaoJDBCImpl();		
		return signDao.addSigns(signList);
		
	}
	/**
	 * 通过文件id查找该文件对应的标记
	 * @param fileId
	 * @return
	 * fjx
	 */
	public List<Sign> getSigns(int fileId) {
		SignDao signDao=new SignDaoJDBCImpl();		
		return signDao.getSigns(fileId);
	}
}
