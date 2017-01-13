package com.demo.main;

import java.io.File;

public class FileCounter {

	public static void main(String args[]) {

		long num = 0;
		num = getFileNum("f:/f");
		System.out.println(num);

	}

	static long getFileNum(String path) {
		long num = 0;
		File file = new File(path);
		if (file.isFile()) {
			return 1;
		} else if(file.isDirectory()) {
			File fileList[] = file.listFiles();
			if(fileList==null){
				return 0;
			}else{
			for (int i = 0; i < fileList.length; i++) {
				File f=fileList[i];
				num+=getFileNum(path+"/" + f.getName());
			}
			}

		}
		return num;
	}

}
