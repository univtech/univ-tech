# 3 表达式和运算符

## 3.1 表达式

An expression consists of at least one operand and zero or more operators.

Operands are typed objects such as constants, variables, and function calls that return values.

Here are some examples:

```c
47
2 + 2
cosine(3.14159)    /* We presume this returns a floating point value. */
```

Parentheses group subexpressions:

```c
( 2 * ( ( 3 + 10 ) - ( 2 * 6 ) ) )
```

Innermost expressions are evaluated first.

In the above example, 3 + 10 and 2 * 6 evaluate to 13 and 12, respectively.

Then 12 is subtracted from 13, resulting in 1.

Finally, 1 is multiplied by 2, resulting in 2.

The outermost parentheses are completely optional.

An operator specifies an operation to be performed on its operand(s).

Operators may have one, two, or three operands, depending on the operator.

## 3.2 赋值运算符

Assignment operators store values in variables.

C provides several variations of assignment operators.

The standard assignment operator = simply stores the value of its right operand in the variable specified by its left operand.

As with all assignment operators, the left operand (commonly referred to as the "lvalue") cannot be a literal or constant value.

```c
int x = 10;
float y = 45.12 + 2.0;
int z = (2 * (3 + function () ));

struct foo {
  int bar;
  int baz;
} quux = {3, 4};
```

Note that, unlike the other assignment operators described below, you can use the plain assignment operator to store values of a structure type.

Compound assignment operators perform an operation involving both the left and right operands, and then assign the resulting expression to the left operand.

Here is a list of the compound assignment operators, and a brief description of what they do:

```
+=     Adds the two operands together, and then assign the result of the addition to the left operand.

-=     Subtract the right operand from the left operand, and then assign the result of the subtraction to the left operand.

*=     Multiply the two operands together, and then assign the result of the multiplication to the left operand.

/=     Divide the left operand by the right operand, and assign the result of the division to the left operand.

%=     Perform modular division on the two operands, and assign the result of the division to the left operand.

<<=    Perform a left shift operation on the left operand, shifting by the number of bits specified by the right operand, and assign the result of the shift to the left operand.

>>=    Perform a right shift operation on the left operand, shifting by the number of bits specified by the right operand, and assign the result of the shift to the left operand.

&=     Perform a bitwise conjunction operation on the two operands, and assign the result of the operation to the left operand.

^=     Performs a bitwise exclusive disjunction operation on the two operands, and assign the result of the operation to the left operand.

|=     Performs a bitwise inclusive disjunction operation on the two operands, and assign the result of the operation to the left operand.
```

Here is an example of using one of the compound assignment operators:

```c
x += y;
```

Since there are no side effects wrought by evaluating the variable x as an lvalue, the above code produces the same result as:

```c
x = x + y;
```

## 3.3 递增和递减

The increment operator ++ adds 1 to its operand.

The operand must be a either a variable of one of the primitive data types, a pointer, or an enumeration variable.

You can apply the increment operator either before or after the operand.

Here are some examples:

```c
char w = '1';
int x = 5;
char y = 'B';
float z = 5.2;
int *p = &x;

++w;    /* w is now the character '2' (not the value 2). */
x++;    /* x is now 6. */
++y;    /* y is now 'C' (on ASCII systems). */
z++;    /* z is now 6.2. */
++p;    /* p is now &x + sizeof(int). */
```

(Note that incrementing a pointer only makes sense if you have reason to believe that the new pointer value will be a valid memory address.)

A prefix increment adds 1 before the operand is evaluated.

A postfix increment adds 1 after the operand is evaluated.

In the previous examples, changing the position of the operator would make no difference.

However, there are cases where it does make a difference:

```c
int x = 5;
printf ("%d \n", x++);    /* Print x and then increment it. */
                          /* x is now equal to 6. */
printf ("%d \n", ++x);    /* Increment x and then print it. */
```

The output of the above example is:

```c
5
7
```

Likewise, you can subtract 1 from an operand using the decrement operator:

```c
int x = 5;
x--;          /* x is now 4. */
```

The concepts of prefix and postfix application apply here as with the increment operator.

## 3.4 算术运算符

