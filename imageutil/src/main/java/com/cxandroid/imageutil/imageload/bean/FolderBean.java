package com.cxandroid.imageutil.imageload.bean;

public class FolderBean {
	//当前文件夹的路径
	private String dir;
	private String firstImagPath;
	private String name;
	int count;
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
		
		int lastIndexOf=this.dir.lastIndexOf("/");
		this.name=this.dir.substring(lastIndexOf);
	}
	public String getFirstImagPath() {
		return firstImagPath;
	}
	public void setFirstImagPath(String firstImagPath) {
		this.firstImagPath = firstImagPath;
	}
	public String getName() {
		return name;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
