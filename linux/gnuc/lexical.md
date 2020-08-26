# 1 词汇元素

This chapter describes the lexical elements that make up C source code after preprocessing.

These elements are called tokens.

There are five types of tokens: keywords, identifiers, constants, operators, and separators.

White space, sometimes required to separate tokens, is also described in this chapter.

## 1.1 标识符

Identifiers are sequences of characters used for naming variables, functions, new data types, and preprocessor macros.

You can include letters, decimal digits, and the underscore character '_' in identifiers.

The first character of an identifier cannot be a digit.

Lowercase letters and uppercase letters are distinct, such that foo and FOO are two different identifiers.

When using GNU extensions, you can also include the dollar sign character '$' in identifiers.

## 1.2 关键字

Keywords are special identifiers reserved for use as part of the programming language itself.

You cannot use them for any other purpose.

Here is a list of keywords recognized by ANSI C89:

```c
auto      break     case        char      const       continue    default     do
double    else      enum        extern    float       for         goto        if
int       long      register    return    short       signed      sizeof      static
struct    switch    typedef     union     unsigned    void        volatile    while
```

ISO C99 adds the following keywords:

```c
inline    _Bool    _Complex    _Imaginary
```

and GNU extensions add these keywords:

```c
__FUNCTION__          __PRETTY_FUNCTION__    __alignof     __alignof__    __asm       __asm__          __attribute     __attribute__
__builtin_offsetof    __builtin_va_arg       __complex     __complex__    __const     __extension__    __func__        __imag
__imag__              __inline               __inline__    __label__      __null      __real           __real__        __restrict
__restrict__          __signed               __signed__    __thread       __typeof    __volatile       __volatile__
```

In both ISO C99 and C89 with GNU extensions, the following is also recognized as a keyword:

```c
restrict
```

## 1.3 常量

A constant is a literal numeric or character value, such as 5 or 'm'.

All constants are of a particular data type; you can use type casting to explicitly specify the type of a constant, or let the compiler use the default type based on the value of the constant.

### 1.3.1 整型常量

An integer constant is a sequence of digits, with an optional prefix to denote a number base.

If the sequence of digits is preceded by 0x or 0X (zero x or zero X), then the constant is considered to be hexadecimal (base 16).

Hexadecimal values may use the digits from 0 to 9, as well as the letters a to f and A to F.

Here are some examples:

```c
0x2f
0x88
0xAB43
0xAbCd
0x1
```

If the first digit is 0 (zero), and the next character is not 'x' or 'X', then the constant is considered to be octal (base 8).

Octal values may only use the digits from 0 to 7; 8 and 9 are not allowed.

Here are some examples:

```c
057
012
03
0241
```

In all other cases, the sequence of digits is assumed to be decimal (base 10).

Decimal values may use the digits from 0 to 9.

Here are some examples:

```c
459
23901
8
12
```

There are various integer data types, for short integers, long integers, signed integers, and unsigned integers.

You can force an integer constant to be of a long and/or unsigned integer type by appending a sequence of one or more letters to the end of the constant:

```c
u, U    Unsigned integer type.
l, L    Long integer type.
```

For example, 45U is an unsigned int constant.

You can also combine letters: 45UL is an unsigned long int constant.

(The letters may be used in any order.)

Both ISO C99 and GNU C extensions add the integer types long long int and unsigned long long int.

You can use two 'L's to get a long long int constant; add a 'U' to that and you have an unsigned long long int constant.

For example: 45ULL.

### 1.3.2 字符常量

A character constant is usually a single character enclosed within single quotation marks, such as 'Q'.

A character constant is of type int by default.

Some characters, such as the single quotation mark character itself, cannot be represented using only one character.

To represent such characters, there are several "escape sequences" that you can use:

```c
\\                       Backslash character.

\?                       Question mark character.

\'                       Single quotation mark.

\"                       Double quotation mark.

\a                       Audible alert.

\b                       Backspace character.

\e                       <ESC> character.
                         (This is a GNU extension.)

\f                       Form feed.

\n                       Newline character.

\r                       Carriage return.

\t                       Horizontal tab.

\v                       Vertical tab.

\o, \oo, \ooo            Octal number.

\xh, \xhh, \xhhh, ...    Hexadecimal number.
```

To use any of these escape sequences, enclose the sequence in single quotes, and treat it as if it were any other character.

For example, the letter m is 'm' and the newline character is '\n'.

The octal number escape sequence is the backslash character followed by one, two, or three octal digits (0 to 7).

For example, 101 is the octal equivalent of 65, which is the ASCII character 'A'.

Thus, the character constant '\101' is the same as the character constant 'A'.

