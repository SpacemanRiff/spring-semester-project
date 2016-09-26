#Argument Parser
#####Written by Scott Troutman, Adam Ingram, Jonnie Hosler, Brandon Taylor, Elgin Bowman, Penzheng Yang and Zachary Alwine

###This does not serve as proper documentation for this program, this is merely here to provide a general overview.
###Every method and class has complete JavaDoc coverage for more in depth descriptions, syntax, and usage for the user.

##What Was Learned In The Process of Writing This Code
===========
Throughout the course of writing this code, I learned how to work in a group using Scrum, how to write an API, and how to use the test-first method of coding, among other things.

Scrum is a very powerful tool that many major companies impliment in order to have more efficient enviroment. We learned how to go about assigning weights to every feature we were going to impliment, and make sure those weights were meaningful, so that we didn't overburden ourselves with work.

Learning to write an API and properly document it is in incredibly important thing to understand how to do if you plan on working on code that exists in the public domain. Making sure every method, every class, every error is properly documented can make both your life and everyone else who plans on using your code's life dramatically easier down the road.

The test-first style of coding is imperative in writing solid code with as few bugs as possible. It is also useful in that it provides a solid beginning and end for every method you write. Knowing what output to expect from the method helps expedite the code writing process so you aren't left guessing as to what the code's output should be.

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


Here is an example of one of the demos, in this case, a program that uses argument parser to manage a pizza shop's orders.
```
    import edu.jsu.mcis.*;

    public class PizzaOrder{		
        public static void main(String[] args){
            
            ArgumentParser p = new ArgumentParser();
            
            p.setProgramDescription("Pizza Creator");
            
            Object[] restrictedSizes = {"small", "Small", "medium", "Medium", "large", "Large"};
            
            p.addPositionalArgument("Size", "The size of the pizza (Small, Medium, or Large)", ArgumentParser.Types.STRING);
            p.setRestrictedValues("Size", restrictedSizes);
            p.addPositionalArgument("Crust", "The style of pizza crust", ArgumentParser.Types.STRING);
            p.addPositionalArgument("Toppings", "The number of toppings for the pizza", ArgumentParser.Types.INTEGER);
            p.addRequiredNamedArgument("quantity", "Allows you to order multiple of the same pizza", ArgumentParser.Types.INTEGER, 1);
            p.addNamedArgument("drink", "The drink you will have with your pizza", ArgumentParser.Types.STRING, "No Drink");
            p.addNamedArgument("togo", "Is the order to go?", ArgumentParser.Types.BOOLEAN, false);
            
            
            p.parse(args);
            
            boolean toGo = p.getValueOf("togo");
            
            System.out.println("\n\nYou have ordered "  + p.getValueOf("quantity")
                                + " " + p.getValueOf("Size") + " pizza(s) with " + p.getValueOf("Crust")
                                + " crust and " + p.getValueOf("Toppings") + " topping(s)");
            System.out.println("Drink: " + p.getValueOf("drink"));
            if(toGo){
                System.out.println("This order is to-go");
            }else{
                System.out.println("This order is for here");
            }
        }
    }
```
In this code, you can see examples of how to add arguments using the addPositionalArgument method, the addRequiredNamedArgument, and the addNamedArgument.

You can also see how to access values stored in arguments using the getValueOf method.

There are other examples such as ones that show [saving and loading XML files](https://github.com/SpacemanRiff/spring-semester-project/tree/master/demos/XMLPizzaDemo).