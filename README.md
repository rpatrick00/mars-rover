#Mars Rover Coding Challenge

This project contains my solution for the Mars Rover coding challenge.

##How to Build

This project requires Java and a working Maven configuration to build.  Once you have installed and configured Maven, just clone the Git repository and run "mvn package" to generate the uber executable jar with all of the dependencies included.

## How to Run

Once the mars-rover/target/mars-rover.jar file has been built, do the following:

1. Create an input file that matches the input definition.  For example:        

```text
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

2.) Run the application pointing it to the input file created in Step 1 like this:

```text
java -jar target/mars-rover.jar -f src/test/resources/rover/parser/ValidInputFile.txt
```

The output of the program will look something like this:

```text
1 3 N
5 1 E
```

