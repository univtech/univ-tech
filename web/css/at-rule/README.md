# @规则

At-rules are CSS statements that instructs CSS how to behave.

They begin with an at sign, '@' (U+0040 COMMERCIAL AT), followed by an identifier and includes everything up to the next semicolon, ';' (U+003B SEMICOLON), or the next CSS block, whichever comes first.

```css
/* General structure */
@IDENTIFIER (RULE);

/* Example: tells browser to use UTF-8 character set */
@charset "utf-8";
```

There are several at-rules, designated by their identifiers, each with a different syntax:

```
@charset           Defines the character set used by the style sheet.
@import            Tells the CSS engine to include an external style sheet.
@namespace         Tells the CSS engine that all its content must be considered prefixed with an XML namespace.
Nested at-rules    A subset of nested statements, which can be used as a statement of a style sheet as well as inside of conditional group rules:

                   @media             A conditional group rule that will apply its content if the device meets the criteria of the condition defined using a media query.
                   @supports          A conditional group rule that will apply its content if the browser meets the criteria of the given condition.
                   @document          A conditional group rule that will apply its content if the document in which the style sheet is applied meets the criteria of the given condition. (deferred to Level 4 of CSS Spec)
                   @page              Describes the aspect of layout changes that will be applied when printing the document.
                   @font-face         Describes the aspect of an external font to be downloaded.
                   @keyframes         Describes the aspect of intermediate steps in a CSS animation sequence.
                   @viewport          Describes the aspects of the viewport for small screen devices. (currently at the Working Draft stage)
                   @counter-style     Defines specific counter styles that are not part of the predefined set of styles. (at the Candidate Recommendation stage, but only implemented in Gecko as of writing)
                   @font-feature-values (plus @swash, @ornaments, @annotation, @stylistic, @styleset and @character-variant)
                                      Define common names in font-variant-alternates for feature activated differently in OpenType. (at the Candidate Recommendation stage, but only implemented in Gecko as of writing)
```






https://developer.mozilla.org/en-US/docs/Web/CSS/@charset
https://developer.mozilla.org/en-US/docs/Web/CSS/@counter-style
https://developer.mozilla.org/en-US/docs/Web/CSS/@document
https://developer.mozilla.org/en-US/docs/Web/CSS/@font-face
https://developer.mozilla.org/en-US/docs/Web/CSS/@font-feature-values
https://developer.mozilla.org/en-US/docs/Web/CSS/@import
https://developer.mozilla.org/en-US/docs/Web/CSS/@keyframes
https://developer.mozilla.org/en-US/docs/Web/CSS/@media
https://developer.mozilla.org/en-US/docs/Web/CSS/@namespace
https://developer.mozilla.org/en-US/docs/Web/CSS/@page
https://developer.mozilla.org/en-US/docs/Web/CSS/@supports
https://developer.mozilla.org/en-US/docs/Web/CSS/@viewport




























