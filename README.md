# Maze Runner

A command-line maze generation and pathfinding application built with Java. This project was completed as part of the [Hyperskill](https://hyperskill.org/projects/47) educational project.

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)
![Architecture](https://img.shields.io/badge/Architecture-OOP-blue)
![Game](https://img.shields.io/badge/Type-Logic_Game-purple)
![Algorithm](https://img.shields.io/badge/Algorithm-DFS_Maze-orange)

## How to Run It

**Prerequisites:** Make sure you have Java 17 or later installed on your machine.

1.  **Clone & Navigate:**
    ```bash
    git clone https://github.com/IrmaLerrr/MazeRunnerCLI.git
    cd MazeRunnerCLI/src/maze
    ```

2.  **Compile & Play:**
    ```bash
    # Compile the Java source files
    javac *.java

    # Run the game!
    java Main
    ```

## How to Use

Maze Runner CLI is a terminal-based application that allows you to generate random mazes, solve them using pathfinding algorithms, and manage maze files - all from your command line.

- **Generate Maze**: Create a new random maze by specifying dimensions
- **Load Maze**: Load previously saved maze from file
- **Save Maze**: Save current maze to file for future use
- **Display Maze**: View the current maze in terminal
- **Find Escape**: Calculate and display the shortest path to exit

## Project Architecture

This project follows an Object-Oriented Programming (OOP) design:
```
src/
├── Main.java               # Application entry point
├── Menu.java               # Handles user menu and navigation
├── Maze.java               # Core maze logic and generation algorithms
├── Cell.java               # Represents individual maze cells
├── CellState.java          # Enum defining cell states (BLOCKED, EMPTY, PATH)
├── FileHandler.java        # Handles maze file I/O operations
├── SerializationUtils.java # Utilities for maze serialization
└── InputHandler.java       # Manages user input validation
```




