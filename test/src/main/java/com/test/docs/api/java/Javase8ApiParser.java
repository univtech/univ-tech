package com.test.docs.api.java;

import com.test.docs.api.ApiParser;

public class Javase8ApiParser extends ApiParser {

	public static void main(String[] args) {
		new Javase8ApiParser().writeAll();
	}

	@Override
	protected String getApiName() {
		return "JavaSE 8 API";
	}

	@Override
	protected String getApiUrl() {
		return "https://docs.oracle.com/javase/8/docs/api";
	}

	@Override
	protected String getApiPath() {
		return "D:/Workspace/univtech/univ-tech/java/javase8/api";
	}

}
