# 2 数据类型

## 2.1 原始数据类型

### 2.1.1 整数类型

The integer data types range in size from at least 8 bits to at least 32 bits.

The C99 standard extends this range to include integer sizes of at least 64 bits.

You should use integer types for storing whole number values (and the char data type for storing characters).

The sizes and ranges listed for these types are minimums; depending on your computer platform, these sizes and ranges may be larger.

While these ranges provide a natural ordering, the standard does not require that any two types have a different range.

For example, it is common for int and long to have the same range.

The standard even allows signed char and long to have the same range, though such platforms are very unusual.

```
signed char               The 8-bit signed char data type can hold integer values in the range of -128 to 127.

unsigned char             The 8-bit unsigned char data type can hold integer values in the range of 0 to 255.

char                      Depending on your system, the char data type is defined as having the same range as either the signed char or the unsigned char data type (they are three distinct types, however).
                          By convention, you should use the char data type specifically for storing ASCII characters (such as 'm'), including escape sequences (such as '\n').

short int                 The 16-bit short int data type can hold integer values in the range of -32,768 to 32,767.
                          You may also refer to this data type as short, signed short int, or signed short.

unsigned short int        The 16-bit unsigned short int data type can hold integer values in the range of 0 to 65,535.
                          You may also refer to this data type as unsigned short.

int                       The 32-bit int data type can hold integer values in the range of -2,147,483,648 to 2,147,483,647.
                          You may also refer to this data type as signed int or signed.

unsigned int              The 32-bit unsigned int data type can hold integer values in the range of 0 to 4,294,967,295.
                          You may also refer to this data type simply as unsigned.

long int                  The 32-bit long int data type can hold integer values in the range of at least -2,147,483,648 to 2,147,483,647.
                          (Depending on your system, this data type might be 64-bit, in which case its range is identical to that of the long long int data type.)
                          You may also refer to this data type as long, signed long int, or signed long.

unsigned long int         The 32-bit unsigned long int data type can hold integer values in the range of at least 0 to 4,294,967,295.
                          (Depending on your system, this data type might be 64-bit, in which case its range is identical to that of the unsigned long long int data type.)
                          You may also refer to this data type as unsigned long.

long long int             The 64-bit long long int data type can hold integer values in the range of -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807.
                          You may also refer to this data type as long long, signed long long int or signed long long.
                          This type is not part of C89, but is both part of C99 and a GNU C extension.

unsigned long long int    The 64-bit unsigned long long int data type can hold integer values in the range of at least 0 to 18,446,744,073,709,551,615.
                          You may also refer to this data type as unsigned long long.
                          This type is not part of C89, but is both part of C99 and a GNU C extension.
```

Here are some examples of declaring and defining integer variables:

```c
int foo;
unsigned int bar = 42;
char quux = 'a';
```

The first line declares an integer named foo but does not define its value; it is left uninitialized, and its value should not be assumed to be anything in particular.

### 2.1.2 实数类型

There are three data types that represent fractional numbers.

While the sizes and ranges of these types are consistent across most computer systems in use today, historically the sizes of these types varied from system to system.

As such, the minimum and maximum values are stored in macro definitions in the library header file float.h.

In this section, we include the names of the macro definitions in place of their possible values; check your system's float.h for specific numbers.

```
float          The float data type is the smallest of the three floating point types, if they differ in size at all.
               Its minimum value is stored in the FLT_MIN, and should be no greater than 1e-37.
               Its maximum value is stored in FLT_MAX, and should be no less than 1e37.

double         The double data type is at least as large as the float type, and it may be larger.
               Its minimum value is stored in DBL_MIN, and its maximum value is stored in DBL_MAX.

long double    The long double data type is at least as large as the float type, and it may be larger.
               Its minimum value is stored in LDBL_MIN, and its maximum value is stored in LDBL_MAX.
```

All floating point data types are signed; trying to use unsigned float, for example, will cause a compile-time error.

Here are some examples of declaring and defining real number variables:

```c
float foo;
double bar = 114.3943;
```

The first line declares a float named foo but does not define its value; it is left uninitialized, and its value should not be assumed to be anything in particular.

The real number types provided in C are of finite precision, and accordingly, not all real numbers can be represented exactly.