C provides operators for standard arithmetic operations: addition, subtraction, multiplication, and division, along with modular division and negation.

Usage of these operators is straightforward; here are some examples:

```c
/* Addition. */
x = 5 + 3;
y = 10.23 + 37.332;
quux_pointer = foo_pointer + bar_pointer;

/* Subtraction. */
x = 5 - 3;
y = 57.223 - 10.903;
quux_pointer = foo_pointer - bar_pointer;
```

You can add and subtract memory pointers, but you cannot multiply or divide them.

```c
/* Multiplication. */
x = 5 * 3;
y = 47.4 * 1.001;

/* Division. */
x = 5 / 3;
y = 940.0 / 20.2;
```

Integer division of positive values truncates towards zero, so 5/3 is 1.

However, if either operand is negative, the direction of rounding is implementation-defined.

Signed Integer Division for information about overflow in signed integer division.

You use the modulus operator % to obtain the remainder produced by dividing its two operands.

You put the operands on either side of the operator, and it does matter which operand goes on which side: 3 % 5 and 5 % 3 do not have the same result.

The operands must be expressions of a primitive data type.

```c
/* Modular division. */
x = 5 % 3;
y = 74 % 47;
```

Modular division returns the remainder produced after performing integer division on the two operands.

The operands must be of a primitive integer type.

```c
/* Negation. */
int x = -5;
float y = -3.14159;
```

If the operand you use with the negative operator is of an unsigned data type, then the result cannot negative, but rather is the maximum value of the unsigned data type, minus the value of the operand.

Many systems use twos-complement arithmetic, and on such systems the most negative value a signed type can hold is further away from zero than the most positive value.

For example, on one platform, this program:

```c
#include <limits.h>
#include <stdio.h>

int main (int argc, char *argv[]) 
{
  int x;
  x = INT_MAX;
  printf("INT_MAX  = %d\n", x);
  x = INT_MIN;
  printf("INT_MIN  = %d\n", x);
  x = -x;
  printf("-INT_MIN = %d\n", x);
  return 0;
}
```

Produces this output:

```c
INT_MAX  = 2147483647
INT_MIN  = -2147483648
-INT_MIN = -2147483648
```

Trivially, you can also apply a positive operator to a numeric expression:

```c
int x = +42;
```

Numeric values are assumed to be positive unless explicitly made negative, so this operator has no effect on program operation.

## 3.5 共轭复数

As a GNU extension, you can use the complex conjugation operator ~ to perform complex conjugation on its operand — that is, it reverses the sign of its imaginary component.

The operand must be an expression of a complex number type.

Here is an example:

```c
__complex__ int x = 5 + 17i;

printf ("%d  \n", (x * ~x));
```

Since an imaginary number (a + bi) multiplied by its conjugate is equal to a^2 + b^2, the above printf statement will print 314, which is equal to 25 + 289.

## 3.6 比较运算符

You use the comparison operators to determine how two operands relate to each other: are they equal to each other, is one larger than the other, is one smaller than the other, and so on.

When you use any of the comparison operators, the result is either 1 or 0, meaning true or false, respectively.

(In the following code examples, the variables x and y stand for any two expressions of arithmetic types, or pointers.)

The equal-to operator == tests its two operands for equality.

The result is 1 if the operands are equal, and 0 if the operands are not equal.

```c
if (x == y)
  puts ("x is equal to y");
else
  puts ("x is not equal to y");
```

The not-equal-to operator != tests its two operands for inequality.

The result is 1 if the operands are not equal, and 0 if the operands are equal.

```c
if (x != y)
  puts ("x is not equal to y");
else
  puts ("x is equal to y");
```

Comparing floating-point values for exact equality or inequality can produce unexpected results.

Real Number Types for more information.

You can compare function pointers for equality or inequality; the comparison tests if two function pointers point to the same function or not.

Beyond equality and inequality, there are operators you can use to test if one value is less than, greater than, less-than-or-equal-to, or greater-than-or-equal-to another value.

Here are some code samples that exemplify usage of these operators:

```c
if (x < y)
  puts ("x is less than y");
if (x <= y)
  puts ("x is less than or equal to y");
if (x > y)
  puts ("x is greater than y");
if (x >= y)
  puts ("x is greater than or equal to y");
```

