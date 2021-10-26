# Introduction

## Structure

The code is written in three files:

 - `src/main/scala/Snake.scala`: logic of the game, you will work on this file
 - `src/main/scala/Launcher.scala`: logic to start the game. This file is provided
 - `src/main/scala/Draw.scala`: logic to draw the terrain and snake on the screen. This file is provided
 - `index.html`: the web page that contains the game. Open this file in Firefox/Chrome/Safari to play

## How to compile and run

 1. Start a `sbt` shell. 
 2. To run the game, execute the task `fastLinkJS`. This will compile the Scala code into a Javascript file which you can find in `target/scala-3.0.2/snake-fastopt/main.js`.
 3. Open `index.html` in your browser of choice, this file loads the Javascript produced in the previous step and runs it.

## Logic overview

The state of the game at any given time is represented by the `World` class

```scala
case class World(snake: Snake, fruit: Fruit, height: Int, width: Int)
```

in other words, each object of type `World` is a photograph of the state of the game. It contains a snake, a fruit, and it has a height and a width.

The goal of this workshop is to implement the `nextWorld` function which, provided the previous state of the game and the action of the player,
returns the next state of the world.

The code in `Launcher.scala` creates a fresh world and every 250 milliseconds calls `nextWorld`.


### The grid

The game can be represented as a table, or grid. Each element of the grid can be empty, occupied by a fruit or by a node of the snake.
The dimensions of the grid are specified in the `World` case class:


 - `height` is the number of rows of the grid. We will use of the convention of naming `y` the variables indicating vertical position
 - `width` is the number of columns of the grid. We will use of the convention of naming `x` the variables indicating horizontal position
 - The top left corner of the grid has position `(x=0, y=0)`
 - The top right corner of the grid has position `(x=width-1, y=0)`
 - The bottom left corner of the grid has position `(x=0, y=height-1)`
 - The bottom right corner of the grid has position `(x=width-1, y=height-1)`

![the grid](/img/snake/grid.png)

### Fruit

```scala
case class Fruit(x: Int, y: Int)
```

A fruit occupies a slot in the grid.

You can create a fruit like this:

```scala
val fruit = Fruit(1, 2)
```

or create a fruit with random position

```scala
val fruit = Fruit.createRandom(maxX, maxY)
```
where you can replace `maxX` and `maxY` with largest values that make `x` and `y` can have.

If you have a fruit, you can read its `x` and `y` attributes using `fruit.x` and `fruit.y`.

### Snake

A snake is made up of blocks, or nodes. Each node has an `x` and `y` coordinates.
All of the nodes of a snake are collected into a list.
The snake also contains the direction in which it is moving.

`Direction` is an `enum`:

```scala
enum Direction:
  case Up, Down, Left, Right
```
This means that any `Direction` can take only one of the values `Up`, `Down`, `Left` or `Right`.

The only way to create a `Direction` is to access one of the cases:

```scala
val right = Direction.Right
```

### List

A list is a sequence of values. `List[Node]` means that all the elements of the list are a `Node`.
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
 - You can obtain a list without the last *n* elements with `.dropRight`:
```scala
List(1, 2, 3).dropRight(2) // res0: List[Int] = List(1)
```
 - You can obtain a list without the first *n* elements with `.drop`:
```scala
List(1, 2, 3).drop(2) // res0: List[Int] = List(3)
```

### More about Scala

In Scala you can assign values to names using `val`:

```scala
val aNumber = 3
```
Optionally, you can explicitly assign a type to the val:
```scala
val aNumber: Int = 3
```

Functions can be defined with `def`:

```
def functionName(argument: TypeOfARgument): TypeOfReturn =
  body
```

for example

```scala
def sum(a: Int, b: Int): Int =
  a + b
```

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

Finally, Scala supports pattern matching. This feature accepts many syntaxes but for this workshop we will use it only on simple enums.

```scala
def directionToString(direction: Direction): String =
  direction match
    case Direction.Up => "You chose Up"
    case Direction.Down => "You chose Down"
    case Direction.Right => "You chose Right"
    case Direction.Left => "You chose Left"
```

After `List`, another common type in Scala is `Option[T]` which is used to express that a `T` might not be provided.
For example in `Snake.scala`, `onTick` is called periodically to advance the game.
During that time the user might have pressed a button or not.
You can use pattern matching to process `Option`s:

```scala
input match
  case Some(UserInput.Pause) => // user pressed pause
  case Some(UserInput.Reset) => // user pressed Reset
  case Some(UserInput.Arrow(direction)) => // user pressed arrow key pointing towards direction
  case None => // User did not press any key
```