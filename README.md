## Bat Cave

## 部署
### 依赖
- Git
- JDK
- Maven
- MySQL
## 步骤
- yum update
- yum install git
- mkdir App
- cd App
- git clone https://github.com/LeDon-SP/community.git
- yum install maven
- mvn -v
- mvn clean compile package
- cp src/main/resources/application.yml src/main/resources/application-production.yml
- vim src/main/resources/application-production.yml
- java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar
- ps -aux | grep java
- mvn package

## 资料
[Spring文档](https://spring.io/guides)

[Spring Web文档](https://spring.io/guides/gs/serving-web-content/)

[GitHub deploy key](https://developer.github.com/apps/building-oauth-apps/)

[Bootstrap](https://v3.bootcss.com/getting-started/#download)

[GitHub OAuth](https://developer.github.com/apps/building-oauth-apps/)

[MvnRepository](https://mvnrepository.com/)

[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

[TimeAgo](https://blog.csdn.net/myme95/article/details/89133862)

[Markdown](http://editor.md.ipandao.com)

[UFile SDK](https://github.com/ucloud/ufile-sdk-java/)

## 工具
[Lombok](https://projectlombok.org)

```
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```