## 3.7 逻辑运算符

Logical operators test the truth value of a pair of operands.

Any nonzero expression is considered true in C, while an expression that evaluates to zero is considered false.

The logical conjunction operator && tests if two expressions are both true.

If the first expression is false, then the second expression is not evaluated.

```c
if ((x == 5) && (y == 10))
  printf ("x is 5 and y is 10");
```

The logical disjunction operator || tests if at least one of two expressions it true.

If the first expression is true, then the second expression is not evaluated.

```c
if ((x == 5) || (y == 10))
   printf ("x is 5 or y is 10");
```

You can prepend a logical expression with a negation operator ! to flip the truth value:

```c
if (!(x == 5))
  printf ("x is not 5");
```

Since the second operand in a logical expression pair is not necessarily evaluated, you can write code with perhaps unintuitive results:

```c
if (foo && x++)
  bar();
```

If foo is ever zero, then not only would bar not be called, but x would not be incremented.

If you intend to increment x regardless of the value of foo, you should do so outside of the conjunction expression.

## 3.8 位移

You use the left-shift operator << to shift its first operand's bits to the left.

The second operand denotes the number of bit places to shift.

Bits shifted off the left side of the value are discarded; new bits added on the right side will all be 0.

```c
x = 47;    /* 47 is 00101111 in binary. */
x << 1;    /* 00101111 << 1 is 01011110. */
```

Similarly, you use the right-shift operator >> to shift its first operand's bits to the right.

Bits shifted off the right side are discarded; new bits added on the left side are usually 0, but if the first operand is a signed negative value, then the added bits will be either 0 or whatever value was previously in the leftmost bit position.

```c
x = 47;    /* 47 is 00101111 in binary. */
x >> 1;    /* 00101111 >> 1 is 00010111. */
```

For both << and >>, if the second operand is greater than the bit-width of the first operand, or the second operand is negative, the behavior is undefined.

You can use the shift operators to perform a variety of interesting hacks.

For example, given a date with the day of the month numbered as d, the month numbered as m, and the year y, you can store the entire date in a single number x:

```c
int d = 12;
int m = 6;
int y = 1983;
int x = (((y << 4) + m) << 5) + d;
```

You can then extract the original day, month, and year out of x using a combination of shift operators and modular division:

```c
d = x % 32;
m = (x >> 5) % 16;
y = x >> 9;
```

## 3.9 位逻辑运算符

C provides operators for performing bitwise conjunction, inclusive disjunction, exclusive disjunction, and negation (complement).

Biwise conjunction examines each bit in its two operands, and when two corresponding bits are both 1, the resulting bit is 1.

All other resulting bits are 0.

Here is an example of how this works, using binary numbers:

```c
11001001 & 10011011 = 10001001
```

Bitwise inclusive disjunction examines each bit in its two operands, and when two corresponding bits are both 0, the resulting bit is 0.

All other resulting bits are 1.

```c
11001001 | 10011011 = 11011011
```

Bitwise exclusive disjunction examines each bit in its two operands, and when two corresponding bits are different, the resulting bit is 1.

All other resulting bits are 0.

```c
11001001 ^ 10011011 = 01010010
```

Bitwise negation reverses each bit in its operand:

```c
~11001001 = 00110110
```

In C, you can only use these operators with operands of an integer (or character) type, and for maximum portability, you should only use the bitwise negation operator with unsigned integer types.

Here are some examples of using these operators in C code:

```c
unsigned int foo = 42;
unsigned int bar = 57;
unsigned int quux;

quux = foo & bar;
quux = foo | bar;
quux = foo ^ bar;
quux = ~foo;
```

## 3.10 指针运算符

You can use the address operator & to obtain the memory address of an object.

```c
int x = 5;
int *pointer_to_x = &x;
```

It is not necessary to use this operator to obtain the address of a function, although you can:

```c
extern int foo (void);
int (*fp1) (void) = foo;     /* fp1 points to foo */
int (*fp2) (void) = &foo;    /* fp2 also points to foo */
```

