package dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.SqlHelper;
import dao.Model_3dDao;
import domain.Model_3d;

/**
 * 图像文件的Service
 */
public class Model_3dDaoJDBCImpl implements Model_3dDao {

	/**
	 * 查询图像文件集合
	 * @return 图像文件集合
	 * @author lxq
	 */
	public List<Model_3d> findModel_3d(String model_name) {
 		List<Model_3d> model_3dList=new ArrayList<Model_3d>();
 		String sql="select model_id,model_root,model_name from 3d_model ";
 		List<Object> params=new ArrayList<Object>();
 		if(model_name != null){
 			sql=sql+" where model_name like ?";
 			params.add("%"+model_name+"%");
 		}

 		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
 		list=SqlHelper.returnMultipleResult(sql, params);
 		/*if(list.size()>0){
 			for(Map<String,Object> map:list){
 				Model_3d model_3d=new Model_3d();
 				model_3d.setModel_id(map.get("model_id").toString());
 				model_3d.setModel_root(map.get("model_root").toString());
 				model_3d.setModel_name(map.get("model_name").toString());
 				model_3dList.add(model_3d);
 			}
 		}*/
 		
 		for(int i=0;i<48;i++){
 			Model_3d model_3d=new Model_3d();
 			model_3d.setModel_id(i+"");
			model_3d.setModel_root("./brain/brain"+i+".png");
			model_3d.setModel_name("3d"+i+"模型");
			model_3dList.add(model_3d);
 		}
		return model_3dList;
	}
	
	@Override
	public boolean addModel(Model_3d model) {
		// TODO Auto-generated method stub
		String sql="insert into 3d_model values(?,?,?,?,?)";
		//添加参数
		List<Object> params=new ArrayList<Object>();
		params.add(model.getModel_id());
		params.add(model.getUpload_user_id());
		params.add(model.getUpload_time());
		params.add(model.getModel_root());
		params.add(model.getModel_name());
		
		return SqlHelper.Execute(sql, params);
	}

	@Override
	public boolean delModel(String modelId) {
		// TODO Auto-generated method stub
		String sql="delete from 3d_model where model_id=?";
		//添加参数
		List<Object> params=new ArrayList<Object>();
		params.add(modelId);
		
		return SqlHelper.Execute(sql, params);
	}

	@Override
	public String getModelDir(String modelId) {
		// TODO Auto-generated method stub
		
		String root="";
		String sql="select model_root from 3d_model where model_id=?";
		//添加参数
		List<Object> params=new ArrayList<Object>();
		params.add(modelId);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list=SqlHelper.returnMultipleResult(sql, params);
		if(list.size()>0){
		    root= list.get(0).get("model_root").toString();
		}
		
		return root;
	}

	@Override
	public List<Model_3d> getModelInfo(String userId) {
		// TODO Auto-generated method stub
		//获取模型的信息
		List<Model_3d> model_3dList=new ArrayList<Model_3d>();
		//添加参数
		List<Object> params=new ArrayList<Object>();
		String sql="select * from 3d_model ";
		if(userId!=null){
			sql+="where upload_user_id=?";
			params.add(userId);
		}
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		list=SqlHelper.returnMultipleResult(sql, params);
		if(list.size()>0){
 			for(Map<String,Object> map:list){
 				Model_3d model_3d=new Model_3d();
 				model_3d.setModel_id(map.get("model_id").toString());
 				model_3d.setModel_root(map.get("model_root").toString());
 				model_3d.setUpload_time(map.get("upload_time").toString());
 				model_3d.setUpload_user_id(map.get("upload_user_id").toString());
 				model_3d.setModel_name(map.get("model_name").toString());
 				model_3dList.add(model_3d);
 			}
 		}
		
		return model_3dList;		
	}
	
}
