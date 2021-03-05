package service;

import java.sql.SQLException;
import java.util.List;

import dao.FileOfModelDao;
import dao.jdbc.FileOfModelDaoJDBCImpl;
import domain.FileOfModel;

public class FileOfModelService {
    /**
     * �����ļ� 
     * @param fileList
     * @return
     * @throws SQLException
     */
	public boolean addFiles(List<FileOfModel> fileList) throws SQLException {
		FileOfModelDao fileDao=new FileOfModelDaoJDBCImpl();
		return fileDao.addFiles(fileList);
	}
	
	/**
	 * ���Ҹ�model�µ������ļ��ĵ�ַ
	 * @param modelId
	 * @return
	 */
	public List<String> selectFileRoot(String modelId) {
		FileOfModelDao fileDao=new FileOfModelDaoJDBCImpl();
		return fileDao.selectFileRoot(modelId);
	}
	/**
	 * ��ȡĳһ��model�����е�ͼ���ļ�����Ϣ
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
