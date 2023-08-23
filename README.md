
*This project is meant as start code for projects and exercises given in Flow-1+2 (+3 using the security-branch) at http://cphbusiness.dk in the Study Program "AP degree in Computer Science"*

*Projects which are expected to use this start-code are projects that require all, or most of the following technologies:*
 - *JPA and REST*
- *Testing, including database test*
- *Testing, including tests of REST-API's*
- *CI and CONTINUOUS DELIVERY*

## Flow 2 week 1

### Preconditions
*In order to use this code, you should have a local developer setup + a "matching" droplet on Digital Ocean as described in the 3. semester guidelines* 

### Getting Started

This document explains how to use this code (build, test and deploy), locally with maven, and remotely with maven controlled by Github actions
 - [How to use](https://docs.google.com/document/d/1rymrRWF3VVR7ujo3k3sSGD_27q73meGeiMYtmUtYt6c/edit?usp=sharing)
 
### Functional programming
[Functional programming in java](https://www.baeldung.com/java-functional-programming)
#### Why should we care
Immutability and pure functions with no side effects makes our program easier to **read, reason about, test, and maintain**.
Declarative programming (such as functional programming style) is often more readable than imperative programming (as traditional OOP)
#### Some key concepts
- Pure functions
  - a pure function should return a value based only on the arguments and should have no side effects
- Immutability
  - an entity can't be modified after being instantiated. Using final keyword
- Functional Composition
  - composing complex functions by combining simpler functions.
- Functional interface
  - any interface with a single abstract method
  - in java these are necessary to (syntaxically only) pass functions to other functions as arguments (in java only objects can be passed)
- Monads
  - Like java.util.steams.Streams<T> are structures that represents computations defined as sequences of steps (by chaining functions together)
  - A stream represents a sequence of elements.
  - Important Stream methods [Stream tutorial here](https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/)
    - reduce
    - collect
    - flatMap
  - Java 8 streams cannot be reused. As soon as you call any terminal operation the stream is closed.
- Currying
- Recursion

#### libraries
With java8 came java.util.function.* with many new classes for functional programming
Also java.util.stream.* with especially java.util.stream.Stream<T> for 

#### Code examples
Find the examples in package: functional# repo auto created
