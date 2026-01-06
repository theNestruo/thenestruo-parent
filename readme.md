# (theNestruo) Parent POM

## (theNestruo) Parent POM for CLI applications

### Minimal _pom.xml_

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<parent>
		<groupId>com.github.thenestruo</groupId>
		<artifactId>thenestruo-cli-parent</artifactId>
		<version>4.0</version>
		<relativePath />
	</parent>

	<groupId>com.github.thenestruo.example</groupId>
	<artifactId>example</artifactId>
	<version>1</version>

	<properties>
		<mainClass>com.github.thenestruoexample.ExampleApp</mainClass>
	</properties>

	<dependencies>

		<!-- JUnit -->
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<scope>test</scope>
		</dependency>

	</dependencies>

	<!-- == Build Settings == -->

	<build>
		<finalName>${project.artifactId}</finalName>

		<plugins>

			<!-- Package the artifact in an uber-jar, including its dependencies -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
			</plugin>

			<!-- Runs the application once to create AppCDS archive file -->
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>exec-maven-plugin</artifactId>
			</plugin>

		</plugins>
	</build>

	<!-- == Environment Settings == -->

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

</project>
```

### Recommended _scr/main/resources/tinylog.properties_

```properties
writer        = console
writer.level  = info
writer.format = [{level}] {message}
```

### Recommended usage

If _maven-shade-plugin_ was included in application _pom.xml_:

```batch
java -jar <finalName>.jar <jarArgs>
```

AppCDS archive file can be used for performance reasons.

- If _exec-maven-plugin_ was used, AppCDS archive file (_.jsa_) will be created alongside Java archive (_.jar_):

	```batch
	java -XX:SharedArchiveFile=<finalName>.jsa -jar <finalName>.jar <jarArgs>
	```

- If _exec-maven-plugin_ was not used:

	```batch
	java -XX:+AutoCreateSharedArchive -XX:SharedArchiveFile=<finalName>.jsa -jar <finalName>.jar <jarArgs>
	```
