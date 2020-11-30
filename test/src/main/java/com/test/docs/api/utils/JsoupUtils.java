package com.test.docs.api.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtils {

	public static Elements getElementsByTagAndClass(Element element, String tagName, String className) {
		Elements subElements = element.getElementsByTag(tagName);
		CollectionUtils.filter(subElements, subElement -> subElement.hasClass(className));
		return subElements;
	}

}