Most computer systems that GCC compiles for use a binary representation for real numbers, which is unable to precisely represent numbers such as, for example, 4.2.

For this reason, we recommend that you consider not comparing real numbers for exact equality with the == operator, but rather check that real numbers are within an acceptable tolerance.

There are other more subtle implications of these imprecise representations; for more details, see David Goldberg's paper What Every Computer Scientist Should Know About Floating-Point Arithmetic and section 4.2.2 of Donald Knuth's The Art of Computer Programming.

### 2.1.3 复数类型

GCC introduced some complex number types as an extension to C89.

Similar features were introduced in C991, but there were a number of differences.

We describe the standard complex number types first.

#### 2.1.3.1 标准的复数类型

Complex types were introduced in C99.

There are three complex types:

```
float _Complex
double _Complex
long double _Complex
```

The names here begin with an underscore and an uppercase letter in order to avoid conflicts with existing program's identifiers.

However, the C99 standard header file <complex.h> introduces some macros which make using complex types easier.

```
complex    Expands to _Complex.
           This allows a variable to be declared as double complex which seems more natural.

I          A constant of type const float _Complex having the value of the imaginary unit normally referred to as i.
```

The <complex.h> header file also declares a number of functions for performing computations on complex numbers, for example the creal and cimag functions which respectively return the real and imaginary parts of a double complex number.

Other functions are also provided, as shown in this example:

```c
#include <complex.h>
#include <stdio.h>

void example (void)
{
  complex double z = 1.0 + 3.0*I;
  printf ("Phase is %f, modulus is %f\n", carg (z), cabs (z));
}
```

#### 2.1.3.2 复数类型的GNU扩展

GCC also introduced complex types as a GNU extension to C89, but the spelling is different.

The floating-point complex types in GCC's C89 extension are:

```
__complex__ float
__complex__ double
__complex__ long double
```

GCC's extension allow for complex types other than floating-point, so that you can declare complex character types and complex integer types; in fact __complex__ can be used with any of the primitive data types.

We won't give you a complete list of all possibilities, but here are some examples:

```
__complex__ float    The __complex__ float data type has two components: a real part and an imaginary part, both of which are of the float data type.

__complex__ int      The __complex__ int data type also has two components: a real part and an imaginary part, both of which are of the int data type.
```

To extract the real part of a complex-valued expression, use the keyword __real__, followed by the expression.

Likewise, use __imag__ to extract the imaginary part.

```
__complex__ float a = 4 + 3i;

float b = __real__ a;          /* b is now 4. */
float c = __imag__ a;          /* c is now 3. */
```

This example creates a complex floating point variable a, and defines its real part as 4 and its imaginary part as 3.

Then, the real part is assigned to the floating point variable b, and the imaginary part is assigned to the floating point variable c.

## 2.2 枚举

An enumeration is a custom data type used for storing constant integer values and referring to them by names.

By default, these values are of type signed int; however, you can use the -fshort-enums GCC compiler option to cause the smallest possible integer type to be used instead.

Both of these behaviors conform to the C89 standard, but mixing the use of these options within the same program can produce incompatibilities.

### 2.2.1 定义枚举

You define an enumeration using the enum keyword, followed by the name of the enumeration (this is optional), followed by a list of constant names (separated by commas and enclosed in braces), and ending with a semicolon.

```c
enum fruit {grape, cherry, lemon, kiwi};
```

That example defines an enumeration, fruit, which contains four constant integer values, grape, cherry, lemon, and kiwi, whose values are, by default, 0, 1, 2, and 3, respectively.

You can also specify one or more of the values explicitly:

```c
enum more_fruit {banana = -17, apple, blueberry, mango};
```

That example defines banana to be -17, and the remaining values are incremented by 1: apple is -16, blueberry is -15, and mango is -14.

Unless specified otherwise, an enumeration value is equal to one more than the previous value (and the first value defaults to 0).

You can also refer to an enumeration value defined earlier in the same enumeration:

```c
enum yet_more_fruit {kumquat, raspberry, peach, plum = peach + 2};
```

In that example, kumquat is 0, raspberry is 1, peach is 2, and plum is 4.

You can't use the same name for an enum as a struct or union in the same scope.

### 2.2.2 声明枚举

You can declare variables of an enumeration type both when the enumeration is defined and afterward.