Function pointers and data pointers are not compatible, in the sense that you cannot expect to store the address of a function into a data pointer, and then copy that into a function pointer and call it successfully.

It might work on some systems, but it's not a portable technique.

As a GNU extension to C89, you can also obtain the address of a label with the label address operator &&.

The result is a void* pointer which can be used with goto.

See The goto Statement.

Given a memory address stored in a pointer, you can use the indirection operator * to obtain the value stored at the address.

(This is called dereferencing the pointer.)

```c
int x = 5;
int y;
int *ptr;

ptr = &x;    /* ptr now holds the address of x. */
y = *ptr;    /* y gets the value stored at the address stored in ptr. */
```

Avoid using dereferencing pointers that have not been initialized to a known memory location.

## 3.11 sizeof运算符

You can use the sizeof operator to obtain the size (in bytes) of the data type of its operand.

The operand may be an actual type specifier (such as int or float), as well as any valid expression.

When the operand is a type name, it must be enclosed in parentheses.

Here are some examples:

```c
size_t a = sizeof(int);
size_t b = sizeof(float);
size_t c = sizeof(5);
size_t d = sizeof(5.143);
size_t e = sizeof a;
```

The result of the sizeof operator is of a type called size_t, which is defined in the header file <stddef.h>.

size_t is an unsigned integer type, perhaps identical to unsigned int or unsigned long int; it varies from system to system.

The size_t type is often a convenient type for a loop index, since it is guaranteed to be able to hold the number of elements in any array; this is not the case with int, for example.

The sizeof operator can be used to automatically compute the number of elements in an array:

```c
#include <stddef.h>
#include <stdio.h>

static const int values[] = { 1, 2, 48, 681 };
#define ARRAYSIZE(x) (sizeof x/sizeof x[0])

int main (int argc, char *argv[]) 
{
    size_t i;
    for (i = 0; i < ARRAYSIZE(values); i++) 
    {
        printf("%d\n", values[i]);
    }
    return 0;
}
```

There are two cases where this technique does not work.

The first is where the array element has zero size (GCC supports zero-sized structures as a GNU extension).

The second is where the array is in fact a function parameter (see Function Parameters).

## 3.12 类型转换

You can use a type cast to explicitly cause an expression to be of a specified data type.

A type cast consists of a type specifier enclosed in parentheses, followed by an expression.

To ensure proper casting, you should also enclose the expression that follows the type specifier in parentheses.

Here is an example:

```c
float x;
int y = 7;
int z = 3;
x = (float) (y / z);
```

In that example, since y and z are both integers, integer division is performed, and even though x is a floating-point variable, it receives the value 2.

Explicitly casting the result of the division to float does no good, because the computed value of y/z is already 2.

To fix this problem, you need to convert one of the operands to a floating-point type before the division takes place:

```c
float x;
int y = 7;
int z = 3;
x = (y / (float)z);
```

Here, a floating-point value close to 2.333... is assigned to x.

Type casting only works with scalar types (that is, integer, floating-point or pointer types).

Therefore, this is not allowed:

```c
struct fooTag { /* members ... */ };
struct fooTag foo;
unsigned char byteArray[8];

foo = (struct fooType) byteArray; /* Fail! */
```

## 3.13 数组下标

You can access array elements by specifying the name of the array, and the array subscript (or index, or element number) enclosed in brackets.

Here is an example, supposing an integer array called my_array:

```c
my_array[0] = 5;
```

The array subscript expression A[i] is defined as being identical to the expression (*((A)+(i))).

This means that many uses of an array name are equivalent to a pointer expression.

It also means that you cannot subscript an array having the register storage class.

## 3.14 函数调用表达式

A call to any function which returns a value is an expression.

```c
int function(void);
...
a = 10 + function();
```

## 3.15 逗号运算符

You use the comma operator , to separate two (ostensibly related) expressions.

For instance, the first expression might produce a value that is used by the second expression:

```c
x++, y = x * x;
```

More commonly, the comma operator is used in for statements, like this:

```c
/* Using the comma operator in a for statement. */

for (x = 1, y = 10; x <= 10 && y >= 1; x++, y--)
{
  ...
}
```

This lets you conveniently set, monitor, and modify multiple control expressions for the for statement.

