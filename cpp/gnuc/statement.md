# 4 语句

You write statements to cause actions and to control flow within your programs.

You can also write statements that do not do anything at all, or do things that are uselessly trivial.

## 4.1 标签

You can use labels to identify a section of source code for use with a later goto (see The goto Statement).

A label consists of an identifier (such as those used for variable names) followed by a colon.

Here is an example:

```c
treet:
```

You should be aware that label names do not interfere with other identifier names:

```c
int treet = 5;    /* treet the variable. */
treet:            /* treet the label. */
```

The ISO C standard mandates that a label must be followed by at least one statement, possibly a null statement (see The Null Statement).

GCC will compile code that does not meet this requirement, but be aware that if you violate it, your code may have portability issues.

## 4.2 表达式语句

You can turn any expression into a statement by adding a semicolon to the end of the expression.

Here are some examples:

```c
5;
2 + 2;
10 >= 9;
```

In each of those, all that happens is that each expression is evaluated.

However, they are useless because they do not store a value anywhere, nor do they actually do anything, other than the evaluation itself.

The compiler is free to ignore such statements.

Expression statements are only useful when they have some kind of side effect, such as storing a value, calling a function, or (this is esoteric) causing a fault in the program.

Here are some more useful examples:

```c
x++;
y = x + 25;
puts ("Hello, user!");
*cucumber;
```

The last of those statements, *cucumber;, could potentially cause a fault in the program if the value of cucumber is both not a valid pointer and has been declared as volatile.

## 4.3 if语句

You can use the if statement to conditionally execute part of your program, based on the truth value of a given expression.

Here is the general form of an if statement:

```c
if (test)
  then-statement
else
  else-statement
```

If test evaluates to true, then then-statement is executed and else-statement is not.

If test evaluates to false, then else-statement is executed and then-statement is not.

The else clause is optional.

Here is an actual example:

```c
if (x == 10)
  puts ("x is 10");
```

If x == 10 evaluates to true, then the statement puts ("x is 10"); is executed.

If x == 10 evaluates to false, then the statement puts ("x is 10"); is not executed.

Here is an example using else:

```c
if (x == 10)
  puts ("x is 10");
else
  puts ("x is not 10");
```

You can use a series of if statements to test for multiple conditions:

```c
if (x == 1)
  puts ("x is 1");
else if (x == 2)
  puts ("x is 2");
else if (x == 3)
  puts ("x is 3");
else
  puts ("x is something else");
```

This function calculates and displays the date of Easter for the given year y:

```c
void
easterDate (int y)
{
  int n = 0;
  int g = (y % 19) + 1;
  int c = (y / 100) + 1;
  int x = ((3 * c) / 4) - 12;
  int z = (((8 * c) + 5) / 25) - 5;
  int d = ((5 * y) / 4) - x - 10;
  int e = ((11 * g) + 20 + z - x) % 30;

  if (((e == 25) && (g > 11)) || (e == 24))
    e++;

  n = 44 - e;

  if (n < 21)
    n += 30;

  n = n + 7 - ((d + n) % 7);

  if (n > 31)
    printf ("Easter: %d April %d", n - 31, y);
  else
    printf ("Easter: %d March %d", n, y);
}
```

## 4.4 switch语句

You can use the switch statement to compare one expression with others, and then execute a series of sub-statements based on the result of the comparisons.

Here is the general form of a switch statement:

```c
switch (test)
  {
    case compare-1:
      if-equal-statement-1
    case compare-2:
      if-equal-statement-2
    ...
    default:
      default-statement
  }
```

The switch statement compares test to each of the compare expressions, until it finds one that is equal to test.

Then, the statements following the successful case are executed.

All of the expressions compared must be of an integer type, and the compare-N expressions must be of a constant integer type (e.g., a literal integer or an expression built of literal integers).

Optionally, you can specify a default case.

If test doesn't match any of the specific cases listed prior to the default case, then the statements for the default case are executed.

Traditionally, the default case is put after the specific cases, but that isn't required.

```c
switch (x)
  {
    case 0:
      puts ("x is 0");
      break;
    case 1:
      puts ("x is 1");
      break;
    default:
      puts ("x is something else");
      break;
  }
```

Notice the usage of the break statement in each of the cases.

This is because, once a matching case is found, not only are its statements executed, but so are the statements for all following cases:

```c
int x = 0;
switch (x)
  {
    case 0:
      puts ("x is 0");
    case 1:
      puts ("x is 1");
    default:
      puts ("x is something else");
  }
```

The output of that example is:

```c
x is 0
x is 1
x is something else
```

This is often not desired.

Including a break statement at the end of each case redirects program flow to after the switch statement.

As a GNU C extension, you can also specify a range of consecutive integer values in a single case label, like this:

```c
case low ... high:
```

This has the same effect as the corresponding number of individual case labels, one for each integer value from low to high, inclusive.

This feature is especially useful for ranges of ASCII character codes:

```c
case 'A' ... 'Z':
```

Be careful to include spaces around the ...; otherwise it may be parsed incorrectly when you use it with integer values.

For example, write this:

```c
case 1 ... 5:
```

