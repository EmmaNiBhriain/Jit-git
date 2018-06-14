# Jit Assignment

## Description
This program is a version control program based on git. This Java program is designed to be run from the command prompt. Functionality includes initialisiing a repository, adding files to a 'staging area', committing these files by adding to the .jit/objects directory and returning to an earlier version of the files. 

## Instructions

Navigate to the project folder using a command prompt window and locate the src/main/java folder:

```
C:\filepath\othajpjit\src\main\java
```

To run the program:

```
java de.othr.ajp.Jit <command>
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
C:\filepath\othajpjit\src\main\java> java de.othr.ajp.Jit init

C:\filepath\othajpjit\src\main\java> java de.othr.ajp.Jit add src/main/test1.txt

C:\filepath\othajpjit\src\main\java> java de.othr.ajp.Jit remove src/main.test1.txt

C:\filepath\othajpjit\src\main\java> java de.othr.ajp.Jit commit "New text file created to test adding functionality"

C:\filepath\othajpjit\src\main\java> java de.othr.ajp.Jit checkout 56b9946f580e5946ae654c0741cf21363521292c

```

