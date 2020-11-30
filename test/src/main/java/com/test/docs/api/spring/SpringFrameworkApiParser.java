package com.test.docs.api.spring;

import com.test.docs.api.ApiParser;

public class SpringFrameworkApiParser extends ApiParser {

	public static void main(String[] args) {
		new SpringFrameworkApiParser().writeAll();
	}

	@Override
	protected String getApiName() {
		return "Spring Framework 5.3.1 API";
	}

	@Override
	protected String getApiUrl() {
		return "https://docs.spring.io/spring-framework/docs/current/javadoc-api";
	}

	@Override
	protected String getApiPath() {
		return "D:/Workspace/univtech/univ-tech/spring/spring-framework-5.3.1/api";
	}

}
