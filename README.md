# Swing Calculator

This project is a basic calculator application built using Java Swing for the graphical user interface (GUI). It supports basic arithmetic operations including addition, subtraction, multiplication, and division.

## Features

- Basic arithmetic operations: addition, subtraction, multiplication, and division
- Clear button to reset the input
- Delete button to remove the last digit
- Support for decimal numbers
- Toggle for negative numbers

## Getting Started

### Prerequisites

To run this project, you need to have Java Development Kit (JDK) installed on your machine.

### Installation

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/MKmasterg/SwingCalculator
   ```

2. Compile the Java source file:

   ```bash
   javac Main.java
   ```

3. Run the application:

   ```bash
   java Main
   ```

## Usage
In the bottom panel we have some operations which are :
- The `CLR` button that clears the input field.
- The `DEL` button that deletes the last entered digit.
- The `-` button that toggles the sign of the current input.

## Code Structure

- `Main.java`: The main class that sets up the JFrame and handles all the button actions.
- `MathComplex`: A nested static class within `Main` that manages the arithmetic operations (like operand handling) and their states.

## Contributing

If you would like to contribute to this project, please fork the repository and submit a pull request. Contributions are welcome!

_This is a part of the Advanced Programming course assignment at Amirkabir University of Technology._
