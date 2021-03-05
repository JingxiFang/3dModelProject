package dao;

import java.util.List;

import domain.Model_3d;

/**
 * ͼ���ļ���Dao
 */
public interface Model_3dDao {

	/**
	 * ��ѯͼ���ļ�����
	 * @return ͼ���ļ�����
	 * @author lxq
	 */
	public List<Model_3d> findModel_3d(String model_name);
	/**
	 * ����model
	 * @param model ʵ��
	 * @return �Ƿ�ɹ�
	 * fjx
	 */
	public boolean addModel(Model_3d model);
	
	/**
	 * ɾ��Model
	 * @param modelId modelId
	 * @return
	 * fjx
	 */
	public boolean delModel(String modelId);
	
	/**
	 * ��ȡģ���ļ������ļ���
	 * @param modelId  ģ��id
	 * @return  �ļ���·��
	 * fjx
	 */
	public String getModelDir(String modelId);
	
	/**
	 * ��ȡģ����Ϣ
	 * @param userId
	 * @return
	 */
	public List<Model_3d> getModelInfo(String userId);
		
	
}
