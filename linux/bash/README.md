# Bash参考手册

https://www.gnu.org/software/bash/manual/

https://www.gnu.org/software/bash/manual/bash.html

https://www.gnu.org/software/bash/manual/html_node/index.html

https://www.gnu.org/software/bash/manual/bash.pdf

## Bash特性

This text is a brief description of the features that are present in the Bash shell (version 5.0, 12 May 2019). The Bash home page is http://www.gnu.org/software/bash/.

This is Edition 5.0, last updated 12 May 2019, of The GNU Bash Reference Manual, for Bash, Version 5.0.

Bash contains features that appear in other popular shells, and some features that only appear in Bash. Some of the shells that Bash has borrowed concepts from are the Bourne Shell (sh), the Korn Shell (ksh), and the C-shell (csh and its successor, tcsh). The following menu breaks the features up into categories, noting which features were inspired by other shells and which are specific to Bash.

This manual is meant as a brief introduction to features found in Bash. The Bash manual page should be used as the definitive reference on shell behavior.

## 目录

+ [前言](preface.md#前言)





1 Introduction
1.1 What is Bash?
1.2 What is a shell?
2 Definitions
3 Basic Shell Features
3.1 Shell Syntax
3.1.1 Shell Operation
3.1.2 Quoting
3.1.2.1 Escape Character
3.1.2.2 Single Quotes
3.1.2.3 Double Quotes
3.1.2.4 ANSI-C Quoting
3.1.2.5 Locale-Specific Translation
3.1.3 Comments
3.2 Shell Commands
3.2.1 Simple Commands
3.2.2 Pipelines
3.2.3 Lists of Commands
3.2.4 Compound Commands
3.2.4.1 Looping Constructs
3.2.4.2 Conditional Constructs
3.2.4.3 Grouping Commands
3.2.5 Coprocesses
3.2.6 GNU Parallel
3.3 Shell Functions
3.4 Shell Parameters
3.4.1 Positional Parameters
3.4.2 Special Parameters
3.5 Shell Expansions
3.5.1 Brace Expansion
3.5.2 Tilde Expansion
3.5.3 Shell Parameter Expansion
3.5.4 Command Substitution
3.5.5 Arithmetic Expansion
3.5.6 Process Substitution
3.5.7 Word Splitting
3.5.8 Filename Expansion
3.5.8.1 Pattern Matching
3.5.9 Quote Removal
3.6 Redirections
3.6.1 Redirecting Input
3.6.2 Redirecting Output
3.6.3 Appending Redirected Output
3.6.4 Redirecting Standard Output and Standard Error
3.6.5 Appending Standard Output and Standard Error
3.6.6 Here Documents
3.6.7 Here Strings
3.6.8 Duplicating File Descriptors
3.6.9 Moving File Descriptors
3.6.10 Opening File Descriptors for Reading and Writing
3.7 Executing Commands
3.7.1 Simple Command Expansion
3.7.2 Command Search and Execution
3.7.3 Command Execution Environment
3.7.4 Environment
3.7.5 Exit Status
3.7.6 Signals
3.8 Shell Scripts
4 Shell Builtin Commands
4.1 Bourne Shell Builtins
4.2 Bash Builtin Commands
4.3 Modifying Shell Behavior
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
































