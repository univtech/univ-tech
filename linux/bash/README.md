# Bash参考手册

https://www.gnu.org/software/bash/manual/

https://www.gnu.org/software/bash/manual/bash.html

https://www.gnu.org/software/bash/manual/html_node/index.html

https://www.gnu.org/software/bash/manual/bash.pdf

## Bash特性

This text is a brief description of the features that are present in the Bash shell (version 5.0, 12 May 2019).

The Bash home page is http://www.gnu.org/software/bash/.

This is Edition 5.0, last updated 12 May 2019, of The GNU Bash Reference Manual, for Bash, Version 5.0.

Bash contains features that appear in other popular shells, and some features that only appear in Bash.

Some of the shells that Bash has borrowed concepts from are the Bourne Shell (sh), the Korn Shell (ksh), and the C-shell (csh and its successor, tcsh).

The following menu breaks the features up into categories, noting which features were inspired by other shells and which are specific to Bash.

This manual is meant as a brief introduction to features found in Bash.

The Bash manual page should be used as the definitive reference on shell behavior.

## 目录

+ [前言](preface.md#前言)





1 简介
1.1 什么是Bash
1.2 什么是Shell
2 定义
3 Shell基本特性
3.1 Shell语法
3.1.1 Shell操作
3.1.2 引用
3.1.2.1 转义字符
3.1.2.2 单引号
3.1.2.3 双引号
3.1.2.4 ANSI-C引用
3.1.2.5 特定区域的翻译
3.1.3 注释
3.2 Shell命令
3.2.1 简单命令
3.2.2 管道
3.2.3 命令列表
3.2.4 组合命令
3.2.4.1 循环结构
3.2.4.2 条件结构
3.2.4.3 分组命令
3.2.5 协进程
3.2.6 GNU并行作业
3.3 Shell函数
3.4 Shell参数
3.4.1 位置参数
3.4.2 特殊参数
3.5 Shell扩展
3.5.1 花括号扩展
3.5.2 波浪线扩展
3.5.3 Shell参数扩展
3.5.4 命令替换
3.5.5 算术扩展
3.5.6 进程替换
3.5.7 单词分割
3.5.8 文件名扩展
3.5.8.1 模式匹配
3.5.9 引用移除
3.6 重定向
3.6.1 重定向输入
3.6.2 重定向输出
3.6.3 追加重定向输出
3.6.4 重定向标准输出和标准错误
3.6.5 追加标准输出和标准错误
3.6.6 当前文档
3.6.7 当前字符串
3.6.8 复制文件描述符
3.6.9 移动文件描述符
3.6.10 打开读写的文件描述符
3.7 执行命令
3.7.1 简单命令扩展
3.7.2 命令搜索和执行
3.7.3 命令执行环境
3.7.4 环境变量
3.7.5 退出状态
3.7.6 信号
3.8 Shell脚本
4 Shell内置命令
4.1 Bourne Shell内置命令
4.2 Bash内置命令
4.3 修改Shell行为
4.3.1 The Set Builtin
4.3.2 The Shopt Builtin
4.4 Special Builtins
5 Shell Variables
5.1 Bourne Shell Variables
5.2 Bash Variables
6 Bash Features
6.1 Invoking Bash
6.2 Bash Startup Files
6.3 Interactive Shells
6.3.1 What is an Interactive Shell?
6.3.2 Is this Shell Interactive?
6.3.3 Interactive Shell Behavior
6.4 Bash Conditional Expressions
6.5 Shell Arithmetic
6.6 Aliases
6.7 Arrays
6.8 The Directory Stack
6.8.1 Directory Stack Builtins
6.9 Controlling the Prompt
6.10 The Restricted Shell
6.11 Bash POSIX Mode
7 Job Control
7.1 Job Control Basics
7.2 Job Control Builtins
7.3 Job Control Variables
8 Command Line Editing
8.1 Introduction to Line Editing
8.2 Readline Interaction
8.2.1 Readline Bare Essentials
8.2.2 Readline Movement Commands
8.2.3 Readline Killing Commands
8.2.4 Readline Arguments
8.2.5 Searching for Commands in the History
8.3 Readline Init File
8.3.1 Readline Init File Syntax
8.3.2 Conditional Init Constructs
8.3.3 Sample Init File
8.4 Bindable Readline Commands
8.4.1 Commands For Moving
8.4.2 Commands For Manipulating The History
8.4.3 Commands For Changing Text
8.4.4 Killing And Yanking
8.4.5 Specifying Numeric Arguments
8.4.6 Letting Readline Type For You
8.4.7 Keyboard Macros
8.4.8 Some Miscellaneous Commands
8.5 Readline vi Mode
8.6 Programmable Completion
8.7 Programmable Completion Builtins
8.8 A Programmable Completion Example
9 Using History Interactively
9.1 Bash History Facilities
9.2 Bash History Builtins
9.3 History Expansion
9.3.1 Event Designators
9.3.2 Word Designators
9.3.3 Modifiers
10 Installing Bash
10.1 Basic Installation
10.2 Compilers and Options
10.3 Compiling For Multiple Architectures
10.4 Installation Names
10.5 Specifying the System Type
10.6 Sharing Defaults
10.7 Operation Controls
10.8 Optional Features
Appendix A Reporting Bugs
Appendix B Major Differences From The Bourne Shell
B.1 Implementation Differences From The SVR4.2 Shell
































