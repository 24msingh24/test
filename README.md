# Number to Words Web Application

## 1. Overview
This is a Java-based web application that converts numeric input into English words, including dollars and cents.

**Example:**  
Input: `123.45`  
Output: `one hundred and twenty-three dollars and forty-five cents`

## 2. Features
- Converts integers and decimals to words.
- Handles numbers up to 999,999,999.
- Provides a simple HTML interface for interactive testing.
- Returns "invalid input" for non-numeric or empty inputs.

## 3. Prerequisites
- Java JDK 11 or higher
- Maven or manual dependency setup with JUnit jars

## 4. Build, Run & Test

### 4.1 Compile the source code
```bash
javac -cp 'libs/*' -d out/ src/main/java/com/example/numbertowords/*.java

java -cp 'out/:libs/*' com.example.numbertowords.Main


Compile the test code-

mvn clean compile

mvn test
