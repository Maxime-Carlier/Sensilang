## Groovy Based Embedded DSL for the Sensilang Java API

### Requirements
* Having installed the Java based Sensilang API (run `mvn clean install` on the sensilang-api folder)

### Building the project
### Building the project
To build the project, run the following command :
```bash
mvn clean compile assembly:single
```

### Running the project
To run the project, execute :
```
java -jar target/sensilang-dsl-jar-with-dependencies.jar <Path to a script file>
```

### Example
Compile the project then execute the following command while being inside the `target/` folder
```bash
java -jar sensilang-jar-with-dependencies.jar ../scripts/sayHello.ssl
```

### Running several example
copy the built jar to the `sensilang-dsl/scenario` repository
cd inside it
then run `java -jar sensilang-jar-with-dependencies.jar ./demo1.ssl`