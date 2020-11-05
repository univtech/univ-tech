# @charset

https://developer.mozilla.org/en-US/docs/Web/CSS/@charset

The @charset CSS at-rule specifies the character encoding used in the style sheet.

It must be the first element in the style sheet and not be preceded by any character; as it is not a nested statement, it cannot be used inside conditional group at-rules.

If several @charset at-rules are defined, only the first one is used, and it cannot be used inside a style attribute on an HTML element or inside the <style> element where the character set of the HTML page is relevant.

```css
@charset "utf-8";
```

This at-rule is useful when using non-ASCII characters in some CSS properties, like content.

As there are several ways to define the character encoding of a style sheet, the browser will try the following methods in the following order (and stop as soon as one yields a result) :

1. The value of the Unicode byte-order character placed at the beginning of the file.

2. The value given by the charset attribute of the Content-Type: HTTP header or the equivalent in the protocol used to serve the style sheet.

3. The @charset CSS at-rule.

4. Use the character encoding defined by the referring document: the charset attribute of the <link> element. This method is obsoleted in HTML5 and must not be used.

5. Assume that the document is UTF-8

## 语法

```css
@charset "UTF-8";
@charset "iso-8859-15";
```

where:

+ charset

  Is a <string> denoting the character encoding to be used.

  It must be the name of a web-safe character encoding defined in the IANA-registry, and must be double-quoted, following exactly one space character (U+0020), and immediately terminated with a semicolon.

  If several names are associated with an encoding, only the one marked with preferred must be used.

## 形式语法

```css
@charset "<charset>";
```

## 示例

Valid and invalid charset declarations

```css
@charset "UTF-8";       /* Set the encoding of the style sheet to Unicode UTF-8 */
@charset 'iso-8859-15'; /* Invalid, wrong quoting style used */
@charset  "UTF-8";      /* Invalid, more than one space */
 @charset "UTF-8";      /* Invalid, there is a character (a space) before the at-rule */
@charset UTF-8;         /* Invalid, without ' or ", the charset is not a CSS <string> */
```

## 规范

 规范                                                                                                                        | 状态           | 描述
:----------------------------------------------------------------------------------------------------------------------------|:---------------|:-------------------
 [CSS Level 2 (Revision 1) The definition of '@charset' in that specification.](https://www.w3.org/TR/CSS2/syndata.html#x57) | Recommendation | Initial definition

## 浏览器兼容性

## 参阅














