# (theNestruo) Parent POM

## (theNestruo) Parent POM for CLI applications

### Minimal CLI application _pom.xml_

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<parent>
		<groupId>com.github.thenestruo</groupId>
		<artifactId>thenestruo-cli-parent</artifactId>
		<version>2.3</version>
		<relativePath />
	</parent>

	<groupId>com.github.thenestruo.example</groupId>
	<artifactId>example</artifactId>
	<version>1</version>

	<properties>
		<mainClass>com.github.thenestruoexample.ExampleApp</mainClass>
	</properties>

	<dependencies>

		<dependency>
			<groupId>com.github.thenestruo</groupId>
			<artifactId>thenestruo-commons</artifactId>
		</dependency>

		<!-- JUnit -->
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<scope>test</scope>
		</dependency>

	</dependencies>

	<repositories>

		<!-- Maven Central -->
		<repository>
			<id>central</id>
			<url>https://repo1.maven.org/maven2</url>
		</repository>

		<!-- GitHub Packages: github.com/theNestruo/thenestruo-parent-->
		<repository>
			<id>github-thenestruo-parent</id>
			<url>https://maven.pkg.github.com/theNestruo/thenestruo-parent</url>
		</repository>

	</repositories>


	<build>
		<finalName>${project.artifactId}</finalName>

		<plugins>

			<!-- Package the artifact in an uber-jar, including its dependencies -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
			</plugin>

		</plugins>
	</build>

</project>
```

### Recommended CLI application _scr/main/resources/logback.xml_

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration>

<configuration>

	<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
		<withJansi>true</withJansi>
		<encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
			<pattern>[%highlight(%level)] %message%rootException%n</pattern>
		</encoder>
	</appender>

	<root level="info">
		<appender-ref ref="CONSOLE" />
	</root>

</configuration>
```
