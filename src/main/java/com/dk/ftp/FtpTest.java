package com.dk.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpTest {
	public static void main(String[] args) {
		InputStream ins = null;
		try {
			FTPClient ftp = getFtp();
			
			ftp.changeWorkingDirectory("/myfolder/shangqi2/");
			List<String> savedFile = new ArrayList<String>();
			FTPFile[] shangqi2files = ftp.listFiles();
			for (FTPFile ftpFile : shangqi2files) {
				savedFile.add(ftpFile.getName());
			}
			
			ftp.disconnect();
			ftp = getFtp();
			ftp.changeWorkingDirectory("/myfolder/shangqixml");
			FTPFile[] listFiles = ftp.listFiles();
			for (FTPFile fileName : listFiles) {
				String name = fileName.getName();
				if(!savedFile.contains(name)){
					ftp.changeWorkingDirectory("/myfolder/shangqixml");
					ins = ftp.retrieveFileStream(name);
					ftp.disconnect();
					ftp = getFtp();
					ftp.changeWorkingDirectory("/myfolder/shangqi2/");
					
					if(ins!=null){
						boolean result = ftp.storeFile(new String(name.getBytes("UTF-8"),"iso-8859-1"), ins);
						ins.close();
						if(result){
							System.out.println(name+"-------------保存成功");
						}else{
							System.out.println(name+"-------------保存失败");
						}
						ins = null;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
