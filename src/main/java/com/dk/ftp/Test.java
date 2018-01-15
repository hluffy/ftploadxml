package com.dk.ftp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;

public class Test {
	public static void main(String[] args) {
//		String url = "www.baidu.com";
		String url = "ftp://192.168.1.123/admin";
		try {
			FTPClient ftp = getFtp("192.168.1.123","admin","admin");
			System.out.println(ftp);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			InetAddress i = InetAddress.getByName(url);
//			System.out.println(i.getHostAddress());
//			System.out.println(i.getHostName());
//			InetAddress i = InetAddress.getLocalHost();
//			System.out.println(i.getHostAddress());
//			System.out.println(i.getHostName());
		
	}
	
	public static FTPClient getFtp(String host,String username,String password) throws SocketException, IOException{
		FTPClient ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		
		InetAddress i = InetAddress.getByName(host);
		String ip = i.getHostAddress();
		System.out.println(ip);
		ftp.connect(ip);
		
		ftp.login(username, password);
		//必须，否则获取不到文件
		ftp.enterLocalPassiveMode();
		return ftp;
	}

}
