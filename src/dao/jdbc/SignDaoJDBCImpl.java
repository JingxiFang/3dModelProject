package dao.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.SqlHelper;

import dao.SignDao;

import domain.Model_3d;
import domain.Sign;

public class SignDaoJDBCImpl implements SignDao {

	@Override
	public boolean addSigns(List<Sign> signList) throws SQLException {
		// TODO Auto-generated method stub
		
	
		String sql="insert into sign(fileId,tag_id,startX,startY,endX,endY,shapecolor,shapetype,description) values(?,?,?,?,?,?,?,?,?)";
		List<List<Object>> paramsList=new ArrayList<List<Object>>();
		for(Sign sign:signList){
			List<Object> params=new ArrayList<Object>();
			params.add(sign.getFileId());
			params.add(sign.getTag_id());
			params.add(sign.getStartX());
			params.add(sign.getStartY());
			params.add(sign.getEndX());
			params.add(sign.getEndY());			
			params.add(sign.getSharpColor());
			params.add(sign.getSharpType());
			params.add(sign.getDescription());
			
			paramsList.add(params);
		}
			
		return SqlHelper.Execute(sql, paramsList)>0?true:false;
		
	}

	@Override
	public List<Sign> getSigns(int fileId) {
		// TODO Auto-generated method stub
		List<Sign> signList=new ArrayList<Sign>();
		String sql="select * from sign  where fileid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(fileId);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list=SqlHelper.returnMultipleResult(sql, params);
		if(list.size()>0){
 			for(Map<String,Object> map:list){
 				Sign sign=new Sign();
 				sign.setDescription(map.get("description").toString());
 				sign.setSharpColor(map.get("shapecolor").toString());
 				sign.setStartX(Integer.parseInt(map.get("startX").toString()));
 				sign.setStartY(Integer.parseInt(map.get("startY").toString()));
 				sign.setEndX(Integer.parseInt(map.get("endX").toString()));
 				sign.setEndY(Integer.parseInt(map.get("endY").toString()));
 				sign.setSharpType(Integer.parseInt(map.get("shapetype").toString()));
 	 			sign.setFileId(Integer.parseInt(map.get("fileId").toString()));
 	 			sign.setTag_id(Integer.parseInt(map.get("tag_id").toString()));
 				signList.add(sign);
 			}
 		}
		
		return signList;	
		
	}

	@Override
	public boolean deleteSign(int signId) {
		// TODO Auto-generated method stub
		String sql="delete from sign where signid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(signId);
		return SqlHelper.Execute(sql, params);
		
	}

}