This example declares one variable, named my_fruit of type enum fruit, all in a single statement:

```c
enum fruit {banana, apple, blueberry, mango} my_fruit;
```

while this example declares the type and variable separately:

```c
enum fruit {banana, apple, blueberry, mango};
enum fruit my_fruit;
```

(Of course, you couldn't declare it that way if you hadn't named the enumeration.)

Although such variables are considered to be of an enumeration type, you can assign them any value that you could assign to an int variable, including values from other enumerations.

Furthermore, any variable that can be assigned an int value can be assigned a value from an enumeration.

However, you cannot change the values in an enumeration once it has been defined; they are constant values.

For example, this won't work:

```c
enum fruit {banana, apple, blueberry, mango};
banana = 15;  /* You can't do this! */
```

Enumerations are useful in conjunction with the switch statement, because the compiler can warn you if you have failed to handle one of the enumeration values.

Using the example above, if your code handles banana, apple and mango only but not blueberry, GCC can generate a warning.

## 2.3 共用体

A union is a custom data type used for storing several variables in the same memory space.

Although you can access any of those variables at any time, you should only read from one of them at a time—assigning a value to one of them overwrites the values in the others.

### 2.3.1 定义共用体

You define a union using the union keyword followed by the declarations of the union's members, enclosed in braces.

You declare each member of a union just as you would normally declare a variable—using the data type followed by one or more variable names separated by commas, and ending with a semicolon.

Then end the union definition with a semicolon after the closing brace.

You should also include a name for the union between the union keyword and the opening brace.

This is syntactically optional, but if you leave it out, you can't refer to that union data type later on (without a typedef, see The typedef Statement).

Here is an example of defining a simple union for holding an integer value and a floating point value:

```c
union numbers
  {
    int i;
    float f;
  };
```

That defines a union named numbers, which contains two members, i and f, which are of type int and float, respectively.

### 2.3.2 声明共用体变量

You can declare variables of a union type when both you initially define the union and after the definition, provided you gave the union type a name.

#### 2.3.2.1 在定义时声明共用体变量

You can declare variables of a union type when you define the union type by putting the variable names after the closing brace of the union definition, but before the final semicolon.

You can declare more than one such variable by separating the names with commas.

```c
union numbers
  {
    int i;
    float f;
  } first_number, second_number;
```

That example declares two variables of type union numbers, first_number and second_number.

#### 2.3.2.2 在定义后声明共用体变量

You can declare variables of a union type after you define the union by using the union keyword and the name you gave the union type, followed by one or more variable names separated by commas.

```c
union numbers
  {
    int i;
    float f;
  };
union numbers first_number, second_number;
```

That example declares two variables of type union numbers, first_number and second_number.

#### 2.3.2.3 初始化共用体成员

You can initialize the first member of a union variable when you declare it:

```c
union numbers
  {
    int i;
    float f;
  };
union numbers first_number = { 5 };
```

In that example, the i member of first_number gets the value 5.

The f member is left alone.

Another way to initialize a union member is to specify the name of the member to initialize.

This way, you can initialize whichever member you want to, not just the first one.

There are two methods that you can use—either follow the member name with a colon, and then its value, like this:

```c
union numbers first_number = { f: 3.14159 };
```

or precede the member name with a period and assign a value with the assignment operator, like this:

```c
union numbers first_number = { .f = 3.14159 };
```

You can also initialize a union member when you declare the union variable during the definition:

```c
union numbers
  {
    int i;
    float f;
  } first_number = { 5 };
```

### 2.3.3 访问共用体成员

You can access the members of a union variable using the member access operator.

You put the name of the union variable on the left side of the operator, and the name of the member on the right side.

```c
union numbers
  {
    int i;
    float f;
  };
union numbers first_number;
first_number.i = 5;
first_number.f = 3.9;
```

Notice in that example that giving a value to the f member overrides the value stored in the i member.

### 2.3.4 共用体的大小

This size of a union is equal to the size of its largest member.

Consider the first union example from this section:

```c
union numbers
  {
    int i;
    float f;
  };
```

The size of the union data type is the same as sizeof (float), because the float type is larger than the int type.

Since all of the members of a union occupy the same memory space, the union data type size doesn't need to be large enough to hold the sum of all their sizes; it just needs to be large enough to hold the largest member.