The hexadecimal escape sequence is the backslash character, followed by x and an unlimited number of hexadecimal digits (0 to 9, and a to f or A to F).

While the length of possible hexadecimal digit strings is unlimited, the number of character constants in any given character set is not.

(The much-used extended ASCII character set, for example, has only 256 characters in it.) If you try to use a hexadecimal value that is outside the range of characters, you will get a compile-time error.

### 1.3.3 实数常量

A real number constant is a value that represents a fractional (floating point) number.

It consists of a sequence of digits which represents the integer (or "whole") part of the number, a decimal point, and a sequence of digits which represents the fractional part.

Either the integer part or the fractional part may be omitted, but not both.

Here are some examples:

```c
double a, b, c, d, e, f;

a = 4.7;

b = 4.;

c = 4;

d = .7;

e = 0.7;
```

(In the third assignment statement, the integer constant 4 is automatically converted from an integer value to a double value.)

Real number constants can also be followed by e or E, and an integer exponent.

The exponent can be either positive or negative.

```c
double x, y;

x = 5e2;     /* x is 5 * 100, or 500.0. */
y = 5e-2;    /* y is 5 * (1/100), or 0.05. */
```

You can append a letter to the end of a real number constant to cause it to be of a particular type.

If you append the letter F (or f) to a real number constant, then its type is float.

If you append the letter L (or l), then its type is long double.

If you do not append any letters, then its type is double.

### 1.3.4 字符串常量

A string constant is a sequence of zero or more characters, digits, and escape sequences enclosed within double quotation marks.

A string constant is of type "array of characters".

All string constants contain a null termination character (\0) as their last character.

Strings are stored as arrays of characters, with no inherent size attribute.

The null termination character lets string-processing functions know where the string ends.

Adjacent string constants are concatenated (combined) into one string, with the null termination character added to the end of the final concatenated string.

A string cannot contain double quotation marks, as double quotation marks are used to enclose the string.

To include the double quotation mark character in a string, use the \" escape sequence.

You can use any of the escape sequences that can be used as character constants in strings.

Here are some example of string constants:

```c
/* This is a single string constant. */
"tutti frutti ice cream"

/* These string constants will be concatenated, same as above. */
"tutti " "frutti" " ice " "cream"

/* This one uses two escape sequences. */
"\"hello, world!\""
```

If a string is too long to fit on one line, you can use a backslash \ to break it up onto separate lines.

```c
"Today's special is a pastrami sandwich on rye bread with \
a potato knish and a cherry soda."
```

Adjacent strings are automatically concatenated, so you can also have string constants span multiple lines by writing them as separate, adjacent, strings.

For example:

```c
"Tomorrow's special is a corned beef sandwich on "
"pumpernickel bread with a kasha knish and seltzer water."
```

is the same as

```c
"Tomorrow's special is a corned beef sandwich on \
pumpernickel bread with a kasha knish and seltzer water."
```

To insert a newline character into the string, so that when the string is printed it will be printed on two different lines, you can use the newline escape sequence '\n'.

```c
printf ("potato\nknish");
```

prints

```c
potato
knish
```

## 1.4 运算符

An operator is a special token that performs an operation, such as addition or subtraction, on either one, two, or three operands.

Full coverage of operators can be found in a later chapter.

See Expressions and Operators.

## 1.5 分隔符

A separator separates tokens.

White space (see next section) is a separator, but it is not a token.

The other separators are all single-character tokens themselves:

```c
( ) [ ] { } ; , . :
```

## 1.6 空格

White space is the collective term used for several characters: the space character, the tab character, the newline character, the vertical tab character, and the form-feed character.

White space is ignored (outside of string and character constants), and is therefore optional, except when it is used to separate tokens.

This means that

```c
#include <stdio.h>

int
main()
{
  printf( "hello, world\n" );
  return 0;
}
```

and

```c
#include <stdio.h> int main(){printf("hello, world\n");
return 0;}
```

are functionally the same program.

Although you must use white space to separate many tokens, no white space is required between operators and operands, nor is it required between other separators and that which they separate.

```c
/* All of these are valid. */

x++;
x ++ ;
x=y+z;
x = y + z ;
x=array[2];
x = array [ 2 ] ;
fraction=numerator / *denominator_ptr;
fraction = numerator / * denominator_ptr ;
```

Furthermore, wherever one space is allowed, any amount of white space is allowed.

```c
/* These two statements are functionally identical. */
x++;

x
       ++       ;
```

In string constants, spaces and tabs are not ignored; rather, they are part of the string.

Therefore,

```c
"potato knish"
```

is not the same as

```c
"potato                        knish"
```




