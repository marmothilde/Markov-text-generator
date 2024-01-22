#!/bin/bash


find ./src/ -type f -name "*.java" > sources.txt

javac -d ./out/ @sources.txt

cp text.txt ./out/text.txt

rm sources.txt

(cd "./out/" && java Main)