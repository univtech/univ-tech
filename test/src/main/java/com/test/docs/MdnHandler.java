package com.test.docs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * 文件来源：https://github.com/mdn/content.git
 * 
 * \ 替换为 /
 * 
 * D:/Workspace/univtech/mdn-content/files/en-us 替换为 https://developer.mozilla.org/en-US/docs
 * </pre>
 */
public class MdnHandler {

	public static void main(String[] args) {
		String path = "D:/Workspace/univtech/mdn-content/files/en-us";

		List<File> files = getFiles(new File(path));
		for (File file : files) {
			System.out.println(file.getParent());
		}

//		List<File> files = getFolders(new File(path));
//		for (File file : files) {
//			System.out.println(file.getPath());
//		}
	}

	private static List<File> getFiles(File dir) {
		List<File> allFiles = new ArrayList<>();

		File[] files = dir.listFiles((file) -> {
			return file.isFile() && file.getName().equals("index.html");
		});
		if (files != null && files.length > 0) {
			allFiles.addAll(Arrays.asList(files));
		}

		File[] folders = dir.listFiles((file) -> {
			return file.isDirectory();
		});
		if (folders != null && folders.length > 0) {
			for (File folder : folders) {
				allFiles.addAll(getFiles(folder));
			}
		}

		return allFiles;
	}

	private static List<File> getFolders(File dir) {
		List<File> allDirs = new ArrayList<>();

		File[] folders = dir.listFiles((file) -> {
			return file.isDirectory();
		});
		if (folders != null && folders.length > 0) {
			for (File folder : folders) {
				allDirs.add(folder);
				allDirs.addAll(getFolders(folder));
			}
		}

		return allDirs;
	}

}
