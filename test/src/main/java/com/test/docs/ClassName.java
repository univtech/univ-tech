package com.test.docs;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ClassName {

	public static void handleClassNames(String filePath) {
		try {
			List<String> lines = FileUtils.readLines(new File(filePath), StandardCharsets.UTF_8);
			List<String> classNames = getClassNames(lines);
			printClassNames(classNames);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<String> getClassNames(List<String> lines) {
		List<String> classNames = new ArrayList<>();
		for (String line : lines) {
			classNames.add(line.substring(line.lastIndexOf(".") + 1));
		}
		return classNames;
	}

	private static void printClassNames(List<String> classNames) {
		for (String className : classNames) {
			System.out.println(className);
		}
	}

}
