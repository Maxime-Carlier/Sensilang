# Sensilang
Sensilang is a **revolutionnary** Java based API which aim to provide developers with an easy to use, complete, and
out of the box, experience for modeling sensors data.

## Authors
* Maxime Carlier
* Mathias Chevalier
* Günther Jungbluth

## Scripts
There are multiple scripts which are represented as **.ssl** files in the sensilang-dsl/scripts folder.
Their meanings are quite explicit and each one of them aim to demonstrate the implementations of one functionality.

## How to use?
The easiest way is by far to launch the project throught your favorite IDE. Run the Main class inside the sensilang-dsl
project.

If you'd like to build and run throught maven here are the commands. But beware, if the script you are launching have
path inside them, you'll have to edit them to make sense for your terminal current working directory.

Build the project :
```bash
cd sensilang-api
mvn clean install
cd ../sensilang-dsl
mvn clean package assembly:single
```

Run the project :
```bash
cd sensilang-dsl/target/
java -jar sensilang-jar-with-dependencies.jar <path to ssl file>
```

Sample ssl file are include in the `sensilang-dsl/scripts/` directory