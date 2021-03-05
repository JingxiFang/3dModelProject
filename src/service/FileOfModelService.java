package service;

import java.sql.SQLException;
import java.util.List;

import dao.FileOfModelDao;
import dao.jdbc.FileOfModelDaoJDBCImpl;
import domain.FileOfModel;

public class FileOfModelService {
    /**
     * 增加文件 
     * @param fileList
     * @return
     * @throws SQLException
     */
	public boolean addFiles(List<FileOfModel> fileList) throws SQLException {
		FileOfModelDao fileDao=new FileOfModelDaoJDBCImpl();
		return fileDao.addFiles(fileList);
	}
	
	/**
	 * 查找该model下的所有文件的地址
	 * @param modelId
	 * @return
	 */
	public List<String> selectFileRoot(String modelId) {
		FileOfModelDao fileDao=new FileOfModelDaoJDBCImpl();
		return fileDao.selectFileRoot(modelId);
	}
	/**
	 * 获取某一个model的所有的图像文件的信息
	 * @param modelId
	 * @return
	 */
	public List<FileOfModel> selectFile(String modelId) {
		FileOfModelDao fileDao=new FileOfModelDaoJDBCImpl();
		SignService signService=new SignService();
		List<FileOfModel> fileList=fileDao.selectFile(modelId);
		for(FileOfModel file:fileList){
			file.setSignList(signService.getSigns(Integer.parseInt(file.getFile_id())));
		}
		return fileList;
	}
}
