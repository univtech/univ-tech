# 附录A 溢出

[This appendix, written principally by Paul Eggert, is from the GNU Autoconf manual. We thought that it would be helpful to include here. –TJR]

In practice many portable C programs assume that signed integer overflow wraps around reliably using two's complement arithmetic.

Yet the C standard says that program behavior is undefined on overflow, and in a few cases C programs do not work on some modern implementations because their overflows do not wrap around as their authors expected.

Conversely, in signed integer remainder, the C standard requires overflow behavior that is commonly not implemented.

## A.1 整数溢出的基础知识

In languages like C, unsigned integer overflow reliably wraps around; e.g., UINT_MAX + 1 yields zero.

This is guaranteed by the C standard and is portable in practice, unless you specify aggressive, nonstandard optimization options suitable only for special applications.

In contrast, the C standard says that signed integer overflow leads to undefined behavior where a program can do anything, including dumping core or overrunning a buffer.

The misbehavior can even precede the overflow.

Such an overflow can occur during addition, subtraction, multiplication, division, and left shift.

Despite this requirement of the standard, many C programs assume that signed integer overflow silently wraps around modulo a power of two, using two's complement arithmetic, so long as you cast the resulting value to a signed integer type or store it into a signed integer variable.

If you use conservative optimization flags, such programs are generally portable to the vast majority of modern platforms, with a few exceptions discussed later.

For historical reasons the C standard also allows implementations with one's complement or signed magnitude arithmetic, but it is safe to assume two's complement nowadays.

Also, overflow can occur when converting an out-of-range value to a signed integer type.

Here a standard implementation must define what happens, but this might include raising an exception.

In practice all known implementations support silent wraparound in this case, so you need not worry about other possibilities.

## A.2 假设环绕溢出的代码示例

There has long been a tension between what the C standard requires for signed integer overflow, and what C programs commonly assume.

The standard allows aggressive optimizations based on assumptions that overflow never occurs, but many practical C programs rely on overflow wrapping around.

These programs do not conform to the standard, but they commonly work in practice because compiler writers are understandably reluctant to implement optimizations that would break many programs, unless perhaps a user specifies aggressive optimization.

The C Standard says that if a program has signed integer overflow its behavior is undefined, and the undefined behavior can even precede the overflow.

To take an extreme example:

```c
if (password == expected_password)
  allow_superuser_privileges ();
else if (counter++ == INT_MAX)
  abort ();
else
  printf ("%d password mismatches\n", counter);
```

If the int variable counter equals INT_MAX, counter++ must overflow and the behavior is undefined, so the C standard allows the compiler to optimize away the test against INT_MAX and the abort call.

Worse, if an earlier bug in the program lets the compiler deduce that counter == INT_MAX or that counter previously overflowed, the C standard allows the compiler to optimize away the password test and generate code that allows superuser privileges unconditionally.

Despite this requirement by the standard, it has long been common for C code to assume wraparound arithmetic after signed overflow, and all known practical C implementations support some C idioms that assume wraparound signed arithmetic, even if the idioms do not conform strictly to the standard.

If your code looks like the following examples it will almost surely work with real-world compilers.

Here is an example derived from the 7th Edition Unix implementation of atoi (1979-01-10):

```c
char *p;
int f, n;
...
while (*p >= '0' && *p <= '9')
  n = n * 10 + *p++ - '0';
return (f ? -n : n);
```

Even if the input string is in range, on most modern machines this has signed overflow when computing the most negative integer (the -n overflows) or a value near an extreme integer (the first + overflows).

Here is another example, derived from the 7th Edition implementation of rand (1979-01-10).

Here the programmer expects both multiplication and addition to wrap on overflow:

```c
static long int randx = 1;
...
randx = randx * 1103515245 + 12345;
return (randx >> 16) & 077777;
```

In the following example, derived from the GNU C Library 2.5 implementation of mktime (2006-09-09), the code assumes wraparound arithmetic in + to detect signed overflow:

```c
time_t t, t1, t2;
int sec_requested, sec_adjustment;
...
t1 = t + sec_requested;
t2 = t1 + sec_adjustment;
if (((t1 < t) != (sec_requested < 0))
    || ((t2 < t1) != (sec_adjustment < 0)))
  return -1;
```

If your code looks like these examples, it is probably safe even though it does not strictly conform to the C standard.

This might lead one to believe that one can generally assume wraparound on overflow, but that is not always true, as can be seen in the next section.

## A.3 打破环绕算法的优化

Compilers sometimes generate code that is incompatible with wraparound integer arithmetic.

