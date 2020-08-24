package kr.or.ddit.rmi.vo;

import java.io.Serializable;

public class FileInfoVO implements Serializable{

	private static final long serialVersionUID = -8679442100976285791L;
	
	// 필요한 매개변수를 생성 
	private String fileName; // 파일 이름 
	private byte[] fileData; // 파일 내용
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	

}
