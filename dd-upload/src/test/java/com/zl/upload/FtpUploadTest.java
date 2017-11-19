//package com.zl.upload;
//
//import com.zl.ddshop.common.util.FtpUtils;
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//public class FtpUploadTest {
//    @Test
//    public void testFtpUpload() throws IOException {
//        //创建FTPClient客户端
//        FTPClient ftpClient = new FTPClient();
//        //创建FTP连接
//        ftpClient.connect("192.168.1.107",21);
//        //登录
//        ftpClient.login("ftpuser","zhanglei123");
//        //读取本地文件
//        FileInputStream fileInputStream = new FileInputStream(new File("d:\\2.png"));
//        //配置上传参数
//        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
//        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//        //上传文件
//        ftpClient.storeFile("hello.jpg",fileInputStream);
//        //关闭连接
//        fileInputStream.close();
//        ftpClient.logout();
//
//    }
//    @Test
//    public void testFtpUtil() throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(new File("d:\\3.png"));
//        FtpUtils.uploadFile("192.168.1.107",21,"ftpuser","zhanglei123","/home/ftpuser/www/images","/2017/09/11","hello2.png",fileInputStream);
//        fileInputStream.close();
//    }
//}
