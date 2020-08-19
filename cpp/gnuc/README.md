# GNU C参考手册

https://www.gnu.org/software/gnu-c-manual/gnu-c-manual.html

This is the GNU C reference manual.

## 目录

+ [2 数据类型](datatype.md#2-数据类型)
    + [2.1 原始数据类型](datatype.md#21-原始数据类型)
        + [2.1.1 整数类型](datatype.md#211-整数类型)
        + [2.1.2 实数类型](datatype.md#212-实数类型)
        + [2.1.3 复数类型](datatype.md#213-复数类型)
            + [2.1.3.1 标准的复数类型](datatype.md#2131-标准的复数类型)
            + [2.1.3.2 复数类型的GNU扩展](datatype.md#2132-复数类型的GNU扩展)
    + [2.2 枚举](datatype.md#22-枚举)
        + [2.2.1 定义枚举](datatype.md#221-定义枚举)
        + [2.2.2 声明枚举](datatype.md#222-声明枚举)
    + [2.3 共用体](datatype.md#23-共用体)
        + [2.3.1 定义共用体](datatype.md#231-定义共用体)
        + [2.3.2 声明共用体变量](datatype.md#232-声明共用体变量)
            + [2.3.2.1 在定义时声明共用体变量](datatype.md#2321-在定义时声明共用体变量)
            + [2.3.2.2 在定义后声明共用体变量](datatype.md#2322-在定义后声明共用体变量)
            + [2.3.2.3 初始化共用体成员](datatype.md#2323-初始化共用体成员)
        + [2.3.3 访问共用体成员](datatype.md#233-访问共用体成员)
        + [2.3.4 共用体的大小](datatype.md#234-共用体的大小)
    + [2.4 结构体](datatype.md#24-结构体)
        + [2.4.1 定义结构体](datatype.md#241-定义结构体)
        + [2.4.2 声明结构体变量](datatype.md#242-声明结构体变量)
            + [2.4.2.1 在定义时声明结构体变量](datatype.md#2421-在定义时声明结构体变量)
            + [2.4.2.2 在定义后声明结构体变量](datatype.md#2422-在定义后声明结构体变量)
            + [2.4.2.3 初始化结构体成员](datatype.md#2423-初始化结构体成员)
        + [2.4.3 访问结构体成员](datatype.md#243-访问结构体成员)
        + [2.4.4 位域](datatype.md#244-位域)
        + [2.4.5 结构体的大小](datatype.md#245-结构体的大小)
    + [2.5 数组](datatype.md#25-数组)
        + [2.5.1 声明数组](datatype.md#251-声明数组)
        + [2.5.2 初始化数组](datatype.md#252-初始化数组)
        + [2.5.3 访问数组元素](datatype.md#253-访问数组元素)
        + [2.5.4 多维数组](datatype.md#254-多维数组)
        + [2.5.5 字符数组](datatype.md#255-字符数组)
        + [2.5.6 共用体数组](datatype.md#256-共用体数组)
        + [2.5.7 结构体数组](datatype.md#257-结构体数组)
    + [2.6 指针](datatype.md#26-指针)
        + [2.6.1 声明指针](datatype.md#261-声明指针)
        + [2.6.2 初始化指针](datatype.md#262-初始化指针)
        + [2.6.3 共用体指针](datatype.md#263-共用体指针)
        + [2.6.4 结构体指针](datatype.md#264-结构体指针)
    + [2.7 不完全类型](datatype.md#27-不完全类型)
    + [2.8 类型限定符](datatype.md#28-类型限定符)
    + [2.9 存储类说明符](datatype.md#29-存储类说明符)
    + [2.10 重命名类型](datatype.md#210-重命名类型)
+ [3 表达式和运算符](expression.md#3-表达式和运算符)
    + [3.1 表达式](expression.md#31-表达式)
    + [3.2 赋值运算符](expression.md#32-赋值运算符)
    + [3.3 递增和递减](expression.md#33-递增和递减)
    + [3.4 算术运算符](expression.md#34-算术运算符)
    + [3.5 共轭复数](expression.md#35-共轭复数)
    + [3.6 比较运算符](expression.md#36-比较运算符)
    + [3.7 逻辑运算符](expression.md#37-逻辑运算符)
    + [3.8 位移](expression.md#38-位移)
    + [3.9 位逻辑运算符](expression.md#39-位逻辑运算符)
    + [3.10 指针运算符](expression.md#310-指针运算符)
    + [3.11 sizeof运算符](expression.md#311-sizeof运算符)
    + [3.12 类型转换](expression.md#312-类型转换)
    + [3.13 数组下标](expression.md#313-数组下标)
    + [3.14 函数调用表达式](expression.md#314-函数调用表达式)
    + [3.15 逗号运算符](expression.md#315-逗号运算符)
    + [3.16 成员访问表达式](expression.md#316-成员访问表达式)
    + [3.17 条件表达式](expression.md#317-条件表达式)
    + [3.18 表达式中的语句和声明](expression.md#318-表达式中的语句和声明)
    + [3.19 运算符优先级](expression.md#319-运算符优先级)
    + [3.20 评估顺序](expression.md#320-评估顺序)
        + [3.20.1 副作用](expression.md#3201-副作用)
        + [3.20.2 序列点](expression.md#3202-序列点)
        + [3.20.3 序列点约束表达式](expression.md#3203-序列点约束表达式)
        + [3.20.4 序列点和信号传递](expression.md#3204-序列点和信号传递)
+ [4 语句](statement.md#4-语句)
    + [4.1 标签](statement.md#41-标签)
    + [4.2 表达式语句](statement.md#42-表达式语句)
    + [4.3 if语句](statement.md#43-if语句)
    + [4.4 switch语句](statement.md#44-switch语句)
    + [4.5 while语句](statement.md#45-while语句)
    + [4.6 do语句](statement.md#46-do语句)
    + [4.7 for语句](statement.md#47-for语句)
    + [4.8 块](statement.md#48-块)
    + [4.9 null语句](statement.md#49-null语句)
    + [4.10 goto语句](statement.md#410-goto语句)
    + [4.11 break语句](statement.md#411-break语句)
    + [4.12 continue语句](statement.md#412-continue语句)
    + [4.13 return语句](statement.md#413-return语句)
    + [4.14 typedef语句](statement.md#414-typedef语句)























