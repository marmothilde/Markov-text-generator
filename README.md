# Markov-text-generator
A text generator based on Markov chains

## Install

In order to run the program you must have Java 21 and Maven installed on your computer. 

### Linux
To compile and run the program, simply run the "run.sh" script that will compile the Java files and lanch the program without other action needed.

### Windows 
To compile and run the program, simply run the "run.bat" script that will compile the Java files and lanch the program without other action needed.

## Change example text

The program is currently using a mix of Wikipedia's pages on LGBT, Star Wars and Markov chain in french as exemple to generate it's text. 
You can change this by adding your text in the "text.txt" file in the ./src/main/resources/ folder .
/!\ Warning : your text must be formated in a certain way for the program to run properly. You must put a space between each word and punctuation otherwise the generated text might be less good.