## 2.4 结构体

A structure is a programmer-defined data type made up of variables of other data types (possibly including other structure types).

### 2.4.1 定义结构体

You define a structure using the struct keyword followed by the declarations of the structure's members, enclosed in braces.

You declare each member of a structure just as you would normally declare a variable—using the data type followed by one or more variable names separated by commas, and ending with a semicolon.

Then end the structure definition with a semicolon after the closing brace.

You should also include a name for the structure in between the struct keyword and the opening brace.

This is optional, but if you leave it out, you can't refer to that structure data type later on (without a typedef, see The typedef Statement).

Here is an example of defining a simple structure for holding the X and Y coordinates of a point:

```c
struct point
  {
    int x, y;
  };
```

That defines a structure type named struct point, which contains two members, x and y, both of which are of type int.

Structures (and unions) may contain instances of other structures and unions, but of course not themselves.

It is possible for a structure or union type to contain a field which is a pointer to the same type (see Incomplete Types).

### 2.4.2 声明结构体变量

You can declare variables of a structure type when both you initially define the structure and after the definition, provided you gave the structure type a name.

#### 2.4.2.1 在定义时声明结构体变量

You can declare variables of a structure type when you define the structure type by putting the variable names after the closing brace of the structure definition, but before the final semicolon.

You can declare more than one such variable by separating the names with commas.

```c
struct point
  {
    int x, y;
  } first_point, second_point;
```

That example declares two variables of type struct point, first_point and second_point.

#### 2.4.2.2 在定义后声明结构体变量

You can declare variables of a structure type after defining the structure by using the struct keyword and the name you gave the structure type, followed by one or more variable names separated by commas.

```c
struct point
  {
    int x, y;
  };
struct point first_point, second_point;
```

That example declares two variables of type struct point, first_point and second_point.

#### 2.4.2.3 初始化结构体成员

You can initialize the members of a structure type to have certain values when you declare structure variables.

If you do not initialize a structure variable, the effect depends on whether it has static storage (see Storage Class Specifiers) or not.

If it is, members with integral types are initialized with 0 and pointer members are initialized to NULL; otherwise, the value of the structure's members is indeterminate.

One way to initialize a structure is to specify the values in a set of braces and separated by commas.

Those values are assigned to the structure members in the same order that the members are declared in the structure in definition.

```c
struct point
  {
    int x, y;
  };
struct point first_point = { 5, 10 };
```

In that example, the x member of first_point gets the value 5, and the y member gets the value 10.

Another way to initialize the members is to specify the name of the member to initialize.

This way, you can initialize the members in any order you like, and even leave some of them uninitialized.

There are two methods that you can use.

The first method is available in C99 and as a C89 extension in GCC:

```c
struct point first_point = { .y = 10, .x = 5 };
```

You can also omit the period and use a colon instead of '=', though this is a GNU C extension:

```c
struct point first_point = { y: 10, x: 5 };
```

You can also initialize the structure variable's members when you declare the variable during the structure definition:

```c
struct point
  {
    int x, y;
  } first_point = { 5, 10 };
```

You can also initialize fewer than all of a structure variable's members:

```c
struct pointy
  {
    int x, y;
    char *p;
  };
struct pointy first_pointy = { 5 };
```

Here, x is initialized with 5, y is initialized with 0, and p is initialized with NULL.

The rule here is that y and p are initialized just as they would be if they were static variables.

Here is another example that initializes a structure's members which are structure variables themselves:

```c
struct point
  {
    int x, y;
  };

struct rectangle
  {
    struct point top_left, bottom_right;
  };

struct rectangle my_rectangle = { {0, 5}, {10, 0} };
```

That example defines the rectangle structure to consist of two point structure variables.

Then it declares one variable of type struct rectangle and initializes its members.

Since its members are structure variables, we used an extra set of braces surrounding the members that belong to the point structure variables.

However, those extra braces are not necessary; they just make the code easier to read.

### 2.4.3 访问结构体成员

You can access the members of a structure variable using the member access operator.

You put the name of the structure variable on the left side of the operator, and the name of the member on the right side.

```c
struct point
  {
    int x, y;
  };

struct point first_point;

first_point.x = 0;
first_point.y = 5;
```

You can also access the members of a structure variable which is itself a member of a structure variable.

