package dao.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import util.SqlHelper;

import dao.FileOfModelDao;
import domain.FileOfModel;

public class FileOfModelDaoJDBCImpl implements FileOfModelDao {

	@Override
	public boolean addFiles(List<FileOfModel> fileList) throws SQLException {
		String sql="insert into fileofmodel(file_root,model_id,upload_user_id,upload_time) values(?,?,?,?)";
		List<List<Object>> paramsList=new ArrayList<List<Object>>();
		for(FileOfModel file:fileList){
			List<Object> params=new ArrayList<Object>();
			params.add(file.getFile_root());
			params.add(file.getModel_id());
			params.add(file.getUpload_user_id());
			params.add(file.getUpload_time());
			
			paramsList.add(params);
		}
			
		return SqlHelper.Execute(sql, paramsList)>0?true:false;
		
	}

	@Override
	public List<String> selectFileRoot(String modelId) {
		// TODO Auto-generated method stub
		//用来放文件们的地址
		List<String> rootList=new ArrayList<String>();
		//应该获取最新的文件
		String sql="select * from fileofmodel where model_id=? AND" +//
				" upload_time IN (SELECT MAX(upload_time) FROM fileofmodel where  model_id=?)";
		List<Object> params=new ArrayList<Object>();
		params.add(modelId);
		params.add(modelId);
		//获取数据
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list=SqlHelper.returnMultipleResult(sql, params);
		if(list.size()>0){
			for(Map<String,Object> map:list){
				String root=map.get("file_root").toString();
				rootList.add(root);
			}
		}
		
		return rootList;
	}
	@Override
	public List<FileOfModel> selectFile(String modelId) {
		// TODO Auto-generated method stub
		//用来放文件们的地址
		List<FileOfModel> fileList=new ArrayList<FileOfModel>();
		//应该获取最新的文件
		String sql="select * from fileofmodel where model_id=? AND" +//
				" upload_time IN (SELECT MAX(upload_time) FROM fileofmodel where  model_id=?)";
		List<Object> params=new ArrayList<Object>();
		params.add(modelId);
		params.add(modelId);
		//获取数据
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list=SqlHelper.returnMultipleResult(sql, params);
		if(list.size()>0){
			for(Map<String,Object> map:list){
				//String root=map.get("file_root").toString();
				/*
				 * `file_id``file_root``model_id``upload_time``upload_user_id`*/
				FileOfModel file=new FileOfModel();
				file.setFile_id(map.get("file_id").toString());
				file.setFile_root(map.get("file_root").toString());
				file.setUpload_time(map.get("upload_time").toString());
				file.setUpload_user_id(map.get("upload_user_id").toString());
				file.setModel_id(map.get("model_id").toString());
				
				fileList.add(file);
			}
		}
		
		return fileList;
	}

}