instead of this:

```c
case 1...5:
```

It is common to use a switch statement to handle various possible values of errno.

In this case a portable program should watch out for the possibility that two macros for errno values in fact have the same value, for example EWOULDBLOCK and EAGAIN.

## 4.5 while语句

The while statement is a loop statement with an exit test at the beginning of the loop.

Here is the general form of the while statement:

```c
while (test)
  statement
```

The while statement first evaluates test.

If test evaluates to true, statement is executed, and then test is evaluated again.

statement continues to execute repeatedly as long as test is true after each execution of statement.

This example prints the integers from zero through nine:

```c
int counter = 0;
while (counter < 10)
  printf ("%d ", counter++);
```

A break statement can also cause a while loop to exit.

## 4.6 do语句

The do statement is a loop statement with an exit test at the end of the loop.

Here is the general form of the do statement:

```c
do
  statement
while (test);
```

The do statement first executes statement.

After that, it evaluates test.

If test is true, then statement is executed again.

statement continues to execute repeatedly as long as test is true after each execution of statement.

This example also prints the integers from zero through nine:

```c
int x = 0;
do
  printf ("%d ", x++);
while (x < 10);
```

A break statement can also cause a do loop to exit.

## 4.7 for语句

The for statement is a loop statement whose structure allows easy variable initialization, expression testing, and variable modification.

It is very convenient for making counter-controlled loops.

Here is the general form of the for statement:

```c
for (initialize; test; step)
  statement
```

The for statement first evaluates the expression initialize.

Then it evaluates the expression test.

If test is false, then the loop ends and program control resumes after statement.

Otherwise, if test is true, then statement is executed.

Finally, step is evaluated, and the next iteration of the loop begins with evaluating test again.

Most often, initialize assigns values to one or more variables, which are generally used as counters, test compares those variables to a predefined expression, and step modifies those variable's values.

Here is another example that prints the integers from zero through nine:

```c
int x;
for (x = 0; x < 10; x++)
  printf ("%d ", x);
```

First, it evaluates initialize, which assigns x the value 0.

Then, as long as x is less than 10, the value of x is printed (in the body of the loop).

Then x is incremented in the step clause and the test re-evaluated.

All three of the expressions in a for statement are optional, and any combination of the three is valid.

Since the first expression is evaluated only once, it is perhaps the most commonly omitted expression.

You could also write the above example as:

```c
int x = 1;
for (; x <= 10; x++)
  printf ("%d ", x);
```

In this example, x receives its value prior to the beginning of the for statement.

If you leave out the test expression, then the for statement is an infinite loop (unless you put a break or goto statement somewhere in statement).

This is like using 1 as test; it is never false.

This for statement starts printing numbers at 1 and then continues indefinitely, always printing x incremented by 1:

```c
for (x = 1; ; x++)
  printf ("%d ", x);
```

If you leave out the step expression, then no progress is made toward completing the loop—at least not as is normally expected with a for statement.

This example prints the number 1 over and over, indefinitely:

```c
for (x = 1; x <= 10;)
  printf ("%d ", x);
```

Perhaps confusingly, you cannot use the comma operator (see The Comma Operator) for monitoring multiple variables in a for statement, because as usual the comma operator discards the result of its left operand.

This loop:

```c
int x, y;
for (x = 1, y = 10; x <= 10, y >= 1; x+=2, y--)
  printf ("%d %d\n", x, y);
```

Outputs:

```c
1 10
3 9
5 8
7 7
9 6
11 5
13 4
15 3
17 2
19 1
```

If you need to test two conditions, you will need to use the && operator:

```c
int x, y;
for (x = 1, y = 10; x <= 10 && y >= 1; x+=2, y--)
  printf ("%d %d\n", x, y);
```

A break statement can also cause a for loop to exit.

Here is an example of a function that computes the summation of squares, given a starting integer to square and an ending integer to square:

```c
int
sum_of_squares (int start, int end)
{
  int i, sum = 0;
  for (i = start; i <= end; i++)
    sum += i * i;
  return sum;
}
```

## 4.8 块

A block is a set of zero or more statements enclosed in braces.

Blocks are also known as compound statements.

Often, a block is used as the body of an if statement or a loop statement, to group statements together.

```c
for (x = 1; x <= 10; x++)
  {
    printf ("x is %d\n", x);
    
    if ((x % 2) == 0)
      printf ("%d is even\n", x);
    else
      printf ("%d is odd\n", x);
  }
```

You can also put blocks inside other blocks:

```c
for (x = 1; x <= 10; x++)
  {
    if ((x % 2) == 0)
      {
        printf ("x is %d\n", x);
        printf ("%d is even\n", x);
      }
    else
      {
        printf ("x is %d\n", x);
        printf ("%d is odd\n", x);
      }
  }
```

You can declare variables inside a block; such variables are local to that block.

In C89, declarations must occur before other statements, and so sometimes it is useful to introduce a block simply for this purpose:

```c
{
  int x = 5;
  printf ("%d\n", x);
}
printf ("%d\n", x);    /* Compilation error! x exists only in the preceding block. */
```

## 4.9 null语句

