package com.dk.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.dk.object.PQIAData;
import com.dk.service.PQIADataService;
import com.dk.serviceimpl.PQIADataServiceImpl;

public class Ftp {
	public static void main(String[] args){
//		String fileName = "D:/test.txt";
//		System.out.println(uploadFile(fileName));
//		System.out.println(downloadFile());
		
//		InputStream ins = null;
//		try {
//			SAXReader saxReader = new SAXReader();
//			FTPClient ftp = getFtp();
//			ftp.changeWorkingDirectory("myfolder");
//			ins = ftp.retrieveFileStream("user.xml");
//			Document doc = saxReader.read(ins);
//			Element root = doc.getRootElement();
//			System.out.println(root);
//			List<Element> name = root.elements("name");
//			for(Element ele:name){
//				System.out.println(ele.elementText("value"));
//				System.out.println(ele.elementText("age"));
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		String path = "myfolder/shangqixml/INSPECT_1494467231005.xml";
		savePqiaData(path);
	}
	
	//保存到数据库
	@SuppressWarnings("unchecked")
	public static void savePqiaData(String path){
		InputStream ins = null;
		PQIADataService service = new PQIADataServiceImpl();
		try {
			SAXReader saxReader = new SAXReader();
			FTPClient ftp = getFtp();
			ins = ftp.retrieveFileStream(path);
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
				info.setConfirmTime(Timestamp.valueOf(ele.elementTextTrim("CONFIRM_TIME")));
				info.setConfirmUser(ele.elementTextTrim("CONFIRM_USER"));
				info.setRepairUser(ele.elementTextTrim("REPAIR_USER"));
				info.setRepairTime(Timestamp.valueOf(ele.elementTextTrim("REPAIR_TIME")));
				info.setRepairContent(ele.elementTextTrim("REPAIR_CONTENT"));
				info.setSiteRepair(ele.elementTextTrim("SITE_REPAIR"));
				
				service.addInfo(info);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//下载文件
	public static boolean downloadFile(){
		boolean result = false;
		//在控制打印出目录
		try {
			FTPClient ftp = getFtp();
			//更换目录
			ftp.changeWorkingDirectory("htdocs");
//			FTPFile[] lists = ftp.listFiles();//遍历该目录下的所有文件
			ftp.setBufferSize(1024);
			ftp.setControlEncoding("GBK");
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			result = ftp.retrieveFile("/myfolder/downloadtest.txt", new FileOutputStream("D:/downtest.txt"));
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//上传文件
	public static boolean uploadFile(String fileName){
		boolean result = false;
		FileInputStream fis = null;
		try {
			FTPClient ftp = getFtp();
			File file = new File(fileName);
			fis = new FileInputStream(file);
			//上传文件
			ftp.changeWorkingDirectory("/myfolder/");
//			ftp.setBufferSize(1024);
//			ftp.setControlEncoding("UTF-8");
//			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			result = ftp.storeFile(new String("/myfolder/test1.txt".getBytes("UTF-8"),"iso-8859-1"), fis);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static FTPClient getFtp() throws SocketException, IOException{
		FTPClient ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		ftp.connect("bxu2442210613.my3w.com");
		ftp.login("bxu2442210613", "");
		//必须，否则获取不到文件
		ftp.enterLocalPassiveMode();
		return ftp;
	}

}
