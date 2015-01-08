package com.amphenol.agis.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件操作工具类。
 * 
 * @author rocky
 * 
 */
public class FileKit {
	private static List<File> files;

	/**
	 * 获取指定路径内的所有文件。
	 * 
	 * @param path
	 *            指定文件路径。
	 * @return files 返回的文件list。
	 * @throws FileNotFoundException
	 */
	public static List<File> getFiles(String path) throws FileNotFoundException {
		files = new ArrayList<File>();
		File file = new File(path);
		return getFiles(file);
	}

	/**
	 * 获取指定路径内的所有文件。
	 * 
	 * @param file
	 * @return files
	 * @throws FileNotFoundException
	 */
	public static List<File> getFiles(File file) throws FileNotFoundException {
		files = new ArrayList<File>();
		if (!file.exists()) {
			throw new FileNotFoundException(file.getAbsolutePath()
					+ " 文件或者文件夹不存在!");
		}
		scanFile(file);
		return files;
	}

	/**
	 * 获取指定路径内所有的文件名，带后缀的文件名。
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static List<String> getFileNames(String path) throws FileNotFoundException{
		getFiles(path);
		ArrayList<String> fileNames = new ArrayList<String>();
		for(File file : files){
			fileNames.add(file.getName());
		}
		return fileNames;
	}
	
	public static String getFileNamesByString(String path,String regex) throws FileNotFoundException{
		List<String> fileNames = getFileNames(path);
		regex = regex.replace("+", "\\+");
		Pattern pattern = Pattern.compile(regex);
		for (String string : fileNames) {
			Matcher matcher = pattern.matcher(string);
			if(matcher.find()){
				return string;
			}
		}
		return "";
		
	}
	/**
	 * 获取指定路径内，指定的文件类型的所有文件
	 * 
	 * @param path
	 * @param fileType
	 * @return fs
	 * @throws FileNotFoundException
	 */
	public static List<File> getSpecifiedTypeFiles(String path, String fileType)
			throws FileNotFoundException {
		List<File> fs = new ArrayList<File>();
		for (File file : getFiles(path)) {
			if (getFileTypeString(file).equalsIgnoreCase(fileType)) {
				fs.add(file);
			}
		}
		return fs;
	}
	/**
	 * 去除文件名的后缀
	 * @param file
	 * @return
	 */
	public static String getFileName(File file){
		if(file.getName().lastIndexOf(".")<=0){
			return file.getName();
		}
		return file.getName().substring(0, file.getName().lastIndexOf("."));
	}
	/**
	 * 文件重命名。
	 * @param oldFile
	 * @param newName
	 */
	public static File renameFile(File oldFile,String newName){
		//检查新的文件名是否带后缀名
		File newFile;
		if(newName.lastIndexOf(".")>0){
			newFile=new File(oldFile.getParent()+File.separator+newName);
			oldFile.renameTo(newFile);
		}else{
			newFile=new File(oldFile.getParent()+File.separator+newName+getFileTypeString(oldFile));
			oldFile.renameTo(newFile);
		}
		return newFile;
	}
	
	/**
	 * 获取文件的后缀名。
	 * @param file
	 * @return
	 */
	public static String getFileTypeString(File file){
		return file.getName().substring(file.getName().lastIndexOf("."));
	}
	
	/**
	 * 文件通道复制文件。
	 * @param sourFile 源文件
	 * @param targetDir 复制目的路径
	 */
	public static void fileChannelCopy(File sourFile,String targetDir){
		fileChannelCopy(sourFile, new File(targetDir+sourFile.getName()));
	}
	
	/**
	 * 文件通道复制文件。
	 * @param s 源文件
	 * @param t 目的新文件
	 */
	public static void fileChannelCopy(File s, File t) {

		FileInputStream fi = null;

		FileOutputStream fo = null;

		FileChannel in = null;

		FileChannel out = null;

		try {

			fi = new FileInputStream(s);

			fo = new FileOutputStream(t);

			in = fi.getChannel();// 得到对应的文件通道

			out = fo.getChannel();// 得到对应的文件通道

			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				fi.close();

				in.close();

				fo.close();

				out.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	/**
	 * 复制文件
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	// 复制文件夹
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * 
	 * @param srcFileName
	 * @param destFileName
	 * @param srcCoding
	 * @param destCoding
	 * @throws IOException
	 */
	public static void copyFile(File srcFileName, File destFileName,
			String srcCoding, String destCoding) throws IOException {// 把文件转换为GBK文件
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					srcFileName), srcCoding));
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(destFileName), destCoding));
			char[] cbuf = new char[1024 * 5];
			int len = cbuf.length;
			int off = 0;
			int ret = 0;
			while ((ret = br.read(cbuf, off, len)) > 0) {
				off += ret;
				len -= ret;
			}
			bw.write(cbuf, 0, off);
			bw.flush();
		} finally {
			if (br != null)
				br.close();
			if (bw != null)
				bw.close();
		}
	}

	/**
	 * 
	 * @param path
	 */
	public static void deleteAll(String path) {
		File file = new File(path);
		deleteAll(file);
	}

	/**
	 * 删除该文件夹下的所有文件。
	 * 
	 * @param file
	 */
	public static void deleteAll(File file) {
		if (file != null && file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteAll(files[i]);
				}
			}
			file.delete();
		}
	}

	/**
	 * 递归扫描文件夹遍历文件。
	 * 
	 * @param file
	 */
	private static void scanFile(File file) {
		if (files == null) {
			return;
		}
		// 判断是文件还是文件夹，如果是文件就存到list里
		if (file.isFile()) {
			files.add(file);
		} else {
			File[] fs = file.listFiles();
			for (File f : fs) {
				scanFile(f);
			}
		}
	}

	/**
	 * 根据文件名创建文件。
	 * 
	 * @param filepath
	 * @return 创建是否成功
	 * @throws Exception
	 */
	public static boolean createFile(String filepath) throws Exception {
		File file = new File(filepath);
		return createFile(file);
	}

	/**
	 * 创建文件
	 * 
	 * @param file
	 * @return
	 */
	public static boolean createFile(File file) throws Exception {
		boolean flag = false;
		try {
			if (!file.exists()) {
				file.createNewFile();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 读TXT文件内容
	 * 
	 * @param file
	 * @return
	 */
	public static String readTxtFile(File file) throws Exception {
		String result = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			try {
				String read = null;
				while ((read = bufferedReader.readLine()) != null) {
					result = result + read + "\r\n";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (fileReader != null) {
				fileReader.close();
			}
		}
		System.out.println("读取出来的文件内容是：" + "\r\n" + result);
		return result;
	}

	/**
	 * 将content内容，写入到file中。
	 * 
	 * @param content
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static boolean writeTxtFile(String content, File file)
			throws Exception {

		boolean flag = false;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(file);
			o.write(content.getBytes("UTF-8"));
			o.close();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (o != null) {
				o.close();
			}
		}
		return flag;
	}

	/**
	 * 将content追加到 filepath中。
	 * 
	 * @param filePath
	 * @param content
	 */

	public static void contentToTxt(String filePath, String content) {
		String str = new String(); // 原有txt内容
		String s1 = new String();// 内容更新
		try {
			File f = new File(filePath);
			if (f.exists()) {
				System.out.print("文件存在");
			} else {
				System.out.print("文件不存在");
				f.createNewFile();// 不存在则创建
			}
			BufferedReader input = new BufferedReader(new FileReader(f));

			while ((str = input.readLine()) != null) {
				s1 += str + "\n";
			}
			System.out.println(s1);
			input.close();
			s1 += content;

			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s1);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
