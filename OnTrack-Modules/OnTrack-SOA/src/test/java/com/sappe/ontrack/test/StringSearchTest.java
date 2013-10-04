package com.sappe.ontrack.test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpClientFactory;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.junit.Test;

import com.jcraft.jsch.Session;

public class StringSearchTest {

//	@Test
	public void test() throws IOException{
		///var/opt/webMethods/IntegrationServer/packages
		File dir = new File("src");
		String[] extensions = new String[] { "java" };
		System.out.println("Getting all java files in " + dir.getCanonicalPath()
				+ " including those in subdirectories");
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		for (File file : files) {
			readFile(file);
			//			System.out.println("file: " + file.getCanonicalPath());
		}
	}

	private void readFile(File file) throws IOException{
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line!= null){
			//			System.out.println(line);
			if(line.indexOf("finally")>0){
				System.out.println("File: "+file.getAbsolutePath()+" tiene finally");
			}
			line = br.readLine();
		}
		fr.close();
	}

	@Test
	public void test2(){
		downloadFilesFromFTP("buadaix001", "wmethods", "1q2w3e4r", "", "/var/opt/webMethods/IntegrationServer/packages");
	}
	
	public void downloadFilesFromFTP(String server,String username,String password,String localdir,String remotedir){
		try {
			FileSystemOptions options = createDefaultOptions();
			
			Session session = SftpClientFactory.createConnection(server, 22, username.toCharArray(), password.toCharArray(), options);
			StandardFileSystemManager manager = new StandardFileSystemManager();
			manager.resolveURI(remotedir);
			FTPClient ftp = new FTPClient();
			int reply;
			//			Map<String,Boolean> saveLogFile = new HashMap<String,Boolean>();

			ftp.connect(server);
			ftp.login(username,password);
			reply = ftp.getReplyCode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory(remotedir);

			if(FTPReply.isPositiveCompletion(reply)){
				FTPFile[] files = ftp.listFiles();
				for (FTPFile file : files) {
					if(file.isFile()){
						if(file.getName().endsWith(".java")){
							InputStream fis = ftp.retrieveFileStream(file.getName());
							OutputStream os = new FileOutputStream(file.getName());
							BufferedOutputStream bos = new BufferedOutputStream(os);

							while(fis.available()>0){
								int toRead = Math.min(fis.available(), 4096);
								byte[]buffer = new byte[toRead];
								fis.read(buffer);
								bos.write(buffer);
							}
						}
					}
				}
			}
		}catch (SocketException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static String createConnectionString(String hostName,String username, String password, String remoteFilePath) {
        // result: "sftp://user:123456@domainname.com/resume.pdf
        return "sftp://" + username + ":" + password + "@" + hostName
                + remoteFilePath;
    }
	
	public static FileSystemOptions createDefaultOptions() throws FileSystemException {
        // Create SFTP options
        FileSystemOptions opts = new FileSystemOptions();
         
        // SSH Key checking
        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
                opts, "no");
         
        // Root directory set to user home
        SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, false);
         
        // Timeout is count by Milliseconds
        SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);
         
        return opts;
    }
}
