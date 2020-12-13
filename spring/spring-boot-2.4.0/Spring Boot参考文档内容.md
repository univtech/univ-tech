# Spring Boot参考文档







## 3. 使用Spring Boot

本节更详细地介绍了应该如何使用Spring Boot。
本节涵盖了诸如构建系统、自动化配置和如何运行应用程序等主题。
本节还介绍了Spring Boot的一些最佳实践。

Although there is nothing particularly special about Spring Boot (it is just another library that you can consume), there are a few recommendations that, when followed, make your development process a little easier.

If you are starting out with Spring Boot, you should probably read the Getting Started guide before diving into this section.

### 3.1. 构建系统

强烈建议你选择一个支持依赖管理的构建系统，并且此构建系统可以使用发布到Maven中央仓库的构件。建议你选择Maven或Gradle。让Spring Boot和其他构建系统（例如Ant）一起工作是有可能的，但是这并没有得到很好的支持。

#### 3.1.1. 依赖管理

Spring Boot的每个版本都提供了一个它所支持的依赖列表。实际上，你并不需要在构建配置中提供这些依赖的版本，因为Spring Boot替你管理了这些依赖的版本。当你升级Spring Boot时，这些依赖也会随着升级。

> 如果你需要的话，你仍然可以指定一个版本来覆盖Spring Boot推荐的版本。

依赖列表中包含了可以和Spring Boot一起使用的所有Spring模块和第三方库。这个列表是一个可以和Maven及Gradle一起使用的标准BOM（spring-boot-dependencies）。

> Spring Boot的每个版本都与Spring Framework的某个版本相关联。强烈建议你不要指定Spring Framework的版本。

#### 3.1.2. Maven

