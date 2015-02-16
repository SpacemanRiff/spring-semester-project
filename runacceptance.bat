cd acceptance
javac -cp .;..\build\classes\main;C:\RobotFramework\robotframework-2.8.5.jar ArgumentParserKeywords.java
java -cp .;..\build\classes\main;C:\RobotFramework\robotframework-2.8.5.jar org.robotframework.RobotFramework ArgumentParserTests.txt
cd ..