package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Model_3dDao;
import dao.jdbc.Model_3dDaoJDBCImpl;
import domain.Model_3d;

/**
 * 图像文件的Service
 */
public class Model_3dService {
	Model_3dDao model_3dDao = new Model_3dDaoJDBCImpl();
	
	/**
	 * 查询图像文件集合
	 * @return 图像文件集合
	 * @author lxq
	 */
	public List<Model_3d> findModel_3d(String model_name){
		return model_3dDao.findModel_3d(model_name);
	}
	/**
	 * 增加model
	 * @param model
	 * @return
	 * @author fjx
	 */
	public  boolean addModel(Model_3d model){
		Model_3dDao modelDao=new Model_3dDaoJDBCImpl();
		return modelDao.addModel(model);
	}
	/**
	 * 删除model
	 * @param modelId
	 * @return
	 * @author fjx
	 */
	public boolean delModel(String modelId) {
		Model_3dDao modelDao=new Model_3dDaoJDBCImpl();
		return modelDao.delModel(modelId);
	}
	
	/**
	 * 获取模型路径
	 * @param modelId
	 * @return
	 * @author fjx
	 * 
	 */
	public String getModelDir(String modelId) {
		Model_3dDao modelDao=new Model_3dDaoJDBCImpl();
		return modelDao.getModelDir(modelId);
	}
	
	/**
	 * 获取模型信息列表
	 * @param userId 用户名
	 * @return
	 */
	public List<Model_3d> gerModelInfo(String userId){
		//获取模型的信息
		Model_3dDao modelDao=new Model_3dDaoJDBCImpl();
		return modelDao.getModelInfo(userId);
	}
}
