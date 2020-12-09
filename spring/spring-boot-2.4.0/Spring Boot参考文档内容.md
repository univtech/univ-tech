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














































## 10. 附录









### 附录F：依赖版本

本附录提供了Spring Boot管理的依赖明细。

#### 10.F.1. 已管理的依赖坐标

下表提供了Spring Boot在CLI（命令行接口）、Maven依赖管理和Gradle插件中提供的所有依赖版本明细。在没有声明版本的情况下声明这些工件（Artifact）的依赖，将会使用下表中列出的版本。

 groupId                                       | artifactId                                  | version
:----------------------------------------------|:--------------------------------------------|:--------------------
 antlr                                         | antlr                                       | 2.7.7
 ch.qos.logback                                | logback-access                              | 1.2.3
 ch.qos.logback                                | logback-classic                             | 1.2.3
 ch.qos.logback                                | logback-core                                | 1.2.3
 com.atomikos                                  | transactions-jdbc                           | 4.0.6
 com.atomikos                                  | transactions-jms                            | 4.0.6
 com.atomikos                                  | transactions-jta                            | 4.0.6
 com.couchbase.client                          | couchbase-spring-cache                      | 2.1.0
 com.couchbase.client                          | java-client                                 | 2.7.11
 com.datastax.cassandra                        | cassandra-driver-core                       | 3.7.2
 com.datastax.cassandra                        | cassandra-driver-mapping                    | 3.7.2
 com.fasterxml                                 | classmate                                   | 1.5.1
 com.fasterxml.jackson.core                    | jackson-annotations                         | 2.10.1
 com.fasterxml.jackson.core                    | jackson-core                                | 2.10.1
 com.fasterxml.jackson.core                    | jackson-databind                            | 2.10.1
 com.fasterxml.jackson.dataformat              | jackson-dataformat-avro                     | 2.10.1
 com.fasterxml.jackson.dataformat              | jackson-dataformat-cbor                     | 2.10.1
 com.fasterxml.jackson.dataformat              | jackson-dataformat-csv                      | 2.10.1
 com.fasterxml.jackson.dataformat              | jackson-dataformat-ion                      | 2.10.1
 com.fasterxml.jackson.dataformat              | jackson-dataformat-properties               | 2.10.1
 com.fasterxml.jackson.dataformat              | jackson-dataformat-protobuf                 | 2.10.1
 com.fasterxml.jackson.dataformat              | jackson-dataformat-smile                    | 2.10.1
 com.fasterxml.jackson.dataformat              | jackson-dataformat-xml                      | 2.10.1
 com.fasterxml.jackson.dataformat              | jackson-dataformat-yaml                     | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-guava                      | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-hibernate3                 | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-hibernate4                 | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-hibernate5                 | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-hppc                       | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-jaxrs                      | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-jdk8                       | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-joda                       | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-json-org                   | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-jsr310                     | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-jsr353                     | 2.10.1
 com.fasterxml.jackson.datatype                | jackson-datatype-pcollections               | 2.10.1
 com.fasterxml.jackson.jaxrs                   | jackson-jaxrs-base                          | 2.10.1
 com.fasterxml.jackson.jaxrs                   | jackson-jaxrs-cbor-provider                 | 2.10.1
 com.fasterxml.jackson.jaxrs                   | jackson-jaxrs-json-provider                 | 2.10.1
 com.fasterxml.jackson.jaxrs                   | jackson-jaxrs-smile-provider                | 2.10.1
 com.fasterxml.jackson.jaxrs                   | jackson-jaxrs-xml-provider                  | 2.10.1
 com.fasterxml.jackson.jaxrs                   | jackson-jaxrs-yaml-provider                 | 2.10.1
 com.fasterxml.jackson.jr                      | jackson-jr-all                              | 2.10.1
 com.fasterxml.jackson.jr                      | jackson-jr-objects                          | 2.10.1
 com.fasterxml.jackson.jr                      | jackson-jr-retrofit2                        | 2.10.1
 com.fasterxml.jackson.jr                      | jackson-jr-stree                            | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-afterburner                  | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-guice                        | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-jaxb-annotations             | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-jsonSchema                   | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-kotlin                       | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-mrbean                       | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-osgi                         | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-parameter-names              | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-paranamer                    | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-scala_2.10                   | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-scala_2.11                   | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-scala_2.12                   | 2.10.1
 com.fasterxml.jackson.module                  | jackson-module-scala_2.13                   | 2.10.1
 com.github.ben-manes.caffeine                 | caffeine                                    | 2.8.0
 com.github.ben-manes.caffeine                 | guava                                       | 2.8.0
 com.github.ben-manes.caffeine                 | jcache                                      | 2.8.0
 com.github.ben-manes.caffeine                 | simulator                                   | 2.8.0
 com.github.mxab.thymeleaf.extras              | thymeleaf-extras-data-attribute             | 2.0.1
 com.google.appengine                          | appengine-api-1.0-sdk                       | 1.9.77
 com.google.code.gson                          | gson                                        | 2.8.6
 com.h2database                                | h2                                          | 1.4.200
 com.hazelcast                                 | hazelcast                                   | 3.12.4
 com.hazelcast                                 | hazelcast-client                            | 3.12.4
 com.hazelcast                                 | hazelcast-hibernate52                       | 1.3.2
 com.hazelcast                                 | hazelcast-hibernate53                       | 1.3.2
 com.hazelcast                                 | hazelcast-spring                            | 3.12.4
 com.ibm.db2                                   | jcc                                         | 11.5.0.0
 com.jayway.jsonpath                           | json-path                                   | 2.4.0
 com.jayway.jsonpath                           | json-path-assert                            | 2.4.0
 com.microsoft.sqlserver                       | mssql-jdbc                                  | 7.4.1.jre8
 com.oracle.ojdbc                              | dms                                         | 19.3.0.0
 com.oracle.ojdbc                              | ojdbc10                                     | 19.3.0.0
 com.oracle.ojdbc                              | ojdbc10_g                                   | 19.3.0.0
 com.oracle.ojdbc                              | ojdbc10dms                                  | 19.3.0.0
 com.oracle.ojdbc                              | ojdbc10dms_g                                | 19.3.0.0
 com.oracle.ojdbc                              | ojdbc8                                      | 19.3.0.0
 com.oracle.ojdbc                              | ojdbc8_g                                    | 19.3.0.0
 com.oracle.ojdbc                              | ojdbc8dms                                   | 19.3.0.0
 com.oracle.ojdbc                              | ojdbc8dms_g                                 | 19.3.0.0
 com.oracle.ojdbc                              | ons                                         | 19.3.0.0
 com.oracle.ojdbc                              | oraclepki                                   | 19.3.0.0
 com.oracle.ojdbc                              | orai18n                                     | 19.3.0.0
 com.oracle.ojdbc                              | osdt_cert                                   | 19.3.0.0
 com.oracle.ojdbc                              | osdt_core                                   | 19.3.0.0
 com.oracle.ojdbc                              | simplefan                                   | 19.3.0.0
 com.oracle.ojdbc                              | ucp                                         | 19.3.0.0
 com.oracle.ojdbc                              | xdb                                         | 19.3.0.0
 com.oracle.ojdbc                              | xmlparserv2                                 | 19.3.0.0
 com.querydsl                                  | querydsl-apt                                | 4.2.2
 com.querydsl                                  | querydsl-collections                        | 4.2.2
 com.querydsl                                  | querydsl-core                               | 4.2.2
 com.querydsl                                  | querydsl-jpa                                | 4.2.2
 com.querydsl                                  | querydsl-mongodb                            | 4.2.2
 com.rabbitmq                                  | amqp-client                                 | 5.7.3
 com.samskivert                                | jmustache                                   | 1.15
 com.sendgrid                                  | sendgrid-java                               | 4.4.1
 com.squareup.okhttp3                          | logging-interceptor                         | 3.14.4
 com.squareup.okhttp3                          | mockwebserver                               | 3.14.4
 com.squareup.okhttp3                          | okcurl                                      | 3.14.4
 com.squareup.okhttp3                          | okhttp                                      | 3.14.4
 com.squareup.okhttp3                          | okhttp-dnsoverhttps                         | 3.14.4
 com.squareup.okhttp3                          | okhttp-sse                                  | 3.14.4
 com.squareup.okhttp3                          | okhttp-testing-support                      | 3.14.4
 com.squareup.okhttp3                          | okhttp-tls                                  | 3.14.4
 com.squareup.okhttp3                          | okhttp-urlconnection                        | 3.14.4
 com.sun.activation                            | jakarta.activation                          | 1.2.1
 com.sun.mail                                  | jakarta.mail                                | 1.6.4
 com.sun.xml.messaging.saaj                    | saaj-impl                                   | 1.5.1
 com.unboundid                                 | unboundid-ldapsdk                           | 4.0.13
 com.zaxxer                                    | HikariCP                                    | 3.4.1
 commons-codec                                 | commons-codec                               | 1.13
 commons-pool                                  | commons-pool                                | 1.6
 de.flapdoodle.embed                           | de.flapdoodle.embed.mongo                   | 2.2.0
 io.dropwizard.metrics                         | metrics-annotation                          | 4.1.1
 io.dropwizard.metrics                         | metrics-core                                | 4.1.1
 io.dropwizard.metrics                         | metrics-ehcache                             | 4.1.1
 io.dropwizard.metrics                         | metrics-graphite                            | 4.1.1
 io.dropwizard.metrics                         | metrics-healthchecks                        | 4.1.1
 io.dropwizard.metrics                         | metrics-httpasyncclient                     | 4.1.1
 io.dropwizard.metrics                         | metrics-jdbi                                | 4.1.1
 io.dropwizard.metrics                         | metrics-jersey2                             | 4.1.1
 io.dropwizard.metrics                         | metrics-jetty9                              | 4.1.1
 io.dropwizard.metrics                         | metrics-jmx                                 | 4.1.1
 io.dropwizard.metrics                         | metrics-json                                | 4.1.1
 io.dropwizard.metrics                         | metrics-jvm                                 | 4.1.1
 io.dropwizard.metrics                         | metrics-log4j2                              | 4.1.1
 io.dropwizard.metrics                         | metrics-logback                             | 4.1.1
 io.dropwizard.metrics                         | metrics-servlet                             | 4.1.1
 io.dropwizard.metrics                         | metrics-servlets                            | 4.1.1
 io.lettuce                                    | lettuce-core                                | 5.2.1.RELEASE
 io.micrometer                                 | micrometer-core                             | 1.3.1
 io.micrometer                                 | micrometer-jersey2                          | 1.3.1
 io.micrometer                                 | micrometer-registry-appoptics               | 1.3.1
 io.micrometer                                 | micrometer-registry-atlas                   | 1.3.1
 io.micrometer                                 | micrometer-registry-azure-monitor           | 1.3.1
 io.micrometer                                 | micrometer-registry-cloudwatch              | 1.3.1
 io.micrometer                                 | micrometer-registry-cloudwatch2             | 1.3.1
 io.micrometer                                 | micrometer-registry-datadog                 | 1.3.1
 io.micrometer                                 | micrometer-registry-dynatrace               | 1.3.1
 io.micrometer                                 | micrometer-registry-elastic                 | 1.3.1
 io.micrometer                                 | micrometer-registry-ganglia                 | 1.3.1
 io.micrometer                                 | micrometer-registry-graphite                | 1.3.1
 io.micrometer                                 | micrometer-registry-humio                   | 1.3.1
 io.micrometer                                 | micrometer-registry-influx                  | 1.3.1
 io.micrometer                                 | micrometer-registry-jmx                     | 1.3.1
 io.micrometer                                 | micrometer-registry-kairos                  | 1.3.1
 io.micrometer                                 | micrometer-registry-new-relic               | 1.3.1
 io.micrometer                                 | micrometer-registry-prometheus              | 1.3.1
 io.micrometer                                 | micrometer-registry-signalfx                | 1.3.1
 io.micrometer                                 | micrometer-registry-stackdriver             | 1.3.1
 io.micrometer                                 | micrometer-registry-statsd                  | 1.3.1
 io.micrometer                                 | micrometer-registry-wavefront               | 1.3.1
 io.micrometer                                 | micrometer-spring-legacy                    | 1.3.1
 io.micrometer                                 | micrometer-test                             | 1.3.1
 io.netty                                      | netty-all                                   | 4.1.43.Final
 io.netty                                      | netty-buffer                                | 4.1.43.Final
 io.netty                                      | netty-codec                                 | 4.1.43.Final
 io.netty                                      | netty-codec-dns                             | 4.1.43.Final
 io.netty                                      | netty-codec-haproxy                         | 4.1.43.Final
 io.netty                                      | netty-codec-http                            | 4.1.43.Final
 io.netty                                      | netty-codec-http2                           | 4.1.43.Final
 io.netty                                      | netty-codec-memcache                        | 4.1.43.Final
 io.netty                                      | netty-codec-mqtt                            | 4.1.43.Final
 io.netty                                      | netty-codec-redis                           | 4.1.43.Final
 io.netty                                      | netty-codec-smtp                            | 4.1.43.Final
 io.netty                                      | netty-codec-socks                           | 4.1.43.Final
 io.netty                                      | netty-codec-stomp                           | 4.1.43.Final
 io.netty                                      | netty-codec-xml                             | 4.1.43.Final
 io.netty                                      | netty-common                                | 4.1.43.Final
 io.netty                                      | netty-dev-tools                             | 4.1.43.Final
 io.netty                                      | netty-example                               | 4.1.43.Final
 io.netty                                      | netty-handler                               | 4.1.43.Final
 io.netty                                      | netty-handler-proxy                         | 4.1.43.Final
 io.netty                                      | netty-resolver                              | 4.1.43.Final
 io.netty                                      | netty-resolver-dns                          | 4.1.43.Final
 io.netty                                      | netty-tcnative-boringssl-static             | 2.0.28.Final
 io.netty                                      | netty-transport                             | 4.1.43.Final
 io.netty                                      | netty-transport-native-epoll                | 4.1.43.Final
 io.netty                                      | netty-transport-native-kqueue               | 4.1.43.Final
 io.netty                                      | netty-transport-native-unix-common          | 4.1.43.Final
 io.netty                                      | netty-transport-rxtx                        | 4.1.43.Final
 io.netty                                      | netty-transport-sctp                        | 4.1.43.Final
 io.netty                                      | netty-transport-udt                         | 4.1.43.Final
 io.projectreactor                             | reactor-core                                | 3.3.1.RELEASE
 io.projectreactor                             | reactor-test                                | 3.3.1.RELEASE
 io.projectreactor                             | reactor-tools                               | 3.3.1.RELEASE
 io.projectreactor.addons                      | reactor-adapter                             | 3.3.1.RELEASE
 io.projectreactor.addons                      | reactor-extra                               | 3.3.1.RELEASE
 io.projectreactor.addons                      | reactor-pool                                | 0.1.1.RELEASE
 io.projectreactor.kafka                       | reactor-kafka                               | 1.2.1.RELEASE
 io.projectreactor.kotlin                      | reactor-kotlin-extensions                   | 1.0.1.RELEASE
 io.projectreactor.netty                       | reactor-netty                               | 0.9.2.RELEASE
 io.projectreactor.rabbitmq                    | reactor-rabbitmq                            | 1.4.0.RELEASE
 io.prometheus                                 | simpleclient_pushgateway                    | 0.7.0
 io.reactivex                                  | rxjava                                      | 1.3.8
 io.reactivex                                  | rxjava-reactive-streams                     | 1.2.1
 io.reactivex.rxjava2                          | rxjava                                      | 2.2.15
 io.rest-assured                               | json-path                                   | 3.3.0
 io.rest-assured                               | json-schema-validator                       | 3.3.0
 io.rest-assured                               | rest-assured                                | 3.3.0
 io.rest-assured                               | scala-support                               | 3.3.0
 io.rest-assured                               | spring-mock-mvc                             | 3.3.0
 io.rest-assured                               | spring-web-test-client                      | 3.3.0
 io.rest-assured                               | xml-path                                    | 3.3.0
 io.rsocket                                    | rsocket-core                                | 1.0.0-RC5
 io.rsocket                                    | rsocket-examples                            | 1.0.0-RC5
 io.rsocket                                    | rsocket-load-balancer                       | 1.0.0-RC5
 io.rsocket                                    | rsocket-micrometer                          | 1.0.0-RC5
 io.rsocket                                    | rsocket-test                                | 1.0.0-RC5
 io.rsocket                                    | rsocket-transport-local                     | 1.0.0-RC5
 io.rsocket                                    | rsocket-transport-netty                     | 1.0.0-RC5
 io.searchbox                                  | jest                                        | 6.3.1
 io.spring.gradle                              | dependency-management-plugin                | 1.0.8.RELEASE
 io.undertow                                   | undertow-core                               | 2.0.28.Final
 io.undertow                                   | undertow-servlet                            | 2.0.28.Final
 io.undertow                                   | undertow-websockets-jsr                     | 2.0.28.Final
 jakarta.activation                            | jakarta.activation-api                      | 1.2.1
 jakarta.annotation                            | jakarta.annotation-api                      | 1.3.5
 jakarta.jms                                   | jakarta.jms-api                             | 2.0.3
 jakarta.json                                  | jakarta.json-api                            | 1.1.6
 jakarta.json.bind                             | jakarta.json.bind-api                       | 1.0.2
 jakarta.mail                                  | jakarta.mail-api                            | 1.6.4
 jakarta.persistence                           | jakarta.persistence-api                     | 2.2.3
 jakarta.servlet                               | jakarta.servlet-api                         | 4.0.3
 jakarta.servlet.jsp.jstl                      | jakarta.servlet.jsp.jstl-api                | 1.2.7
 jakarta.transaction                           | jakarta.transaction-api                     | 1.3.3
 jakarta.validation                            | jakarta.validation-api                      | 2.0.1
 jakarta.websocket                             | jakarta.websocket-api                       | 1.1.2
 jakarta.ws.rs                                 | jakarta.ws.rs-api                           | 2.1.6
 jakarta.xml.bind                              | jakarta.xml.bind-api                        | 2.3.2
 jakarta.xml.ws                                | jakarta.xml.ws-api                          | 2.3.2
 javax.activation                              | javax.activation-api                        | 1.2.0
 javax.annotation                              | javax.annotation-api                        | 1.3.2
 javax.cache                                   | cache-api                                   | 1.1.1
 javax.jms                                     | javax.jms-api                               | 2.0.1
 javax.json                                    | javax.json-api                              | 1.1.4
 javax.json.bind                               | javax.json.bind-api                         | 1.0
 javax.mail                                    | javax.mail-api                              | 1.6.2
 javax.money                                   | money-api                                   | 1.0.3
 javax.persistence                             | javax.persistence-api                       | 2.2
 javax.servlet                                 | javax.servlet-api                           | 4.0.1
 javax.servlet                                 | jstl                                        | 1.2
 javax.transaction                             | javax.transaction-api                       | 1.3
 javax.validation                              | validation-api                              | 2.0.1.Final
 javax.websocket                               | javax.websocket-api                         | 1.1
 javax.xml.bind                                | jaxb-api                                    | 2.3.1
 javax.xml.ws                                  | jaxws-api                                   | 2.3.1
 jaxen                                         | jaxen                                       | 1.2.0
 joda-time                                     | joda-time                                   | 2.10.5
 junit                                         | junit                                       | 4.12
 mysql                                         | mysql-connector-java                        | 8.0.18
 net.bytebuddy                                 | byte-buddy                                  | 1.10.4
 net.bytebuddy                                 | byte-buddy-agent                            | 1.10.4
 net.java.dev.jna                              | jna                                         | 4.5.2
 net.java.dev.jna                              | jna-platform                                | 4.5.2
 net.sf.ehcache                                | ehcache                                     | 2.10.6
 net.sourceforge.htmlunit                      | htmlunit                                    | 2.36.0
 net.sourceforge.jtds                          | jtds                                        | 1.3.1
 net.sourceforge.nekohtml                      | nekohtml                                    | 1.9.22
 nz.net.ultraq.thymeleaf                       | thymeleaf-layout-dialect                    | 2.4.1
 org.apache.activemq                           | activemq-amqp                               | 5.15.11
 org.apache.activemq                           | activemq-blueprint                          | 5.15.11
 org.apache.activemq                           | activemq-broker                             | 5.15.11
 org.apache.activemq                           | activemq-camel                              | 5.15.11
 org.apache.activemq                           | activemq-client                             | 5.15.11
 org.apache.activemq                           | activemq-console                            | 5.15.11
 org.apache.activemq                           | activemq-http                               | 5.15.11
 org.apache.activemq                           | activemq-jaas                               | 5.15.11
 org.apache.activemq                           | activemq-jdbc-store                         | 5.15.11
 org.apache.activemq                           | activemq-jms-pool                           | 5.15.11
 org.apache.activemq                           | activemq-kahadb-store                       | 5.15.11
 org.apache.activemq                           | activemq-karaf                              | 5.15.11
 org.apache.activemq                           | activemq-leveldb-store                      | 5.15.11
 org.apache.activemq                           | activemq-log4j-appender                     | 5.15.11
 org.apache.activemq                           | activemq-mqtt                               | 5.15.11
 org.apache.activemq                           | activemq-openwire-generator                 | 5.15.11
 org.apache.activemq                           | activemq-openwire-legacy                    | 5.15.11
 org.apache.activemq                           | activemq-osgi                               | 5.15.11
 org.apache.activemq                           | activemq-partition                          | 5.15.11
 org.apache.activemq                           | activemq-pool                               | 5.15.11
 org.apache.activemq                           | activemq-ra                                 | 5.15.11
 org.apache.activemq                           | activemq-run                                | 5.15.11
 org.apache.activemq                           | activemq-runtime-config                     | 5.15.11
 org.apache.activemq                           | activemq-shiro                              | 5.15.11
 org.apache.activemq                           | activemq-spring                             | 5.15.11
 org.apache.activemq                           | activemq-stomp                              | 5.15.11
 org.apache.activemq                           | activemq-web                                | 5.15.11
 org.apache.activemq                           | artemis-amqp-protocol                       | 2.10.1
 org.apache.activemq                           | artemis-commons                             | 2.10.1
 org.apache.activemq                           | artemis-core-client                         | 2.10.1
 org.apache.activemq                           | artemis-jms-client                          | 2.10.1
 org.apache.activemq                           | artemis-jms-server                          | 2.10.1
 org.apache.activemq                           | artemis-journal                             | 2.10.1
 org.apache.activemq                           | artemis-selector                            | 2.10.1
 org.apache.activemq                           | artemis-server                              | 2.10.1
 org.apache.activemq                           | artemis-service-extensions                  | 2.10.1
 org.apache.commons                            | commons-dbcp2                               | 2.7.0
 org.apache.commons                            | commons-lang3                               | 3.9
 org.apache.commons                            | commons-pool2                               | 2.7.0
 org.apache.derby                              | derby                                       | 10.14.2.0
 org.apache.httpcomponents                     | fluent-hc                                   | 4.5.10
 org.apache.httpcomponents                     | httpasyncclient                             | 4.1.4
 org.apache.httpcomponents                     | httpclient                                  | 4.5.10
 org.apache.httpcomponents                     | httpclient-cache                            | 4.5.10
 org.apache.httpcomponents                     | httpclient-osgi                             | 4.5.10
 org.apache.httpcomponents                     | httpclient-win                              | 4.5.10
 org.apache.httpcomponents                     | httpcore                                    | 4.4.12
 org.apache.httpcomponents                     | httpcore-nio                                | 4.4.12
 org.apache.httpcomponents                     | httpmime                                    | 4.5.10
 org.apache.johnzon                            | johnzon-core                                | 1.2.2
 org.apache.johnzon                            | johnzon-jaxrs                               | 1.2.2
 org.apache.johnzon                            | johnzon-jsonb                               | 1.2.2
 org.apache.johnzon                            | johnzon-jsonb-extras                        | 1.2.2
 org.apache.johnzon                            | johnzon-jsonschema                          | 1.2.2
 org.apache.johnzon                            | johnzon-mapper                              | 1.2.2
 org.apache.johnzon                            | johnzon-websocket                           | 1.2.2
 org.apache.kafka                              | connect-api                                 | 2.3.1
 org.apache.kafka                              | connect-basic-auth-extension                | 2.3.1
 org.apache.kafka                              | connect-file                                | 2.3.1
 org.apache.kafka                              | connect-json                                | 2.3.1
 org.apache.kafka                              | connect-runtime                             | 2.3.1
 org.apache.kafka                              | connect-transforms                          | 2.3.1
 org.apache.kafka                              | kafka_2.11                                  | 2.3.1
 org.apache.kafka                              | kafka_2.12                                  | 2.3.1
 org.apache.kafka                              | kafka-clients                               | 2.3.1
 org.apache.kafka                              | kafka-log4j-appender                        | 2.3.1
 org.apache.kafka                              | kafka-streams                               | 2.3.1
 org.apache.kafka                              | kafka-streams-scala_2.11                    | 2.3.1
 org.apache.kafka                              | kafka-streams-scala_2.12                    | 2.3.1
 org.apache.kafka                              | kafka-streams-test-utils                    | 2.3.1
 org.apache.kafka                              | kafka-tools                                 | 2.3.1
 org.apache.logging.log4j                      | log4j-1.2-api                               | 2.12.1
 org.apache.logging.log4j                      | log4j-api                                   | 2.12.1
 org.apache.logging.log4j                      | log4j-appserver                             | 2.12.1
 org.apache.logging.log4j                      | log4j-cassandra                             | 2.12.1
 org.apache.logging.log4j                      | log4j-core                                  | 2.12.1
 org.apache.logging.log4j                      | log4j-couchdb                               | 2.12.1
 org.apache.logging.log4j                      | log4j-docker                                | 2.12.1
 org.apache.logging.log4j                      | log4j-flume-ng                              | 2.12.1
 org.apache.logging.log4j                      | log4j-iostreams                             | 2.12.1
 org.apache.logging.log4j                      | log4j-jcl                                   | 2.12.1
 org.apache.logging.log4j                      | log4j-jmx-gui                               | 2.12.1
 org.apache.logging.log4j                      | log4j-jpa                                   | 2.12.1
 org.apache.logging.log4j                      | log4j-jul                                   | 2.12.1
 org.apache.logging.log4j                      | log4j-liquibase                             | 2.12.1
 org.apache.logging.log4j                      | log4j-mongodb2                              | 2.12.1
 org.apache.logging.log4j                      | log4j-mongodb3                              | 2.12.1
 org.apache.logging.log4j                      | log4j-slf4j18-impl                          | 2.12.1
 org.apache.logging.log4j                      | log4j-slf4j-impl                            | 2.12.1
 org.apache.logging.log4j                      | log4j-spring-cloud-config-client            | 2.12.1
 org.apache.logging.log4j                      | log4j-taglib                                | 2.12.1
 org.apache.logging.log4j                      | log4j-to-slf4j                              | 2.12.1
 org.apache.logging.log4j                      | log4j-web                                   | 2.12.1
 org.apache.solr                               | solr-analysis-extras                        | 8.2.0
 org.apache.solr                               | solr-analytics                              | 8.2.0
 org.apache.solr                               | solr-cell                                   | 8.2.0
 org.apache.solr                               | solr-clustering                             | 8.2.0
 org.apache.solr                               | solr-core                                   | 8.2.0
 org.apache.solr                               | solr-dataimporthandler                      | 8.2.0
 org.apache.solr                               | solr-dataimporthandler-extras               | 8.2.0
 org.apache.solr                               | solr-langid                                 | 8.2.0
 org.apache.solr                               | solr-ltr                                    | 8.2.0
 org.apache.solr                               | solr-solrj                                  | 8.2.0
 org.apache.solr                               | solr-test-framework                         | 8.2.0
 org.apache.solr                               | solr-velocity                               | 8.2.0
 org.apache.tomcat                             | tomcat-annotations-api                      | 9.0.29
 org.apache.tomcat                             | tomcat-jdbc                                 | 9.0.29
 org.apache.tomcat                             | tomcat-jsp-api                              | 9.0.29
 org.apache.tomcat.embed                       | tomcat-embed-core                           | 9.0.29
 org.apache.tomcat.embed                       | tomcat-embed-el                             | 9.0.29
 org.apache.tomcat.embed                       | tomcat-embed-jasper                         | 9.0.29
 org.apache.tomcat.embed                       | tomcat-embed-websocket                      | 9.0.29
 org.aspectj                                   | aspectjrt                                   | 1.9.5
 org.aspectj                                   | aspectjtools                                | 1.9.5
 org.aspectj                                   | aspectjweaver                               | 1.9.5
 org.assertj                                   | assertj-core                                | 3.13.2
 org.awaitility                                | awaitility                                  | 4.0.1
 org.awaitility                                | awaitility-groovy                           | 4.0.1
 org.awaitility                                | awaitility-kotlin                           | 4.0.1
 org.awaitility                                | awaitility-scala                            | 4.0.1
 org.codehaus.btm                              | btm                                         | 2.1.4
 org.codehaus.groovy                           | groovy                                      | 2.5.8
 org.codehaus.groovy                           | groovy-ant                                  | 2.5.8
 org.codehaus.groovy                           | groovy-backports-compat23                   | 2.5.8
 org.codehaus.groovy                           | groovy-bsf                                  | 2.5.8
 org.codehaus.groovy                           | groovy-cli-commons                          | 2.5.8
 org.codehaus.groovy                           | groovy-cli-picocli                          | 2.5.8
 org.codehaus.groovy                           | groovy-console                              | 2.5.8
 org.codehaus.groovy                           | groovy-datetime                             | 2.5.8
 org.codehaus.groovy                           | groovy-dateutil                             | 2.5.8
 org.codehaus.groovy                           | groovy-docgenerator                         | 2.5.8
 org.codehaus.groovy                           | groovy-groovydoc                            | 2.5.8
 org.codehaus.groovy                           | groovy-groovysh                             | 2.5.8
 org.codehaus.groovy                           | groovy-jaxb                                 | 2.5.8
 org.codehaus.groovy                           | groovy-jmx                                  | 2.5.8
 org.codehaus.groovy                           | groovy-json                                 | 2.5.8
 org.codehaus.groovy                           | groovy-json-direct                          | 2.5.8
 org.codehaus.groovy                           | groovy-jsr223                               | 2.5.8
 org.codehaus.groovy                           | groovy-macro                                | 2.5.8
 org.codehaus.groovy                           | groovy-nio                                  | 2.5.8
 org.codehaus.groovy                           | groovy-servlet                              | 2.5.8
 org.codehaus.groovy                           | groovy-sql                                  | 2.5.8
 org.codehaus.groovy                           | groovy-swing                                | 2.5.8
 org.codehaus.groovy                           | groovy-templates                            | 2.5.8
 org.codehaus.groovy                           | groovy-test                                 | 2.5.8
 org.codehaus.groovy                           | groovy-test-junit5                          | 2.5.8
 org.codehaus.groovy                           | groovy-testng                               | 2.5.8
 org.codehaus.groovy                           | groovy-xml                                  | 2.5.8
 org.codehaus.janino                           | commons-compiler                            | 3.1.0
 org.codehaus.janino                           | commons-compiler-jdk                        | 3.1.0
 org.codehaus.janino                           | janino                                      | 3.1.0
 org.eclipse.jetty                             | apache-jsp                                  | 9.4.24.v20191120
 org.eclipse.jetty                             | apache-jstl                                 | 9.4.24.v20191120
 org.eclipse.jetty                             | infinispan-common                           | 9.4.24.v20191120
 org.eclipse.jetty                             | infinispan-embedded-query                   | 9.4.24.v20191120
 org.eclipse.jetty                             | infinispan-remote-query                     | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-alpn-client                           | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-alpn-conscrypt-client                 | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-alpn-conscrypt-server                 | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-alpn-java-client                      | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-alpn-java-server                      | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-alpn-openjdk8-client                  | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-alpn-openjdk8-server                  | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-alpn-server                           | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-annotations                           | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-ant                                   | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-client                                | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-continuation                          | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-deploy                                | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-distribution                          | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-hazelcast                             | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-home                                  | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-http                                  | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-http-spi                              | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-io                                    | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-jaas                                  | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-jaspi                                 | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-jmx                                   | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-jndi                                  | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-nosql                                 | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-openid                                | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-plus                                  | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-proxy                                 | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-quickstart                            | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-reactive-httpclient                   | 1.0.3
 org.eclipse.jetty                             | jetty-rewrite                               | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-security                              | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-server                                | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-servlet                               | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-servlets                              | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-spring                                | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-unixsocket                            | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-util                                  | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-util-ajax                             | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-webapp                                | 9.4.24.v20191120
 org.eclipse.jetty                             | jetty-xml                                   | 9.4.24.v20191120
 org.eclipse.jetty.fcgi                        | fcgi-client                                 | 9.4.24.v20191120
 org.eclipse.jetty.fcgi                        | fcgi-server                                 | 9.4.24.v20191120
 org.eclipse.jetty.gcloud                      | jetty-gcloud-session-manager                | 9.4.24.v20191120
 org.eclipse.jetty.http2                       | http2-client                                | 9.4.24.v20191120
 org.eclipse.jetty.http2                       | http2-common                                | 9.4.24.v20191120
 org.eclipse.jetty.http2                       | http2-hpack                                 | 9.4.24.v20191120
 org.eclipse.jetty.http2                       | http2-http-client-transport                 | 9.4.24.v20191120
 org.eclipse.jetty.http2                       | http2-server                                | 9.4.24.v20191120
 org.eclipse.jetty.memcached                   | jetty-memcached-sessions                    | 9.4.24.v20191120
 org.eclipse.jetty.orbit                       | javax.servlet.jsp                           | 2.2.0.v201112011158
 org.eclipse.jetty.osgi                        | jetty-httpservice                           | 9.4.24.v20191120
 org.eclipse.jetty.osgi                        | jetty-osgi-boot                             | 9.4.24.v20191120
 org.eclipse.jetty.osgi                        | jetty-osgi-boot-jsp                         | 9.4.24.v20191120
 org.eclipse.jetty.osgi                        | jetty-osgi-boot-warurl                      | 9.4.24.v20191120
 org.eclipse.jetty.websocket                   | javax-websocket-client-impl                 | 9.4.24.v20191120
 org.eclipse.jetty.websocket                   | javax-websocket-server-impl                 | 9.4.24.v20191120
 org.eclipse.jetty.websocket                   | websocket-api                               | 9.4.24.v20191120
 org.eclipse.jetty.websocket                   | websocket-client                            | 9.4.24.v20191120
 org.eclipse.jetty.websocket                   | websocket-common                            | 9.4.24.v20191120
 org.eclipse.jetty.websocket                   | websocket-server                            | 9.4.24.v20191120
 org.eclipse.jetty.websocket                   | websocket-servlet                           | 9.4.24.v20191120
 org.ehcache                                   | ehcache                                     | 3.8.1
 org.ehcache                                   | ehcache-clustered                           | 3.8.1
 org.ehcache                                   | ehcache-transactions                        | 3.8.1
 org.elasticsearch                             | elasticsearch                               | 6.8.5
 org.elasticsearch.client                      | elasticsearch-rest-client                   | 6.8.5
 org.elasticsearch.client                      | elasticsearch-rest-high-level-client        | 6.8.5
 org.elasticsearch.client                      | transport                                   | 6.8.5
 org.elasticsearch.distribution.integ-test-zip | elasticsearch                               | 6.8.5
 org.elasticsearch.plugin                      | transport-netty4-client                     | 6.8.5
 org.firebirdsql.jdbc                          | jaybird-jdk17                               | 3.0.8
 org.firebirdsql.jdbc                          | jaybird-jdk18                               | 3.0.8
 org.flywaydb                                  | flyway-core                                 | 6.0.8
 org.freemarker                                | freemarker                                  | 2.3.29
 org.glassfish                                 | jakarta.el                                  | 3.0.3
 org.glassfish.jaxb                            | codemodel                                   | 2.3.2
 org.glassfish.jaxb                            | codemodel-annotation-compiler               | 2.3.2
 org.glassfish.jaxb                            | jaxb-jxc                                    | 2.3.2
 org.glassfish.jaxb                            | jaxb-runtime                                | 2.3.2
 org.glassfish.jaxb                            | jaxb-xjc                                    | 2.3.2
 org.glassfish.jaxb                            | txw2                                        | 2.3.2
 org.glassfish.jaxb                            | txwc2                                       | 2.3.2
 org.glassfish.jaxb                            | xsom                                        | 2.3.2
 org.glassfish.jersey.bundles                  | jaxrs-ri                                    | 2.29.1
 org.glassfish.jersey.connectors               | jersey-apache-connector                     | 2.29.1
 org.glassfish.jersey.connectors               | jersey-grizzly-connector                    | 2.29.1
 org.glassfish.jersey.connectors               | jersey-jdk-connector                        | 2.29.1
 org.glassfish.jersey.connectors               | jersey-jetty-connector                      | 2.29.1
 org.glassfish.jersey.connectors               | jersey-netty-connector                      | 2.29.1
 org.glassfish.jersey.containers               | jersey-container-grizzly2-http              | 2.29.1
 org.glassfish.jersey.containers               | jersey-container-grizzly2-servlet           | 2.29.1
 org.glassfish.jersey.containers               | jersey-container-jdk-http                   | 2.29.1
 org.glassfish.jersey.containers               | jersey-container-jetty-http                 | 2.29.1
 org.glassfish.jersey.containers               | jersey-container-jetty-servlet              | 2.29.1
 org.glassfish.jersey.containers               | jersey-container-netty-http                 | 2.29.1
 org.glassfish.jersey.containers               | jersey-container-servlet                    | 2.29.1
 org.glassfish.jersey.containers               | jersey-container-servlet-core               | 2.29.1
 org.glassfish.jersey.containers               | jersey-container-simple-http                | 2.29.1
 org.glassfish.jersey.containers.glassfish     | jersey-gf-ejb                               | 2.29.1
 org.glassfish.jersey.core                     | jersey-client                               | 2.29.1
 org.glassfish.jersey.core                     | jersey-common                               | 2.29.1
 org.glassfish.jersey.core                     | jersey-server                               | 2.29.1
 org.glassfish.jersey.ext                      | jersey-bean-validation                      | 2.29.1
 org.glassfish.jersey.ext                      | jersey-declarative-linking                  | 2.29.1
 org.glassfish.jersey.ext                      | jersey-entity-filtering                     | 2.29.1
 org.glassfish.jersey.ext                      | jersey-metainf-services                     | 2.29.1
 org.glassfish.jersey.ext                      | jersey-mvc                                  | 2.29.1
 org.glassfish.jersey.ext                      | jersey-mvc-bean-validation                  | 2.29.1
 org.glassfish.jersey.ext                      | jersey-mvc-freemarker                       | 2.29.1
 org.glassfish.jersey.ext                      | jersey-mvc-jsp                              | 2.29.1
 org.glassfish.jersey.ext                      | jersey-mvc-mustache                         | 2.29.1
 org.glassfish.jersey.ext                      | jersey-proxy-client                         | 2.29.1
 org.glassfish.jersey.ext                      | jersey-servlet-portability                  | 2.29.1
 org.glassfish.jersey.ext                      | jersey-spring4                              | 2.29.1
 org.glassfish.jersey.ext                      | jersey-spring5                              | 2.29.1
 org.glassfish.jersey.ext                      | jersey-wadl-doclet                          | 2.29.1
 org.glassfish.jersey.ext.cdi                  | jersey-cdi1x                                | 2.29.1
 org.glassfish.jersey.ext.cdi                  | jersey-cdi1x-ban-custom-hk2-binding         | 2.29.1
 org.glassfish.jersey.ext.cdi                  | jersey-cdi1x-servlet                        | 2.29.1
 org.glassfish.jersey.ext.cdi                  | jersey-cdi1x-transaction                    | 2.29.1
 org.glassfish.jersey.ext.cdi                  | jersey-cdi1x-validation                     | 2.29.1
 org.glassfish.jersey.ext.cdi                  | jersey-weld2-se                             | 2.29.1
 org.glassfish.jersey.ext.microprofile         | jersey-mp-config                            | 2.29.1
 org.glassfish.jersey.ext.microprofile         | jersey-mp-rest-client                       | 2.29.1
 org.glassfish.jersey.ext.rx                   | jersey-rx-client-guava                      | 2.29.1
 org.glassfish.jersey.ext.rx                   | jersey-rx-client-rxjava                     | 2.29.1
 org.glassfish.jersey.ext.rx                   | jersey-rx-client-rxjava2                    | 2.29.1
 org.glassfish.jersey.inject                   | jersey-cdi2-se                              | 2.29.1
 org.glassfish.jersey.inject                   | jersey-hk2                                  | 2.29.1
 org.glassfish.jersey.media                    | jersey-media-jaxb                           | 2.29.1
 org.glassfish.jersey.media                    | jersey-media-json-binding                   | 2.29.1
 org.glassfish.jersey.media                    | jersey-media-json-jackson                   | 2.29.1
 org.glassfish.jersey.media                    | jersey-media-json-jackson1                  | 2.29.1
 org.glassfish.jersey.media                    | jersey-media-json-jettison                  | 2.29.1
 org.glassfish.jersey.media                    | jersey-media-json-processing                | 2.29.1
 org.glassfish.jersey.media                    | jersey-media-kryo                           | 2.29.1
 org.glassfish.jersey.media                    | jersey-media-moxy                           | 2.29.1
 org.glassfish.jersey.media                    | jersey-media-multipart                      | 2.29.1
 org.glassfish.jersey.media                    | jersey-media-sse                            | 2.29.1
 org.glassfish.jersey.security                 | oauth1-client                               | 2.29.1
 org.glassfish.jersey.security                 | oauth1-server                               | 2.29.1
 org.glassfish.jersey.security                 | oauth1-signature                            | 2.29.1
 org.glassfish.jersey.security                 | oauth2-client                               | 2.29.1
 org.glassfish.jersey.test-framework           | jersey-test-framework-core                  | 2.29.1
 org.glassfish.jersey.test-framework           | jersey-test-framework-util                  | 2.29.1
 org.glassfish.jersey.test-framework.providers | jersey-test-framework-provider-bundle       | 2.29.1
 org.glassfish.jersey.test-framework.providers | jersey-test-framework-provider-external     | 2.29.1
 org.glassfish.jersey.test-framework.providers | jersey-test-framework-provider-grizzly2     | 2.29.1
 org.glassfish.jersey.test-framework.providers | jersey-test-framework-provider-inmemory     | 2.29.1
 org.glassfish.jersey.test-framework.providers | jersey-test-framework-provider-jdk-http     | 2.29.1
 org.glassfish.jersey.test-framework.providers | jersey-test-framework-provider-jetty        | 2.29.1
 org.glassfish.jersey.test-framework.providers | jersey-test-framework-provider-simple       | 2.29.1
 org.hamcrest                                  | hamcrest                                    | 2.1
 org.hamcrest                                  | hamcrest-core                               | 2.1
 org.hamcrest                                  | hamcrest-library                            | 2.1
 org.hibernate                                 | hibernate-c3p0                              | 5.4.9.Final
 org.hibernate                                 | hibernate-core                              | 5.4.9.Final
 org.hibernate                                 | hibernate-ehcache                           | 5.4.9.Final
 org.hibernate                                 | hibernate-entitymanager                     | 5.4.9.Final
 org.hibernate                                 | hibernate-envers                            | 5.4.9.Final
 org.hibernate                                 | hibernate-hikaricp                          | 5.4.9.Final
 org.hibernate                                 | hibernate-java8                             | 5.4.9.Final
 org.hibernate                                 | hibernate-jcache                            | 5.4.9.Final
 org.hibernate                                 | hibernate-jpamodelgen                       | 5.4.9.Final
 org.hibernate                                 | hibernate-proxool                           | 5.4.9.Final
 org.hibernate                                 | hibernate-spatial                           | 5.4.9.Final
 org.hibernate                                 | hibernate-testing                           | 5.4.9.Final
 org.hibernate                                 | hibernate-vibur                             | 5.4.9.Final
 org.hibernate.validator                       | hibernate-validator                         | 6.0.18.Final
 org.hibernate.validator                       | hibernate-validator-annotation-processor    | 6.0.18.Final
 org.hsqldb                                    | hsqldb                                      | 2.5.0
 org.infinispan                                | infinispan-cachestore-jdbc                  | 9.4.16.Final
 org.infinispan                                | infinispan-cachestore-jpa                   | 9.4.16.Final
 org.infinispan                                | infinispan-cachestore-leveldb               | 9.4.16.Final
 org.infinispan                                | infinispan-cachestore-remote                | 9.4.16.Final
 org.infinispan                                | infinispan-cachestore-rest                  | 9.4.16.Final
 org.infinispan                                | infinispan-cachestore-rocksdb               | 9.4.16.Final
 org.infinispan                                | infinispan-cdi-common                       | 9.4.16.Final
 org.infinispan                                | infinispan-cdi-embedded                     | 9.4.16.Final
 org.infinispan                                | infinispan-cdi-remote                       | 9.4.16.Final
 org.infinispan                                | infinispan-client-hotrod                    | 9.4.16.Final
 org.infinispan                                | infinispan-cloud                            | 9.4.16.Final
 org.infinispan                                | infinispan-clustered-counter                | 9.4.16.Final
 org.infinispan                                | infinispan-clustered-lock                   | 9.4.16.Final
 org.infinispan                                | infinispan-commons                          | 9.4.16.Final
 org.infinispan                                | infinispan-core                             | 9.4.16.Final
 org.infinispan                                | infinispan-directory-provider               | 9.4.16.Final
 org.infinispan                                | infinispan-hibernate-cache-v53              | 9.4.16.Final
 org.infinispan                                | infinispan-jcache                           | 9.4.16.Final
 org.infinispan                                | infinispan-jcache-commons                   | 9.4.16.Final
 org.infinispan                                | infinispan-jcache-remote                    | 9.4.16.Final
 org.infinispan                                | infinispan-lucene-directory                 | 9.4.16.Final
 org.infinispan                                | infinispan-objectfilter                     | 9.4.16.Final
 org.infinispan                                | infinispan-osgi                             | 9.4.16.Final
 org.infinispan                                | infinispan-persistence-cli                  | 9.4.16.Final
 org.infinispan                                | infinispan-persistence-soft-index           | 9.4.16.Final
 org.infinispan                                | infinispan-query                            | 9.4.16.Final
 org.infinispan                                | infinispan-query-dsl                        | 9.4.16.Final
 org.infinispan                                | infinispan-remote-query-client              | 9.4.16.Final
 org.infinispan                                | infinispan-remote-query-server              | 9.4.16.Final
 org.infinispan                                | infinispan-scripting                        | 9.4.16.Final
 org.infinispan                                | infinispan-server-core                      | 9.4.16.Final
 org.infinispan                                | infinispan-server-hotrod                    | 9.4.16.Final
 org.infinispan                                | infinispan-server-memcached                 | 9.4.16.Final
 org.infinispan                                | infinispan-server-router                    | 9.4.16.Final
 org.infinispan                                | infinispan-spring4-common                   | 9.4.16.Final
 org.infinispan                                | infinispan-spring4-embedded                 | 9.4.16.Final
 org.infinispan                                | infinispan-spring4-remote                   | 9.4.16.Final
 org.infinispan                                | infinispan-spring5-common                   | 9.4.16.Final
 org.infinispan                                | infinispan-spring5-embedded                 | 9.4.16.Final
 org.infinispan                                | infinispan-spring5-remote                   | 9.4.16.Final
 org.infinispan                                | infinispan-tasks                            | 9.4.16.Final
 org.infinispan                                | infinispan-tasks-api                        | 9.4.16.Final
 org.infinispan                                | infinispan-tools                            | 9.4.16.Final
 org.infinispan                                | infinispan-tree                             | 9.4.16.Final
 org.influxdb                                  | influxdb-java                               | 2.15
 org.jboss                                     | jboss-transaction-spi                       | 7.6.0.Final
 org.jboss.logging                             | jboss-logging                               | 3.4.1.Final
 org.jdom                                      | jdom2                                       | 2.0.6
 org.jetbrains.kotlin                          | kotlin-compiler                             | 1.3.61
 org.jetbrains.kotlin                          | kotlin-compiler-embeddable                  | 1.3.61
 org.jetbrains.kotlin                          | kotlin-daemon-client                        | 1.3.61
 org.jetbrains.kotlin                          | kotlin-main-kts                             | 1.3.61
 org.jetbrains.kotlin                          | kotlin-osgi-bundle                          | 1.3.61
 org.jetbrains.kotlin                          | kotlin-reflect                              | 1.3.61
 org.jetbrains.kotlin                          | kotlin-scripting-common                     | 1.3.61
 org.jetbrains.kotlin                          | kotlin-scripting-jvm                        | 1.3.61
 org.jetbrains.kotlin                          | kotlin-scripting-jvm-host                   | 1.3.61
 org.jetbrains.kotlin                          | kotlin-script-runtime                       | 1.3.61
 org.jetbrains.kotlin                          | kotlin-script-util                          | 1.3.61
 org.jetbrains.kotlin                          | kotlin-stdlib                               | 1.3.61
 org.jetbrains.kotlin                          | kotlin-stdlib-common                        | 1.3.61
 org.jetbrains.kotlin                          | kotlin-stdlib-jdk7                          | 1.3.61
 org.jetbrains.kotlin                          | kotlin-stdlib-jdk8                          | 1.3.61
 org.jetbrains.kotlin                          | kotlin-stdlib-js                            | 1.3.61
 org.jetbrains.kotlin                          | kotlin-test                                 | 1.3.61
 org.jetbrains.kotlin                          | kotlin-test-annotations-common              | 1.3.61
 org.jetbrains.kotlin                          | kotlin-test-common                          | 1.3.61
 org.jetbrains.kotlin                          | kotlin-test-js                              | 1.3.61
 org.jetbrains.kotlin                          | kotlin-test-junit                           | 1.3.61
 org.jetbrains.kotlin                          | kotlin-test-junit5                          | 1.3.61
 org.jetbrains.kotlin                          | kotlin-test-testng                          | 1.3.61
 org.jetbrains.kotlinx                         | kotlinx-coroutines-android                  | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-core                     | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-core-common              | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-core-js                  | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-core-linuxx64            | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-core-native              | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-debug                    | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-guava                    | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-javafx                   | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-jdk8                     | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-play-services            | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-reactive                 | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-reactor                  | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-rx2                      | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-slf4j                    | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-swing                    | 1.3.2
 org.jetbrains.kotlinx                         | kotlinx-coroutines-test                     | 1.3.2
 org.jolokia                                   | jolokia-core                                | 1.6.2
 org.jooq                                      | jooq                                        | 3.12.3
 org.jooq                                      | jooq-codegen                                | 3.12.3
 org.jooq                                      | jooq-meta                                   | 3.12.3
 org.junit.jupiter                             | junit-jupiter                               | 5.5.2
 org.junit.jupiter                             | junit-jupiter-api                           | 5.5.2
 org.junit.jupiter                             | junit-jupiter-engine                        | 5.5.2
 org.junit.jupiter                             | junit-jupiter-migrationsupport              | 5.5.2
 org.junit.jupiter                             | junit-jupiter-params                        | 5.5.2
 org.junit.platform                            | junit-platform-commons                      | 1.5.2
 org.junit.platform                            | junit-platform-console                      | 1.5.2
 org.junit.platform                            | junit-platform-engine                       | 1.5.2
 org.junit.platform                            | junit-platform-launcher                     | 1.5.2
 org.junit.platform                            | junit-platform-reporting                    | 1.5.2
 org.junit.platform                            | junit-platform-runner                       | 1.5.2
 org.junit.platform                            | junit-platform-suite-api                    | 1.5.2
 org.junit.platform                            | junit-platform-testkit                      | 1.5.2
 org.junit.vintage                             | junit-vintage-engine                        | 5.5.2
 org.jvnet.mimepull                            | mimepull                                    | 1.9.12
 org.liquibase                                 | liquibase-core                              | 3.8.2
 org.mariadb.jdbc                              | mariadb-java-client                         | 2.4.4
 org.messaginghub                              | pooled-jms                                  | 1.0.6
 org.mockito                                   | mockito-core                                | 3.1.0
 org.mockito                                   | mockito-inline                              | 3.1.0
 org.mockito                                   | mockito-junit-jupiter                       | 3.1.0
 org.mongodb                                   | bson                                        | 3.11.2
 org.mongodb                                   | mongodb-driver                              | 3.11.2
 org.mongodb                                   | mongodb-driver-async                        | 3.11.2
 org.mongodb                                   | mongodb-driver-core                         | 3.11.2
 org.mongodb                                   | mongodb-driver-reactivestreams              | 1.12.0
 org.mongodb                                   | mongo-java-driver                           | 3.11.2
 org.mortbay.jasper                            | apache-el                                   | 8.5.49
 org.neo4j                                     | neo4j-ogm-api                               | 3.2.3
 org.neo4j                                     | neo4j-ogm-bolt-driver                       | 3.2.3
 org.neo4j                                     | neo4j-ogm-bolt-native-types                 | 3.2.3
 org.neo4j                                     | neo4j-ogm-core                              | 3.2.3
 org.neo4j                                     | neo4j-ogm-embedded-driver                   | 3.2.3
 org.neo4j                                     | neo4j-ogm-embedded-native-types             | 3.2.3
 org.neo4j                                     | neo4j-ogm-http-driver                       | 3.2.3
 org.postgresql                                | postgresql                                  | 42.2.8
 org.projectlombok                             | lombok                                      | 1.18.10
 org.quartz-scheduler                          | quartz                                      | 2.3.2
 org.quartz-scheduler                          | quartz-jobs                                 | 2.3.2
 org.reactivestreams                           | reactive-streams                            | 1.0.3
 org.seleniumhq.selenium                       | htmlunit-driver                             | 2.36.0
 org.seleniumhq.selenium                       | selenium-api                                | 3.141.59
 org.seleniumhq.selenium                       | selenium-chrome-driver                      | 3.141.59
 org.seleniumhq.selenium                       | selenium-edge-driver                        | 3.141.59
 org.seleniumhq.selenium                       | selenium-firefox-driver                     | 3.141.59
 org.seleniumhq.selenium                       | selenium-ie-driver                          | 3.141.59
 org.seleniumhq.selenium                       | selenium-java                               | 3.141.59
 org.seleniumhq.selenium                       | selenium-opera-driver                       | 3.141.59
 org.seleniumhq.selenium                       | selenium-remote-driver                      | 3.141.59
 org.seleniumhq.selenium                       | selenium-safari-driver                      | 3.141.59
 org.seleniumhq.selenium                       | selenium-support                            | 3.141.59
 org.skyscreamer                               | jsonassert                                  | 1.5.0
 org.slf4j                                     | jcl-over-slf4j                              | 1.7.29
 org.slf4j                                     | jul-to-slf4j                                | 1.7.29
 org.slf4j                                     | log4j-over-slf4j                            | 1.7.29
 org.slf4j                                     | slf4j-api                                   | 1.7.29
 org.slf4j                                     | slf4j-ext                                   | 1.7.29
 org.slf4j                                     | slf4j-jcl                                   | 1.7.29
 org.slf4j                                     | slf4j-jdk14                                 | 1.7.29
 org.slf4j                                     | slf4j-log4j12                               | 1.7.29
 org.slf4j                                     | slf4j-nop                                   | 1.7.29
 org.slf4j                                     | slf4j-simple                                | 1.7.29
 org.springframework                           | spring-aop                                  | 5.2.2.RELEASE
 org.springframework                           | spring-aspects                              | 5.2.2.RELEASE
 org.springframework                           | spring-beans                                | 5.2.2.RELEASE
 org.springframework                           | spring-context                              | 5.2.2.RELEASE
 org.springframework                           | spring-context-indexer                      | 5.2.2.RELEASE
 org.springframework                           | spring-context-support                      | 5.2.2.RELEASE
 org.springframework                           | spring-core                                 | 5.2.2.RELEASE
 org.springframework                           | spring-expression                           | 5.2.2.RELEASE
 org.springframework                           | spring-instrument                           | 5.2.2.RELEASE
 org.springframework                           | spring-jcl                                  | 5.2.2.RELEASE
 org.springframework                           | spring-jdbc                                 | 5.2.2.RELEASE
 org.springframework                           | spring-jms                                  | 5.2.2.RELEASE
 org.springframework                           | spring-messaging                            | 5.2.2.RELEASE
 org.springframework                           | spring-orm                                  | 5.2.2.RELEASE
 org.springframework                           | spring-oxm                                  | 5.2.2.RELEASE
 org.springframework                           | spring-test                                 | 5.2.2.RELEASE
 org.springframework                           | spring-tx                                   | 5.2.2.RELEASE
 org.springframework                           | spring-web                                  | 5.2.2.RELEASE
 org.springframework                           | spring-webflux                              | 5.2.2.RELEASE
 org.springframework                           | spring-webmvc                               | 5.2.2.RELEASE
 org.springframework                           | spring-websocket                            | 5.2.2.RELEASE
 org.springframework.amqp                      | spring-amqp                                 | 2.2.2.RELEASE
 org.springframework.amqp                      | spring-rabbit                               | 2.2.2.RELEASE
 org.springframework.amqp                      | spring-rabbit-junit                         | 2.2.2.RELEASE
 org.springframework.amqp                      | spring-rabbit-test                          | 2.2.2.RELEASE
 org.springframework.batch                     | spring-batch-core                           | 4.2.1.RELEASE
 org.springframework.batch                     | spring-batch-infrastructure                 | 4.2.1.RELEASE
 org.springframework.batch                     | spring-batch-integration                    | 4.2.1.RELEASE
 org.springframework.batch                     | spring-batch-test                           | 4.2.1.RELEASE
 org.springframework.boot                      | spring-boot                                 | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-actuator                        | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-actuator-autoconfigure          | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-autoconfigure                   | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-autoconfigure-processor         | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-configuration-metadata          | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-configuration-processor         | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-devtools                        | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-loader                          | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-loader-tools                    | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-properties-migrator             | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter                         | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-activemq                | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-actuator                | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-amqp                    | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-aop                     | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-artemis                 | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-batch                   | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-cache                   | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-cloud-connectors        | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-cassandra          | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-cassandra-reactive | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-couchbase          | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-couchbase-reactive | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-elasticsearch      | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-jdbc               | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-jpa                | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-ldap               | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-mongodb            | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-mongodb-reactive   | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-neo4j              | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-redis              | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-redis-reactive     | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-rest               | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-data-solr               | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-freemarker              | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-groovy-templates        | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-hateoas                 | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-integration             | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-jdbc                    | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-jersey                  | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-jetty                   | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-jooq                    | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-json                    | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-jta-atomikos            | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-jta-bitronix            | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-log4j2                  | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-logging                 | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-mail                    | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-mustache                | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-oauth2-client           | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-oauth2-resource-server  | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-quartz                  | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-reactor-netty           | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-rsocket                 | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-security                | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-test                    | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-thymeleaf               | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-tomcat                  | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-undertow                | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-validation              | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-web                     | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-webflux                 | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-web-services            | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-starter-websocket               | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-test                            | 2.2.2.RELEASE
 org.springframework.boot                      | spring-boot-test-autoconfigure              | 2.2.2.RELEASE
 org.springframework.cloud                     | spring-cloud-cloudfoundry-connector         | 2.0.7.RELEASE
 org.springframework.cloud                     | spring-cloud-connectors-core                | 2.0.7.RELEASE
 org.springframework.cloud                     | spring-cloud-heroku-connector               | 2.0.7.RELEASE
 org.springframework.cloud                     | spring-cloud-localconfig-connector          | 2.0.7.RELEASE
 org.springframework.cloud                     | spring-cloud-spring-service-connector       | 2.0.7.RELEASE
 org.springframework.data                      | spring-data-cassandra                       | 2.2.3.RELEASE
 org.springframework.data                      | spring-data-commons                         | 2.2.3.RELEASE
 org.springframework.data                      | spring-data-couchbase                       | 3.2.3.RELEASE
 org.springframework.data                      | spring-data-elasticsearch                   | 3.2.3.RELEASE
 org.springframework.data                      | spring-data-envers                          | 2.2.3.RELEASE
 org.springframework.data                      | spring-data-gemfire                         | 2.2.3.RELEASE
 org.springframework.data                      | spring-data-geode                           | 2.2.3.RELEASE
 org.springframework.data                      | spring-data-jdbc                            | 1.1.3.RELEASE
 org.springframework.data                      | spring-data-jpa                             | 2.2.3.RELEASE
 org.springframework.data                      | spring-data-keyvalue                        | 2.2.3.RELEASE
 org.springframework.data                      | spring-data-ldap                            | 2.2.3.RELEASE
 org.springframework.data                      | spring-data-mongodb                         | 2.2.3.RELEASE
 org.springframework.data                      | spring-data-neo4j                           | 5.2.3.RELEASE
 org.springframework.data                      | spring-data-redis                           | 2.2.3.RELEASE
 org.springframework.data                      | spring-data-relational                      | 1.1.3.RELEASE
 org.springframework.data                      | spring-data-rest-core                       | 3.2.3.RELEASE
 org.springframework.data                      | spring-data-rest-hal-browser                | 3.2.3.RELEASE
 org.springframework.data                      | spring-data-rest-hal-explorer               | 3.2.3.RELEASE
 org.springframework.data                      | spring-data-rest-webmvc                     | 3.2.3.RELEASE
 org.springframework.data                      | spring-data-solr                            | 4.1.3.RELEASE
 org.springframework.hateoas                   | spring-hateoas                              | 1.0.2.RELEASE
 org.springframework.integration               | spring-integration-amqp                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-core                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-event                    | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-feed                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-file                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-ftp                      | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-gemfire                  | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-groovy                   | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-http                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-ip                       | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-jdbc                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-jms                      | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-jmx                      | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-jpa                      | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-mail                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-mongodb                  | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-mqtt                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-redis                    | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-rmi                      | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-rsocket                  | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-scripting                | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-security                 | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-sftp                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-stomp                    | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-stream                   | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-syslog                   | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-test                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-test-support             | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-webflux                  | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-websocket                | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-ws                       | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-xml                      | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-xmpp                     | 5.2.2.RELEASE
 org.springframework.integration               | spring-integration-zookeeper                | 5.2.2.RELEASE
 org.springframework.kafka                     | spring-kafka                                | 2.3.4.RELEASE
 org.springframework.kafka                     | spring-kafka-test                           | 2.3.4.RELEASE
 org.springframework.ldap                      | spring-ldap-core                            | 2.3.2.RELEASE
 org.springframework.ldap                      | spring-ldap-core-tiger                      | 2.3.2.RELEASE
 org.springframework.ldap                      | spring-ldap-ldif-batch                      | 2.3.2.RELEASE
 org.springframework.ldap                      | spring-ldap-ldif-core                       | 2.3.2.RELEASE
 org.springframework.ldap                      | spring-ldap-odm                             | 2.3.2.RELEASE
 org.springframework.ldap                      | spring-ldap-test                            | 2.3.2.RELEASE
 org.springframework.restdocs                  | spring-restdocs-asciidoctor                 | 2.0.4.RELEASE
 org.springframework.restdocs                  | spring-restdocs-core                        | 2.0.4.RELEASE
 org.springframework.restdocs                  | spring-restdocs-mockmvc                     | 2.0.4.RELEASE
 org.springframework.restdocs                  | spring-restdocs-restassured                 | 2.0.4.RELEASE
 org.springframework.restdocs                  | spring-restdocs-webtestclient               | 2.0.4.RELEASE
 org.springframework.retry                     | spring-retry                                | 1.2.4.RELEASE
 org.springframework.security                  | spring-security-acl                         | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-aspects                     | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-cas                         | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-config                      | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-core                        | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-crypto                      | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-data                        | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-ldap                        | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-messaging                   | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-oauth2-client               | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-oauth2-core                 | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-oauth2-jose                 | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-oauth2-resource-server      | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-openid                      | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-remoting                    | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-rsocket                     | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-saml2-service-provider      | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-taglibs                     | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-test                        | 5.2.1.RELEASE
 org.springframework.security                  | spring-security-web                         | 5.2.1.RELEASE
 org.springframework.session                   | spring-session-core                         | 2.2.0.RELEASE
 org.springframework.session                   | spring-session-data-geode                   | 2.2.0.RELEASE
 org.springframework.session                   | spring-session-data-mongodb                 | 2.2.1.RELEASE
 org.springframework.session                   | spring-session-data-redis                   | 2.2.0.RELEASE
 org.springframework.session                   | spring-session-hazelcast                    | 2.2.0.RELEASE
 org.springframework.session                   | spring-session-jdbc                         | 2.2.0.RELEASE
 org.springframework.ws                        | spring-ws-core                              | 3.0.8.RELEASE
 org.springframework.ws                        | spring-ws-security                          | 3.0.8.RELEASE
 org.springframework.ws                        | spring-ws-support                           | 3.0.8.RELEASE
 org.springframework.ws                        | spring-ws-test                              | 3.0.8.RELEASE
 org.springframework.ws                        | spring-xml                                  | 3.0.8.RELEASE
 org.synchronoss.cloud                         | nio-multipart-parser                        | 1.1.0
 org.thymeleaf                                 | thymeleaf                                   | 3.0.11.RELEASE
 org.thymeleaf                                 | thymeleaf-spring5                           | 3.0.11.RELEASE
 org.thymeleaf.extras                          | thymeleaf-extras-java8time                  | 3.0.4.RELEASE
 org.thymeleaf.extras                          | thymeleaf-extras-springsecurity5            | 3.0.4.RELEASE
 org.webjars                                   | hal-browser                                 | 3325375
 org.webjars                                   | webjars-locator-core                        | 0.41
 org.xerial                                    | sqlite-jdbc                                 | 3.28.0
 org.xmlunit                                   | xmlunit-assertj                             | 2.6.3
 org.xmlunit                                   | xmlunit-core                                | 2.6.3
 org.xmlunit                                   | xmlunit-legacy                              | 2.6.3
 org.xmlunit                                   | xmlunit-matchers                            | 2.6.3
 org.xmlunit                                   | xmlunit-placeholders                        | 2.6.3
 org.yaml                                      | snakeyaml                                   | 1.25
 redis.clients                                 | jedis                                       | 3.1.0
 wsdl4j                                        | wsdl4j                                      | 1.6.3