Maven用户可以继承spring-boot-starter-parent，spring-boot-starter-parent提供了以下特性：
+ Java 1.8默认编译器级别；
+ UTF-8资源编码；
+ 从spring-boot-dependencies的pom继承而来的dependencyManagement，管理了常用依赖的版本，使用这些依赖时，可以忽略version标签；
+ 执行包含repackage执行id的[repackage目标](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/maven-plugin//repackage-mojo.html)；
+ 合理的[资源过滤](https://maven.apache.org/plugins/maven-resources-plugin/examples/filter.html)；
+ 合理的插件配置（[exec](https://www.mojohaus.org/exec-maven-plugin)、[git-commit-id](https://github.com/ktoso/maven-git-commit-id-plugin)、[shade](https://maven.apache.org/plugins/maven-shade-plugin)）；
+ 对application.properties和application.yml文件进行合理的资源过滤，包括profile特定的文件，例如：application-dev.properties和application-dev.yml。

> 因为application.properties和application.yml文件接受Spring风格的占位符${...}，所以Maven过滤时使用@...@占位符。可以通过设置Maven属性resource.delimiter来覆盖Maven过滤时使用的@...@占位符。

##### 3.1.2.1. 继承spring-boot-starter-parent

配置parent，继承spring-boot-starter-parent：

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.2.RELEASE</version>
</parent>
```

> 这个依赖中指定了Spring Boot的版本号，因此在引入其他starter时，可以忽略版本号。

继承spring-boot-starter-parent之后，依赖版本的覆盖方式如下：
+ 通过属性覆盖方式来覆盖依赖版本。

```xml
<properties>
    <spring-data-releasetrain.version>Fowler-SR2</spring-data-releasetrain.version>
</properties>
```

> Spring Boot支持的属性列表，请参照[spring-boot-dependencies的pom文件](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-dependencies/pom.xml)。

##### 3.1.2.2. 引入spring-boot-dependencies

不想继承spring-boot-starter-parent的原因：
+ 需要使用自己的parent；
+ 需要显式声明所有Maven配置。

不想继承spring-boot-starter-parent时，可以在dependencyManagement中引入spring-boot-dependencies：
+ 使用scope=import依赖；
+ 使用这种配置方式，只能从依赖管理中获益，不能从插件管理中获益。

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.2.2.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

引入spring-boot-dependencies之后，依赖版本的覆盖方式如下：
+ 不能通过属性覆盖方式来覆盖依赖版本；
+ 在spring-boot-dependencies之前添加其他依赖。

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-releasetrain</artifactId>
            <version>Fowler-SR2</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.2.2.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

> dependencyManagement中可以覆盖任何依赖类型，不只是bom（type=pom）。

##### 3.1.2.3. 使用spring-boot-maven-plugin

spring-boot-maven-plugin可以把项目打包为一个可执行jar包，在plugins中添加spring-boot-maven-plugin：

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

> 继承spring-boot-starter-parent时，只需要添加插件，不需要其他配置，除非需要修改parent中定义的配置。

#### 3.1.3. Gradle

Spring Boot和Gradle的使用，请参考Spring Boot的Gradle插件文档：

+ 参考文档
    + [HTML](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/gradle-plugin/reference/html/)
    + [PDF](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/gradle-plugin/reference/pdf/spring-boot-gradle-plugin-reference.pdf)
+ [API](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/gradle-plugin/reference/api/)

#### 3.1.3. Ant

可以使用Apache Ant和Ivy来构建Spring Boot项目，spring-boot-antlib模块可以用于帮助Ant创建可执行Jar包。

声明依赖的ivy.xml文件示例：

```xml
<ivy-module version="2.0">
    <info organisation="org.springframework.boot" module="spring-boot-sample-ant" />
    <configurations>
        <conf name="compile" description="everything needed to compile this module" />
        <conf name="runtime" extends="compile" description="everything needed to run this module" />
    </configurations>
    <dependencies>
        <dependency org="org.springframework.boot" name="spring-boot-starter"
            rev="${spring-boot.version}" conf="compile" />
    </dependencies>
</ivy-module>
```

build.xml示例：

```xml
<project
    xmlns:ivy="antlib:org.apache.ivy.ant"
    xmlns:spring-boot="antlib:org.springframework.boot.ant"
    name="myapp" default="build">

    <property name="spring-boot.version" value="2.2.2.RELEASE" />

    <target name="resolve" description="--> retrieve dependencies with ivy">
        <ivy:retrieve pattern="lib/[conf]/[artifact]-[type]-[revision].[ext]" />
    </target>

    <target name="classpaths" depends="resolve">
        <path id="compile.classpath">
            <fileset dir="lib/compile" includes="*.jar" />
        </path>
    </target>

    <target name="init" depends="classpaths">
        <mkdir dir="build/classes" />
    </target>

    <target name="compile" depends="init" description="compile">
        <javac srcdir="src/main/java" destdir="build/classes" classpathref="compile.classpath" />
    </target>

    <target name="build" depends="compile">
        <spring-boot:exejar destfile="build/myapp.jar" classes="build/classes">
            <spring-boot:lib>
                <fileset dir="lib/runtime" />
            </spring-boot:lib>
        </spring-boot:exejar>
    </target>
</project>
```

#### 3.1.5. 启动器




##### 3.1.5.1. 应用程序启动器

Spring Boot应用程序启动器在org.springframework.boot group中。

 名称                                        | 描述                                                                                                             | pom
:--------------------------------------------|:-----------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 spring-boot-starter                         | 核心启动器，包括自动化配置支持、日志和YAML                                                                       | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter/pom.xml)
 spring-boot-starter-activemq                | 使用Apache ActiveMQ的JMS消息启动器                                                                               | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-activemq/pom.xml)
 spring-boot-starter-amqp                    | 使用Spring AMQP和Rabbit MQ的启动器                                                                               | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-amqp/pom.xml)
 spring-boot-starter-aop                     | 使用Spring AOP和AspectJ来进行面向切面编程的启动器                                                                | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-aop/pom.xml)
 spring-boot-starter-artemis                 | 使用Apache Artemis的JMS消息启动器                                                                                | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-artemis/pom.xml)
 spring-boot-starter-batch                   | 使用Spring Batch的启动器                                                                                         | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-batch/pom.xml)
 spring-boot-starter-cache                   | 使用Spring Framework缓存支持的启动器                                                                             | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-cache/pom.xml)
 spring-boot-starter-cloud-connectors        | 使用Spring Cloud Connectors来简化连接云平台（例如：Cloud Foundry和Heroku）服务的启动器，已废弃，请使用Java CFEnv | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-cloud-connectors/pom.xml)
 spring-boot-starter-data-cassandra          | 使用Cassandra分布式数据库和Spring Data Cassandra的启动器                                                         | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-cassandra/pom.xml)
 spring-boot-starter-data-cassandra-reactive | 使用Cassandra分布式数据库和Spring Data Cassandra Reactive的启动器                                                | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-cassandra-reactive/pom.xml)
 spring-boot-starter-data-couchbase          | 使用Couchbase文档型数据库和Spring Data Couchbase的启动器                                                         | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-couchbase/pom.xml)
 spring-boot-starter-data-couchbase-reactive | 使用Couchbase文档型数据库和Spring Data Couchbase Reactive的启动器                                                | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-couchbase-reactive/pom.xml)
 spring-boot-starter-data-elasticsearch      | 使用Elasticsearch搜索分析引擎和Spring Data Elasticsearch的启动器                                                 | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-elasticsearch/pom.xml)
 spring-boot-starter-data-jdbc               | 使用Spring Data JDBC的启动器                                                                                     | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-jdbc/pom.xml)
 spring-boot-starter-data-jpa                | 使用Spring Data JPA和Hibernate的启动器                                                                           | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-jpa/pom.xml)
 spring-boot-starter-data-ldap               | 使用Spring Data LDAP的启动器                                                                                     | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-ldap/pom.xml)
 spring-boot-starter-data-mongodb            | 使用MongoDB文档型数据库和Spring Data MongoDB的启动器                                                             | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-mongodb/pom.xml)
 spring-boot-starter-data-mongodb-reactive   | 使用MongoDB文档型数据库和Spring Data MongoDB Reactive的启动器                                                    | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-mongodb-reactive/pom.xml)
 spring-boot-starter-data-neo4j              | 使用Neo4j图形数据库和Spring Data Neo4j的启动器                                                                   | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-neo4j/pom.xml)
 spring-boot-starter-data-redis              | 使用Redis键值数据存储和Spring Data Redis以及Lettuce客户端的启动器                                                | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-redis/pom.xml)
 spring-boot-starter-data-redis-reactive     | 使用Redis键值数据存储和Spring Data Redis Reactive以及Lettuce客户端的启动器                                       | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-redis-reactive/pom.xml)
 spring-boot-starter-data-rest               | 使用Spring Data REST在REST上公开Spring数据仓库的启动器                                                           | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-rest/pom.xml)
 spring-boot-starter-data-solr               | 使用Apache Solr搜索平台和Spring Data Solr的启动器                                                                | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-data-solr/pom.xml)
 spring-boot-starter-freemarker              | 使用FreeMarker视图来构建MVC模式的Web应用程序的启动器                                                             | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-freemarker/pom.xml)
 spring-boot-starter-groovy-templates        | 使用Groovy模板视图来构建MVC模式的Web应用程序的启动器                                                             | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-groovy-templates/pom.xml)
 spring-boot-starter-hateoas                 | 使用Spring MVC和Spring HATEOAS来构建基于Hypermedia的RESTful风格的Web应用程序的启动器                             | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-hateoas/pom.xml)
 spring-boot-starter-integration             | 使用Spring Integration的启动器                                                                                   | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-integration/pom.xml)
 spring-boot-starter-jdbc                    | 使用JDBC和HikariCP连接池的启动器                                                                                 | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-jdbc/pom.xml)
 spring-boot-starter-jersey                  | 使用JAX-RS和Jersey来构建RESTful风格的Web应用程序的启动器，spring-boot-starter-web的替代品                        | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-jersey/pom.xml)
 spring-boot-starter-jooq                    | 使用jOOQ来访问SQL数据库的启动器，spring-boot-starter-data-jpa或spring-boot-starter-jdbc的替代品                  | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-jooq/pom.xml)
 spring-boot-starter-json                    | 读写Json的启动器                                                                                                 | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-json/pom.xml)
 spring-boot-starter-jta-atomikos            | 使用Atomikos的JTA事务启动器                                                                                      | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-jta-atomikos/pom.xml)
 spring-boot-starter-jta-bitronix            | 使用Bitronix的JTA事务启动器                                                                                      | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-jta-bitronix/pom.xml)
 spring-boot-starter-mail                    | 使用Java Mail和Spring Framework邮件发送支持的启动器                                                              | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-mail/pom.xml)
 spring-boot-starter-mustache                | 使用Mustache视图来构建Web应用程序的启动器                                                                        | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-mustache/pom.xml)
 spring-boot-starter-oauth2-client           | 使用Spring Security的OAuth2或OpenID Connect客户端特性的启动器                                                    | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-oauth2-client/pom.xml)
 spring-boot-starter-oauth2-resource-server  | 使用Spring Security的OAuth2资源服务器特性的启动器                                                                | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-oauth2-resource-server/pom.xml)
 spring-boot-starter-quartz                  | 使用Quartz调度器的启动器                                                                                         | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-quartz/pom.xml)
 spring-boot-starter-rsocket                 | 构建RSocket客户端和服务器的启动器                                                                                | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-rsocket/pom.xml)
 spring-boot-starter-security                | 使用Spring Security的启动器                                                                                      | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-security/pom.xml)
 spring-boot-starter-test                    | 使用JUnit、Hamcrest和Mockito库来测试Spring Boot应用程序的启动器                                                  | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-test/pom.xml)
 spring-boot-starter-thymeleaf               | 使用Thymeleaf视图来构建MVC模式的Web应用程序的启动器                                                              | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-thymeleaf/pom.xml)
 spring-boot-starter-validation              | 使用Java Bean Validation和Hibernate Validator的启动器                                                            | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-validation/pom.xml)
 spring-boot-starter-web                     | 使用Spring MVC来构建Web应用程序和RESTful应用程序的启动器，使用Tomcat作为默认的嵌入式容器                         | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-web/pom.xml)
 spring-boot-starter-web-services            | 使用Spring Web Services的启动器                                                                                  | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-web-services/pom.xml)
 spring-boot-starter-webflux                 | 使用Spring Framework对Reactive Web的支持来构建WebFlux应用程序的启动器                                            | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-webflux/pom.xml)
 spring-boot-starter-websocket               | 使用Spring Framework对WebSocket的支持来构建WebSocket应用程序的启动器                                             | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-websocket/pom.xml)

##### 3.1.5.2. 添加Production-ready特性的启动器

 名称                         | 描述                                                                            | pom
:-----------------------------|:--------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------
 spring-boot-starter-actuator | 使用Spring Boot的Actuator来提供监控和管理应用程序的Production-ready特性的启动器 | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-actuator/pom.xml)

##### 3.1.5.3. 排除或切换特定技术的启动器

 名称                              | 描述                                                                                                                        | pom
:----------------------------------|:----------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------
 spring-boot-starter-jetty         | 使用Jetty作为嵌入式Servlet容器的启动器，spring-boot-starter-tomcat的替代品                                                  | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-jetty/pom.xml)
 spring-boot-starter-log4j2        | 使用Log4j2来记录日志的启动器，spring-boot-starter-logging的替代品                                                           | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-log4j2/pom.xml)
 spring-boot-starter-logging       | 使用Logback来记录日志的启动器，默认的日志启动器                                                                             | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-logging/pom.xml)
 spring-boot-starter-reactor-netty | 使用Reactor Netty作为嵌入式Reactive HTTP服务器的启动器                                                                      | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-reactor-netty/pom.xml)
 spring-boot-starter-tomcat        | 使用Tomcat作为嵌入式Servlet容器的启动器，使用spring-boot-starter-web时，spring-boot-starter-tomcat是默认的Servlet容器启动器 | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-tomcat/pom.xml)
 spring-boot-starter-undertow      | 使用Undertow作为嵌入式Servlet容器的启动器，spring-boot-starter-tomcat的替代品                                               | [pom](https://github.com/spring-projects/spring-boot/tree/v2.2.2.RELEASE/spring-boot-project/spring-boot-starters/spring-boot-starter-undertow/pom.xml)

> 社区提供的其他启动器列表，请参照spring-boot-starters模块的[README文件](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-project/spring-boot-starters/README.adoc)


























































































































## 4. Spring Boot功能






# 4.1. SpringApplication

SpringApplication类用于从main()方法启动Spring应用程序。很多情况下，可以委托给SpringApplication.run静态方法：

```java
public static void main(String[] args) {
    SpringApplication.run(MySpringConfiguration.class, args);
}
```

输出信息：

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::   v2.2.2.RELEASE

2019-04-31 13:09:54.117  INFO 56603 --- [main] o.s.b.s.app.SampleApplication: Starting SampleApplication v0.1.0 on mycomputer with PID 56603 (/apps/myapp.jar started by pwebb)
2019-04-31 13:09:54.166  INFO 56603 --- [main] ationConfigServletWebServerApplicationContext: Refreshing org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@6e5a8246: startup date [Wed Jul 31 00:08:16 PDT 2013]; root of context hierarchy
2019-04-01 13:09:56.912  INFO 41370 --- [main] .t.TomcatServletWebServerFactory: Server initialized with port: 8080
2019-04-01 13:09:57.501  INFO 41370 --- [main] o.s.b.s.app.SampleApplication: Started SampleApplication in 2.992 seconds (JVM running for 3.658)
```



