# challenge-robots
Martian Robots

#### Requirements:

- Install Gradle 6 (6.3 or later)
- Install Java 11

---

#### Build

> ./gradlew build


#### Run

There is a folder with samples of input files in the project under:

> <PROJECT>/samples

To run the application, execute the autoexecutable jar file and pass as an argument the input file:

> java -jar build/libs/challenge-robots-0.0.1-SNAPSHOT.jar <PROJECT_PATH>/samples/samples.txt

The output is written in both, the console and in an output file. 

The output file is placed in the same folder of the input file and contains the input filename with an appended '.out' extension.

---

