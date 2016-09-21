#Argument Parser
#####Written by Scott Troutman, Adam Ingram, Jonnie Hosler, Brandon Taylor, Elgin Bowman, Penzheng Yang and Zachary Alwine
===========
##Description
===========
This software is designed to allow the user to let their program to take in arguments through the command line and then be parsed into usable values.

You can set program descriptions in code to allow for accurate personal documentation in code.

This program accepts 4 main data types: integers, strings, floats, and boolean values (denoted in code through the Types enum). There are relevant errors that will be thrown if the wrong data type is parsed.

The program can be set up to make command line arguments either required, named, or required named variables.
* Required arguments are arguments that must always be present when the parser is called, and must be in the exact same order as they are declared in the program.
* Named arguments are arguments that are not required to be present and can be used in whatever order the user desires. They are denoted by a name that is determined by the user when creating the code to use Argument Parser.
* Named required arguments function much the same as Named Arguments, except that they must be present, but they do not have a specific order like Required Arguments do.

