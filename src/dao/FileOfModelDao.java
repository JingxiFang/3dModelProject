package dao;
import java.sql.SQLException;
import java.util.List;

import domain.FileOfModel;
public interface FileOfModelDao {
	/**
	 * 添加模型的组成文件
	 * @param fileList
	 * @return
	 * @throws SQLException
	 */
    public boolean addFiles(List<FileOfModel> fileList) throws SQLException;
    
    /**
     * 查找某一model的所有文件
     * @param modelId
     * @return
     */
    public List<String> selectFileRoot(String modelId);
    /**
     * 获取某一模型的最新文件的信息
     * @param modelId
     * @return
     */
    public List<FileOfModel> selectFile(String modelId) ;
}
