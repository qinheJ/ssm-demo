# ssm-demo
一、创建一个maven项目

项目配置如下：

![](E:\docu\笔记\ssm\Snipaste_2019-07-28_20-55-26.png)



![](E:\docu\笔记\ssm\Snipaste_2019-07-28_20-56-57.png)

![](E:\docu\笔记\ssm\Snipaste_2019-07-28_20-57-19.png)

![](E:\docu\笔记\ssm\Snipaste_2019-07-28_20-57-54.png)

项目初始化结构如下：

![](E:\docu\笔记\ssm\Snipaste_2019-07-28_21-17-47.png)

![](E:\docu\笔记\ssm\Snipaste_2019-07-28_21-22-48.png)



# 二、整理项目结构

## 1、项目结构简介

- .idea文件夹和helloworld.iml是idea的一些配置文件，我们不用管,就算删了也没事，当然删了，重新打开项目，就得重新配置了
- src文件夹就是我们放项目代码的地方
- pom.xml是maven的配置文件，我们需要什么jar包，都可以在里面添加依赖，然后maven就会自己帮我们下到本地仓库里面

## 2、项目结构修改

- 接下来就需要创建构思项目所需的文件结构，main下新建一个java，用来放java文件，src下新建一个test，用来放测试文件，main下新建一个resources文件夹，用来放置配置文件，如下图所示：

![](E:\docu\笔记\ssm\Snipaste_2019-07-28_21-36-29.png)

- 下面要修改目录的类型

  点击这个按钮

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-28_21-39-11.png)

  选择Modules

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-28_21-39-58.png)

  将项目文件夹修改成如下：

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-28_21-48-40.png)

- 在test的java文件夹上(如没有java文件夹，则手动创建，下同)，鼠标右键，找到make directory as，选择Test 

- 在test的resources文件夹上，鼠标右键，找到make directory as，选择Test Resources root

  目录结构如下：

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-28_21-49-36.png)



- src->main->java下新建package com.my，这对应我们刚开始的groupid
- my包下新建四个包，controller包用来放前台url映射文件，dao用来放与数据库的接口文件，entity用来放实体类文件，service用来放自己定义的接口
- webapp下新建css、images等文件，用来放前端资源
- web-inf下新建views文件件，jsp文件就放这里面了

按照上面的流程修改后目录结构如下图：

![](E:\docu\笔记\ssm\Snipaste_2019-07-28_22-04-56.png)

- 注意：views如果放在根目录，则可以通过URL直接访问得到，如果不想暴露jsp给外界，想所有的jsp都是通过controller方法返回的，则可以将jsp页面views文件夹放置在/WEB-INF目录下，然后将spring-mvc.xml下的视图解析器的配置从

  ```xml
  <!-- 视图解析器 -->
      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <property name="prefix" value="/views/"/>
          <property name="suffix" value=".jsp"/>
      </bean>
  ```

  改为

  ```xml
  <!-- 视图解析器 -->
      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <property name="prefix" value="/WEB-INF/views/"/>
          <property name="suffix" value=".jsp"/>
      </bean>
  ```

  

