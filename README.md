# NQueen Solver

a java program to solve n queen problem using backtracking algolithm

## Installation

Just make sure you have [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) installed on VSCode.

## Usage

There will be an instruction log in the terminal when you run the program.

## Explain algolithm

'Q' is represent a queen
'=' is represent an empty space

The Program will try to place a queen start from top to bottom row then move to next column if queen can be place on any row.

1.) Start from the first column, first row, the program will try to place a queen and check only on the left side of it if any other queen is occurring in the attack line.
if not we place this queen here. if so we return a false backtrack to the previous column and try another row

2.) this process continues until a solution is found or until all possibilities are exhausted.

** Result **

![image](https://github.com/PattrayusC/NQueen/assets/121107697/d477594c-fea6-4c5f-8c9c-86d3bb0e4bd0)

![image](https://github.com/PattrayusC/NQueen/assets/121107697/d15f4444-a418-4e0e-a150-a6172cdc1f1d)

<p align="center">![image](https://github.com/PattrayusC/NQueen/assets/121107697/32e1145e-1dbe-40b3-9591-432e8bb589b2)</p>
<p align="center">Happy Coding!</p>
