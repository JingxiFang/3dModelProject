package dao;
import java.sql.SQLException;
import java.util.List;

import domain.FileOfModel;
public interface FileOfModelDao {
	/**
	 * ���ģ�͵�����ļ�
	 * @param fileList
	 * @return
	 * @throws SQLException
	 */
    public boolean addFiles(List<FileOfModel> fileList) throws SQLException;
    
    /**
     * ����ĳһmodel�������ļ�
     * @param modelId
     * @return
     */
    public List<String> selectFileRoot(String modelId);
    /**
     * ��ȡĳһģ�͵������ļ�����Ϣ
     * @param modelId
     * @return
     */
    public List<FileOfModel> selectFile(String modelId) ;
}
