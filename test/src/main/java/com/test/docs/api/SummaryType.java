package com.test.docs.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum SummaryType {

	Annotation(0, "Annotation Type", "注解"),

	Enum(1, "Enum", "枚举"),

	Interface(2, "Interface", "接口"),

	Class(3, "Class", "类"),

	Exception(4, "Exception", "异常"),

	Error(5, "Error", "错误");

	private int order;

	private String enName;

	private String zhName;

	private SummaryType(int order, String enName, String zhName) {
		this.order = order;
		this.enName = enName;
		this.zhName = zhName;
	}

	public int getOrder() {
		return order;
	}

	public String getEnName() {
		return enName;
	}

	public String getZhName() {
		return zhName;
	}

	public static int getOrder(String enName) {
		SummaryType summaryType = getSummaryType(enName);
		return summaryType == null ? values().length : summaryType.getOrder();
	}

	public static String getZhName(String enName) {
		SummaryType summaryType = getSummaryType(enName);
		return summaryType == null ? enName : summaryType.getZhName();
	}

	public static SummaryType getSummaryType(String enName) {
		for (SummaryType summaryType : values()) {
			if (summaryType.getEnName().equals(enName)) {
				return summaryType;
			}
		}
		return null;
	}

	public static Map<String, List<String>> getSummaryMap() {
		Map<String, List<String>> summaryMap = new LinkedHashMap<>();
		for (SummaryType summaryType : values()) {
			summaryMap.put(summaryType.getZhName(), new ArrayList<>());
		}
		return summaryMap;
	}

}
