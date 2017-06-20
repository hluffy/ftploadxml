package com.dk.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dk.object.PQIAData;
import com.dk.object.PQIAInfo;
import com.dk.result.Result;
import com.dk.service.PQIADataService;
import com.dk.util.DBUtil;

public class PQIAInfoServiceImpl implements PQIADataService{

	public Result addInfo(PQIAInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
//			String sql = "insert into pqiadata(shift,defect_status,dr_flag,difference,defect_level,create_user,create_time,vin,tps,series,material,csn_body,defect_mode_name,defect_type,site,location_name,part_name_third,custom_name_cn,custom_name_en,duty_dept,fixing_name,confirm_site,confirm_time,confirm_user,repair_user,repair_time,repair_content,site_repair) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql = "insert into pqiadata(vin,tps,series,material,csn_body,csn_paint,csn_assembly,defect_mode_name,defect_type,site,fixing_name,location_name,part_name_first,part_name_secont,part_name_third,part_name_match,custom_name_cn,custom_name_en,shift,defect_status,dr_flag,duty_dept,defect_point,difference,defect_level,lost_assembly,shift_group,cause_type,dr_cause_type,property_one,property_two,property_three,property_four,property_five,property_six,property_seven,property_eight,duty_user,create_user,create_time,delete_cause,delete_time,delete_user,update_cause,update_time,update_user,back_cause,back_time,back_user,confirm_site,confirm_time,confirm_user,repair_user,repair_time,repair_content,repair_type,site_repair,repair_area,rework_time,repair_measures,repair_step_type,repair_duty_dept,repair_line_name,field_one,field_two,field_three,field_four,field_five) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, info.getVin());
			ps.setString(2, info.getTps());
			ps.setString(3, info.getSeries());
			ps.setString(4, info.getMaterial());
			ps.setString(5, info.getCsnBody());
			ps.setString(6, info.getCsnPaint());
			ps.setString(7, info.getCsnAssembly());
			ps.setString(8, info.getDefectModeName());
			ps.setString(9, info.getDefectType());
			ps.setString(10, info.getSite());
			ps.setString(11, info.getFixingName());
			ps.setString(12, info.getLocationName());
			ps.setString(13, info.getPartNameFirst());
			ps.setString(14, info.getPartNameSecont());
			ps.setString(15, info.getPartNameThird());
			ps.setString(16, info.getPartNameMatch());
			ps.setString(17, info.getCustomNameCn());
			ps.setString(18, info.getCustomNameEn());
			ps.setString(19, info.getShift());
			ps.setString(20, info.getDefectStatus());
			ps.setString(21, info.getDrFlag());
			ps.setString(22, info.getDutyDept());
			ps.setString(23, info.getDefectPoint());
			ps.setString(24, info.getDifference());
			ps.setString(25, info.getDefectLevel());
			ps.setString(26, info.getLostAssembly());
			ps.setString(27, info.getShiftGroup());
			ps.setString(28, info.getCauseType());
			ps.setString(29, info.getDrCauseType());
			ps.setString(30, info.getPropertyOne());
			ps.setString(31, info.getPropertyTwo());
			ps.setString(32, info.getPropertyThree());
			ps.setString(33, info.getPropertyFour());
			ps.setString(34, info.getPropertyFive());
			ps.setString(35, info.getPropertySix());
			ps.setString(36, info.getPropertySeven());
			ps.setString(37, info.getPropertyEight());
			ps.setString(38, info.getDutyUser());
			ps.setString(39, info.getCreateUser());
			ps.setTimestamp(40, info.getCreateTime());
			
			ps.setString(41, info.getDeleteCause());
			ps.setTimestamp(42, info.getDeleteTime());
			ps.setString(43, info.getDeleteUser());
			
			ps.setString(44, info.getUpdateCause());
			ps.setTimestamp(45, info.getUpdateTime());
			ps.setString(46, info.getUpdateUser());
			
			ps.setString(47, info.getBackCause());
			ps.setTimestamp(48, info.getBackTime());
			ps.setString(49, info.getBackUser());
			
			ps.setString(50, info.getConfirmSite());
			ps.setTimestamp(51, info.getConfirmTime());
			ps.setString(52, info.getConfirmUser());
			
			ps.setString(53, info.getRepairUser());
			ps.setTimestamp(54, info.getRepairTime());
			ps.setString(55, info.getRepairContent());
			ps.setString(56, info.getRepairType());
			ps.setString(57, info.getSiteRepair());
			ps.setString(58, info.getRepairArea());
			ps.setInt(59, info.getReworkTime());
			ps.setString(60, info.getRepairMeasures());
			ps.setString(61, info.getRepairStepType());
			ps.setString(62, info.getRepairDutyDept());
			ps.setString(63, info.getRepairLineName());
			ps.setString(64, info.getFieldOne());
			ps.setString(65, info.getFieldTwo());
			ps.setString(66, info.getFieldThree());
			ps.setString(67, info.getFieldFour());
			ps.setString(68, info.getFieldFive());
			
			
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
