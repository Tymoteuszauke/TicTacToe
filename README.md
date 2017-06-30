# Tic Tac Toe Game 
This project is about simple game Tic Tac Toe. Main objective to achieve is full OOP design,
with good test coverage.
## 1. How to run the project
### 1.1 Installation

``mvn clean install``
### 1.2 Testing
``mvn test``
### 1.3 How to run
``mvn exec:java -Dexec.mainClass="com.bratek.Main"``
## 2. To do list
 
https://trello.com/b/QeLIT0hE/tic-tac-toe
## 3. List of test
0. Test communication.
1. Create board of given size (Square). 
 
2. Win Conditions 
## 4. Problems
1. Test validation Input. 
2. How to test many inputs from one method?

## 5. Diagrams
### 5.1 CRC
Currently I don't finished the diagram at all, still have some changes.
### 5.2 Class Diagram
## 6. Improvements
### 6.1 Maven 
## 7. Ideas
### 7.1 Board 
 At the beginning of the game, board is a map of Integer(Key) and " " (values).
 If player put him sing to one of tiles then create immutable object and insert it to 
 map as a value.
 
 ### 7.2 Player
   Create player interface and his implementations:
   + HumanPlayer
   + ComputerPlayer
 ### 7.3 Game
 Crete game interface and his implementations:
 + HumansGame
 + ComputersGame
 + HumanVsComputer


