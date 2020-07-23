# Go文档

The Go programming language is an open source project to make programmers more productive.

Go is expressive, concise, clean, and efficient.

Its concurrency mechanisms make it easy to write programs that get the most out of multicore and networked machines, while its novel type system enables flexible and modular program construction.

Go compiles quickly to machine code yet has the convenience of garbage collection and the power of run-time reflection.

It's a fast, statically typed, compiled language that feels like a dynamically typed, interpreted language.

## 安装Go

### [入门指南](https://golang.google.cn/doc/install)

Instructions for downloading and installing the Go compilers, tools, and libraries.

## 学习Go

### Go之旅

An interactive introduction to Go in three sections.

The first section covers basic syntax and data structures; the second discusses methods and interfaces; and the third introduces Go's concurrency primitives.

Each section concludes with a few exercises so you can practice what you've learned.

You can install it locally with:

```shell
$ go get golang.org/x/tour
```

This will place the tour binary in your workspace's bin directory.

### [如何编写Go代码](https://golang.google.cn/doc/code.html)

This doc explains how to develop a simple set of Go packages inside a module, and it shows how to use the [go command](https://golang.google.cn/cmd/go/) to build and test packages.

### [编辑器插件和IDE](https://golang.google.cn/doc/editors.html)

A document that summarizes commonly used editor plugins and IDEs with Go support.

### [高效的Go](https://golang.google.cn/doc/effective_go.html)

A document that gives tips for writing clear, idiomatic Go code.

A must read for any new Go programmer.

It augments the tour and the language specification, both of which should be read first.

### [诊断问题](https://golang.google.cn/doc/diagnostics.html)

Summarizes tools and methodologies to diagnose problems in Go programs.

### [常见问题（FAQ）](https://golang.google.cn/doc/faq)

Answers to common questions about Go.

### [Go Wiki](https://golang.google.cn/wiki)

A wiki maintained by the Go community.

### 更多

See the [Learn](https://golang.google.cn/wiki/Learn) page at the [Wiki](https://golang.google.cn/wiki) for more Go learning resources.

## 参考文档

### [包参考文档](https://golang.google.cn/pkg/)

The documentation for the Go standard library.

### [命令参考文档](https://golang.google.cn/doc/cmd)

The documentation for the Go tools.

### [语言规范](https://golang.google.cn/ref/spec)

The official Go Language specification.

### [Go内存模型](https://golang.google.cn/ref/mem)

A document that specifies the conditions under which reads of a variable in one goroutine can be guaranteed to observe values produced by writes to the same variable in a different goroutine.

### [版本历史](https://golang.google.cn/doc/devel/release.html)

A summary of the changes between Go releases.

## 文章

### 代码走查

Guided tours of Go programs.

+ [First-Class Functions in Go](https://golang.google.cn/doc/codewalk/functions)
+ [Generating arbitrary text: a Markov chain algorithm](https://golang.google.cn/doc/codewalk/markov)
+ [Share Memory by Communicating](https://golang.google.cn/doc/codewalk/sharemem)
+ [Writing Web Applications](https://golang.google.cn/doc/articles/wiki/) - building a simple web application.

### 工具

+ [About the Go command](https://golang.google.cn/doc/articles/go_command.html) - why we wrote it, what it is, what it's not, and how to use it.
+ [Debugging Go Code with GDB](https://golang.google.cn/doc/gdb)
+ [Data Race Detector](https://golang.google.cn/doc/articles/race_detector.html) - a manual for the data race detector.
+ [A Quick Guide to Go's Assembler](https://golang.google.cn/doc/asm) - an introduction to the assembler used by Go.

### 更多

See the [Articles page](https://golang.google.cn/wiki/Articles) at the [Wiki](https://golang.google.cn/wiki) for more Go articles.

## 非英文文档

See the [NonEnglish](https://golang.google.cn/wiki/NonEnglish) page at the [Wiki](https://golang.google.cn/wiki) for localized documentation.




