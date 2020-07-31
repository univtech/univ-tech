# Effective Go



## Introduction



### Examples



## 格式



## 注释

Go provides C-style /* */ block comments and C++-style // line comments.

Line comments are the norm; block comments appear mostly as package comments, but are useful within an expression or to disable large swaths of code.

The program—and web server—godoc processes Go source files to extract documentation about the contents of the package.

Comments that appear before top-level declarations, with no intervening newlines, are extracted along with the declaration to serve as explanatory text for the item.

The nature and style of these comments determines the quality of the documentation godoc produces.

Every package should have a package comment, a block comment preceding the package clause.

For multi-file packages, the package comment only needs to be present in one file, and any one will do.

The package comment should introduce the package and provide information relevant to the package as a whole.

It will appear first on the godoc page and should set up the detailed documentation that follows.

```go
/*
Package regexp implements a simple library for regular expressions.

The syntax of the regular expressions accepted is:

    regexp:
        concatenation { '|' concatenation }
    concatenation:
        { closure }
    closure:
        term [ '*' | '+' | '?' ]
    term:
        '^'
        '$'
        '.'
        character
        '[' [ '^' ] character-ranges ']'
        '(' regexp ')'
*/
package regexp
```

If the package is simple, the package comment can be brief.

```go
// Package path implements utility routines for
// manipulating slash-separated filename paths.
```











## Names



### Package names



### Getters



### Interface names



### MixedCaps



## Semicolons



## Control structures



### If

简单的if：

```go
if x > 0 {
    return y
}
```

包含初始化语句的if：

```go
if err := file.Chmod(0664); err != nil {
    log.Print(err)
    return err
}
```










### Redeclaration and reassignment



### for循环

Go的for循环统一了for和while，没有do while。

```go
// 类似于C语言的for
for init; condition; post { }

// 类似于C语言的while
for condition { }

// 类似于C语言的for(;;)
for { }
```

```go
sum := 0
for i := 0; i < 10; i++ {
    sum += i
}
```

循环string、array、slice、map，或者读取channel时，可以使用range子句：
```go
for key, value := range oldMap {
    newMap[key] = value
}
```

获取key或者index：
```go
for key := range m {
    if key.expired() {
        delete(m, key)
    }
}
```

获取value：
```go
sum := 0
for _, value := range array {
    sum += value
}
```


Go没有逗号操作符，++和--是语句而不是表达式。要在for循环中使用多个变量，需要采用平行赋值的方式。

```go
// 反转a
for i, j := 0, len(a)-1; i < j; i, j = i+1, j-1 {
    a[i], a[j] = a[j], a[i]
}
```





































### switch



### Type switch



## function



### 多个返回值



### 命名结果参数

func nextInt(b []byte, pos int) (value, nextPos int) {


func ReadFull(r Reader, buf []byte) (n int, err error) {
    for len(buf) > 0 && err == nil {
        var nr int
        nr, err = r.Read(buf)
        n += nr
        buf = buf[nr:]
    }
    return
}




### defer

// Contents returns the file's contents as a string.
func Contents(filename string) (string, error) {
    f, err := os.Open(filename)
    if err != nil {
        return "", err
    }
    defer f.Close()  // f.Close will run when we're finished.

    var result []byte
    buf := make([]byte, 100)
    for {
        n, err := f.Read(buf[0:])
        result = append(result, buf[0:n]...) // append is discussed later.
        if err != nil {
            if err == io.EOF {
                break
            }
            return "", err  // f will be closed if we return here.
        }
    }
    return string(result), nil // f will be closed if we return here.
}


## 数据



### Allocation with new



### Constructors and composite literals



### Allocation with make



### Arrays



### Slices



### Two-dimensional slices



### Maps



### Printing



### Append



## Initialization



### Constants



### Variables



### The init function



## Methods



### Pointers vs. Values



## Interfaces and other types



### Interfaces



### Conversions



### Interface conversions and type assertions



### Generality



### Interfaces and methods



## The blank identifier



### The blank identifier in multiple assignment



### Unused imports and variables



### Import for side effect



### Interface checks



## Embedding



## Concurrency



### Share by communicating



### Goroutines



### Channels



### Channels of channels



### Parallelization



### A leaky buffer



## Errors



### Panic



### Recover



## A web server
