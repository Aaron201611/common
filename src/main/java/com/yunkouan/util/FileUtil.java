/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2016年12月13日 上午10:59:54</P>
 * @author andy wang<br/>
 */
package com.yunkouan.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.yunkouan.exception.BizException;

import java.io.BufferedInputStream;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile; 
import jcifs.smb.SmbFileInputStream; 
import jcifs.smb.SmbFileOutputStream;
import jcifs.smb.SmbFilenameFilter; 

/**
 * 文件操作类<br/><br/>
 * @version 2016年12月13日 上午10:59:54</P>
 * @author andy wang<br/>
 */
public class FileUtil {
	private static Log log = LogFactory.getLog(FileUtil.class);
	
	/**
	 * 获取文件名
	 * @return 文件名
	 * @version 2017年2月20日 上午11:31:45<br/>
	 * @author andy wang<br/>
	 */
	public static String getFileName () {
		return String.valueOf(UUID.randomUUID());
	}
	
	public static String getResource() {
		return FileUtil.class.getResource("/").getPath();
	}
	
	
	/**
	 * 获取服务器路径
	 * @return 服务器路径
	 * @version 2017年2月20日 上午11:32:34<br/>
	 * @author andy wang<br/>
	 */
	public static String getResourcePath() {
		return FileUtil.class.getResource("/").getPath().replace("webapps/wms/WEB-INF/classes/", "upload/");
	}
	
