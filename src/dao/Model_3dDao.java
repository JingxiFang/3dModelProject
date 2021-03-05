package dao;

import java.util.List;

import domain.Model_3d;

/**
 * 图像文件的Dao
 */
public interface Model_3dDao {

	/**
	 * 查询图像文件集合
	 * @return 图像文件集合
	 * @author lxq
	 */
	public List<Model_3d> findModel_3d(String model_name);
	/**
	 * 增加model
	 * @param model 实体
	 * @return 是否成功
	 * fjx
	 */
	public boolean addModel(Model_3d model);
	
	/**
	 * 删除Model
	 * @param modelId modelId
	 * @return
	 * fjx
	 */
	public boolean delModel(String modelId);
	
	/**
	 * 获取模型文件所在文件夹
	 * @param modelId  模型id
	 * @return  文件夹路径
	 * fjx
	 */
	public String getModelDir(String modelId);
	
	/**
	 * 获取模型信息
	 * @param userId
	 * @return
	 */
	public List<Model_3d> getModelInfo(String userId);
		
	
}