A comma is also used to separate function parameters; however, this is not the comma operator in action.

In fact, if the comma operator is used as we have discussed here in a function call, then the compiler will interpret that as calling the function with an extra parameter.

If you want to use the comma operator in a function argument, you need to put parentheses around it.

That's because commas in a function argument list have a different meaning: they separate arguments.

Thus,

```c
foo (x,  y=47,  x,  z);
```

is interpreted as a function call with four arguments, but

```c
foo (x,  (y=47,  x),  z);
```

is a function call with just three arguments.

(The second argument is (y=47, x).)

## 3.16 成员访问表达式

You can use the member access operator . to access the members of a structure or union variable.

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

You can also access the members of a structure or union variable via a pointer by using the indirect member access operator ->.

x->y is equivalent to (*x).y.

```c
struct fish
{
  int length, weight;
};

struct fish salmon;
struct fish *fish_pointer = &salmon;

fish_pointer->length = 3;
fish_pointer->weight = 9;
```

See Pointers.

## 3.17 条件表达式

You use the conditional operator to cause the entire conditional expression to evaluate to either its second or its third operand, based on the truth value of its first operand.

Here's an example:

```c
a ? b : c
```

If expression a is true, then expression b is evaluated and the result is the value of b.

Otherwise, expression c is evaluated and the result is c.

Expressions b and c must be compatible.

That is, they must both be

1. arithmetic types
2. compatible struct or union types
3. pointers to compatible types (one of which might be the NULL pointer)

Alternatively, one operand is a pointer and the other is a void* pointer.

Here is an example

```c
a = (x == 5) ? y : z;
```

Here, if x equals 5, then a will receive the value of y.

Otherwise, a will receive the value of z.

This can be considered a shorthand method for writing a simple if...else statement.

The following example will accomplish the same task as the previous one:

```c
if (x == 5)
    a = y;
else
    a = z;
```

If the first operand of the conditional operator is true, then the third operand is never evaluated.

Similarly, if the first operand is false, then the second operand is never evaluated.

The first operand is always evaluated.

## 3.18 表达式中的语句和声明

As a GNU C extension, you can build an expression using compound statement enclosed in parentheses.

This allows you to included loops, switches, and local variables within an expression.

Recall that a compound statement (also known as a block) is a sequence of statements surrounded by braces.

In this construct, parentheses go around the braces.

Here is an example:

```c
({ int y = function (); int z;
    if (y > 0) z = y;
   else z = - y;
   z; })
```

That is a valid (though slightly more complex than necessary) expression for the absolute value of function ().

The last thing in the compound statement should be an expression followed by a semicolon; the value of this subexpression serves as the value of the entire construct.

(If you use some other kind of statement last within the braces, the construct has type void, and thus effectively no value.)

This feature is especially useful in making macro definitions "safe" (so that they evaluate each operand exactly once).

For example, the "maximum" function is commonly defined as a macro in standard C as follows:

```c
#define max(a,b) ((a) > (b) ? (a) : (b))
```

But this definition computes either a or b twice, with bad results if the operand has side effects.

