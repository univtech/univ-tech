# 前言

This is a reference manual for the C programming language as implemented by the GNU Compiler Collection (GCC).

Specifically, this manual aims to document:

+ The 1989 ANSI C standard, commonly known as "C89"
+ The 1999 ISO C standard, commonly known as "C99", to the extent that C99 is implemented by GCC
+ The current state of GNU extensions to standard C

This manual describes C89 as its baseline.

C99 features and GNU extensions are explicitly labeled as such.

By default, GCC will compile code as C89 plus GNU-specific extensions.

Much of C99 is supported; once full support is available, the default compilation dialect will be C99 plus GNU-specific extensions.

(Some of the GNU extensions to C89 ended up, sometimes slightly modified, as standard language features in C99.)

The C language includes a set of preprocessor directives, which are used for things such as macro text replacement, conditional compilation, and file inclusion.

Although normally described in a C language manual, the GNU C preprocessor has been thoroughly documented in The C Preprocessor, a separate manual which covers preprocessing for C, C++, and Objective-C programs, so it is not included here.

## 鸣谢

Thanks to everyone who has helped with editing, proofreading, ideas, typesetting, and administrivia, including: Diego Andres Alvarez Marin, Nelson H. F. Beebe, Karl Berry, Robert Chassell, Hanfeng Chen, Mark de Volld, Antonio Diaz Diaz, dine, Andreas Foerster, Denver Gingerich, Lisa Goldstein, Robert Hansen, Jean-Christophe Helary, Mogens Hetsholm, Teddy Hogeborn, Joe Humphries, J. Wren Hunt, Dutch Ingraham, Adam Johansen, Vladimir Kadlec, Benjamin Kagia, Dright Kayorent, Sugun Kedambadi, Felix Lee, Bjorn Liencres, Steve Morningthunder, Aljosha Papsch, Matthew Plant, Jonathan Sisti, Richard Stallman, J. Otto Tennant, Ole Tetlie, Keith Thompson, T.F. Torrey, James Youngman, and Steve Zachar.

Trevis Rothwell serves as project maintainer and, along with James Youngman, wrote the bulk of the text.

Some example programs are based on algorithms in Donald Knuth's The Art of Computer Programming.

Please send bug reports and suggestions to gnu-c-manual@gnu.org.