```c
struct rectangle
  {
    struct point top_left, bottom_right;
  };

struct rectangle my_rectangle;

my_rectangle.top_left.x = 0;
my_rectangle.top_left.y = 5;

my_rectangle.bottom_right.x = 10;
my_rectangle.bottom_right.y = 0;
```

### 2.4.4 位域

You can create structures with integer members of nonstandard sizes, called bit fields.

You do this by specifying an integer (int, char, long int, etc.) member as usual, and inserting a colon and the number of bits that the member should occupy in between the member's name and the semicolon.

```c
struct card
  {
    unsigned int suit : 2;
    unsigned int face_value : 4;
  };
```

That example defines a structure type with two bit fields, suit and face_value, which take up 2 bits and 4 bits, respectively.

suit can hold values from 0 to 3, and face_value can hold values from 0 to 15.

Notice that these bit fields were declared as unsigned int; had they been signed integers, then their ranges would have been from -2 to 1, and from -8 to 7, respectively.

More generally, the range of an unsigned bit field of N bits is from 0 to 2^N - 1, and the range of a signed bit field of N bits is from -(2^N) / 2 to ((2^N) / 2) - 1.

Bit fields can be specified without a name in order to control which actual bits within the containing unit are used.

However, the effect of this is not very portable and it is rarely useful.

You can also specify a bit field of size 0, which indicates that subsequent bit fields not further bit fields should be packed into the unit containing the previous bit field.

This is likewise not generally useful.

You may not take the address of a bit field with the address operator & (see Pointer Operators).

### 2.4.5 结构体的大小

The size of a structure type is equal to the sum of the size of all of its members, possibly including padding to cause the structure type to align to a particular byte boundary.

The details vary depending on your computer platform, but it would not be atypical to see structures padded to align on four- or eight-byte boundaries.

This is done in order to speed up memory accesses of instances of the structure type.

As a GNU extension, GCC allows structures with no members.

Such structures have zero size.

If you wish to explicitly omit padding from your structure types (which may, in turn, decrease the speed of structure memory accesses), then GCC provides multiple methods of turning packing off.

The quick and easy method is to use the -fpack-struct compiler option.

For more details on omitting packing, please see the GCC manual which corresponds to your version of the compiler.

## 2.5 数组

An array is a data structure that lets you store one or more elements consecutively in memory.

In C, array elements are indexed beginning at position zero, not one.

### 2.5.1 声明数组

You declare an array by specifying the data type for its elements, its name, and the number of elements it can store.

Here is an example that declares an array that can store ten integers:

```c
int my_array[10];
```

For standard C code, the number of elements in an array must be positive.

As a GNU extension, the number of elements can be as small as zero.

Zero-length arrays are useful as the last element of a structure which is really a header for a variable-length object:

```c
struct line
{
  int length;
  char contents[0];
};

{
  struct line *this_line = (struct line *)
    malloc (sizeof (struct line) + this_length);
  this_line -> length = this_length;
}
```

Another GNU extension allows you to declare an array size using variables, rather than only constants.

For example, here is a function definition that declares an array using its parameter as the number of elements:

```c
int
my_function (int number)
{
  int my_array[number];
  ...;
}
```

### 2.5.2 初始化数组

You can initialize the elements in an array when you declare it by listing the initializing values, separated by commas, in a set of braces.

Here is an example:

```c
int my_array[5] = { 0, 1, 2, 3, 4 };
```

You don't have to explicitly initialize all of the array elements.

For example, this code initializes the first three elements as specified, and then initializes the last two elements to a default value of zero:

```c
int my_array[5] = { 0, 1, 2 };
```

When using either ISO C99, or C89 with GNU extensions, you can initialize array elements out of order, by specifying which array indices to initialize.

To do this, include the array index in brackets, and optionally the assignment operator, before the value.

Here is an example:

```c
int my_array[5] = { [2] 5, [4] 9 };
```

Or, using the assignment operator:

```c
int my_array[5] = { [2] = 5, [4] = 9 };
```

Both of those examples are equivalent to:

```c
int my_array[5] = { 0, 0, 5, 0, 9 };
```

When using GNU extensions, you can initialize a range of elements to the same value, by specifying the first and last indices, in the form [first] ... [last] .

Here is an example:

