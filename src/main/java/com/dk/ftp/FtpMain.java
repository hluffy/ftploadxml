package com.dk.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import com.dk.object.PQIAData;
import com.dk.result.Result;
import com.dk.service.PQIADataService;
import com.dk.serviceimpl.PQIADataServiceImpl;

public class FtpMain {
	public static void main(String[] args) {
		if(args.length<3){
			System.out.println("please input full path!");
			System.exit(0);
		}
		final String host = args[0];
		final String username = args[1];
		final String password = args[2];
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				runFtp(host, username, password);
			}
		}, 15*60*1000);
		
		runFtp(host, username, password);
	}
	
	public static void runFtp(String host,String username,String password){
		InputStream ins = null;
		String path = "D:/shangqi/";
		try {
//			String host ="bxu2442210613.my3w.com";
//			String username ="bxu2442210613";
//			String password ="xiaohanxiaohan";
			
			FTPClient ftp = getFtp(host,username,password);
			
			ftp.changeWorkingDirectory("/myfolder/shangqi2/");
//			ftp.changeWorkingDirectory("/INTERFACE/MES_INSPECT/QA01_DEFECT_INFO/2200/");
			List<String> savedFile = new ArrayList<String>();
			FTPFile[] shangqi2files = ftp.listFiles();
			for (FTPFile ftpFile : shangqi2files) {
				savedFile.add(ftpFile.getName());
			}
			
			ftp.disconnect();
			ftp = getFtp(host,username,password);
			ftp.changeWorkingDirectory("/myfolder/shangqixml");
//			ftp.changeWorkingDirectory("/INTERFACE/MES_INSPECT/QA01_DEFECT_INFO/2200/");
			FTPFile[] listFiles = ftp.listFiles();
			for (FTPFile fileName : listFiles) {
				String name = fileName.getName();
				if(!savedFile.contains(name)){
					ftp.disconnect();
					ftp = getFtp(host,username,password);
					ftp.changeWorkingDirectory("/myfolder/shangqixml");
//					ftp.changeWorkingDirectory("/INTERFACE/MES_INSPECT/QA01_DEFECT_INFO/2200/");
					ins = ftp.retrieveFileStream(name);
					ftp.disconnect();
					if(ins!=null){
						Result result = savePqiaData(ins);
						if(result.isStatus()){
							ftp = getFtp(host, username, password);
							ftp.changeWorkingDirectory("/myfolder/shangqixml");
							boolean flag = ftp.retrieveFile(name,new FileOutputStream(path+name));
							ftp.disconnect();
							if(flag){
								File file = new File(path+name);
								FileInputStream fis  = new FileInputStream(file);
								ftp = getFtp(host, username, password);
								ftp.changeWorkingDirectory("/myfolder/shangqi2/");
								boolean success = ftp.storeFile(new String(name.getBytes("UTF-8"),"iso-8859-1"), fis);
								ftp.disconnect();
								if(success){
									System.out.println("保存成功");
								}else{
									System.out.println("保存失败");
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static FTPClient getFtp(String host,String username,String password) throws SocketException, IOException{
		FTPClient ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//		ftp.connect("bxu2442210613.my3w.com");
		ftp.connect(host);
//		ftp.connect("ftp://10.91.235.156");
//		ftp.login("bxu2442210613", "");
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
			PQIADataService service = new PQIADataServiceImpl();
			try {
				SAXReader saxReader = new SAXReader();
//				FTPClient ftp = getFtp();
//				ins = ftp.retrieveFileStream(path);
//				ins = new FileInputStream(path);
				Document doc = saxReader.read(ins);
				Element root = doc.getRootElement();
				List<Element> datas = root.elements("DEFECT_DATA");
				for (Element ele : datas) {
					PQIAData info = new PQIAData();
					info.setShift(ele.elementTextTrim("SHIFT"));
					info.setDefectStatus(ele.elementTextTrim("DEFECT_STATUS"));
					info.setDrFlag(ele.elementTextTrim("DR_FLAG"));
					info.setDifference(ele.elementTextTrim("DIFFERENCE"));
					info.setDefectLevel(ele.elementTextTrim("DEFECT_LEVEL"));
					info.setCreateUser(ele.elementTextTrim("CREATE_USER"));
					info.setCreateTime(Timestamp.valueOf(ele.elementTextTrim("CREATE_TIME")));
					info.setVin(ele.elementTextTrim("VIN"));
					info.setTps(ele.elementTextTrim("TPS"));
					info.setSeries(ele.elementTextTrim("SERIES"));
					info.setMaterial(ele.elementTextTrim("MATERIAL"));
					info.setCsnBody(ele.elementTextTrim("CSN_BODY"));
					info.setDefectModeName(ele.elementTextTrim("DEFECT_MODE_NAME"));
					info.setDefectType(ele.elementTextTrim("DEFECT_TYPE"));
					info.setSite(ele.elementTextTrim("SITE"));
					info.setLocationName(ele.elementTextTrim("LOCATION_NAME"));
					info.setPartNameThird(ele.elementTextTrim("PART_NAME_THIRD"));
					info.setCustomNameCn(ele.elementTextTrim("CUSTOM_NAME_CN"));
					info.setCustomNameEn(ele.elementTextTrim("CUSTOM_NAME_EN"));
					info.setDutyDept(ele.elementTextTrim("DUTY_DEPT"));
					info.setFixingName(ele.elementTextTrim("FIXING_NAME"));
					info.setConfirmSite(ele.elementTextTrim("CONFIRM_SITE"));
					if(ele.elementTextTrim("CONFIRM_TIME")!=null&&!ele.elementTextTrim("CONFIRM_TIME").isEmpty()){
						info.setConfirmTime(Timestamp.valueOf(ele.elementTextTrim("CONFIRM_TIME")));
					}
					info.setConfirmUser(ele.elementTextTrim("CONFIRM_USER"));
					info.setRepairUser(ele.elementTextTrim("REPAIR_USER"));
					if(ele.elementTextTrim("REPAIR_TIME")!=null&&!ele.elementTextTrim("REPAIR_TIME").isEmpty()){
						info.setRepairTime(Timestamp.valueOf(ele.elementTextTrim("REPAIR_TIME")));
					}
					info.setRepairContent(ele.elementTextTrim("REPAIR_CONTENT"));
					info.setSiteRepair(ele.elementTextTrim("SITE_REPAIR"));
					
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