package com.test.docs;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.docs.api.utils.FileLineReader;

public class HrefHandler {

	public enum ReadType {
		PAGE, HTML, FILE
	}

	public static void handleHrefs(String pageUrl, String filePath, ReadType readType) {
		Set<String> hrefPaths = new LinkedHashSet<>();
		if (readType == ReadType.PAGE) {
			hrefPaths = readHrefsFromPage(pageUrl);
		} else if (readType == ReadType.HTML) {
			hrefPaths = readHrefsFromHtml(filePath);
		} else if (readType == ReadType.FILE) {
			hrefPaths = readHrefsFromFile(filePath);
		}
//		Set<String> httpPaths = processHrefs(pageUrl, hrefPaths);
		printHrefs(hrefPaths);
	}

	private static Set<String> readHrefsFromPage(String pageUrl) {
		try {
			Document document = Jsoup.connect(pageUrl).get();
			return readHrefsFromDocument(document);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptySet();
		}
	}

	private static Set<String> readHrefsFromHtml(String filePath) {
		try {
			Document document = Jsoup.parse(new File(filePath), "UTF-8");
			return readHrefsFromDocument(document);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptySet();
		}
	}

	private static Set<String> readHrefsFromDocument(Document document) {
		Set<String> hrefPaths = new LinkedHashSet<>();
		Elements elements = document.getElementsByTag("a");
		for (Element element : elements) {
			hrefPaths.add(element.attr("href"));
		}
		return hrefPaths;
	}

	private static Set<String> readHrefsFromFile(String filePath) {
		String href = "href=\"";
		Set<String> hrefPaths = new LinkedHashSet<>();
		List<String> lines = FileLineReader.readLines(filePath);

		for (String line : lines) {
			while (line.contains(href)) {
				int beginIndex = line.indexOf(href) + href.length();
				line = line.substring(beginIndex);

				int endIndex = line.indexOf("\"");
				hrefPaths.add(line.substring(0, endIndex));

				line = line.substring(endIndex); // 可能存在多个href
			}
		}
		return hrefPaths;
	}

	private static Set<String> processHrefs(String pageUrl, Set<String> hrefPaths) {
		Set<String> httpPaths = new LinkedHashSet<>();
		for (String hrefPath : hrefPaths) {
			if (hrefPath.startsWith("http")) {
				httpPaths.add(hrefPath);
				continue;
			}

			if (hrefPath.startsWith("//")) {
				httpPaths.add("https:" + hrefPath);
				continue;
			}

			if (hrefPath.startsWith("/")) {
				hrefPath = hrefPath.substring(1);
			}
			if (!hrefPath.startsWith("#")) {
				httpPaths.add(pageUrl + hrefPath);
			}
		}
		return httpPaths;
	}

	private static void printHrefs(Set<String> httpPaths) {
		for (String httpPath : httpPaths) {
			System.out.println(httpPath);
		}
	}

}
