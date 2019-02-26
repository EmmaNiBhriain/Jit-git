# Jit Assignment

## Description
This program is a version control program based on git. This Java program is designed to be run from the command prompt. Functionality includes initialisiing a repository, adding files to a 'staging area', committing these files by adding to the .jit/objects directory and returning to an earlier version of the files. 

* Please Note: The jar file included in the target folder was compiled with Java 9.

## Instructions

#### Method 1 : With Maven installed 

Navigate to the project folder using a command prompt window: 

```
C:\filepath\othajpjit>
```

To compile the program: 

```
mvn clean compile assembly:single
```

To run the program:

```
java -jar target/oth-ajp-jit-1.0-jar-with-dependencies.jar <command>
```

Possible commands include
```
* init 
* add <filepath>
* remove <filepath>
* commit "type commit message here"
* checkout <hash of commit>
```

#### Examples
```
C:\filepath\othajpjit> java -jar target/oth-ajp-jit-1.0-jar-with-dependencies.jar init

C:\filepath\othajpjit> java -jar target/oth-ajp-jit-1.0-jar-with-dependencies.jar add src/main/java/test1.txt

C:\filepath\othajpjit> java -jar target/oth-ajp-jit-1.0-jar-with-dependencies.jar src/main/java/test1.txt

C:\filepath\othajpjit> java -jar target/oth-ajp-jit-1.0-jar-with-dependencies.jar commit "New text file created to test adding functionality"

C:\filepath\othajpjit>java -jar target/oth-ajp-jit-1.0-jar-with-dependencies.jar checkout 56b9946f580e5946ae654c0741cf21363521292c

```

#### Method 2 : From within the IDE
* Open the project in IntelliJ 
* Edit the Run configurations to have paramters 
* Run from within the IDE

