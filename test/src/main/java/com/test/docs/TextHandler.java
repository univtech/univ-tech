package com.test.docs;

import java.text.MessageFormat;
import java.util.List;

import com.test.docs.api.utils.FileLineReader;

public class TextHandler {

	public static void handleTexts(String filePath) {
		List<String> texts = FileLineReader.readLines(filePath);
		int maxLength = getMaxLength(texts);
		formatTexts(texts, maxLength);
	}

	private static int getMaxLength(List<String> texts) {
		int maxLength = 0;
		for (String text : texts) {
			if (text.length() > maxLength) {
				maxLength = text.length();
			}
		}
		return maxLength;
	}

	private static void formatTexts(List<String> texts, int maxLength) {
		String format = MessageFormat.format("%-{0}s", String.valueOf(maxLength));
		for (String text : texts) {
			System.out.println(String.format(format, text));
		}
	}

}
