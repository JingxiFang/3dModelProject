package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Model_3dDao;
import dao.jdbc.Model_3dDaoJDBCImpl;
import domain.Model_3d;

/**
 * ͼ���ļ���Service
 */
public class Model_3dService {
	Model_3dDao model_3dDao = new Model_3dDaoJDBCImpl();
	
	/**
	 * ��ѯͼ���ļ�����
	 * @return ͼ���ļ�����
	 * @author lxq
	 */
	public List<Model_3d> findModel_3d(String model_name){
		return model_3dDao.findModel_3d(model_name);
	}
	/**
	 * ����model
	 * @param model
	 * @return
	 * @author fjx
	 */
	public  boolean addModel(Model_3d model){
		Model_3dDao modelDao=new Model_3dDaoJDBCImpl();
		return modelDao.addModel(model);
	}
	/**
	 * ɾ��model
	 * @param modelId
	 * @return
	 * @author fjx
	 */
	public boolean delModel(String modelId) {
		Model_3dDao modelDao=new Model_3dDaoJDBCImpl();
		return modelDao.delModel(modelId);
	}
	
	/**
	 * ��ȡģ��·��
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
	 * ��ȡģ����Ϣ�б�
	 * @param userId �û���
	 * @return
	 */
	public List<Model_3d> gerModelInfo(String userId){
		//��ȡģ�͵���Ϣ
		Model_3dDao modelDao=new Model_3dDaoJDBCImpl();
		return modelDao.getModelInfo(userId);
	}
}
