package dao;

import java.sql.SQLException;
import java.util.List;

import domain.Sign;

public interface SignDao {
	
	/**
	 * 向数据库中批量添加标记信息
	 * @param signList
	 * @return
	 * @throws SQLException 
	 * fjx
	 */
	public boolean addSigns(List<Sign> signList ) throws SQLException;
	
	/**
	 * 通过文件id查找该文件对应的标记
	 * @param fileId
	 * @return
	 * fjx
	 */
	public List<Sign> getSigns(int fileId);
	
	/**
	 * 通过标记id删除标记
	 * @param singId
	 * @return
	 */
	public boolean deleteSign(int singId);
}
