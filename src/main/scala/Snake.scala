import scala.util.Random

/** Represents an occupied space in the world */
case class Node(x: Int, y: Int)

case class Fruit(position: Node)

enum Direction:
  case Up, Down, Left, Right

case class World(snake: Snake, fruit: Fruit, size: Size)

case class Size(height: Int, width: Int)

case object GameOver

enum UserInput:
  case Arrow(direction: Direction) // An arrow key was pressed
  case Pause // The pause key was pressed
  case Reset // The reset key was pressed

case class Snake(direction: Direction, body: List[Node])

def nextFromInput(world: World, input: Option[UserInput]): World | GameOver.type =
  input match
    case anyInput if bitItself(world.snake) => GameOver
    case Some(UserInput.Reset)              => GameOver
    case Some(UserInput.Pause)              => world
    case Some(UserInput.Arrow(direction))   => nextWorld(world, Some(direction))
    case None                               => nextWorld(world, None)

def nextWorld(world: World, inputDirection: Option[Direction]): World =
  val isEating = eatsFruit(world.snake, world.fruit)
  val newSnake = nextSnake(world.snake, inputDirection, isEating, world.size)
  val newFruit = if isEating then createRandomFruit(world.size) else world.fruit
  world.copy(snake = newSnake, fruit = newFruit)

def nextSnake(snake: Snake, inputDirection: Option[Direction], isEating: Boolean, size: Size): Snake =
  val newDir = nextDirection(snake.direction, inputDirection)
  val newHead = nextHead(snake, newDir, size)
  val newBody = nextBody(snake, isEating)
  Snake(newDir, newHead :: newBody)

def createRandomFruit(size: Size): Fruit =
  val x = Random.nextInt(size.width)
  val y = Random.nextInt(size.height)
  Fruit(Node(x, y))

// student
def bitItself(snake: Snake): Boolean =
  snake.body.tail.contains(snake.body.head)

// student
def eatsFruit(snake: Snake, fruit: Fruit): Boolean =
  fruit.position == snake.body.head

// student
def nextHead(snake: Snake, nextDirection: Direction, size: Size): Node =
  val head = snake.body.head
  nextDirection match
    case Direction.Up    => head.copy(y = wrapY(size, head.y - 1))
    case Direction.Down  => head.copy(y = wrapY(size, head.y + 1))
    case Direction.Left  => head.copy(x = wrapX(size, head.x - 1))
    case Direction.Right => head.copy(x = wrapX(size, head.x + 1))

// student (consider cheatsheet for list methods)
def nextBody(snake: Snake, isEating: Boolean): List[Node] =
  if isEating then snake.body
  else snake.body.dropRight(1)

// student
def wrapX(size: Size, x: Int) =
  if x >= size.width then 0
  else if x < 0 then size.width - 1
  else x

// student
def wrapY(size: Size, y: Int) =
  if y >= size.height then 0
  else if y < 0 then size.height - 1
  else y

// student
def nextDirection(current: Direction, inputDirection: Option[Direction]): Direction =
  inputDirection
    .filter(input => input != opposite(current))
    .getOrElse(current)

// student
def opposite(to: Direction): Direction =
  to match
    case Direction.Up    => Direction.Down
    case Direction.Down  => Direction.Up
    case Direction.Left  => Direction.Right
    case Direction.Right => Direction.Left

def score(world: World): Int = world.snake.body.length - 1

/*
 * end of workshop. BUT time for expansion. Possible ideas:
 - Better graphics: images, colors, text, effects
 - Richer game engine: accelerate after fruit, fruits with different weight,
                       walls etc
 - toggle pause
 - context parameter for canvas context
 - make snake always have a head?
 */
