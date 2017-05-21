package com.dk.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class PQIAData implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -5131455456718791296L;
	//	SHIFT 班次
//	DEFECT_STATUS 缺陷状态
//	DR_FLAG 缺陷来源
//	DIFFERENCE 区分质保车间
//	DEFECT_LEVEL 问题等级
//	CREATE_USER 创建人
//	CREATE_TIME 创建时间
//	VIN VIN号
//	TPS TPS号
//	SERIES 车型型号
//	MATERIAL 整车物料号
//	CSN_BODY 车身号
//	DEFECT_MODE_NAME 缺陷描述
//	DEFECT_TYPE 缺陷类型
//	SITE 站点
//	LOCATION_NAME 方位
//	PART_NAME_THIRD 三级部件
//	CUSTOM_NAME_CN 自定义缺陷中文描述
//	CUSTOM_NAME_EN 自定义缺陷英文描述
//	DUTY_DEPT 责任部门
//	FIXING_NAME 细小部件
//	CONFIRM_SITE 确认站点
//	CONFIRM_TIME 确认时间
//	CONFIRM_USER 确认人
//	REPAIR_USER 返修人
//	REPAIR_TIME 返修时间
//	REPAIR_CONTENT 返修内容
//	SITE_REPAIR 返修站点
	private Integer id;
	private String shift;
	private String defectStatus;
	private String drFlag;
	private String difference;
	private String defectLevel;
	private String createUser;
	private Timestamp createTime;
	private String vin;
	private String tps;
	private String series;
	private String material;
	private String csnBody;
	private String defectModeName;
	private String defectType;
	private String site;
	private String locationName;
	private String partNameThird;
	private String customNameCn;
	private String customNameEn;
	private String dutyDept;
	private String fixingName;
	private String confirmSite;
	private Timestamp confirmTime;
	private String confirmUser;
	private String repairUser;
	private Timestamp repairTime;
	private String repairContent;
	private String siteRepair;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getDefectStatus() {
		return defectStatus;
	}
	public void setDefectStatus(String defectStatus) {
		this.defectStatus = defectStatus;
	}
	public String getDrFlag() {
		return drFlag;
	}
	public void setDrFlag(String drFlag) {
		this.drFlag = drFlag;
	}
	public String getDifference() {
		return difference;
	}
	public void setDifference(String difference) {
		this.difference = difference;
	}
	public String getDefectLevel() {
		return defectLevel;
	}
	public void setDefectLevel(String defectLevel) {
		this.defectLevel = defectLevel;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getTps() {
		return tps;
	}
	public void setTps(String tps) {
		this.tps = tps;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getCsnBody() {
		return csnBody;
	}
	public void setCsnBody(String csnBody) {
		this.csnBody = csnBody;
	}
	public String getDefectModeName() {
		return defectModeName;
	}
	public void setDefectModeName(String defectModeName) {
		this.defectModeName = defectModeName;
	}
	public String getDefectType() {
		return defectType;
	}
	public void setDefectType(String defectType) {
		this.defectType = defectType;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getPartNameThird() {
		return partNameThird;
	}
	public void setPartNameThird(String partNameThird) {
		this.partNameThird = partNameThird;
	}
	public String getCustomNameCn() {
		return customNameCn;
	}
	public void setCustomNameCn(String customNameCn) {
		this.customNameCn = customNameCn;
	}
	public String getCustomNameEn() {
		return customNameEn;
	}
	public void setCustomNameEn(String customNameEn) {
		this.customNameEn = customNameEn;
	}
	public String getDutyDept() {
		return dutyDept;
	}
	public void setDutyDept(String dutyDept) {
		this.dutyDept = dutyDept;
	}
	public String getFixingName() {
		return fixingName;
	}
	public void setFixingName(String fixingName) {
		this.fixingName = fixingName;
	}
	public String getConfirmSite() {
		return confirmSite;
	}
	public void setConfirmSite(String confirmSite) {
		this.confirmSite = confirmSite;
	}
	public Timestamp getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Timestamp confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getConfirmUser() {
		return confirmUser;
	}
	public void setConfirmUser(String confirmUser) {
		this.confirmUser = confirmUser;
	}
	public String getRepairUser() {
		return repairUser;
	}
	public void setRepairUser(String repairUser) {
		this.repairUser = repairUser;
	}
	public Timestamp getRepairTime() {
		return repairTime;
	}
	public void setRepairTime(Timestamp repairTime) {
		this.repairTime = repairTime;
	}
	public String getRepairContent() {
		return repairContent;
	}
	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	public String getSiteRepair() {
		return siteRepair;
	}
	public void setSiteRepair(String siteRepair) {
		this.siteRepair = siteRepair;
	}
	

}
