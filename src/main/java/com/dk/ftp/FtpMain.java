package com.dk.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.dk.object.PQIAInfo;
import com.dk.result.Result;
import com.dk.service.PQIADataService;
import com.dk.serviceimpl.PQIADataServiceImpl;
import com.dk.serviceimpl.PQIAInfoServiceImpl;

public class FtpMain {
	public static void main(String[] args) {
//		if(args.length<3){
//			System.out.println("please input full path!");
//			System.exit(0);
//		}
//		final String host = args[0];
//		final String username = args[1];
//		final String password = args[2];
//		
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				runFtp(host, username, password);
//			}
//		}, 15*60*1000);
		
		final String host1 = "10.91.235.156/INTERFACE/MES_INSPECT/QA01_DEFECT_INFO/2200";
		final String host2 = "10.91.235.156/INTERFACE/MES_INSPECT/QA01_DEFECT_INFO/ARCHIVE";
		final String username = "lgmg2in1_lg";
		final String password = "lgmg2in1_lg";
		
//		final String host1 = "ftp://bxw2713550723.my3w.com/PQIA_DPS/QA01_DEFECT_INFO/LG/";
//		final String host2 = "ftp://bxw2713550723.my3w.com/PQIA_DPS/QA01_DEFECT_INFO/LG/Archive/";
//		String host = "bxu2442210613.my3w.com";
//		String username = "bxu2442210613";
//		String password = "";
		runFtp(host1,host2, username, password);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				runFtp(host1,host2, username, password);
			}
		}, 0, 14*60*1000);
	}
	
	public static void runFtp(String host1,String host2,String username,String password){
		InputStream ins = null;
		String path = "C:/shangqi/";
		FTPClient ftp = null;
		try {
			
			ftp = getFtp(host2,username,password);
			
			ftp.changeWorkingDirectory("/PQIA_DPS/QA01_DEFECT_INFO/LG/Archive/");
			
			List<String> savedFile = new ArrayList<String>();
			FTPFile[] shangqi2files = ftp.listFiles();
			for (FTPFile ftpFile : shangqi2files) {
				savedFile.add(ftpFile.getName());
			}
			
			ftp.disconnect();
			ftp = getFtp(host1,username,password);
			ftp.changeWorkingDirectory("/PQIA_DPS/QA01_DEFECT_INFO/LG/");
			FTPFile[] listFiles = ftp.listFiles();
			for (FTPFile fileName : listFiles) {
				if(!fileName.isDirectory()){
					String name = fileName.getName();
					if(!savedFile.contains(name)){
						ftp.disconnect();
						ftp = getFtp(host1,username,password);
						ftp.changeWorkingDirectory("/PQIA_DPS/QA01_DEFECT_INFO/LG/");
						ins = ftp.retrieveFileStream(name);
						ftp.disconnect();
						if(ins!=null){
							Result result = savePqiaData(ins);
							System.out.println(result.isStatus());
							if(result.isStatus()){
								ftp = getFtp(host1, username, password);
								ftp.changeWorkingDirectory("/PQIA_DPS/QA01_DEFECT_INFO/LG/");
								System.out.println(path+name);
								boolean flag = ftp.retrieveFile(name,new FileOutputStream(path+name));
								
								//删除文件
//								ftp.deleteFile(fileName.getName());
								
								ftp.disconnect();
								if(flag){
									File file = new File(path+name);
									FileInputStream fis  = new FileInputStream(file);
									ftp = getFtp(host2, username, password);
									ftp.changeWorkingDirectory("/PQIA_DPS/QA01_DEFECT_INFO/LG/Archive/");
									boolean success = ftp.storeFile(new String(name.getBytes("UTF-8"),"iso-8859-1"), fis);
//									file.delete();
									fis.close();
									ftp.disconnect();
									if(success){
										System.out.println(name+"保存成功");
									}else{
										System.out.println(name+"保存失败");
									}
								}
							}
						}
					}
				}
				
			}
//			ftp.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(ftp!=null){
				try {
					ftp.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static FTPClient getFtp(String host,String username,String password) throws SocketException, IOException{
		FTPClient ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//		ftp.connect("bxu2442210613.my3w.com");
//		ftp.connect(host);
		
//		InetAddress i = InetAddress.getByName(host);
//		String ip = i.getHostAddress();
//		ftp.connect(ip);
		
//		ftp.connect("ftp://10.91.235.156");
//		ftp.login("bxu2442210613", "");
		ftp.connect(host);
		ftp.login(username, password);
//		ftp.login("lgmg2in1_lg", "lgmg2in1_lg");
		//必须，否则获取不到文件
		ftp.enterLocalPassiveMode();
		return ftp;
	}
	
	//保存到数据库
		@SuppressWarnings("unchecked")
		public static Result savePqiaData(InputStream ins){
			Result result = new Result();
			PQIADataService service = new PQIAInfoServiceImpl();
			try {
				SAXReader saxReader = new SAXReader();
//				FTPClient ftp = getFtp();
//				ins = ftp.retrieveFileStream(path);
//				ins = new FileInputStream(path);
				Document doc = saxReader.read(ins);
				Element root = doc.getRootElement();
				List<Element> datas = root.elements("DEFECT_DATA");
				for (Element ele : datas) {
					PQIAInfo info = new PQIAInfo();
//					info.setShift(ele.elementTextTrim("SHIFT"));
//					info.setDefectStatus(ele.elementTextTrim("DEFECT_STATUS"));
//					info.setDrFlag(ele.elementTextTrim("DR_FLAG"));
//					info.setDifference(ele.elementTextTrim("DIFFERENCE"));
//					info.setDefectLevel(ele.elementTextTrim("DEFECT_LEVEL"));
//					info.setCreateUser(ele.elementTextTrim("CREATE_USER"));
//					info.setCreateTime(Timestamp.valueOf(ele.elementTextTrim("CREATE_TIME")));
//					info.setVin(ele.elementTextTrim("VIN"));
//					info.setTps(ele.elementTextTrim("TPS"));
//					info.setSeries(ele.elementTextTrim("SERIES"));
//					info.setMaterial(ele.elementTextTrim("MATERIAL"));
//					info.setCsnBody(ele.elementTextTrim("CSN_BODY"));
//					info.setDefectModeName(ele.elementTextTrim("DEFECT_MODE_NAME"));
//					info.setDefectType(ele.elementTextTrim("DEFECT_TYPE"));
//					info.setSite(ele.elementTextTrim("SITE"));
//					info.setLocationName(ele.elementTextTrim("LOCATION_NAME"));
//					info.setPartNameThird(ele.elementTextTrim("PART_NAME_THIRD"));
//					info.setCustomNameCn(ele.elementTextTrim("CUSTOM_NAME_CN"));
//					info.setCustomNameEn(ele.elementTextTrim("CUSTOM_NAME_EN"));
//					info.setDutyDept(ele.elementTextTrim("DUTY_DEPT"));
//					info.setFixingName(ele.elementTextTrim("FIXING_NAME"));
//					info.setConfirmSite(ele.elementTextTrim("CONFIRM_SITE"));
//					if(ele.elementTextTrim("CONFIRM_TIME")!=null&&!ele.elementTextTrim("CONFIRM_TIME").isEmpty()){
//						info.setConfirmTime(Timestamp.valueOf(ele.elementTextTrim("CONFIRM_TIME")));
//					}
//					info.setConfirmUser(ele.elementTextTrim("CONFIRM_USER"));
//					info.setRepairUser(ele.elementTextTrim("REPAIR_USER"));
//					if(ele.elementTextTrim("REPAIR_TIME")!=null&&!ele.elementTextTrim("REPAIR_TIME").isEmpty()){
//						info.setRepairTime(Timestamp.valueOf(ele.elementTextTrim("REPAIR_TIME")));
//					}
//					info.setRepairContent(ele.elementTextTrim("REPAIR_CONTENT"));
//					info.setSiteRepair(ele.elementTextTrim("SITE_REPAIR"));
					
					info.setVin(ele.elementTextTrim("VIN"));
					info.setTps(ele.elementTextTrim("TPS"));
					info.setSeries(ele.elementTextTrim("SERIES"));
					info.setMaterial(ele.elementTextTrim("MATERIAL"));
					info.setCsnBody(ele.elementTextTrim("CSN_BODY"));
					info.setCsnPaint(ele.elementTextTrim("CSN_PAINT"));
					info.setCsnAssembly(ele.elementTextTrim("CSN_ASSEMBLY"));
					info.setDefectModeName(ele.elementTextTrim("DEFECT_MODEL_NAME"));
					info.setDefectType(ele.elementTextTrim("DEFECT_TYPE"));
					info.setSite(ele.elementTextTrim("SITE"));
					info.setFixingName(ele.elementTextTrim("FIXING_NAME"));
					info.setLocationName(ele.elementTextTrim("LOCATION_NAME"));
					info.setPartNameFirst(ele.elementTextTrim("PART_NAME_FIRST"));
					info.setPartNameSecont(ele.elementTextTrim("PART_NAME_SECONT"));
					info.setPartNameThird(ele.elementTextTrim("PART_NAME_THIRD"));
					info.setPartNameMatch(ele.elementTextTrim("PART_NAME_MATCH"));
					info.setCustomNameCn(ele.elementTextTrim("CUSTOM_NAME_CN"));
					info.setCustomNameEn(ele.elementTextTrim("CUSTOM_NAME_EN"));
					info.setShift(ele.elementTextTrim("SHIFT"));
					info.setDefectStatus(ele.elementTextTrim("DEFECT_STATUS"));
					info.setDrFlag(ele.elementTextTrim("DR_FLAG"));
					info.setDutyDept(ele.elementTextTrim("DUTY_DEPT"));
					info.setDefectPoint(ele.elementTextTrim("DEFECT_POINT"));
					info.setDifference(ele.elementTextTrim("DIFFERENCE"));
					info.setDefectLevel(ele.elementTextTrim("DEFECT_LEVEL"));
					info.setLostAssembly(ele.elementTextTrim("LOST_ASSEMBLY"));
					info.setShiftGroup(ele.elementTextTrim("SHIFT_GROUP"));
					info.setCauseType(ele.elementTextTrim("CAUSE_TYPE"));
					info.setDrCauseType(ele.elementTextTrim("DR_CAUSE_TYPE"));
					info.setPropertyOne(ele.elementTextTrim("PROPERTY_ONE"));
					info.setPropertyTwo(ele.elementTextTrim("PROPERTY_TWO"));
					info.setPropertyThree(ele.elementTextTrim("PROPERTY_THREE"));
					info.setPropertyFour(ele.elementTextTrim("PROPERTY_FOUR"));
					info.setPropertyFive(ele.elementTextTrim("PROPERTY_FIVE"));
					info.setPropertySix(ele.elementTextTrim("PROPERTY_SIX"));
					info.setPropertySeven(ele.elementTextTrim("PROPERTY_SEVEN"));
					info.setPropertyEight(ele.elementTextTrim("PROPERTY_EIGHT"));
					info.setDutyUser(ele.elementTextTrim("DUTY_USER"));
					info.setCreateUser(ele.elementTextTrim("CREATE_USER"));
					String createTime = ele.elementTextTrim("CREATE_TIME");
					if(createTime!=null&&!createTime.isEmpty()){
						info.setCreateTime(Timestamp.valueOf(createTime));
					}
					
					info.setDeleteCause(ele.elementTextTrim("DELETE_CAUSE"));
					String deleteTime = ele.elementTextTrim("DELETE_TIME");
					if(deleteTime!=null&&!deleteTime.isEmpty()){
						info.setDeleteTime(Timestamp.valueOf(deleteTime));
					}
					info.setDeleteUser(ele.elementTextTrim("DELETE_USER"));
					
					info.setUpdateCause(ele.elementTextTrim("UPDATE_CAUSE"));
					String updateTime = ele.elementTextTrim("UPDATE_TIME");
					if(updateTime!=null&&!updateTime.isEmpty()){
						info.setUpdateTime(Timestamp.valueOf(updateTime));
					}
					info.setUpdateUser(ele.elementTextTrim("UPDATE_USER"));
					
					info.setBackCause(ele.elementTextTrim("BACK_CAUSE"));
					String backTime = ele.elementTextTrim("BACK_TIME");
					if(backTime!=null&&!backTime.isEmpty()){
						info.setBackTime(Timestamp.valueOf(backTime));
					}
					info.setBackUser(ele.elementTextTrim("BACK_USER"));
					
					info.setConfirmSite(ele.elementTextTrim("CONFIRM_SITE"));
					String confirmTime = ele.elementTextTrim("CONFIRM_TIME");
					if(confirmTime!=null&&!confirmTime.isEmpty()){
						info.setConfirmTime(Timestamp.valueOf(confirmTime));
					}
					info.setConfirmUser(ele.elementTextTrim("CONFIRM_USER"));
					
					info.setRepairUser(ele.elementTextTrim("REPAIR_USER"));
					String repairTime = ele.elementTextTrim("REPAIR_TIME");
					if(repairTime!=null&&!repairTime.isEmpty()){
						info.setRepairTime(Timestamp.valueOf(repairTime));
					}
					info.setRepairContent(ele.elementTextTrim("REPAIR_CONTENT"));
					info.setRepairType(ele.elementTextTrim("REPAIR_TYPE"));
					info.setSiteRepair(ele.elementTextTrim("SITE_REPAIR"));
					info.setRepairArea(ele.elementTextTrim("REPAIR_AREA"));
					String reworkTime = ele.elementTextTrim("REWORK_TIME");
					if(reworkTime!=null&&!reworkTime.isEmpty()){
						info.setReworkTime(Integer.valueOf(reworkTime));
					}
					info.setRepairMeasures(ele.elementTextTrim("REPAIR_MEASURES"));
					info.setRepairStepType(ele.elementTextTrim("REPAIR_STEP_TYPE"));
					info.setRepairDutyDept(ele.elementTextTrim("REPAIR_DUTY_DEPT"));
					info.setRepairLineName(ele.elementTextTrim("REPAIR_LINE_NAME"));
					info.setFieldOne(ele.elementTextTrim("FIELD_ONE"));
					info.setFieldTwo(ele.elementTextTrim("FIELD_TWO"));
					info.setFieldThree(ele.elementTextTrim("FIELD_THREE"));
					info.setFieldFour(ele.elementTextTrim("FIELD_FOUR"));
					info.setFieldFive(ele.elementTextTrim("FIELD_FIVE"));
					
					
					result = service.addInfo(info);
					System.out.println("保存成功");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
			
		}

}
