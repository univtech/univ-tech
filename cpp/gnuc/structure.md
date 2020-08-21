# 6 程序结构和作用域

Now that we have seen all of the fundamental elements of C programs, it's time to look at the big picture.

## 6.1 程序结构

A C program may exist entirely within a single source file, but more commonly, any non-trivial program will consist of several custom header files and source files, and will also include and link with files from existing libraries.

By convention, header files (with a ".h" extension) contain variable and function declarations, and source files (with a ".c" extension) contain the corresponding definitions.

Source files may also store declarations, if these declarations are not for objects which need to be seen by other files.

However, header files almost certainly should not contain any definitions.

For example, if you write a function that computes square roots, and you wanted this function to be accessible to files other than where you define the function, then you would put the function declaration into a header file (with a ".h" file extension):

```c
/* sqrt.h */

double
computeSqrt (double x);
```

This header file could be included by other source files which need to use your function, but do not need to know how it was implemented.

The implementation of the function would then go into a corresponding source file (with a ".c" file extension):

```c
/* sqrt.c */
#include "sqrt.h"

double
computeSqrt (double x)
{
  double result;
  ...
  return result;
}
```

## 6.2 作用域

Scope refers to what parts of the program can "see" a declared object.

A declared object can be visible only within a particular function, or within a particular file, or may be visible to an entire set of files by way of including header files and using extern declarations.

Unless explicitly stated otherwise, declarations made at the top-level of a file (i.e., not within a function) are visible to the entire file, including from within functions, but are not visible outside of the file.

Declarations made within functions are visible only within those functions.

A declaration is not visible to declarations that came before it; for example:

```c
int x = 5;
int y = x + 10;
```

will work, but:

```c
int x = y + 10;
int y = 5;
```

will not.

See Storage Class Specifiers, for more information on changing the scope of declared objects.

Also see Static Functions.