A simple example is an algebraic simplification: a compiler might translate (i * 2000) / 1000 to i * 2 because it assumes that i * 2000 does not overflow.

The translation is not equivalent to the original when overflow occurs: e.g., in the typical case of 32-bit signed two's complement wraparound int, if i has type int and value 1073742, the original expression returns -2147483 but the optimized version returns the mathematically correct value 2147484.

More subtly, loop induction optimizations often exploit the undefined behavior of signed overflow.

Consider the following contrived function sumc:

```c
int
sumc (int lo, int hi)
{
  int sum = 0;
  int i;
  for (i = lo; i <= hi; i++)
    sum ^= i * 53;
  return sum;
}
```

To avoid multiplying by 53 each time through the loop, an optimizing compiler might internally transform sumc to the equivalent of the following:

```c
int
transformed_sumc (int lo, int hi)
{
  int sum = 0;
  int hic = hi * 53;
  int ic;
  for (ic = lo * 53; ic <= hic; ic += 53)
    sum ^= ic;
  return sum;
}
```

This transformation is allowed by the C standard, but it is invalid for wraparound arithmetic when INT_MAX / 53 < hi, because then the overflow in computing expressions like hi * 53 can cause the expression i <= hi to yield a different value from the transformed expression ic <= hic.

For this reason, compilers that use loop induction and similar techniques often do not support reliable wraparound arithmetic when a loop induction variable like ic is involved.

Since loop induction variables are generated by the compiler, and are not visible in the source code, it is not always trivial to say whether the problem affects your code.

Hardly any code actually depends on wraparound arithmetic in cases like these, so in practice these loop induction optimizations are almost always useful.

However, edge cases in this area can cause problems.

For example:

```c
int j;
for (j = 1; 0 < j; j *= 2)
  test (j);
```

Here, the loop attempts to iterate through all powers of 2 that int can represent, but the C standard allows a compiler to optimize away the comparison and generate an infinite loop, under the argument that behavior is undefined on overflow.

As of this writing this optimization is not done by any production version of GCC with -O2, but it might be performed by other compilers, or by more aggressive GCC optimization options, and the GCC developers have not decided whether it will continue to work with GCC and -O2.

## A.4 对有符号溢出问题的实用建议

Ideally the safest approach is to avoid signed integer overflow entirely.

For example, instead of multiplying two signed integers, you can convert them to unsigned integers, multiply the unsigned values, then test whether the result is in signed range.

Rewriting code in this way will be inconvenient, though, particularly if the signed values might be negative.

Also, it may hurt performance.

Using unsigned arithmetic to check for overflow is particularly painful to do portably and efficiently when dealing with an integer type like uid_t whose width and signedness vary from platform to platform.

Furthermore, many C applications pervasively assume wraparound behavior and typically it is not easy to find and remove all these assumptions.

Hence it is often useful to maintain nonstandard code that assumes wraparound on overflow, instead of rewriting the code.

The rest of this section attempts to give practical advice for this situation.

If your code wants to detect signed integer overflow in sum = a + b, it is generally safe to use an expression like (sum < a) != (b < 0).

If your code uses a signed loop index, make sure that the index cannot overflow, along with all signed expressions derived from the index.

Here is a contrived example of problematic code with two instances of overflow.

```c
for (i = INT_MAX - 10; i <= INT_MAX; i++)
  if (i + 1 < 0)
    {
      report_overflow ();
      break;
    }
```

Because of the two overflows, a compiler might optimize away or transform the two comparisons in a way that is incompatible with the wraparound assumption.

If your code uses an expression like (i * 2000) / 1000 and you actually want the multiplication to wrap around on overflow, use unsigned arithmetic to do it, e.g., ((int) (i * 2000u)) / 1000.

If your code assumes wraparound behavior and you want to insulate it against any GCC optimizations that would fail to support that behavior, you should use GCC's -fwrapv option, which causes signed overflow to wrap around reliably (except for division and remainder, as discussed in the next section).

If you need to port to platforms where signed integer overflow does not reliably wrap around (e.g., due to hardware overflow checking, or to highly aggressive optimizations), you should consider debugging with GCC's -ftrapv option, which causes signed overflow to raise an exception.

## A.5 有符号整数除法和整数溢出

Overflow in signed integer division is not always harmless: for example, on CPUs of the i386 family, dividing INT_MIN by -1 yields a SIGFPE signal which by default terminates the program.

Worse, taking the remainder of these two values typically yields the same signal on these CPUs, even though the C standard requires INT_MIN % -1 to yield zero because the expression does not overflow.




