package com.dk.object;

import java.io.Serializable;
import java.sql.Timestamp;

public class PQIAInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1477009382515240693L;
	private Integer id;
	private String vin;
	private String tps;
	private String series;
	private String material;
	private String csnBody;
	private String csnPaint;
	private String csnAssembly;
	private String defectModeName;
	private String defectType;
	private String site;
	private String fixingName;
	private String locationName;
	private String partNameFirst;
	private String partNameSecont;
	private String partNameThird;
	private String partNameMatch;
	private String customNameCn;
	private String customNameEn;
	private String shift;
	private String defectStatus;
	private String drFlag;
	private String dutyDept;
	private String defectPoint;
	private String difference;
	private String defectLevel;
	private String lostAssembly;
	private String shiftGroup;
	private String causeType;
	private String drCauseType;
	private String propertyOne;
	private String propertyTwo;
	private String propertyThree;
	private String propertyFour;
	private String propertyFive;
	private String propertySix;
	private String propertySeven;
	private String propertyEight;
	private String dutyUser;
	private String createUser;
	private Timestamp createTime;
	
	private String deleteCause;
	private Timestamp deleteTime;
	private String deleteUser;
	
	private String updateCause;
	private Timestamp updateTime;
	private String updateUser;
	
	private String backCause;
	private Timestamp backTime;
	private String backUser;
	
	private String confirmSite;
	private Timestamp confirmTime;
	private String confirmUser;
	
	private String repairUser;
	private Timestamp repairTime;
	private String repairContent;
	private String repairType;
	private String siteRepair;
	private String repairArea;
	private Integer reworkTime;
	private String repairMeasures;
	private String repairStepType;
	private String repairDutyDept;
	private String repairLineName;
	private String fieldOne;
	private String fieldTwo;
	private String fieldThree;
	private String fieldFour;
	private String fieldFive;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getCsnPaint() {
		return csnPaint;
	}
	public void setCsnPaint(String csnPaint) {
		this.csnPaint = csnPaint;
	}
	public String getCsnAssembly() {
		return csnAssembly;
	}
	public void setCsnAssembly(String csnAssembly) {
		this.csnAssembly = csnAssembly;
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
	public String getFixingName() {
		return fixingName;
	}
	public void setFixingName(String fixingName) {
		this.fixingName = fixingName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getPartNameFirst() {
		return partNameFirst;
	}
	public void setPartNameFirst(String partNameFirst) {
		this.partNameFirst = partNameFirst;
	}
	public String getPartNameSecont() {
		return partNameSecont;
	}
	public void setPartNameSecont(String partNameSecont) {
		this.partNameSecont = partNameSecont;
	}
	public String getPartNameThird() {
		return partNameThird;
	}
	public void setPartNameThird(String partNameThird) {
		this.partNameThird = partNameThird;
	}
	public String getPartNameMatch() {
		return partNameMatch;
	}
	public void setPartNameMatch(String partNameMatch) {
		this.partNameMatch = partNameMatch;
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
	public String getDutyDept() {
		return dutyDept;
	}
	public void setDutyDept(String dutyDept) {
		this.dutyDept = dutyDept;
	}
	public String getDefectPoint() {
		return defectPoint;
	}
	public void setDefectPoint(String defectPoint) {
		this.defectPoint = defectPoint;
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
	public String getLostAssembly() {
		return lostAssembly;
	}
	public void setLostAssembly(String lostAssembly) {
		this.lostAssembly = lostAssembly;
	}
	public String getShiftGroup() {
		return shiftGroup;
	}
	public void setShiftGroup(String shiftGroup) {
		this.shiftGroup = shiftGroup;
	}
	public String getCauseType() {
		return causeType;
	}
	public void setCauseType(String causeType) {
		this.causeType = causeType;
	}
	public String getDrCauseType() {
		return drCauseType;
	}
	public void setDrCauseType(String drCauseType) {
		this.drCauseType = drCauseType;
	}
	public String getPropertyOne() {
		return propertyOne;
	}
	public void setPropertyOne(String propertyOne) {
		this.propertyOne = propertyOne;
	}
	public String getPropertyTwo() {
		return propertyTwo;
	}
	public void setPropertyTwo(String propertyTwo) {
		this.propertyTwo = propertyTwo;
	}
	public String getPropertyThree() {
		return propertyThree;
	}
	public void setPropertyThree(String propertyThree) {
		this.propertyThree = propertyThree;
	}
	public String getPropertyFour() {
		return propertyFour;
	}
	public void setPropertyFour(String propertyFour) {
		this.propertyFour = propertyFour;
	}
	public String getPropertyFive() {
		return propertyFive;
	}
	public void setPropertyFive(String propertyFive) {
		this.propertyFive = propertyFive;
	}
	public String getPropertySix() {
		return propertySix;
	}
	public void setPropertySix(String propertySix) {
		this.propertySix = propertySix;
	}
	public String getPropertySeven() {
		return propertySeven;
	}
	public void setPropertySeven(String propertySeven) {
		this.propertySeven = propertySeven;
	}
	public String getPropertyEight() {
		return propertyEight;
	}
	public void setPropertyEight(String propertyEight) {
		this.propertyEight = propertyEight;
	}
	public String getDutyUser() {
		return dutyUser;
	}
	public void setDutyUser(String dutyUser) {
		this.dutyUser = dutyUser;
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
	public String getDeleteCause() {
		return deleteCause;
	}
	public void setDeleteCause(String deleteCause) {
		this.deleteCause = deleteCause;
	}
	public Timestamp getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}
	public String getDeleteUser() {
		return deleteUser;
	}
	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}
	public String getUpdateCause() {
		return updateCause;
	}
	public void setUpdateCause(String updateCause) {
		this.updateCause = updateCause;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getBackCause() {
		return backCause;
	}
	public void setBackCause(String backCause) {
		this.backCause = backCause;
	}
	public Timestamp getBackTime() {
		return backTime;
	}
	public void setBackTime(Timestamp backTime) {
		this.backTime = backTime;
	}
	public String getBackUser() {
		return backUser;
	}
	public void setBackUser(String backUser) {
		this.backUser = backUser;
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
	public String getRepairType() {
		return repairType;
	}
	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}
	public String getSiteRepair() {
		return siteRepair;
	}
	public void setSiteRepair(String siteRepair) {
		this.siteRepair = siteRepair;
	}
	public String getRepairArea() {
		return repairArea;
	}
	public void setRepairArea(String repairArea) {
		this.repairArea = repairArea;
	}
	public Integer getReworkTime() {
		return reworkTime;
	}
	public void setReworkTime(Integer reworkTime) {
		this.reworkTime = reworkTime;
	}
	public String getRepairMeasures() {
		return repairMeasures;
	}
	public void setRepairMeasures(String repairMeasures) {
		this.repairMeasures = repairMeasures;
	}
	public String getRepairStepType() {
		return repairStepType;
	}
	public void setRepairStepType(String repairStepType) {
		this.repairStepType = repairStepType;
	}
	public String getRepairDutyDept() {
		return repairDutyDept;
	}
	public void setRepairDutyDept(String repairDutyDept) {
		this.repairDutyDept = repairDutyDept;
	}
	public String getRepairLineName() {
		return repairLineName;
	}
	public void setRepairLineName(String repairLineName) {
		this.repairLineName = repairLineName;
	}
	public String getFieldOne() {
		return fieldOne;
	}
	public void setFieldOne(String fieldOne) {
		this.fieldOne = fieldOne;
	}
	public String getFieldTwo() {
		return fieldTwo;
	}
	public void setFieldTwo(String fieldTwo) {
		this.fieldTwo = fieldTwo;
	}
	public String getFieldThree() {
		return fieldThree;
	}
	public void setFieldThree(String fieldThree) {
		this.fieldThree = fieldThree;
	}
	public String getFieldFour() {
		return fieldFour;
	}
	public void setFieldFour(String fieldFour) {
		this.fieldFour = fieldFour;
	}
	public String getFieldFive() {
		return fieldFive;
	}
	public void setFieldFive(String fieldFive) {
		this.fieldFive = fieldFive;
	}
	
	

}
