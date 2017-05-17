package com.dk.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Ftp {
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
//		String fileName = "D:/test.txt";
//		System.out.println(uploadFile(fileName));
//		System.out.println(downloadFile());
		
		InputStream ins = null;
		try {
			SAXReader saxReader = new SAXReader();
			FTPClient ftp = getFtp();
			FTPFile path = new FTPFile();
			System.out.println(path.getName());
			ins = ftp.retrieveFileStream("/myfolder/user.xml");
			Document doc = saxReader.read(ins);
			Element root = doc.getRootElement();
			System.out.println(root);
			List<Element> name = root.elements("name");
			for(Element ele:name){
				System.out.println(ele.elementText("value"));
				System.out.println(ele.elementText("age"));
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
//			FTPFile[] lists = ftp.listFiles();//便利该目录下的所有文件
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
		ftp.connect("");
		ftp.login("", "");
		//必须，否则获取不到文件
		ftp.enterLocalPassiveMode();
		return ftp;
	}

}