	/**
	 * 根据上传的文件对象，创建一个新的文件到服务器
	 * @param fileLicense 上传的文件对象
	 * @return 新文件的对象
	 * @version 2017年2月20日 上午11:36:21<br/>
	 * @author andy wang<br/>
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static File createNewFile ( MultipartFile fileLicense ) throws Exception {
		if ( fileLicense == null || StringUtil.isTrimEmpty(fileLicense.getOriginalFilename()) ) {
			return null;
		}
		// 原始名称
		String originalFilename = fileLicense.getOriginalFilename();
		// 存储文件的物理路径
		String path = FileUtil.getResourcePath();
		// 新的文件名称
		String newFileName = FileUtil.getFileName()
				+ originalFilename.substring(originalFilename
						.lastIndexOf("."));
		// 创建新文件
		File newFile = new File(path + newFileName);
		// 判断目标文件所在的目录是否存在
		if (!newFile.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			log.error("目标文件所在目录不存在，准备创建它！");
			if (!newFile.getParentFile().mkdirs()) {
				System.out.println("创建目标文件所在目录失败！");
				throw new BizException("创建目标文件所在目录失败！");
			}
		}
		// 将内存中的数据写入磁盘
		fileLicense.transferTo(newFile);
		return newFile;
	}
	
	
	/**
	 * 根据路径读取Excel，并转化为流
	 * @param path Excel文件路径
	 * @param downloadName 用户下载的文件名称
	 * @return 响应流对象
	 * @throws Exception
	 * @version 2017年4月25日 下午8:44:18<br/>
	 * @author andy wang<br/>
	 */
	public static ResponseEntity<byte[]> excel2Stream ( String path , String downloadName ) throws Exception {
		InputStream fis = null;
		OutputStream fos = null;
		ResponseEntity<byte[]> responseEntity = null;
		try {
			HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    	headers.setContentDispositionFormData("attachment", downloadName);
	    	fis = new FileInputStream(new File(path));
	    	byte[] b = new byte[fis.available()];  
	    	fos = new ByteArrayOutputStream(fis.available()); 
	    	int n;  
	    	while ((n = fis.read(b)) != -1) {  
	    		fos.write(b, 0, n);  
	    	}  
	    	fos.write(b);
	    	responseEntity = new ResponseEntity<byte[]>(b,headers, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		} finally {
			if ( fis != null ) {
				fis.close();
			}
			if ( fos != null ) {
				fos.close();
			}
		}
		return responseEntity;
	}
	
	/**
	 * 根据路径读取Excel，并转化为流
	 * @param path Excel文件路径
	 * @param downloadName 用户下载的文件名称
	 * @return 响应流对象
	 * @throws Exception
	 * @version 2017年4月25日 下午8:44:18<br/>
	 * @author andy wang<br/>
	 */
	public static ResponseEntity<byte[]> stream2Byte ( String downloadName, OutputStream fos, int length ) throws Exception {
		ResponseEntity<byte[]> responseEntity = null;
		try {
			HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    	headers.setContentDispositionFormData("attachment", downloadName);
	    	byte[] b = new byte[length];
	    	fos.write(b);
	    	responseEntity = new ResponseEntity<byte[]>(b,headers, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		} finally {
			if ( fos != null ) {
				fos.close();
			}
		}
		return responseEntity;
	}


	/** 
	* @Title: listFiles 
	* @Description: 列出共享文件夹目录下的文件
	* @auth tphe06
	* @time 2018 2018年7月31日 下午3:13:49
	* @param remoteDoc 共享目录
	* 如果是无需密码的共享，则类似如下格式：smb://ip/sharefolder(例如：smb://192.168.0.77/test/1.xml)
	* 如果需要用户名、密码，则类似如下格式：Smb://username:password@ip/sharefolder(例如：smb://chb:123456@192.168.0.1/test/1.xml)
	* @param pattern 文件名正则匹配表达式
	* @return
	* @throws MalformedURLException
	* @throws SmbException
	* SmbFile[]
	*/
	public static SmbFile[] listFiles(String remoteDoc, String pattern) throws MalformedURLException, SmbException {
		if(log.isDebugEnabled()) log.debug(remoteDoc);
		SmbFile file = new SmbFile(remoteDoc);
		return file.listFiles(new SmbFilenameFilter() {
			@Override
			public boolean accept(SmbFile dir, String name) throws SmbException {
				return PatternUtil.valid(name, pattern);
			}
		});
	}

	/** 
	* @Title: write 
	* @Description: 写文件
	* @auth tphe06
	* @time 2018 2018年7月26日 上午11:31:38
	* @param url 文件完整路径
	* @param content 文件内容（UTF-8编码）
	* @throws IOException
	* void
	*/
	public static void write(String url, String content) throws IOException {
        FileOutputStream out = null;
        BufferedOutputStream buff = null;
		try {
            out = new FileOutputStream(new File(url));
            buff = new BufferedOutputStream(out);
            buff.write(content.getBytes("UTF-8"));
            buff.flush();
		} finally {
			try {
				if(buff != null) buff.close();
			} catch (IOException e) {
				if(log.isErrorEnabled()) log.error(e.getMessage(), e);
			}
			try {
				if(out != null) out.close();
			} catch (IOException e) {
				if(log.isErrorEnabled()) log.error(e.getMessage(), e);
			}
		}
	}
	/** 
	* @Title: smbWrite 
	* @Description: 写入共享文件夹下文件内容
	* @auth tphe06
	* @time 2018 2018年7月31日 下午3:03:46
	* @param remoteFile
	* 如果是无需密码的共享，则类似如下格式：smb://ip/sharefolder(例如：smb://192.168.0.77/test/1.xml)
	* 如果需要用户名、密码，则类似如下格式：Smb://username:password@ip/sharefolder(例如：smb://chb:123456@192.168.0.1/test/1.xml)
	* @param content
	* @throws IOException
	* void
	*/
	public static void smbWrite(String remoteFile, String content) throws IOException {
		if(log.isInfoEnabled()) log.info(remoteFile);
		if(log.isInfoEnabled()) log.info("写入共享文件夹内容：");
		if(log.isInfoEnabled()) log.info(content);
		SmbFileOutputStream out = null;
        BufferedOutputStream buff = null;
		try {
            out = new SmbFileOutputStream(new SmbFile(remoteFile));
            buff = new BufferedOutputStream(out);
            buff.write(content.getBytes("UTF-8"));
            buff.flush();
		} finally {
			try {
				if(buff != null) buff.close();
			} catch (IOException e) {
				if(log.isErrorEnabled()) log.error(e.getMessage(), e);
			}
			try {
				if(out != null) out.close();
			} catch (IOException e) {
				if(log.isErrorEnabled()) log.error(e.getMessage(), e);
			}
		}
	}

	/** 
	* @Title: read 
	* @Description: 读文件（注意有大小限制）
	* @auth tphe06
	* @time 2018 2018年7月26日 上午11:58:03
	* @param file
	* @return
	* @throws IOException
	* String
	*/
	public static String read(File file) throws IOException {
        byte[] filecontent = new byte[(int) file.length()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(filecontent);
            return new String(filecontent, "UTF-8");
        } finally {
        	if(in != null)
				try {
					in.close();
				} catch (IOException e) {
					if(log.isErrorEnabled()) log.error(e.getMessage(), e);
				}
        }
    }
	/** 
	* @Title: read 
	* @Description: 读文件（注意有大小限制）
	* @auth tphe06
	* @time 2018 2018年7月26日 上午11:43:16
	* @param fileName 文件完整路径
	* @return
	* @throws IOException
	* String
	*/
	public static String read(String fileName) throws IOException {
		return read(new File(fileName));
	}
	/** 
	* @Title: smbRead 
	* @Description: 读取共享文件夹目录下文件内容
	* @auth tphe06
	* @time 2018 2018年7月31日 下午3:18:14
	* @param file
	* @return
	 * @throws IOException
	* String
	*/
	public static String smbRead(SmbFile file) throws IOException {
		if(log.isInfoEnabled()) log.info(file.getPath());
        byte[] filecontent = new byte[(int) file.length()];
        SmbFileInputStream in = null;
        try {
            in = new SmbFileInputStream(file);
            in.read(filecontent);
            String content = new String(filecontent, "UTF-8");
    		if(log.isInfoEnabled()) log.info("读取共享文件夹内容：");
    		if(log.isInfoEnabled()) log.info(content);
            return content;
        } finally {
        	if(in != null)
				try {
					in.close();
				} catch (IOException e) {
					if(log.isErrorEnabled()) log.error(e.getMessage(), e);
				}
        }
    }
	/** 
	* @Title: smbRead 
	* @Description: 读取共享文件夹目录下文件内容
	* @auth tphe06
	* @time 2018 2018年7月31日 下午3:02:55
	* @param remoteFile
	* 如果是无需密码的共享，则类似如下格式：smb://ip/sharefolder(例如：smb://192.168.0.77/test/1.xml)
	* 如果需要用户名、密码，则类似如下格式：Smb://username:password@ip/sharefolder(例如：smb://chb:123456@192.168.0.1/test/1.xml)
	* @return
	* @throws IOException
	* String
	*/
	public static String smbRead(String remoteFile) throws IOException {
		if(log.isInfoEnabled()) log.info(remoteFile);
		SmbFile file = new SmbFile(remoteFile);
		return smbRead(file);
    }


	/** 
	* Description: 从共享目录拷贝文件到本地 
	* @Version1.0 Sep 25, 2009 3:48:38 PM 
	* @param remoteFileName 共享目录上的文件路径 
	* 如果是无需密码的共享，则类似如下格式：smb://ip/sharefolder(例如：smb://192.168.0.77/test)
	* 如果需要用户名、密码，则类似如下格式：Smb://username:password@ip/sharefolder(例如：smb://chb:123456@192.168.0.1/test)
	* smb:域名;用户名:密码@目的IP/文件夹/文件名.xxx：test.smbPut("smb://szpcg;jiang.t:xxx@192.168.193.13/Jake", "c://test.txt") ;
	* @param localFileName 本地文件路径
	*/
	public static boolean smbGet(String remoteFileName, String localFileName) {
		if(log.isInfoEnabled()) log.info(remoteFileName);
		if(log.isInfoEnabled()) log.info(localFileName);
		InputStream in = null;
		OutputStream out = null;
		try {
			SmbFile remoteFile = new SmbFile(remoteFileName);
//			String fileName = remoteFile.getName();
//			File localFile = new File(localDir+File.separator+fileName);
			File localFile = new File(localFileName);
			in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
			out = new BufferedOutputStream(new FileOutputStream(localFile));
			byte[] buffer = new byte[1024];
			while(in.read(buffer)!=-1){
				out.write(buffer);
				buffer = new byte[1024];
			}
		} catch (Exception e) {
			if(log.isErrorEnabled()) log.error(e.getMessage(), e);
			return false;
		} finally {
			try {
				if(in != null) in.close();
			} catch (IOException e) {
				if(log.isErrorEnabled()) log.error(e.getMessage(), e);
			}
			try {
				if(out != null) out.close();
			} catch (IOException e) {
				if(log.isErrorEnabled()) log.error(e.getMessage(), e);
			}
		}
		return true;
	}

	/** 
	* Description: 从本地上传文件到共享目录 
	* @Version1.0 Sep 25, 2009 3:49:00 PM 
	* @param remoteFilePath 共享文件路径
	* 如果是无需密码的共享，则类似如下格式：smb://ip/sharefolder(例如：smb://192.168.0.77/test)
	* 如果需要用户名、密码，则类似如下格式：Smb://username:password@ip/sharefolder(例如：smb://yka:Zy9048#zh@Windows-lpfe23b/软件/exchange)
	* smb:域名;用户名:密码@目的IP/文件夹/文件名.xxx：test.smbPut("smb://szpcg;jiang.t:xxx@192.168.193.13/Jake", "c://test.txt") ;
	* @param localFilePath 本地文件绝对路径 
	*/ 
	public static void smbPut(String remoteFilePath, String localFilePath) {
		if(log.isInfoEnabled()) log.info(remoteFilePath);
		if(log.isInfoEnabled()) log.info(localFilePath);
		InputStream in = null;
		OutputStream out = null;
		try {
			File localFile = new File(localFilePath);
			SmbFile remoteFile = new SmbFile(remoteFilePath);
			in = new BufferedInputStream(new FileInputStream(localFile));
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
			byte[] buffer = new byte[1024];
			while(in.read(buffer)!=-1){
				out.write(buffer);
				buffer = new byte[1024];
			}
		} catch (Exception e) {
			if(log.isErrorEnabled()) log.error(e.getMessage(), e);
		} finally {
			try {
				if(in != null) in.close();
			} catch (IOException e) {
				if(log.isErrorEnabled()) log.error(e.getMessage(), e);
			}
			try {
				if(out != null) out.close();
			} catch (IOException e) {
				if(log.isErrorEnabled()) log.error(e.getMessage(), e);
			}
		}
	}
}