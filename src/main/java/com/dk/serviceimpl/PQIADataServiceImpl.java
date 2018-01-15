package com.dk.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dk.object.PQIAData;
import com.dk.object.PQIAInfo;
import com.dk.result.Result;
import com.dk.service.PQIADataService;
import com.dk.util.DBUtil;

public class PQIADataServiceImpl{

	public Result addInfo(PQIAInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into pqiadata(shift,defect_status,dr_flag,difference,defect_level,create_user,create_time,vin,tps,series,material,csn_body,defect_mode_name,defect_type,site,location_name,part_name_third,custom_name_cn,custom_name_en,duty_dept,fixing_name,confirm_site,confirm_time,confirm_user,repair_user,repair_time,repair_content,site_repair) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, info.getShift());
			ps.setString(2, info.getDefectStatus());
			ps.setString(3, info.getDrFlag());
//			ps.setString(4, info.getDifference());
			ps.setString(5, info.getDefectLevel());
			ps.setString(6, info.getCreateUser());
			ps.setTimestamp(7, info.getCreateTime());
			ps.setString(8, info.getVin());
			ps.setString(9, info.getTps());
			ps.setString(10, info.getSeries());
			ps.setString(11, info.getMaterial());
			ps.setString(12, info.getCsnBody());
			ps.setString(13, info.getDefectModeName());
			ps.setString(14, info.getDefectType());
			ps.setString(15, info.getSite());
			ps.setString(16, info.getLocationName());
			ps.setString(17, info.getPartNameThird());
			ps.setString(18, info.getCustomNameCn());
			ps.setString(19, info.getCustomNameEn());
			ps.setString(20, info.getDutyDept());
			ps.setString(21, info.getFixingName());
			ps.setString(22, info.getConfirmSite());
			ps.setTimestamp(23, info.getConfirmTime());
			ps.setString(24, info.getConfirmUser());
			ps.setString(25, info.getRepairUser());
			ps.setTimestamp(26, info.getRepairTime());
			ps.setString(27, info.getRepairContent());
			ps.setString(28, info.getSiteRepair());
			
			ps.execute();
			result.setStatus(true);
			result.setMessage("保存成功");
			result.setData(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStatus(false);
			result.setMessage("保存失败");
			e.printStackTrace();
		} finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
