package com.amphenol.agis.pojo;

public class EpromFile 
{
	private String rootPath;
	private String workStationName;
	private String filename;
	private String logFileType;
	
	/**
	 * 
	 */
	public EpromFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rootPath
	 * @param workStationName
	 * @param filename
	 * @param logFileType
	 */
	public EpromFile(String rootPath, String workStationName, String filename,
			String logFileType) {
		super();
		this.rootPath = rootPath;
		this.workStationName = workStationName;
		this.filename = filename;
		this.logFileType = logFileType;
	}

	/**
	 * @return the rootPath
	 */
	public String getRootPath() {
		return rootPath;
	}

	/**
	 * @param rootPath the rootPath to set
	 */
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	/**
	 * @return the workStationName
	 */
	public String getWorkStationName() {
		return workStationName;
	}

	/**
	 * @param workStationName the workStationName to set
	 */
	public void setWorkStationName(String workStationName) {
		this.workStationName = workStationName;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the logFileType
	 */
	public String getLogFileType() {
		return logFileType;
	}

	/**
	 * @param logFileType the logFileType to set
	 */
	public void setLogFileType(String logFileType) {
		this.logFileType = logFileType;
	}
	
}
