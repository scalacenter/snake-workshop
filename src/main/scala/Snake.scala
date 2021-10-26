import scala.util.Random

case class Block(x: Int, y: Int)

case class Fruit(block: Block)

enum Direction:
  case Up, Down, Left, Right

case class World(snake: Snake, fruit: Fruit, size: Size)

case class Size(width: Int, height: Int)

case object GameOver

enum UserInput:
  case Arrow(direction: Direction) // An arrow key was pressed
  case Pause // The pause key was pressed
  case Reset // The reset key was pressed

case class Snake(direction: Direction, body: List[Block])

def score(world: World): Int = world.snake.body.length - 1

// ********

/** Tests if the snake is eating the fruit.
  *
  * If the fruit and snake's head have an equal position, then the snake eats the fruit.
  *
  * hint: Use the `head` method on the `body` field of snake to get the head of the snake. The positition of the snake's
  * head is the head of the snake. The position of the fruit is accessed from the `position` field.
  * We can test that two nodes are equal using `==`.
  */
def eatsFruit(snake: Snake, fruit: Fruit): Boolean =
  fruit.block == snake.body.head

// student (consider cheatsheet for list methods)
def nextBody(snake: Snake, isEating: Boolean): List[Block] =
  if isEating then snake.body
  else snake.body.dropRight(1)

// ********

// student
def opposite(direction: Direction): Direction =
  direction match
    case Direction.Up    => Direction.Down
    case Direction.Down  => Direction.Up
    case Direction.Left  => Direction.Right
    case Direction.Right => Direction.Left

// student
def nextDirection(currentDirection: Direction, inputDirection: Option[Direction]): Direction =
  inputDirection match
    case Some(newDirection) =>
      if newDirection == opposite(currentDirection) then currentDirection
      else newDirection
    case None =>
      currentDirection

// ********

def wrapX(worldSize: Size, x: Int) =
  if x >= worldSize.width then 0
  else if x < 0 then worldSize.width - 1
  else x

def wrapY(worldSize: Size, y: Int) =
  if y >= worldSize.height then 0
  else if y < 0 then worldSize.height - 1
  else y

// student
def nextHead(snake: Snake, nextDirection: Direction, worldSize: Size): Block =
  val head = snake.body.head
  nextDirection match
    case Direction.Up    => Block(x = head.x, y = wrapY(worldSize, head.y - 1))
    case Direction.Down  => Block(x = head.x, y = wrapY(worldSize, head.y + 1))
    case Direction.Left  => Block(x = wrapX(worldSize, head.x - 1), y = head.y)
    case Direction.Right => Block(x = wrapX(worldSize, head.x + 1), y = head.y)

// ********

def nextSnake(snake: Snake, inputDirection: Option[Direction], isEating: Boolean, worldSize: Size): Snake =
  val newDir = nextDirection(snake.direction, inputDirection)
  val newHead = nextHead(snake, newDir, worldSize)
  val newBody = nextBody(snake, isEating)
  Snake(newDir, newHead :: newBody)

// ********

def createRandomFruit(worldSize: Size): Fruit =
  val x = Random.nextInt(worldSize.width)
  val y = Random.nextInt(worldSize.height)
  Fruit(Block(x, y))

def nextWorld(world: World, inputDirection: Option[Direction]): World =
  val isEating: Boolean = eatsFruit(world.snake, world.fruit)
  val newSnake: Snake = nextSnake(world.snake, inputDirection, isEating, world.size)
  val newFruit: Fruit = if isEating then createRandomFruit(world.size) else world.fruit
  World(newSnake, newFruit, world.size)

// ********

/** Tests if the snakes head has collided with the rest of its body.
  *
  * If the snake's tail contains the snake's head, then the snake has bit itself.
  *
  * hint: Use the `head` and `tail` methods on `body` fielf of snake to get the head
  * and tail of the snake.
  */
def bitItself(snake: Snake): Boolean =
  snake.body.tail.contains(snake.body.head)

def updateGame(world: World, input: Option[UserInput]): World | GameOver.type =
  input match
    case anyInput if bitItself(world.snake) => GameOver
    case Some(UserInput.Reset)              => GameOver
    case Some(UserInput.Pause)              => world // student
    case Some(UserInput.Arrow(direction))   => nextWorld(world, Some(direction)) // student
    case None                               => nextWorld(world, None) // student

// ********

/*
 * end of workshop. BUT time for expansion. Possible ideas:
 - Better graphics: images, colors, text, effects
 - Richer game engine: accelerate after fruit, fruits with different weight,
                       walls etc
 - toggle pause
 - context parameter for canvas context
 - make snake always have a head?
 */
