---
sidebar_position: 2
---

# Scala Basics

This page provides an overview of the Scala language features required to complete
the workshop.

## Values

In Scala you can assign values to names using `val`:

```scala
val aNumber = 3
```
Optionally, you can explicitly assign a type to the val:
```scala
val aNumber: Int = 3
```

Scala is a statically typed language: every value has a type. Types help us to
detect mistakes early, during compilation, rather than later, when the program
is used. When we do not specify the type for a `val` Scala *guesses*, or infers,
an appropriate type

## Definitions

Functions can be defined with `def`:

```
def functionName(argument: TypeOfArgument): TypeOfReturn =
  body
```

for example

```scala
def sum(a: Int, b: Int): Int =
  a + b
```

## Case classes

A case class is used to create a new type combining already existing ones.
For example `Block` indicates a block in the Snake game and contains the `x` and `y` 
coordinates of the block.


```scala
case class Block(x: Int, y: Int)
```

You can create a block like this:

```scala
val block = Block(1, 2)
```

If you have a block, you can read its `x` and `y` attributes using `block.x` and `block.y`.
If you prefer, you can specify the variable name when you create a case class instance:

```scala
val block = Block(x=1, y=2)
```

## Enums

`enum` can be used to define a type with a finite set of named values. For example:

```scala
enum Direction:
  case Up, Down, Left, Right
```

This means that any `Direction` can take only one of the values `Up`, `Down`, `Left` or `Right`.

The only way to create a `Direction` is to access one of the cases:

```scala
val right = Direction.Right
```

## List

A list is a sequence of values. `List[Block]` means that all the elements of the list are a `Block`.
Here are a few useful operations on lists:

 - You can obtain the length of the list with `.length`: 
```scala
List(1, 2, 3).length // res0: Int = 3
```
 - You can extract the first element of the list with `.head`:
```scala
List(1, 2, 3).head // res0: Int = 1
```
 - You can obtain a list without the first element with `.tail`:
```scala
List(1, 2, 3).tail // res0: List[Int] = List(2, 3)
```
 - You can add a new element to the beginning a list with `::`:
```scala
0 :: List(1, 2, 3) // res0: List[Int] = List(0, 1, 2, 3)
```
 - You can check if a list contains an element with: `.contains`:
```scala
List(1, 2, 3).contains(2) // res0: Boolean = true
```
 - You can obtain a list without the last *n* elements with `.dropRight`:
```scala
List(1, 2, 3).dropRight(2) // res0: List[Int] = List(1)
```
 - You can obtain a list without the first *n* elements with `.drop`:
```scala
List(1, 2, 3).drop(2) // res0: List[Int] = List(3)
```

## If expressions

If expressions are similar to other languages

```scala
def evenTest(x: Int): String =
  if x % 2 == 0 then "x is even"
  else "x is odd"
```

To test multiple conditions you can chain conditions with `else if`:

```scala
def binary(x: Int): String =
  if x == 0 then "I know 0!"
  else if x == 1 then "I know 1!"
  else "What is this?"
```

## Pattern matching

Finally, Scala supports pattern matching. This feature accepts many syntaxes but
for this workshop we will only use it with `enum`, `Option` and case classes.

```scala
def directionToString(direction: Direction): String =
  direction match
    case Direction.Up => "You chose Up"
    case Direction.Down => "You chose Down"
    case Direction.Right => "You chose Right"
    case Direction.Left => "You chose Left"
```

After `List`, another common type in Scala is `Option[T]` which is used to express that a `T` might not be provided.
For example in `Snake.scala`, `updateGame` is called periodically to advance the game.
During that time the user might have pressed a button or not.
You can use pattern matching to process `Option`s:

```scala
def matchOptionExample(maybeNumber: Option[Int]): String
  maybeNumber match
    case Some(number) => "I received number " + number
    case None => "I did not receive any number"
```