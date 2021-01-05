#### develop environment setup on windows 10
install openjdk 15 (or jdk 1.8 higher) & set `JAVA_HOME` & add `%JAVA_HOME%\bin` to Path
```
$ java -version
openjdk version "15.0.1" 2020-10-20
OpenJDK Runtime Environment (build 15.0.1+9-18)
OpenJDK 64-Bit Server VM (build 15.0.1+9-18, mixed mode, sharing)
```
install scala & set `SCALA_HOME` & add `%SCALA_HOME%\bin` to Path 
```
$ scala -version
Scala code runner version 2.13.4 -- Copyright 2002-2020, LAMP/EPFL and Lightbend, Inc.
```
install sbt & add it to Path
```
$ sbt -version
copying runtime jar...
sbt version in this project: 1.4.6
sbt script version: 1.4.5
```


#### create a scala project using sbt
```
sbt new scala/hello-world.g8
```

#### run & package
```
$ sbt run package
[info] welcome to sbt 1.4.6 (Oracle Corporation Java 15.0.1)
[info] loading global plugins from C:\Users\t_xuq\.sbt\1.0\plugins
[info] loading project definition from D:\work-demos2\hello-scala-sbt\project
[info] loading settings for project hello-scala-sbt from build.sbt ...
[info] set current project to hello-scala-sbt (in build file:/D:/work-demos2/hello-scala-sbt/)
[info] running HelloScala
hello, world..
[success] Total time: 0 s, completed 5 Jan 2021, 22:23:24
[success] Total time: 0 s, completed 5 Jan 2021, 22:23:24
```

#### run jar
```
$ scala target/scala-2.13/*.jar
hello, world..
```