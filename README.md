# appium-androidNative-IOT-app
Appium sample project for IOT smart home android native app

Pre-requisites:

Install Appium
Install Android SDK.
Setup environment variable for ANDROID_HOME, JAVA_HOME
Gradle must be installed. Please refer to https://gradle.org/install/ for instructions pertaining to your operating system
JDK 8. Please refer to https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html for instructions pertaining to your operating system
Disclaimer : This code has not been tested in higher JDK versions.

Instruction to run the automation test on your machine using gradle command:

Open terminal. Clone the repository by typing the following command:

Jump into the newly cloned repository by using cd command on terminal

Run following command to execute the tests:

./gradlew clean test

Instruction to run the automation test on your machine using IntelliJ Idea IDE:

Open terminal. Clone the repository(looks at Step 1 above)

Import the project into IntelliJ Idea.

Open Run Configurations and create TestNG run configuration. Provide the test Class Name 

Run the TestNG configuration using the green triangular button on top left of the IDE.