```c
int new_array[100] = { [0 ... 9] = 1, [10 ... 98] = 2, 3 };
```

That initializes elements 0 through 9 to 1, elements 10 through 98 to 2, and element 99 to 3.

(You also could explicitly write [99] = 3.) Also, notice that you must have spaces on both sides of the '...'.

If you initialize every element of an array, then you do not have to specify its size; its size is determined by the number of elements you initialize.

Here is an example:

```c
int my_array[] = { 0, 1, 2, 3, 4 };
```

Although this does not explicitly state that the array has five elements using my_array[5], it initializes five elements, so that is how many it has.

Alternately, if you specify which elements to initialize, then the size of the array is equal to the highest element number initialized, plus one.

For example:

```c
int my_array[] = { 0, 1, 2, [99] = 99 };
```

In that example, only four elements are initialized, but the last one initialized is element number 99, so there are 100 elements.

### 2.5.3 访问数组元素

You can access the elements of an array by specifying the array name, followed by the element index, enclosed in brackets.

Remember that the array elements are numbered starting with zero.

Here is an example:

```c
my_array[0] = 5;
```

That assigns the value 5 to the first element in the array, at position zero.

You can treat individual array elements like variables of whatever data type the array is made up of.

For example, if you have an array made of a structure data type, you can access the structure elements like this:

```c
struct point
{
  int x, y;
};
struct point point_array[2] = { {4, 5}, {8, 9} };
point_array[0].x = 3;
```

### 2.5.4 多维数组

You can make multidimensional arrays, or "arrays of arrays".

You do this by adding an extra set of brackets and array lengths for every additional dimension you want your array to have.

For example, here is a declaration for a two-dimensional array that holds five elements in each dimension (a two-element array consisting of five-element arrays):

```c
int two_dimensions[2][5] { {1, 2, 3, 4, 5}, {6, 7, 8, 9, 10} };
```

Multidimensional array elements are accessed by specifying the desired index of both dimensions:

```c
two_dimensions[1][3] = 12;
```

In our example, two_dimensions[0] is itself an array.

The element two_dimensions[0][2] is followed by two_dimensions[0][3], not by two_dimensions[1][2].

### 2.5.5 字符数组

You can use an array of characters to hold a string (see String Constants).

The array may be built of either signed or unsigned characters.

When you declare the array, you can specify the number of elements it will have.

That number will be the maximum number of characters that should be in the string, including the null character used to end the string.

If you choose this option, then you do not have to initialize the array when you declare it.

Alternately, you can simply initialize the array to a value, and its size will then be exactly large enough to hold whatever string you used to initialize it.

There are two different ways to initialize the array.

You can specify of comma-delimited list of characters enclosed in braces, or you can specify a string literal enclosed in double quotation marks.

Here are some examples:

```c
char blue[26];
char yellow[26] = {'y', 'e', 'l', 'l', 'o', 'w', '\0'};
char orange[26] = "orange";
char gray[] = {'g', 'r', 'a', 'y', '\0'};
char salmon[] = "salmon";
```

In each of these cases, the null character \0 is included at the end of the string, even when not explicitly stated.

(Note that if you initialize a string using an array of individual characters, then the null character is not guaranteed to be present.

It might be, but such an occurrence would be one of chance, and should not be relied upon.)

After initialization, you cannot assign a new string literal to an array using the assignment operator.

For example, this will not work:

```c
char lemon[26] = "custard";
lemon = "steak sauce";      /* Fails! */
```

However, there are functions in the GNU C library that perform operations (including copy) on string arrays.

You can also change one character at a time, by accessing individual string elements as you would any other array:

```c
char name[] = "bob";
name[0] = 'r';
```

It is possible for you to explicitly state the number of elements in the array, and then initialize it using a string that has more characters than there are elements in the array.

This is not a good thing.

The larger string will not override the previously specified size of the array, and you will get a compile-time warning.

Since the original array size remains, any part of the string that exceeds that original size is being written to a memory location that was not allocated for it.

### 2.5.6 共用体数组

You can create an array of a union type just as you can an array of a primitive data type.

```c
union numbers
  {
    int i;
    float f;
  };
union numbers number_array [3];
```

That example creates a 3-element array of union numbers variables called number_array.

You can also initialize the first members of the elements of a number array:

```c
union numbers number_array [3] = { {3}, {4}, {5} };
```

