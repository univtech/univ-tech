package com.test.docs.api.java;

import com.test.docs.api.ApiParser;

public class Javase11ApiParser extends ApiParser {

	public static void main(String[] args) {
		new Javase11ApiParser().writeAll();
	}

	@Override
	protected String getApiName() {
		return "JavaSE 8 API";
	}

//	{0}/index.html
//	{0}/java.base/module-summary.html
//	{0}/java.base/java/io/package-summary.html
//	{0}/allclasses.html

	@Override
	protected String getApiUrl() {
		return "https://docs.oracle.com/en/java/javase/11/docs/api";
	}

	@Override
	protected String getApiPath() {
		return "D:/Workspace/univtech/univ-tech/java/javase8/api";
	}

}
