#Argument Parser
#####Written by Scott Troutman, Adam Ingram, Jonnie Hosler, Brandon Taylor, Elgin Bowman, Penzheng Yang and Zachary Alwine

###This does not serve as proper documentation for this program, this is merely here to provide a general overview.
###Every method and class has complete JavaDoc coverage for more in depth descriptions, syntax, and usage for the user.

===========
##Description
===========
This software is designed to allow the user to let their program to take in arguments through the command line and then be parsed into usable values.

You can set program descriptions in code to allow for accurate personal documentation in code.

This program accepts 4 main data types: integers, strings, floats, and boolean values (denoted in code through the Types enum). There are relevant errors that will be thrown if the wrong data type is parsed.

The program can be set up to make command line arguments either required, named, or required named variables.
* Required arguments are arguments that must always be present when the parser is called, and must be in the exact same order as they are declared in the program.
* Named arguments are arguments that are not required to be present and can be used in whatever order the user desires. They are denoted by a name that is determined by the user when creating the code to use Argument Parser.
* Named required arguments function much the same as named arguments, except that they must be present, but they do not have a specific order like Required Arguments do.

The details about the configuration of the argument parser can be printed to screen using the printProgramInformation() method. 
This diplays the following information:
*The required arguments and their data types in the correct order that are to be typed into the command line.
*All named arguments, required or optional, and their data types.
*A description of the program, and descriptions of every argument.

Arguments configurations can be loaded in from an XML file, if the user prefers. There is an XML schema provided to help with this process.

Argument configurations that are designed or edited in code can be saved to an XML file to be loaded back later, making modularity a big plus.

All code has complete code coverage where applicable.

##Examples
===========
Examples of code in use can be found in the [demos folder](https://github.com/SpacemanRiff/spring-semester-project/tree/master/demos) in the root directory of this repository.

Examples show off all features including adding all different arguments types, saving and loading to XML, as well as showing a few of the including error messages and cases where they appear.