The additional inner grouping braces are optional.

After initialization, you can still access the union members in the array using the member access operator.

You put the array name and element number (enclosed in brackets) to the left of the operator, and the member name to the right.

```c
union numbers number_array [3];
number_array[0].i = 2;
```

### 2.5.7 结构体数组

You can create an array of a structure type just as you can an array of a primitive data type.

```c
struct point
  {
    int x, y;
  };
struct point point_array [3];
```

That example creates a 3-element array of struct point variables called point_array.

You can also initialize the elements of a structure array:

```c
struct point point_array [3] = { {2, 3}, {4, 5}, {6, 7} };
```

As with initializing structures which contain structure members, the additional inner grouping braces are optional.

But, if you use the additional braces, then you can partially initialize some of the structures in the array, and fully initialize others:

```c
struct point point_array [3] = { {2}, {4, 5}, {6, 7} };
```

In that example, the first element of the array has only its x member initialized.

Because of the grouping braces, the value 4 is assigned to the x member of the second array element, not to the y member of the first element, as would be the case without the grouping braces.

After initialization, you can still access the structure members in the array using the member access operator.

You put the array name and element number (enclosed in brackets) to the left of the operator, and the member name to the right.

```c
struct point point_array [3];
point_array[0].x = 2;
point_array[0].y = 3;
```

## 2.6 指针

Pointers hold memory addresses of stored constants or variables.

For any data type, including both primitive types and custom types, you can create a pointer that holds the memory address of an instance of that type.

### 2.6.1 声明指针

You declare a pointer by specifying a name for it and a data type.

The data type indicates of what type of variable the pointer will hold memory addresses.

To declare a pointer, include the indirection operator (see Pointer Operators) before the identifier.

Here is the general form of a pointer declaration:

```c
data-type * name;
```

White space is not significant around the indirection operator:

```c
data-type *name;
data-type* name;
```

Here is an example of declaring a pointer to hold the address of an int variable:

```c
int *ip;
```

Be careful, though: when declaring multiple pointers in the same statement, you must explicitly declare each as a pointer, using the indirection operator:

```c
int *foo, *bar;  /* Two pointers. */
int *baz, quux;  /* A pointer and an integer variable. */
```

### 2.6.2 初始化指针

You can initialize a pointer when you first declare it by specifying a variable address to store in it.

For example, the following code declares an int variable 'i', and a pointer which is initialized with the address of 'i':

```c
int i;
int *ip = &i;
```

Note the use of the address operator (see Pointer Operators), used to get the memory address of a variable.

After you declare a pointer, you do not use the indirection operator with the pointer's name when assigning it a new address to point to.

On the contrary, that would change the value of the variable that the points to, not the value of the pointer itself.

For example:

```c
int i, j;
int *ip = &i;  /* 'ip' now holds the address of 'i'. */
ip = &j;       /* 'ip' now holds the address of 'j'. */
*ip = &i;      /* 'j' now holds the address of 'i'. */
```

The value stored in a pointer is an integral number: a location within the computer's memory space.

If you are so inclined, you can assign pointer values explicitly using literal integers, casting them to the appropriate pointer type.

However, we do not recommend this practice unless you need to have extremely fine-tuned control over what is stored in memory, and you know exactly what you are doing.

It would be all too easy to accidentally overwrite something that you did not intend to.

Most uses of this technique are also non-portable.

It is important to note that if you do not initialize a pointer with the address of some other existing object, it points nowhere in particular and will likely make your program crash if you use it (formally, this kind of thing is called undefined behavior).

### 2.6.3 共用体指针

You can create a pointer to a union type just as you can a pointer to a primitive data type.

```c
union numbers
  {
    int i;
    float f;
  };
union numbers foo = {4};
union numbers *number_ptr = &foo;
```

That example creates a new union type, union numbers, and declares (and initializes the first member of) a variable of that type named foo.

Finally, it declares a pointer to the type union numbers, and gives it the address of foo.

You can access the members of a union variable through a pointer, but you can't use the regular member access operator anymore.

Instead, you have to use the indirect member access operator (see Member Access Expressions).

Continuing with the previous example, the following example will change the value of the first member of foo:

```c
number_ptr -> i = 450;
```

Now the i member in foo is 450.