In GNU C, if you know the type of the operands (here let's assume int), you can define the macro safely as follows:

```c
#define maxint(a,b) \
  ({int _a = (a), _b = (b); _a > _b ? _a : _b; })
```

If you don't know the type of the operand, you can still do this, but you must use typeof expressions or type naming.

Embedded statements are not allowed in constant expressions, such as the value of an enumeration constant, the width of a bit field, or the initial value of a static variable.

## 3.19 运算符优先级

When an expression contains multiple operators, such as a + b * f(), the operators are grouped based on rules of precedence.

For instance, the meaning of that expression is to call the function f with no arguments, multiply the result by b, then add that result to a.

That's what the C rules of operator precedence determine for this expression.

The following is a list of types of expressions, presented in order of highest precedence first.

Sometimes two or more operators have equal precedence; all those operators are applied from left to right unless stated otherwise.

1. Function calls, array subscripting, and membership access operator expressions.

2. Unary operators, including logical negation, bitwise complement, increment, decrement, unary positive, unary negative, indirection operator, address operator, type casting, and sizeof expressions.

   When several unary operators are consecutive, the later ones are nested within the earlier ones: !-x means !(-x).

3. Multiplication, division, and modular division expressions.

4. Addition and subtraction expressions.

5. Bitwise shifting expressions.

6. Greater-than, less-than, greater-than-or-equal-to, and less-than-or-equal-to expressions.

7. Equal-to and not-equal-to expressions.

8. Bitwise AND expressions.

9. Bitwise exclusive OR expressions.

10. Bitwise inclusive OR expressions.

11. Logical AND expressions.

12. Logical OR expressions.

13. Conditional expressions (using ?:).

    When used as subexpressions, these are evaluated right to left.

14. All assignment expressions, including compound assignment.

    When multiple assignment statements appear as subexpressions in a single larger expression, they are evaluated right to left.

15. Comma operator expressions.

The above list is somewhat dry and is apparently straightforward, but it does hide some pitfalls.

Take this example:

```c
foo = *p++;
```

Here p is incremented as a side effect of the expression, but foo takes the value of *(p++) rather than (*p)++, since the unary operators bind right to left.

There are other examples of potential surprises lurking behind the C precedence table.

For this reason if there is the slightest risk of the reader misunderstanding the meaning of the program, you should use parentheses to make your meaning clear.

## 3.20 评估顺序

In C you cannot assume that multiple subexpressions are evaluated in the order that seems natural.

For instance, consider the expression ++a * f().

Does this increment a before or after calling the function f?

The compiler could do it in either order, so you cannot make assumptions.

This manual explains the semantics of the C language in the abstract.

However, an actual compiler translates source code into specific actions in an actual computer, and may re-order operations for the sake of efficiency.

The correspondence between the program you write and the things the computer actually does are specified in terms of side effects and sequence points.

### 3.20.1 副作用

A side effect is one of the following:

1. accessing a volatile object
2. modifying an object
3. modifying a file
4. a call to a function which performs any of the above side effects

These are essentially the externally-visible effects of running a program.

They are called side effects because they are effects of expression evaluation beyond the expression's actual resulting value.

The compiler is allowed to perform the operations of your program in an order different to the order implied by the source of your program, provided that in the end all the necessary side effects actually take place.

The compiler is also allowed to entirely omit some operations; for example it's allowed to skip evaluating part of an expression if it can be certain that the value is not used and evaluating that part of the expression won't produce any needed side effects.

### 3.20.2 序列点

Another requirement on the compiler is that side effects should take place in the correct order.

In order to provide this without over-constraining the compiler, the C89 and C90 standards specify a list of sequence points.

A sequence point is one of the following:

1. a call to a function (after argument evaluation is complete)
2. the end of the left-hand operand of the and operator &&
3. the end of the left-hand operand of the or operator ||
4. the end of the left-hand operand of the comma operator ,
5. the end of the first operand of the ternary operator a ? b : c
6. the end of a full declarator 2
7. the end of an initialisation expression
8. the end of an expression statement (i.e. an expression followed by ;)
9. the end of the controlling expression of an if or switch statement
10. the end of the controlling expression of a while or do statement
11. the end of any of the three controlling expressions of a for statement
12. the end of the expression in a return statement
13. immediately before the return of a library function
14. after the actions associated with an item of formatted I/O (as used for example with the strftime or the printf and scanf famlies of functions).
15. immediately before and after a call to a comparison function (as called for example by qsort)

At a sequence point, all the side effects of previous expression evaluations must be complete, and no side effects of later evaluations may have taken place.

This may seem a little hard to grasp, but there is another way to consider this.

Imagine you wrote a library (some of whose functions are external and perhaps others not) and compiled it, allowing someone else to call one of your functions from their code.

The definitions above ensure that, at the time they call your function, the data they pass in has values which are consistent with the behaviour specified by the abstract machine, and any data returned by your function has a state which is also consistent with the abstract machine.

This includes data accessible via pointers (i.e. not just function parameters and identifiers with external linkage).

The above is a slight simplification, since compilers exist that perform whole-program optimisation at link time.

Importantly however, although they might perform optimisations, the visible side effects of the program must be the same as if they were produced by the abstract machine.

### 3.20.3 序列点约束表达式

The code fragment

```c
i = i + 1;
```

is quite normal and no doubt occurs in many programs.

However, the quite similar code fragment

```c
i = ++i + 1;
```

is a little harder to understand; what is the final value of i?

The C standards (both C89 and C99) both forbid this construct in conforming programs.

Between two sequence points,

1. an object may have its stored value modified at most once by the evaluation of an expression
2. the prior value of the object shall be read only to determine the value to be stored.

The first of these two conditions forbids expressions like foo(x=2, ++x).

The second condition forbids expressions like a[i++] = i.

```c
int x=0; foo(++x, ++x)
```

Not allowed in a conforming program; modifies x twice before argument evaluation is complete.

```c
int x=0; bar((++x,++x))
```

Allowed; the function bar takes one argument (the value 2 is passed here), and there is a sequence point at the comma operator.

```c
*p++ || *p++
```

Allowed; there is a sequence point at ||.

```c
int x = 1, y = x++;
```

Allowed; there is a sequence point after the full declarator of x.

```c
x=2; x++;
```

Allowed; there is a sequence point at the end of the first expression statement.

```c
if (x++ > MAX) x = 0;
```

Allowed; there is a sequence point at the end of the controlling expression of the if3.

```c
(x=y) ? ++x : x--;
```

Allowed; there is a sequence point before the ?, and only one of the two following expressions is evaluated.

```c
int *p=malloc(sizeof(*p)), *q=p; *p=foo(); bar((*p)++,(*q)++);
```

Not allowed; the object at p is being modified twice before the evaluation of the arguments to bar is complete.

The fact that this is done once via p and once via q is irrelevant, since they both point to the same object.

Let's go back to the example we used to introduce the problem of the order of evaluation, ++a * f().

Suppose the code actually looks like this:

```c
static int a = 1;

static int f (void)
{
  a = 100;
  return 3;
}

int foo (void)
{
   return ++a * f();
}
```

Is this code allowed in a standard-conforming program?

Although the expression in foo modifies a twice, this is not a problem.

Let's look at the two possible cases.

The right operand f() is evaluated first

+ Since f returns a value other than void, it must contain a return statement.
+ Therefore, there is a sequence point at the end of the return expression.
+ That comes between the modification to a that f makes and the evaluation of the left operand.

The left operand ++a is evaluated first

+ First, a is incremented.
+ Then the arguments to f are evaluated (there are zero of them).
+ Then there is a sequence point before f is actually called.

So, we see that our program is standard-conforming.

Notice that the above argument does not actually depend on the details of the body of the function f.

It only depends on the function containing something ending in a sequence point – in our example this is a return statement, but an expression statement or a full declarator would do just as well.

However, the result of executing this code depends on the order of evaluation of the operands of *.

If the left-hand operand is evaluated first, foo returns 6.

Otherwise, it returns 303.

The C standard does not specify in which order the operands should be evaluated, and also does not require an implementation either to document the order or even to stick to one order.

The effect of this code is unspecified, meaning that one of several specific things will happen, but the C standards do not say which.

### 3.20.4 序列点和信号传递

Signals are mainly documented in the GNU C Library manual rather than this document, even though the C standards consider the compiler and the C library together to be "the implementation".

When a signal is received, this will happen between sequence points.

Side effects on volatile objects prior to the previous sequence point will have occurred, but other updates may not have occurred yet.

This even applies to straight assignments, such as x=0;, because the code generated for that statement may require more than one instruction, meaning that it can be interrupted part-way through by the delivery of a signal.

The C standard is quite restrictive about what data access can occur within a signal handler.

They can of course use auto variables, but in terms of reading or writing other objects, they must be volatile sig_atomic_t.

The volatile type qualifier ensures that access to the variable in the other parts of the program doesn't span sequence points and the use of the sig_atomic_t type ensures that changes to the variable are atomic with respect to signal delivery.

The POSIX standard also allows a small number of library functions to be called from a signal handler.

These functions are referred to as the set of async-signal-safe functions.

If your program is intended to run on a POSIX system but not on other systems, you can safely call these from your signal handler too.




