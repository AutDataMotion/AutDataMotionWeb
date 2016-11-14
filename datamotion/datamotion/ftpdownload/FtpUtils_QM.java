/**
 * <p>title:FtpUtils.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月5日下午4:34:05
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.ftpdownload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import csuduc.platform.util.networkCom.FtpUtils;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;



/**  
 * 创建时间：2015年11月5日 下午4:34:05  
 * 项目名称：zwplatform   
 * 文件名称：FtpUtils.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月5日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: FtpUtils<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2015年11月5日
 */
public class FtpUtils_QM {
	private static Logger logger = Logger.getLogger(FtpUtils.class);
	static int index = 0;//下载文件列表中的第几个元素
	/**
	 * 获取FTPClient对象
	 * 
	 * @param ftpHost
	 *            FTP主机服务器
	 * @param ftpPassword
	 *            FTP 登录密码
	 * @param ftpUserName
	 *            FTP登录用户名
	 * @param ftpPort
	 *            FTP端口 默认为21
	 * @return
	 */
	
	public static FTPClient getFTPClient(String ftpHost, String ftpPassword,
			String ftpUserName, int ftpPort) {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
			ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				System.out.println("未连接到FTP，用户名或密码错误。");
				ftpClient.disconnect();
				ftpClient = null;
			} else {
				System.out.println("FTP连接成功。");
			}
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("FTP的IP地址可能错误，请正确配置。");
			ftpClient = null;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("FTP的端口错误,请正确配置。");
			ftpClient = null;
		}
		return ftpClient;
	}

	public FTPClient connectFtp(String ftpHost, int ftpPort,String ftpUserName, String ftpPassword){
		FTPClient ftpClient = null;
		try {
			ftpClient = FtpUtils.getFTPClient(ftpHost, ftpPassword,
					ftpUserName, ftpPort);
			if (null == ftpClient) {
				System.out.println("登录失败！！！");
				return null;
			}
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setBufferSize(1024*8);
			return ftpClient;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	/*
	 * 使用连接池创建ftpClient
	 */
	public static FTPClient getFTPClientByPool(String ftpHost, int ftpPort, String ftpNameString,
			String ftpPasswordString) {
		FTPClientConfigure config = new FTPClientConfigure(); 
		FTPClient ftpClient = null;
        config.setHost(ftpHost);  
        config.setPort(ftpPort);
        config.setUsername(ftpNameString);  
        config.setPassword(ftpPasswordString);  
        config.setEncoding("utf-8");  
        config.setPassiveMode("false");  
        config.setClientTimeout(30 * 1000);  
          
        FTPClientFactory factory = new FTPClientFactory(config);  
        FTPClientPool pool;
		try {
			pool = new FTPClientPool(factory);
			ftpClient = pool.borrowObject();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return ftpClient;
	}
	/*
	 * 使用连接池连接ftpClient
	 */
	public FTPClient connectFtpByPool(String ftpHost, int ftpPort, String ftpNameString, String ftpPasswordString){
		FTPClient ftpClient = null;
		try {
			ftpClient = getFTPClientByPool(ftpHost, ftpPort, ftpNameString, ftpPasswordString);
			if (null == ftpClient) {
				System.out.println("登录失败！！！");
				return null;
			}
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setBufferSize(1024*8);
			return ftpClient;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	/**
	 * 去 服务器的FTP路径下上读取文件
	 * 
	 * @param ftpUserName
	 * @param ftpPassword
	 * @param ftpPath
	 * @param FTPServer
	 * @return
	 */
	public boolean downloadFile(FTPClient ftpClient,
			String ftpPath, String fileName,String localfilePath, String localfileName) {
		OutputStream output = null;
		try {
			ftpClient.changeWorkingDirectory(ftpPath);
			FTPFile[] remoteFiles;
			remoteFiles = ftpClient.listFiles();
			int indexFile = -1;
			if (remoteFiles != null) {
				System.out.println("---" + remoteFiles.length);
				for (int i = 0; i < remoteFiles.length; i++) {
					System.out.println("---"+remoteFiles[i].getName());
					if (remoteFiles[i].getName().equals(fileName)) {

						indexFile = i;
						break;
					}
				}
			} else {
				System.out.println(ftpPath + "目录为空");
				return false;
			}
			if (-1 == indexFile) {
				System.out.println("没有找到" + fileName + "文件");
				return false;
			}
			File localFile = new File(localfilePath+"/"+localfileName);
			long sizeFile = remoteFiles[indexFile].getSize();

			output = new FileOutputStream(localFile);
			System.out.println(ftpPath + fileName + "---" + sizeFile);
			ftpClient.retrieveFile(fileName, output);
			output.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if (output != null) {
				output.close();
				System.out.println("关闭文件流!");
				output = null;
			}
//			if(ftpClient != null){
//				disconnectFtp(ftpClient);
//			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void disconnectFtp(FTPClient ftpClient){
		try {
			ftpClient.logout();
			ftpClient.disconnect();
			System.out.println("断开连接!");
			ftpClient = null;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	/**
	 * 本地上传文件到FTP服务器
	 * 
	 * @param ftpPath
	 *            远程文件路径FTP
	 * @throws IOException
	 */
	public void uploadfile(String ftpHost, int ftpPort, String ftpUserName,
			String ftpPassword, String ftpPath, String ftpfileName,
			String localpath, String localfilename) {
		FTPClient ftpClient = null;
		System.out.println("开始上传文件到FTP.");
		System.out.format("ftpHost:%s--port:%d--\nftpUserName:%s"
				+ "--ftpPassword:%s--\nftpPath:%s--ftpfileName:%s\n"
				+ "localpath:%s--localfilename:%s"
				, ftpHost
				,ftpPort
				,ftpUserName
				,ftpPassword
				,ftpPath
				,ftpfileName
				,localpath
				,localfilename);
		try {
			ftpClient = FtpUtils.getFTPClient(ftpHost, ftpPassword,
					ftpUserName, ftpPort);
			// 设置PassiveMode传输
			ftpClient.enterLocalPassiveMode();
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

			File f = new File(localpath + localfilename);
			System.out.println("========="+localpath + localfilename);
			InputStream in = new FileInputStream(f);
			ftpClient.storeFile(ftpPath+ftpfileName, in);
			System.out.println("---------"+ftpPath+ftpfileName);
			in.close();
			System.out.println("上传文件" + ftpfileName + "到FTP成功!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ftpClient.disconnect();
				System.out.println("断开连接!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 本地上传文件到FTP服务器
	 * 
	 * @param ftpPath
	 *            远程文件路径FTP
	 * @throws IOException
	 */
	public void upload(String ftpPath, String ftpUserName, String ftpPassword,
			String ftpHost, int ftpPort, String fileContent,
			String writeTempFielPath) {
		FTPClient ftpClient = null;
		System.out.println("开始上传文件到FTP.");
		try {
			ftpClient = FtpUtils.getFTPClient(ftpHost, ftpPassword,
					ftpUserName, ftpPort);
			// 设置PassiveMode传输
			ftpClient.enterLocalPassiveMode();
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// 对远程目录的处理
			String remoteFileName = ftpPath;
			if (ftpPath.contains("/")) {
				remoteFileName = ftpPath
						.substring(ftpPath.lastIndexOf("/") + 1);
			}
			// FTPFile[] files = ftpClient.listFiles(new
			// String(remoteFileName));
			// 先把文件写在本地。在上传到FTP上最后在删除
			boolean writeResult = write(remoteFileName, fileContent,
					writeTempFielPath);
			if (writeResult) {
				File f = new File(writeTempFielPath + "/" + remoteFileName);
				InputStream in = new FileInputStream(f);
				ftpClient.storeFile(remoteFileName, in);
				in.close();
				System.out.println("上传文件" + remoteFileName + "到FTP成功!");
				f.delete();
			} else {
				System.out.println("写文件失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ftpClient.disconnect();
				System.out.println("断开连接!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 把配置文件先写到本地的一个文件中取
	 * 
	 * @param ftpPath
	 * @param str
	 * @return
	 */
	public boolean write(String fileName, String fileContext,
			String writeTempFielPath) {
		try {
			System.out.println("开始写配置文件");
			File f = new File(writeTempFielPath + "/" + fileName);
			if (!f.exists()) {
				if (!f.createNewFile()) {
					System.out.println("文件不存在，创建失败!");
				}
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
			bw.write(fileContext.replaceAll("\n", "\r\n"));
			bw.flush();
			bw.close();
			return true;
		} catch (Exception e) {
			logger.error("写文件没有成功");
			e.printStackTrace();
			return false;
		}
	}

	public boolean isEmpty(FTPClient ftpClient, String ftpPath){
		FTPFile[] remoteFiles;
		try {
			ftpClient.changeWorkingDirectory(ftpPath);
			remoteFiles = ftpClient.listFiles();
			if (remoteFiles.length < 3) { //待确认
				return false;
			}
			else return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 数据归档
	 * @param oldPath 目录oldPath/ZW下存放数据
	 * @param newPath 在待归档目录newPath下新建ZW文件夹（当且仅当不存在该文件夹时）存放数据
	 * @return
	 */
	public boolean copyFolder(String oldPath,String newPath){
		//oldPath为待归档目录，分别以ZW/IALT/MWI新建子文件夹存放数据；newPath为实时归档目录
		File aFile = new File(oldPath);
		File[] fileListsFiles = aFile.listFiles();
		boolean ifGD = false;//判断是否归档
		//高度计归档
		for(int i = 0;i < fileListsFiles.length;i++){
			if(fileListsFiles[i].getName().equals("IALT")){
//				newPath = Parameters.guidangIALT;//设置归档目录，V盘为不实时归档,可以将归档目录写在Parameters文件里
				String newFilePathString = newPath + "\\IALT\\";
				java.io.File fileFolder = new java.io.File(newFilePathString);
				if (!fileFolder.exists()) {
					fileFolder.mkdirs();
				}
				File[] files = fileListsFiles[i].listFiles();
				for(int j = 0;j < files.length;j++){
					String[] namesStrings = files[j].getName().split("_");
					if(namesStrings.length != 10){//数据命名方式错误时，不归档
						System.out.println(files[j].getName()+"命名方式错误，未归档");
						continue;
					}
					if((namesStrings[3].equals("PLS"))&&(namesStrings[4].equals("SCI"))&&(namesStrings[9].equals("0B.dat"))){
						copyFile(files[j].getAbsolutePath(), newFilePathString+files[j].getName());
						ifGD = true;
					}
				}
			}
			//宽波段归档
			else if(fileListsFiles[i].getName().equals("MWI")){
//				newPath = Parameters.guidangMWI;//设置归档目录，V盘为不实时归档,可以将归档目录写在Parameters文件里
				String newFilePathString = newPath + "\\MWI\\";
				java.io.File fileFolder = new java.io.File(newFilePathString);
				if (!fileFolder.exists()) {
					fileFolder.mkdirs();
				}
				File[] files = fileListsFiles[i].listFiles();
				for(int j = 0;j < files.length;j++){
					String[] namesStrings = files[j].getName().split("_");
					if(namesStrings.length != 10){//数据命名方式错误时，不归档
						System.out.println(files[j].getName()+"命名方式错误，未归档");
						continue;
					}
						
					if((namesStrings[4].equals("IMG"))&&(namesStrings[9].equals("0C.dat"))){
						copyFile(files[j].getAbsolutePath(), newFilePathString+files[j].getName());
						ifGD = true;
					}
					if((namesStrings[4].equals("AUX"))&&(namesStrings[3].equals("WBI"))&&((namesStrings[9].equals("0C.csv"))||(namesStrings[9].equals("0B.dat")))){
						copyFile(files[j].getAbsolutePath(), newFilePathString+files[j].getName());
						ifGD = true;
					}
				}
			}
			//紫外归档
			else if(fileListsFiles[i].getName().equals("ZW")){
//				newPath = Parameters.guidangZW;//设置归档目录，V盘为不实时归档,可以将归档目录写在Parameters文件里
				String newFilePathString = newPath + "\\ZW\\";
				java.io.File fileFolder = new java.io.File(newFilePathString);
				if (!fileFolder.exists()) {
					fileFolder.mkdirs();
				}
				File[] files = fileListsFiles[i].listFiles();
				for(int j = 0;j < files.length;j++){
					String[] namesStrings = files[j].getName().split("_");
					if(namesStrings.length != 10){//数据命名方式错误时，不归档
						System.out.println(files[j].getName()+"命名方式错误，未归档");
						continue;
					}
					if((namesStrings[4].equals("IMG"))&&(namesStrings[9].equals("0C.dat"))){
						copyFile(files[j].getAbsolutePath(), newFilePathString+files[j].getName());
						ifGD = true;
					}
					if((namesStrings[4].equals("AUX"))&&(namesStrings[9].equals("0C.csv"))){
						copyFile(files[j].getAbsolutePath(), newFilePathString+files[j].getName());
						ifGD = true;
					}
					if((namesStrings[3].equals("DTD"))&&(namesStrings[4].equals("TTC"))&&(namesStrings[9].equals("0C.csv"))){
						copyFile(files[j].getAbsolutePath(), newFilePathString+files[j].getName());
						ifGD = true;
					}
				}
			}
		}
		if(ifGD == true){
			System.out.println("复制完成！");
		}
		else {
			System.out.println("无待归档数据！");
		}
		return true;
	}
	
	public final static String correctPath(String path){
		return path.replace('\\', '/');
		
	}
	/**
	 * 拷贝单个文件
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public boolean copyFile(String oldPath,
			String newPath) {
		// target 不存在则先创建
		CopyOption[] options =  new CopyOption[] { REPLACE_EXISTING };
		//copy 失败则重复尝试3次
		int cnt = 3;
		while (cnt >0) {
			try {
				System.out.println("文件从"+correctPath(oldPath)+"复制到"+correctPath(newPath)+"");
				Files.copy(Paths.get((oldPath)),
						Paths.get((newPath)), options);
				return true;
			} catch (IOException x) {
				System.out.println(String.format("Unable to copy file :\n from:%s \nto:%s \n errinfo: %s \n retry...",oldPath,newPath, x));
				cnt --;
			}
			
		}
		return false;

	}
	
	
	/**
	 * <p>
	 * Title: main<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String ftpHost = "192.168.199.204";
		final int ftpPort = 21;
		final String ftpNameString = "anonymous";
		final String ftpPasswordString = "anonymous";
		final String ftpPath = "\\testData";//ftp存储路径
		final String ftpFileName = "TS_TG02_QKDS_PRD1_ENG_20161027170746_20161027170746_20161028165342_0C.csv";
		final String localfilePath = "D:\\test";
		final FtpUtils_QM ftpUtils = new FtpUtils_QM();
		final FTPClient ftpClient = ftpUtils.connectFtp(ftpHost, ftpPort, ftpNameString, ftpPasswordString);
		ftpUtils.downloadFile(ftpClient, ftpPath, ftpFileName, localfilePath, ftpFileName);
		ftpUtils.disconnectFtp(ftpClient);
//		final String localPath = "D:\\testData1031\\";//本地存储路径
//		final String copyPath = "D:\\testDataDest";
//		ftpUtils.copyFolder(localPath, copyPath);
		
//		final FTPClient fClient = ftpClient.connectFtp("192.168.2.201",21,
//    			"user", "user");
//		boolean res = false;
//		fileStructList = ftpClient.getSizeLists(fClient, ftpPath);
//		final long timeInterval = 5*1000;
//		Runnable runnable = new Runnable() {
//			boolean isNotEmpty = true;
//			int i = 0;
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				while (true) {//isNotEmpty
//					FTPClient fClient = ftpClient.connectFtp("192.168.2.201",21,
//			    			"user", "user");
//					System.out.println("第"+i+"个线程");
//					i++;
//					try {
//						Thread.sleep(timeInterval);
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//					ftpClient.downloadFile(fClient,
//							ftpPath,  localPath);
////					ftpClient.copyFolder(localPath,serverPathString);
////					isNotEmpty = ftpClient.isEmpty(fClient, ftpPath);
//				}
//			}
//		};
//		Thread thread = new Thread(runnable);
//		try {
//			Thread.sleep(timeInterval);
//		} catch (InterruptedException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		thread.start();
//		
//		ftpClient.getSizeLists(fClient, ftpPath);

		
//！！！！connection reset 异常，则需要检查客户端和服务器端关闭防火墙
		
//		res = ftpClient.downloadFile( "192.168.0.70",21,
//		"anonymous", "anonymous",
//				"/", "plugin_hj.jar",
//				"D:/ftplocal", "plugin_hj.jar");
//		
//		if (res) {
//			System.out.println("下载成功！！");
//		}else{
//			System.out.println("下载失败！！");
//		}
//		ftpClient
//				.uploadfile("192.168.0.70", 21, "anonymous", "anonymous",
//						"/localUser","plugin_SYY.jar","D:/ftplocal", "plugin_SYY.jar");
	}

}
