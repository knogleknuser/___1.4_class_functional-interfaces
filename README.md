# Functional programming in java

### Getting Started

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
Find the examples in package: functional
- `CallbackInJava`: 
- `DoubleColonOperator`: 
- `FunctionalComposition`: 
- `StreamDemo`: 
