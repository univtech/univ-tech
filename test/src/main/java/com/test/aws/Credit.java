package com.test.aws;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Credit {

	private static final String FILE_PATH = "D:/Development/EclipseJee_2020.03/workspace/test/src/main/resources/HttpHandler.txt";

	public static void main(String[] args) {
		Set<String> lines = readLines(FILE_PATH);
		printLines(lines);
	}

	private static Set<String> readLines(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = null;
			Set<String> lines = new LinkedHashSet<>();

			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			return lines;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptySet();
		}
	}

	private static void printLines(Set<String> lines) {
		for (String line : lines) {
			String lineLower = line.toLowerCase();
			if (lineLower.contains("poc") || lineLower.contains("credit")) {
				System.out.println(line);
			}
		}
	}

}
