package com.test.docs.api.apache.commons;

import com.test.docs.api.ApiParser;

public class CommonsIoApiParser extends ApiParser {

	public static void main(String[] args) {
		new CommonsIoApiParser().writeAll();
	}

	@Override
	protected String getApiName() {
		return "Apache Commons IO 2.8.0 API";
	}

	@Override
	protected String getApiUrl() {
		return "http://commons.apache.org/proper/commons-io/apidocs";
	}

	@Override
	protected String getApiPath() {
		return "D:/Workspace/univtech/univ-tech/apache/commons/commons-io-2.8.0/api";
	}

}
