package com.test.docs.api;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.docs.api.utils.JsoupUtils;

public abstract class ApiParser {

	protected abstract String getApiName();

	protected abstract String getApiUrl();

	protected abstract String getApiPath();

	protected String getAllClassesUrl() {
		return MessageFormat.format("{0}/allclasses-noframe.html", getApiUrl());
	}

	protected String getAllClassesPath() {
		return MessageFormat.format("{0}/所有类.md", getApiPath());
	}

	protected String getAllPackagesUrl() {
		return MessageFormat.format("{0}/overview-summary.html", getApiUrl());
	}

	protected String getAllPackagesPath() {
		return MessageFormat.format("{0}/所有包.md", getApiPath());
	}

	protected String getPackageClassesUrl(String packageName) {
		return MessageFormat.format("{0}/{1}/package-summary.html", getApiUrl(), packageName.replaceAll("\\.", "\\/"));
	}

	protected String getPackageClassesPath(String packageName) {
		return MessageFormat.format("{0}/{1}.md", getApiPath(), packageName);
	}

	protected void writeAll() {
		writeAllClasses();
		writeAllPackages();
		printAllClasses();
	}

	private void writeAllClasses() {
		List<String> lines = new ArrayList<>();
		lines.add(MessageFormat.format("# {0}\r\n", getApiName()));
		lines.addAll(parseAllClasses());
		lines.add("\r\n\r\n\r\n");
		writeLines(lines, getAllClassesPath());
	}

	private List<String> parseAllClasses() {
		try {
			Document htmlDocument = Jsoup.connect(getAllClassesUrl()).get();
			Elements divElements = JsoupUtils.getElementsByTagAndClass(htmlDocument, "div", "indexContainer");
			if (CollectionUtils.isEmpty(divElements)) {
				return Collections.emptyList();
			}

			Element divElement = divElements.get(0);
			Elements aElements = divElement.getElementsByTag("a");

			String in = "in ";
			List<String> fullClassNames = new ArrayList<>();
			for (Element aElement : aElements) {
				String title = aElement.attr("title");
				String packageName = title.substring(title.indexOf(in) + in.length());
				String className = aElement.text();
				String fullClassName = MessageFormat.format("{0}.{1}", packageName, className);
				fullClassNames.add(fullClassName);
			}
			Collections.sort(fullClassNames);
			return fullClassNames;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	private void writeAllPackages() {
		List<String> packageNames = parseAllPackages();
		for (String packageName : packageNames) {
			writePackageClasses(packageName);
		}

		List<String> lines = new ArrayList<>();
		lines.add(MessageFormat.format("# {0}\r\n", getApiName()));
		lines.addAll(packageNames);
		lines.add("\r\n\r\n\r\n");
		writeLines(lines, getAllPackagesPath());
	}

	private List<String> parseAllPackages() {
		try {
			Document htmlDocument = Jsoup.connect(getAllPackagesUrl()).get();
			Elements tableElements = JsoupUtils.getElementsByTagAndClass(htmlDocument, "table", "overviewSummary");
			if (CollectionUtils.isEmpty(tableElements)) {
				return Collections.emptyList();
			}

			Element tableElement = tableElements.get(0);
			Elements colFirstElements = tableElement.getElementsByClass("colFirst");
			if (CollectionUtils.isEmpty(colFirstElements) || colFirstElements.size() < 2) {
				return Collections.emptyList();
			}

			List<String> packageNames = new ArrayList<>();
			for (int index = 1; index < colFirstElements.size(); index++) {
				String packageName = colFirstElements.get(index).text();
				packageNames.add(packageName);
			}
			Collections.sort(packageNames);
			return packageNames;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	private void writePackageClasses(String packageName) {
		List<String> lines = new ArrayList<>();
		lines.add(MessageFormat.format("# {0}", packageName));

		Map<String, List<String>> fullClassNameMap = parsePackageClasses(packageName);
		for (Entry<String, List<String>> fullClassNameEntry : fullClassNameMap.entrySet()) {
			if (CollectionUtils.isNotEmpty(fullClassNameEntry.getValue())) {
				lines.add(MessageFormat.format("\r\n## {0}\r\n", fullClassNameEntry.getKey()));
				lines.addAll(fullClassNameEntry.getValue());
			}
		}

		lines.add("\r\n\r\n\r\n");
		writeLines(lines, getPackageClassesPath(packageName));
	}

	private Map<String, List<String>> parsePackageClasses(String packageName) {
		try {
			Document htmlDocument = Jsoup.connect(getPackageClassesUrl(packageName)).get();
			Elements tableElements = JsoupUtils.getElementsByTagAndClass(htmlDocument, "table", "typeSummary");
			if (CollectionUtils.isEmpty(tableElements)) {
				return Collections.emptyMap();
			}

			Map<String, List<String>> fullClassNameMap = SummaryType.getSummaryMap();
			for (Element tableElement : tableElements) {
				Elements colFirstElements = tableElement.getElementsByClass("colFirst");
				if (CollectionUtils.isEmpty(colFirstElements) || colFirstElements.size() < 2) {
					continue;
				}

				String summaryNameEn = colFirstElements.get(0).text();
				String summaryNameZh = SummaryType.getZhName(summaryNameEn);
				List<String> fullClassNames = fullClassNameMap.get(summaryNameZh);

				for (int index = 1; index < colFirstElements.size(); index++) {
					String className = colFirstElements.get(index).text();
					String fullClassName = MessageFormat.format("{0}.{1}", packageName, className);
					fullClassNames.add(fullClassName);
				}
				Collections.sort(fullClassNames);
			}
			return fullClassNameMap;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyMap();
		}
	}

	private void printAllClasses() {
		try {
			List<String> ignoreFiles = Arrays.asList("README.md", "所有类.md", "所有包.md");
			File[] files = new File(getApiPath()).listFiles((file) -> {
				return !ignoreFiles.contains(file.getName());
			});

			List<String> fullClassNames = new ArrayList<>();
			for (File file : files) {
				String fileName = file.getName();
				String packageName = fileName.substring(0, fileName.lastIndexOf(".md"));
				List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);

				for (String line : lines) {
					if (line.startsWith(packageName)) {
						int index = line.indexOf("<");
						if (index > 0) {
							line = line.substring(0, index);
						}
						fullClassNames.add(line);
					}
				}
			}

			Collections.sort(fullClassNames);
			printLines(fullClassNames);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printLines(List<String> lines) {
		for (String line : lines) {
			System.out.println(line);
		}
	}

	private void writeLines(List<String> lines, String path) {
		try {
			if (StringUtils.isEmpty(path)) {
				printLines(lines);
			} else {
				FileUtils.writeLines(new File(path), lines);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
