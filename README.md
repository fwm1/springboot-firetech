# NOTE

* springsecurity会拦截静态资源，需要配置；
  springboot默认静态资源在/resources/static下,但如果要在static下加子文件夹的话，还是需要配置, 若还是莫名 bug 尝试maven clean重新编译
	spring.mvc.static-path-pattern=/static/**

* spring.datasource.driver-class-name=org.sqlite.JDBC
  spring.datasource.jdbc-url=jdbc:sqlite:./sqlite.db 
  
* springsecurity可以取得UserDetail但是得不到username：
	mybatis属性和字段映射要写完整,否则无法正确构造SystemUser对象
	
* jsp获取登录用户名：
	${pageContext.request.remoteUser}

* 在没有用ajax发送http请求的时候,在controller中可以通过return "redirect:/user/list";的方式进行页面跳转.
  在使用ajax对表单数据进行封装,并发送至controller进行处理后就不能用以往的方法进行处理, 而需要在ajax提交的函数里进行跳转的设置:
window.location.href="/busasst/user/linestation";

* ajax向后台发送请求时，dataType指的是"后台"预期的返回数据类型，若后台没有返回数据或者返回数据不一致则会执行error的回调函数， 前台记得加上@ResponseBody，才能回调ajax的success函数

* Spring Boot 打jar包 无法响应jsp 问题
	** 增加jsp依赖
	** 编译插件版本指定1.4.2
	```
	<plugin>
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-maven-plugin</artifactId>
   		<version>1.4.2.RELEASE</version>
	</plugin>
	```
	**  将src/main/webapp下的所有文件文件编译到classes/META-INF/resources下
	```
	<resource>
   		<directory>src/main/webapp</sdirectory>
   		<targetPath>META-INF/resources</targetPath>
   		<includes>
      			<include>**/*.*</include>
   		</includes>
	</resource>
       ```
       ** application 配置
         ```
	 spring.mvc.view.prefix=/WEB-INF/views/
         spring.mvc.view.suffix=.jsp
	 ```
* RequestMapping("/login")
	return "login"
可能会引起循环viewResolve！！！！

* bootstrap 设置表格宽度 
```
 <colgroup>
	<col style="width:10%">
	<col style="width:15%">
	<col style="width:30%">
	<col style="width:15%">
	<col style="width:15%">
	<col style="width:15%">
</colgroup>
```

* jstl c:when判断字符串相等 <c:when test="${user.currentRoute eq route.routeName}">

* javascript
   === 表示恒等，首先比较两边的变量数据类型是否相等，其次比较两边的变量的数值是否相等,
  NaN和其他任何值都是不相等的，包括它本身！！！通过x!==x来判断x是否为NaN，只有在x为NaN的时候，这个表达式的值才为true。
   ==类型转换后相等，比如 "1"==1 :true
   ===不转换进行比较，比如 "1" === 1 :false
   
  
* springboot本身不支持jsp， 因此目录下默认没有webapp， 导致找不到jsp文件(WhiteLabel Error Page)，解决办法：
	Project Structure --> Modules -->添加web,并将web resource path指向webapp目录