# 三、添加maven依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.my</groupId>
    <artifactId>ssm-demo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <name>ssm-demo Maven Webapp</name>
    <!-- FIXME change it to the project's website -->
    <url>http://www.example.com</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <!-- spring/spring-mvc版本号 -->
        <spring.version>4.3.23.RELEASE</spring.version>
        <!-- 数据库相关 -->
        <mysql.version>5.1.32</mysql.version>
        <druid.version>1.0.9</druid.version>
        <mybatis.version>3.4.1</mybatis.version>
        <pagehelper.version>4.1.2</pagehelper.version>
        <!-- mybatis与spring整合包 -->
        <mybatis.spring.version>1.2.2</mybatis.spring.version>
        <!-- 日志相关 -->
        <slf4j.version>1.7.7</slf4j.version>
        <!-- commons工具包 -->
        <commons-lang3.version>3.3.2</commons-lang3.version>
        <commons-io.version>1.3.2</commons-io.version>
        <commons-fileupload.version>1.3.1</commons-fileupload.version>
        <commons-net.version>3.3</commons-net.version>
        <!-- redis客户端 -->
        <jedis.version>2.7.2</jedis.version>
        <!-- quartz时间任务 -->
        <quartz.version>2.2.2</quartz.version>
        <!-- 单元测试 -->
        <junit.version>4.12</junit.version>
        <!-- dubbo -->
        <dubbo.version>2.5.3</dubbo.version>
        <zookeeper.version>3.4.7</zookeeper.version>
        <zkclient.version>0.1</zkclient.version>
    </properties>

    <dependencies>
        <!-- Apache工具组件 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>${commons-lang3.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-io</artifactId>
            <version>${commons-io.version}</version>
        </dependency>

        <!-- 文件上传组件 -->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>${commons-fileupload.version}</version>
        </dependency>
        <dependency>
            <groupId>commons-net</groupId>
            <artifactId>commons-net</artifactId>
            <version>${commons-net.version}</version>
        </dependency>

        <!-- 日志处理 -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${slf4j.version}</version>
        </dependency>

        <!--spring开发包-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jms</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- jsp相关 -->
        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <!-- servlet-api和jsp-api -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>javax.servlet.jsp-api</artifactId>
            <version>2.3.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>


        <!-- mysql驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
        </dependency>

        <!-- 数据库连接池包 德鲁伊（druid） -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${druid.version}</version>
        </dependency>

        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>${mybatis.version}</version>
        </dependency>

        <!-- mybatis的分页插件 -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>${pagehelper.version}</version>
        </dependency>


        <!-- mybatis与spring的整合包 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>${mybatis.spring.version}</version>
        </dependency>

        <!-- Redis客户端 -->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>${jedis.version}</version>
        </dependency>

        <!-- 定时任务 -->
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>${quartz.version}</version>
        </dependency>

        <!-- dubbo相关 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>${dubbo.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>${zookeeper.version}</version>
        </dependency>
        <dependency>
            <groupId>com.github.sgroschupf</groupId>
            <artifactId>zkclient</artifactId>
            <version>${zkclient.version}</version>
        </dependency>

        <!--单元测试-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <finalName>ssm-demo</finalName>
        <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
            <plugins>
                <plugin>
                    <artifactId>maven-clean-plugin</artifactId>
                    <version>3.1.0</version>
                </plugin>
                <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
                <plugin>
                    <artifactId>maven-resources-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.0</version>
                </plugin>
                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.22.1</version>
                </plugin>
                <plugin>
                    <artifactId>maven-war-plugin</artifactId>
                    <version>3.2.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-install-plugin</artifactId>
                    <version>2.5.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-deploy-plugin</artifactId>
                    <version>2.8.2</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
</project>

```

# 四、添加web框架

项目刚被创建是没有web框架的，所以需要手动加module，让idea能够识别这是一个web工程

- 进入Project Structure ，选择Modules，点击加号，加入web框架

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-28_22-08-19.png)

  配置如下，注意目录是否正确（默认的目录可能是错的）

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-28_22-10-52.png)



# 五、ssm整合以及配置文件

- 整体思路是用spring来整合所有的javabean，同时整合配置文件。

1. dao层（mybatis和spring整合）

   a) spring管理数据源

   b) spring管理SqlSessionFactory

   c) spring生成mapper接口的代理对象

2. service层（spring）

   a) spring管理service的bean

   b) spring管理事务

3. Controller层（springmvc）

   a) springmvc本身是spring的一个模块，无需整合。

## 1、web.xml配置

- 在/WEB-INF下的web.xml中加入如下代码

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
           http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
           version="3.1">
  
      <display-name>ssm-demo</display-name>
      <!--说明性文字-->
  
      <!--
          "ssm-demo"这个字符串可以随便写任何字符串。如果不配置默认值是"webapp.root"。
  
          可以用System.getProperty("webapp.root")来动态获项目的运行路径。
          一般返回结果例如：/usr/local/tomcat6/webapps/项目名
      -->
      <context-param>
          <param-name>webAppRootKey</param-name>
          <param-value>ssm-demo</param-value>
      </context-param>
      <listener>
          <listener-class>
              org.springframework.web.util.WebAppRootListener
          </listener-class>
      </listener>
  
      <!--配置spring的listener-->
      <context-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>
              classpath:spring.xml
          </param-value>
      </context-param>
      <listener>
          <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
      </listener>
  
      <!--配置spring-mvc-->
      <servlet>
          <servlet-name>spring</servlet-name>
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>classpath:spring-mvc.xml</param-value>
          </init-param>
          <load-on-startup>1</load-on-startup>
      </servlet>
      <servlet-mapping>
          <servlet-name>spring</servlet-name>
          <url-pattern>/</url-pattern>
      </servlet-mapping>
  
      <!--乱码-->
      <filter>
          <filter-name>CharacterEncodingFilter</filter-name>
          <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
          <init-param>
              <param-name>encoding</param-name>
              <param-value>UTF-8</param-value>
          </init-param>
          <init-param>
              <param-name>forceEncoding</param-name>
              <param-value>true</param-value>
          </init-param>
      </filter>
      <filter-mapping>
          <filter-name>CharacterEncodingFilter</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  
      <!-- 配置 Druid 监控信息显示页面 -->
      <servlet>
          <servlet-name>DruidStatView</servlet-name>
          <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
          <init-param>
              <!-- 允许清空统计数据 -->
              <param-name>resetEnable</param-name>
              <param-value>true</param-value>
          </init-param>
          <init-param>
              <!-- 用户名 -->
              <param-name>loginUsername</param-name>
              <param-value>druid</param-value>
          </init-param>
          <init-param>
              <!-- 密码 -->
              <param-name>loginPassword</param-name>
              <param-value>147258369</param-value>
          </init-param>
      </servlet>
      <servlet-mapping>
          <servlet-name>DruidStatView</servlet-name>
          <url-pattern>/druid/*</url-pattern>
      </servlet-mapping>
      <filter>
          <filter-name>DruidWebStatFilter</filter-name>
          <filter-class>com.alibaba.druid.support.http.WebStatFilter</filter-class>
          <!-- 排除统计资源 -->
          <init-param>
              <param-name>exclusions</param-name>
              <param-value>*.js,*.gif,*.jpg,*.png,*.css,*.ico,*_jpg,*_jpeg,*_blob,*_gif,/druid/*</param-value>
          </init-param>
          <!-- 声明用户标识 -->
          <init-param>
              <param-name>principalSessionName</param-name>
              <param-value>_s_key_phone</param-value>
          </init-param>
          <!-- 监控单个url调用的sql列表 -->
          <init-param>
              <param-name>profileEnable</param-name>
              <param-value>false</param-value>
          </init-param>
      </filter>
      <filter-mapping>
          <filter-name>DruidWebStatFilter</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  
  </web-app>
  ```

## 2、springmvc配置

对应web.xml中的

```xml
<!--配置spring-mvc-->
    <servlet>
        <servlet-name>spring</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>spring</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```

在resources目录中创建spring-mvc.xml如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--开启@Controller、@service、@Autowire等注解-->
    <context:annotation-config/>

    <!-- 配置注解驱动 开启@ResquestMapping-->
    <mvc:annotation-driven/>

    <!-- 配置包扫描器 -->
    <context:component-scan base-package="com.my.controller" use-default-filters="false">
        <context:include-filter type="regex"
                                expression=".*\.[^.]*Controller"/>
    </context:component-scan>

    <!--让htm可以返回json数据，spring mvc4如果类型不匹配会拦截掉返回http status 406-->
    <bean id="contentNegotiationManager" class="org.springframework.web.accept.ContentNegotiationManagerFactoryBean">
        <property name="favorPathExtension" value="false"/>
    </bean>
    <mvc:annotation-driven content-negotiation-manager="contentNegotiationManager">
        <mvc:message-converters register-defaults="true">
            <!-- 配置Fastjson支持 -->
            <bean id="fastJsonHttpMessageConverter"
                  class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                        <value>application/json;charset=UTF-8</value>
                    </list>
                </property>
            </bean>
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <property name="supportedMediaTypes" value="application/json;charset=UTF-8"/>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!--如果发送的请求不想通过controller，只想直接地跳转到目标页面。相当于在controller方法中直接返回，然后使用ViewResolver进行解析-->
    <mvc:view-controller path="/" view-name="redirect:index"/>

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
<!--        <property name="viewNames" value="order*"/>-->
        <property name="prefix" value="/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!-- 配置静态资源访问 -->
    <mvc:resources location="/css/" mapping="/css/**"/>
    <mvc:resources location="/js/" mapping="/js/**"/>
    <mvc:resources location="/images/" mapping="/images/**"/>

    <!-- 控制器异常处理 -->
    <!--    <bean id="exceptionHandlerExceptionResolver"-->
    <!--          class="org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver"/>-->
    <bean class="com.my.handler.DefaultExceptionHandler"/>

    <!--针对于所有mapping配置全局的拦截器 根据path做过滤
		拦截器只拦截@Controller注解的类和方法，对于直接访问jsp不拦截，另外需要注意的是，拦截器是会拦截静态资源的 比如html js css image这类，虽然都是页面 但是html属于静态资源,jsp不属于
	-->
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <!--            <mvc:exclude-mapping path="/admin/login"/>&lt;!&ndash;排除拦截页面&ndash;&gt;-->
            <bean class="com.my.interceptor.DemoInterceptor"/>
        </mvc:interceptor>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <bean class="com.my.interceptor.DemoInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>

    <!-- 配置文件上传，如果没有使用文件上传可以不用配置，当然如果不配，那么配置文件中也不必引入上传组件包 -->
    <bean id="multipartResolver"
          class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!-- 默认编码 -->
        <property name="defaultEncoding" value="utf-8"/>
        <!-- 文件大小最大值 -->
        <property name="maxUploadSize" value="10485760000"/>
        <!-- 内存中的最大值 -->
        <property name="maxInMemorySize" value="40960"/>
        <!-- 启用是为了推迟文件解析，以便捕获文件大小异常 -->
        <property name="resolveLazily" value="true"/>
    </bean>
</beans>
```

- 注意：拦截器只拦截@Controller注解的类和方法，对于直接访问jsp不拦截，另外需要注意的是，拦截器是会拦截静态资源的 比如html js css image这类，虽然都是页面 但是html属于静态资源,jsp不属于

## 3、spring配置

- 对应web.xml中的

```xml
<!--配置spring的listener-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
            classpath:spring.xml
        </param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
```

spring配置有几个模块：aop、dao、service等等，采用拆分后整合的模式

在resources目录下创建spring.xml，如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.my">
        <context:exclude-filter type="regex" expression="com.my.controller.*"/>
    </context:component-scan>

    <import resource="classpath:spring-mvc.xml"/>
    <import resource="classpath:spring-jdbc.xml"/>
    <import resource="classpath:applicationContext-aop.xml"/>
    <import resource="classpath:applicationContext-dao.xml"/>

</beans>
```

##  



## 4、spring-aop配置

- 主要文件如下：

![](E:\docu\笔记\ssm\Snipaste_2019-07-29_17-42-50.png)

- 创建包aop，添加两个java类

  MyAspectForUser（通知、切面类）

  ```java
  package com.my.aop;
  
  /**
   * @author QinHe at 2019-07-29
   */
  public class MyAspectForUser {
      //前置增强
      public void before() {
          System.out.println("前置增强 ===========");
      }
  }
  
  
  ```

  User（目标类）

  ```java
  package com.my.aop;
  
  /**
   * @author QinHe at 2019-07-29
   */
  public class User {
      private String name;
  
      public void say() {
          System.out.println("我的名字是：" + getName());
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  }
  
  
  ```

- 配置文件

  在resources目录下创建spring配置文件如下：

  applicationContext-aop.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:aop="http://www.springframework.org/schema/aop"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
  
      <!--spring-aop的示例-->
  
      <!--创建目标bean-->
      <bean id="user" class="com.my.aop.User">
          <property name="name" value="abc"/>
      </bean>
  
      <!--创建切面bean-->
      <bean id="myAspectForUser" class="com.my.aop.MyAspectForUser"/>
  
      <!-- 进行 aop的配置 -->
      <aop:config>
          <!--配置切入点表达式:哪些类的哪些方法需要增强-->
          <aop:pointcut expression="execution(* com.my.aop.User.*(..))" id="pointcut1"/>
          <!--配置切面-->
          <aop:aspect ref="myAspectForUser"><!-- ref 为切面或者通知bean-->
              <aop:before method="before" pointcut-ref="pointcut1"/><!--将通知myAspectForUser织入切点pointcut1-->
          </aop:aspect>
      </aop:config>
  
      <!--下面用aop的注解-->
      <!--开启aop注解的自动代理-->
      <aop:aspectj-autoproxy/>
  </beans>
  
  ```

- 测试：

  在my包下创建运行类：

  Application

  ```java
  package com.my;
  
  import com.my.aop.User;
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  
  /**
   * @author QinHe at 2019-07-29
   */
  public class Application {
      public static void main(String[] args) {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
          User user = (User) applicationContext.getBean("user");
          user.say();
          System.out.println("end");
      }
  }
  
  
  ```

- 启动，查看结果：

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-29_17-46-50.png)



- 也可以用aop的注解

- 只需要一句开启注解的代码，其他配置都可以不用

  ```xml
  <!--下面用aop的注解-->
      <!--开启aop注解的自动代理-->
      <aop:aspectj-autoproxy/>
  
  ```

- 编写切面类

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-29_19-33-24.png)

  就可以了



## 5、数据访问层

### ①、两种不常用的事务配置

- 编程式事务(不常用，也就没用)

  ```xml
  <!--配置事务管理器-->
   <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
       <property name="dataSource" ref="dataSource"/>  
  </bean> 
   <!--配置事务管理模板-->
   <bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
        <property name="transactionManager" ref="transactionManager"/>  
  </bean> 
  <!--在业务层注入事务管理模板-->
   <bean id="accountService" class="com.my.service.AccountServiceImpl">
       <property name="accountDao" ref="accountDao"/> 
  	<!--注入事务管理模板-->
    	<property name="transactionTemplate" ref="transactionTemplate"/>  
  </bean>
       
  
  
  ```

  手动编写代码实现事务管理

  ```java
  public void updateAccount(final String from, final String to, final Double money) {
      transactionTemplate.execute(new TransactionCallbackWithoutResult(){
          @override
          protected void doInTransactionWithoutResult(TransactionStatus status){
              accountDao.out(from,money);
              int d = 1/0;
              accountDao.in(to,money);
          }
      }
   }    
                              
  
  ```

- 声明式aop事务(不常用，也就没用)

  ```xml
  <!--事务管理器--> 
  <bean id ="transactionManager" 
        class ="org.springframework.jdbc.datasource.DataSourceTransactionManager" > 
      <property name ="dataSource" ref ="dataSource" /> 
  </bean>
  <!--配置事务的增强-->
  <tx:advice id ="txAdvice" transaction-manager ="transactionManager" >
      <tx:attributes > 
          <!-- isolation="DEFAULT" 隔离级别 
          propagation="REQUIRED" 传播行为 
          read-only="false" 只读 
          timeout=" -1" 过期时间 
          rollback-for="" -Exception
          no-rollback-for="" +Exception 
          --> 
          <tx:method name ="update*" propagation ="REQUIRED" /> 
      </ tx:attributes > 
  </ tx:advice >
  
  <!--aop-->
  <aop:config > 
      <aop:pointcut expression ="execution(* cn.itcast.transaction.demo2.AccountServiceImpl.transfer(..))" 
                    id ="pointcut1" /> 
      <aop:advisor advice-ref ="txAdvice" pointcut-ref ="pointcut1" /> 
  </ aop:config >
  
  
  ```



### ②项目中使用的dao层结构如下（包括声明式注解事务）：

- 在src/main/resources目录下，创建properties目录，然后创建db.properties文件

```properties
db.driver= com.mysql.jdbc.Driver
db.url= jdbc:mysql://ip:3306/ssm_demo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=round&autoReconnect=true&useSSL=false&allowMultiQueries=true
db.username= root
db.password= 12345678
db.maxActive= 10
db.minIdle= 5

```



- 在src/main/resources目录下，创建spring-jdbc.xml，内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:tx="http://www.springframework.org/schema/tx" xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 加载数据库的配置文件 -->
    <context:property-placeholder location="classpath*:properties/*.properties"/>

    <!-- 创建数据源 -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
          destroy-method="close">
        <property name="driverClassName" value="${db.driver}"/>
        <property name="url" value="${db.url}"/>
        <property name="username" value="${db.username}"/>
        <property name="password" value="${db.password}"/>
        <property name="maxActive" value="${db.maxActive}"/>
        <property name="minIdle" value="${db.minIdle}"/>
    </bean>

    <!--声明式注解事务-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <tx:annotation-driven transaction-manager="transactionManager"/>

</beans>

```



- 在src/main/resources目录下，创建applicationContext-dao.xml，内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--mybatis配置-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="mapperLocations" value="classpath:mybatis/**/*.xml"/>
        <property name="configLocation" value="classpath:sqlMapConfig.xml"/>
        <property name="typeAliasesPackage" value="com.my.entity"/><!--在sqlMapConfig.xml也有别名定义，取其一-->
    </bean>

    <!--自动扫描包配置，将Mapper接口生成代理注入到Spring-->
    <bean id="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.my.dao"/>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <property name="annotationClass" value="com.my.annotation.MyBatisRepository"/>
    </bean>
</beans>

```

mybatis涉及到的目录结构如下：

![](E:\docu\笔记\ssm\Snipaste_2019-07-30_16-07-29.png)



代码如下：

OrderRecord.java

```java
package com.my.entity;

import java.io.Serializable;

/**
 * @author QinHe at 2019-07-29
 */
public class OrderRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


```

OrderRecordDao.java

```java
package com.my.dao;

import com.my.annotation.MyBatisRepository;
import com.my.entity.OrderRecord;

/**
 * @author QinHe at 2019-07-29
 */
@MyBatisRepository
public interface OrderRecordDao {
    OrderRecord get(Long id);
}


```

MyBatisRepository.java

```java
package com.my.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author QinHe at 2019-07-29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBatisRepository {
}


```

OrderRecordMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.my.dao.OrderRecordDao">
    <resultMap id="OrderRecordResultMap" type="OrderRecord">
        <id column="id" property="id" jdbcType="BIGINT"/>
    </resultMap>
    <select id="get" parameterType="long" resultMap="OrderRecordResultMap">
        select *
        from order_record
        where id = #{id,jdbcType=BIGINT}
    </select>
</mapper>

```

# 六、部署启动

- 点击这里

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-30_16-09-41.png)

- 点左上角加号

- 选择tomcat

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-30_16-10-28.png)







- 进行配置：选择Deployment，点击加号，选择一个artifact

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-30_16-13-00.png)

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-30_16-13-19.png)



- ok之后：

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-30_16-14-31.png)



- 将Application context 项目的应用名改成根目录

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-30_16-16-13.png)



- 回到server 如下图：

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-30_16-16-56.png)

- 点击ok

- 启动项目

  ![](E:\docu\笔记\ssm\Snipaste_2019-07-30_16-18-55.png)



# 七、完整项目代码

https://github.com/qinheJ/ssm-demo.git

