The null statement is merely a semicolon alone.

```c
;
```

A null statement does not do anything.

It does not store a value anywhere.

It does not cause time to pass during the execution of your program.

Most often, a null statement is used as the body of a loop statement, or as one or more of the expressions in a for statement.

Here is an example of a for statement that uses the null statement as the body of the loop (and also calculates the integer square root of n, just for fun):

```c
for (i = 1; i*i < n; i++)
  ;
```

Here is another example that uses the null statement as the body of a for loop and also produces output:

```c
for (x = 1; x <= 5; printf ("x is now %d\n", x), x++)
  ;
```

A null statement is also sometimes used to follow a label that would otherwise be the last thing in a block.

## 4.10 goto语句

You can use the goto statement to unconditionally jump to a different place in the program.

Here is the general form of a goto statement:

```c
goto label;
```

You have to specify a label to jump to; when the goto statement is executed, program control jumps to that label.

See Labels.

Here is an example:

```c
goto end_of_program;
...
end_of_program:
```

The label can be anywhere in the same function as the goto statement that jumps to it, but a goto statement cannot jump to a label in a different function.

You can use goto statements to simulate loop statements, but we do not recommend it—it makes the program harder to read, and GCC cannot optimize it as well.

You should use for, while, and do statements instead of goto statements, when possible.

As an extension, GCC allows a goto statement to jump to an address specified by a void* variable.

To make this work, you also need to take the address of a label by using the unary operator && (not &).

Here is a contrived example:

```c
enum Play { ROCK=0, PAPER=1, SCISSORS=2 };
enum Result { WIN, LOSE, DRAW };

static enum Result turn (void) 
{
  const void * const jumptable[] = {&&rock, &&paper, &&scissors};
  enum Play opp;                                                     /* opponent's play */
  goto *jumptable[select_option (&opp)];
 rock:
  return opp == ROCK ? DRAW : (opp == PAPER ? LOSE : WIN);
 paper:
  return opp == ROCK ? WIN  : (opp == PAPER ? DRAW : LOSE);
 scissors:
  return opp == ROCK ? LOSE : (opp == PAPER ? WIN  : DRAW);
}
```

## 4.11 break语句

You can use the break statement to terminate a while, do, for, or switch statement.

Here is an example:

```c
int x;
for (x = 1; x <= 10; x++)
  {
    if (x == 8)
      break;
    else
      printf ("%d ", x);
  }
```

That example prints numbers from 1 to 7.

When x is incremented to 8, x == 8 is true, so the break statement is executed, terminating the for loop prematurely.

If you put a break statement inside of a loop or switch statement which itself is inside of a loop or switch statement, the break only terminates the innermost loop or switch statement.

## 4.12 continue语句

You can use the continue statement in loops to terminate an iteration of the loop and begin the next iteration.

Here is an example:

```c
for (x = 0; x < 100; x++)
  {
    if (x % 2 == 0)
      continue;
    else
      sum_of_odd_numbers + = x;
  }
```

If you put a continue statement inside a loop which itself is inside a loop, then it affects only the innermost loop.

## 4.13 return语句

You can use the return statement to end the execution of a function and return program control to the function that called it.

Here is the general form of the return statement:

```c
return return-value;
```

return-value is an optional expression to return.

If the function's return type is void, then it is invalid to return an expression.

You can, however, use the return statement without a return value.

If the function's return type is not the same as the type of return-value, and automatic type conversion cannot be performed, then returning return-value is invalid.

If the function's return type is not void and no return value is specified, then the return statement is valid unless the function is called in a context that requires a return value.

For example:

```c
x = cosine (y);
```

In that case, the function cosine was called in a context that required a return value, so the value could be assigned to x.

Even in contexts where a return value is not required, it is a bad idea for a non-void function to omit the return value.

With GCC, you can use the command line option -Wreturn-type to issue a warning if you omit the return value in such functions.

Here are some examples of using the return statement, in both a void and non-void function:

```c
void
print_plus_five (int x)
{
  printf ("%d ", x + 5);
  return;
}
int
square_value (int x)
{
  return x * x;
}
```

## 4.14 typedef语句

You can use the typedef statement to create new names for data types.

Here is the general form of the typedef statement:

```c
typedef old-type-name new-type-name
```

old-type-name is the existing name for the type, and may consist of more than one token (e.g., unsigned long int).

new-type-name is the resulting new name for the type, and must be a single identifier.

Creating this new name for the type does not cause the old name to cease to exist.

Here are some examples:

```c
typedef unsigned char byte_type;
typedef double real_number_type;
```

In the case of custom data types, you can use typedef to make a new name for the type while defining the type:

```c
typedef struct fish
{
  float weight;
  float length;
  float probability_of_being_caught;
} fish_type;
```

To make a type definition of an array, you first provide the type of the element, and then establish the number of elements at the end of the type definition:

```c
typedef char array_of_bytes [5];
array_of_bytes five_bytes = {0, 1, 2, 3, 4};
```

When selecting names for types, you should avoid ending your type names with a _t suffix.

The compiler will allow you to do this, but the POSIX standard reserves use of the _t suffix for standard library type names.




