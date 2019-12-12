# Functions\_Gui and ComplexFunctions

**Authors:** Orad Cohen, Teddy Grossman.

**Language:** Java.

**Interface:** function, functions, complex\_function.

**Package:** Ex1.

**Files:** Function\_Gui, ComplexFunction, FunctionGuiTest, ComplexFunctionTest, PolynomTest, MonomTest

**Description**

Our project deals with complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions),  y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).

In addition, our implementation of functions, Function\_Gui, can store multiple complex functions, read them from a file and save the stored functions to a file, in addition, we can draw function using STDDraw, while using our parameters stores in json file, or directly entering the parameters into the method.

**Design decisions and issues.**

For Complex function, we had trouble understanding how to store the functions. But that was because our lack of understanding of interfaces. Where function can be polynom or monom or Complex function, after we understood that, it was easy to implement it.

For Function\_Gui we have stored the function in ArrayList, since the order doesn&#39;t matter, we used STDDraw to draw functions, since it was provided with the assignment and it&#39;s a comfortable library to work with.

see more in Issues and wiki.

**Testing:**

We use Junit library to test our functions, since it was our first time it took a while at first to do, tests exmaples are found in our [Wiki.](https://github.com/orad-cohen/Ex1/wiki)