### 2.6.4 结构体指针

You can create a pointer to a structure type just as you can a pointer to a primitive data type.

```c
struct fish
  {
    float length, weight;
  };
struct fish salmon = {4.3, 5.8};
struct fish *fish_ptr = &salmon;
```

That example creates a new structure type, struct fish, and declares (and initializes) a variable of that type named salmon.

Finally, it declares a pointer to the type struct fish, and gives it the address of salmon.

You can access the members of a structure variable through a pointer, but you can't use the regular member access operator anymore.

Instead, you have to use the indirect member access operator (see Member Access Expressions).

Continuing with the previous example, the following example will change the values of the members of salmon:

```c
fish_ptr -> length = 5.1;
fish_ptr -> weight = 6.2;
```

Now the length and width members in salmon are 5.1 and 6.2, respectively.

## 2.7 不完全类型

You can define structures, unions, and enumerations without listing their members (or values, in the case of enumerations).

Doing so results in an incomplete type.

You can't declare variables of incomplete types, but you can work with pointers to those types.

```c
struct point;
```

At some time later in your program you will want to complete the type.

You do this by defining it as you usually would:

```c
struct point
  {
    int x, y;
  };
```

This technique is commonly used to for linked lists:

```c
struct singly_linked_list
  {
    struct singly_linked_list *next;
    int x;
    /* other members here perhaps */
  };
struct singly_linked_list *list_head;
```

## 2.8 类型限定符

There are two type qualifiers that you can prepend to your variable declarations which change how the variables may be accessed: const and volatile.

const causes the variable to be read-only; after initialization, its value may not be changed.

```c
const float pi = 3.14159f;
```

In addition to helping to prevent accidental value changes, declaring variables with const can aid the compiler in code optimization.

volatile tells the compiler that the variable is explicitly changeable, and seemingly useless accesses of the variable (for instance, via pointers) should not be optimized away.

You might use volatile variables to store data that is updated via callback functions or signal handlers.

Sequence Points and Signal Delivery.

```c
volatile float currentTemperature = 40.0;
```

## 2.9 存储类说明符

There are four storage class specifiers that you can prepend to your variable declarations which change how the variables are stored in memory: auto, extern, register, and static.

You use auto for variables which are local to a function, and whose values should be discarded upon return from the function in which they are declared.

This is the default behavior for variables declared within functions.

```c
void
foo (int value)
{
  auto int x = value;
  ...
  return;
}
```

register is nearly identical in purpose to auto, except that it also suggests to the compiler that the variable will be heavily used, and, if possible, should be stored in a register.

You cannot use the address-of operator to obtain the address of a variable declared with register.

This means that you cannot refer to the elements of an array declared with storage class register.

In fact the only thing you can do with such an array is measure its size with sizeof.

GCC normally makes good choices about which values to hold in registers, and so register is not often used.

static is essentially the opposite of auto: when applied to variables within a function or block, these variables will retain their value even when the function or block is finished.

This is known as static storage duration.

```c
int
sum (int x)
{
  static int sumSoFar = 0;
  sumSoFar = sumSoFar + x;
  return sumSoFar;
}
```

You can also declare variables (or functions) at the top level (that is, not inside a function) to be static; such variables are visible (global) to the current source file (but not other source files).

This gives an unfortunate double meaning to static; this second meaning is known as static linkage.

Two functions or variables having static linkage in separate files are entirely separate; neither is visible outside the file in which it is declared.

Uninitialized variables that are declared as extern are given default values of 0, 0.0, or NULL, depending on the type.

Uninitialized variables that are declared as auto or register (including the default usage of auto) are left uninitialized, and hence should not be assumed to hold any particular value.

extern is useful for declaring variables that you want to be visible to all source files that are linked into your project.

You cannot initialize a variable in an extern declaration, as no space is actually allocated during the declaration.

You must make both an extern declaration (typically in a header file that is included by the other source files which need to access the variable) and a non-extern declaration which is where space is actually allocated to store the variable.

The extern declaration may be repeated multiple times.

```c
extern int numberOfClients;
...
int numberOfClients = 0;
```

See Program Structure and Scope, for related information.

## 2.10 重命名类型

Sometimes it is convenient to give a new name to a type.

You can do this using the typedef statement.

See The typedef Statement, for more information.




