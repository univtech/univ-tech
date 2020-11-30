package com.test.docs;

import com.test.docs.HrefHandler.ReadType;
import com.test.docs.api.utils.FileLineReader;

public class DocsHandler {

	private static final String DIR_PATH = "D:/Development/EclipseJee_2020.03/workspace/test/src/main/resources/docs/";

	private static final String FILE_PATH = DIR_PATH + "docs.txt";

	public static void main(String[] args) {
		for (String line : FileLineReader.readLines(FILE_PATH)) {
			int index = line.indexOf("/");
			line = line.substring(index + 1);
			line = line.replaceAll("\\/", ".");
			System.out.println(line);
		}
//		handlePython();
//		handleGolang();
//		ClassName.handleClassNames(FILE_PATH);
	}

	private static void handlePython() {
//		String url = "https://www.python.org/";
		String url = "https://docs.python.org/3/";
		HrefHandler.handleHrefs(url, FILE_PATH, ReadType.FILE);
	}
	
	private static void handleGolang() {
//		String url = "https://golang.google.cn/";
//		String url = "https://golang.google.cn/pkg/";
//		String url = "https://golang.google.cn/doc/";
		String url = "https://golang.google.cn/doc/effective_go.html";
		HrefHandler.handleHrefs(url, FILE_PATH, ReadType.FILE);
	}

}
