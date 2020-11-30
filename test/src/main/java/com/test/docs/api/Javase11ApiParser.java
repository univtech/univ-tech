package com.test.docs.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.docs.api.utils.FileLineReader;

public class Javase11ApiParser extends ApiParser  {

	private static final String version = "11";

	public static void main(String[] args) {
		System.out.println("开始");
		parseApis();
		readApis();
		System.out.println("结束");
	}

	private static void parseApis() {
		Map<String, List<String>> moduleMap = new LinkedHashMap<>();
		List<String> moduleNames = parseModules();
		for (String moduleName : moduleNames) {
			List<String> packageNames = parsePackages(moduleName);
			moduleMap.put(moduleName, packageNames);

			for (String packageName : packageNames) {
				Map<String, List<String>> typeMap = parseTypes(moduleName, packageName);
				writeTypes(moduleName, packageName, typeMap);
			}
		}
		writeModules(moduleMap);
	}

	private static List<String> parseModules() {
		String pattern = "https://docs.oracle.com/en/java/javase/{0}/docs/api/index.html";
		String url = MessageFormat.format(pattern, version);

		try {
			Document htmlDocument = Jsoup.connect(url).get();
			Elements tableElements = htmlDocument.getElementsByTag("table");
			CollectionUtils.filter(tableElements, tableElement -> tableElement.hasClass("overviewSummary"));

			Element tableElement = tableElements.get(0);
			Elements tbodyElements = tableElement.getElementsByTag("tbody");

			Element tbodyElement = tbodyElements.get(1);
			Elements thElements = tbodyElement.getElementsByTag("th");

			List<String> modules = new ArrayList<>();
			for (Element thElement : thElements) {
				modules.add(thElement.text());
			}
			return modules;
		} catch (Exception e) {
			System.out.println(MessageFormat.format("{0}解析异常：{1}", url, e.getMessage()));
			return Collections.emptyList();
		}
	}

	private static List<String> parsePackages(String moduleName) {
		String pattern = "https://docs.oracle.com/en/java/javase/{0}/docs/api/{1}/module-summary.html";
		String url = MessageFormat.format(pattern, version, moduleName);

		try {
			Document htmlDocument = Jsoup.connect(url).get();
			Elements tableElements = htmlDocument.getElementsByTag("table");
			CollectionUtils.filter(tableElements, tableElement -> tableElement.hasClass("packagesSummary"));
			if (CollectionUtils.isEmpty(tableElements)) {
				return Collections.emptyList();
			}

			Element tableElement = tableElements.get(0);
			Elements tbodyElements = tableElement.getElementsByTag("tbody");
			String summaryName = tbodyElements.get(0).getElementsByTag("th").get(0).text();
			if (!"Package".equals(summaryName)) {
				return Collections.emptyList();
			}

			List<String> packageNames = new ArrayList<>();
			Elements thElements = tbodyElements.get(1).getElementsByTag("th");
			for (Element thElement : thElements) {
				packageNames.add(thElement.text());
			}
			return packageNames;
		} catch (Exception e) {
			System.out.println(MessageFormat.format("{0}解析异常：{1}", url, e.getMessage()));
			return Collections.emptyList();
		}
	}

	private static Map<String, List<String>> parseTypes(String moduleName, String packageName) {
		String pattern = "https://docs.oracle.com/en/java/javase/{0}/docs/api/{1}/{2}/package-summary.html";
		String url = MessageFormat.format(pattern, version, moduleName, packageName.replaceAll("\\.", "\\/"));

		try {
			Document htmlDocument = Jsoup.connect(url).get();
			Elements tableElements = htmlDocument.getElementsByTag("table");
			CollectionUtils.filter(tableElements, tableElement -> tableElement.hasClass("typeSummary"));

			Map<String, String> summaryMap = buildSummaryMap();
			Map<String, List<String>> typeMap = buildTypeMap();

			for (Element tableElement : tableElements) {
				Elements tbodyElements = tableElement.getElementsByTag("tbody");

				String summaryName = tbodyElements.get(0).getElementsByTag("th").get(0).text();
				List<String> typeNames = typeMap.get(summaryMap.get(summaryName));

				Elements thElements = tbodyElements.get(1).getElementsByTag("th");
				for (Element thElement : thElements) {
					typeNames.add(thElement.text());
				}
			}
			return typeMap;
		} catch (Exception e) {
			System.out.println(MessageFormat.format("{0}解析异常：{1}", url, e.getMessage()));
			return Collections.emptyMap();
		}
	}

	private static void writeModules(Map<String, List<String>> moduleMap) {
		String pattern = "D:/Workspace/univtech/univ-tech/java/javase{0}/api/README.md";
		String path = MessageFormat.format(pattern, version);

		File folder = new File(path).getParentFile();
		if (!folder.exists()) {
			folder.mkdirs();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			writer.write(MessageFormat.format("# JavaSE {0} API\r\n", version)); // 一级标题
			for (Entry<String, List<String>> moduleEntry : moduleMap.entrySet()) {
				writer.write(MessageFormat.format("\r\n## {0}\r\n\r\n", moduleEntry.getKey())); // 二级标题
				for (String packageName : moduleEntry.getValue()) {
					writer.write(MessageFormat.format("{0}\r\n", packageName)); // 包名
				}
			}
			writer.write("\r\n\r\n\r\n\r\n");
		} catch (Exception e) {
			System.out.println(MessageFormat.format("{0}输出异常：{1}", path, e.getMessage()));
		}
	}

	private static void writeTypes(String moduleName, String packageName, Map<String, List<String>> typeMap) {
		String pattern = "D:/Workspace/univtech/univ-tech/java/javase{0}/api/{1}/{2}.md";
		String path = MessageFormat.format(pattern, version, moduleName, packageName);
		File folder = new File(path).getParentFile();
		if (!folder.exists()) {
			folder.mkdirs();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			writer.write(MessageFormat.format("# {0}\r\n", packageName)); // 一级标题
			for (Entry<String, List<String>> typeEntry : typeMap.entrySet()) {
				if (CollectionUtils.isNotEmpty(typeEntry.getValue())) {
					writer.write(MessageFormat.format("\r\n## {0}\r\n\r\n", typeEntry.getKey())); // 二级标题
					for (String typeName : typeEntry.getValue()) {
						writer.write(MessageFormat.format("{0}.{1}\r\n", packageName, typeName)); // 类型名称
					}
				}
			}
			writer.write("\r\n\r\n\r\n\r\n");
		} catch (Exception e) {
			System.out.println(MessageFormat.format("{0}输出异常：{1}", path, e.getMessage()));
		}
	}

	private static void readApis() {
		String pattern = "D:/Workspace/univtech/univ-tech/java/javase{0}/api";
		String path = MessageFormat.format(pattern, version);

		File folder = new File(path);
		File[] subFolders = folder.listFiles((file) -> {
			return file.isDirectory();
		});

		for (File subFolder : subFolders) {
			File[] files = subFolder.listFiles();

			for (File file : files) {
				String packageName = file.getName().replace(".md", "");
				List<String> lines = FileLineReader.readLines(file.getPath());

				for (String line : lines) {
					if (line.startsWith(packageName) && !line.equals(packageName)) {
						System.out.println(line);
					}
				}
			}
		}
	}

}
