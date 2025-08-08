# Task Management Application - Programming Languages Project
This repository contains the source code for a programming languages project, developed as part of a summer course. The primary goal of this project is to explore and implement concepts related to various programming languages, focusing on data structures, algorithms, and practical applications.

## Project Overview
The project is structured around a core application built with Java, demonstrating key principles of Object-Oriented Programming (OOP) and software design.

A central part of the application's design is the use of an abstract superclass, AbstractItem. This class serves as a blueprint for different types of items within the application. The program implements the concept of inheritance by using AbstractItem as a base for two concrete subclasses: SimpleTask and TaskWithDeadline. This design allows for polymorphism and code reusability, which are fundamental principles of OOP.

### Key features include:

Implementation of fundamental data structures.

Algorithms for sorting and searching.

A user-friendly command-line interface for interacting with the program.

## Getting Started
Follow these instructions to get a copy of the project up and running on your local machine.

Prerequisites
You will need the following installed:

Java Development Kit (JDK): Version 11 or higher.

Gradle: A build automation tool.

Git: To clone the repository.

### Installation
Clone the repository:
```
git clone https://github.com/ReaganEschbach/Programming-Languages-Project-Summer-2025.git
```


Navigate to the project directory:
```
cd Programming-Languages-Project-Summer-2025
```


Build the project using Gradle:
```
./gradlew build
```


### Running the Application
To run the main application, use the following Gradle command from the project root directory:
```
./gradlew run
```


### Technologies Used
Java: The core programming language for the project.

Gradle: Used for dependency management and building the project.

### File Structure
The project has a standard Gradle project layout. The key directories are:

src/main/java: Contains the main Java source files.

src/test/java: Contains the unit tests for the application.

build.gradle.kts: The Gradle build script that defines dependencies and build tasks.

## Contributors
Reagan Eschbach

Sai Porumamilla (@flamebot43261)
