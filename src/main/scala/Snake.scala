import scala.util.Random

///// PROVIDED
case class Fruit(x: Int, y: Int)

object Fruit:
  def createRandom(maxX: Int, maxY: Int): Fruit =
    Fruit(
      Random.nextInt(maxX),
      Random.nextInt(maxY)
    )

enum Direction:
  case Up, Down, Left, Right

case class Node(x: Int, y: Int)

case class Snake(direction: Direction, head: Node, body: List[Node])

case class World(snake: Snake, fruit: Fruit, height: Int, width: Int)

case object GameOver

enum UserInput:
  case Arrow(direction: Direction) // An arrow key was pressed
  case Pause // The pause key was pressed
  case Reset // The reset key was pressed
  case Empty // No input was recieved this tick

def score(world: World): Int = world.snake.body.length - 1

//// WORKSHOP PARTICIPANTS WORK HERE:

// student
def bitItself(snake: Snake): Boolean =
  if snake.body.length <= 3 then false
  else snake.body.contains(snake.head)

// student
def wrap(x: Int, min: Int, max: Int) =
  if x >= max then min
  else if x < min then max - 1
  else x

// student
def nextDirection(previous: Direction, input: UserInput): Direction =
  input match
    case UserInput.Arrow(next) => next
    case _                     => previous

// student
def nextHead(head: Node, facing: Direction, height: Int, width: Int): Node =
  facing match
    case Direction.Up    => head.copy(y = wrap(head.y - 1, 0, height))
    case Direction.Down  => head.copy(y = wrap(head.y + 1, 0, height))
    case Direction.Left  => head.copy(x = wrap(head.x - 1, 0, width))
    case Direction.Right => head.copy(x = wrap(head.x + 1, 0, width))

// student (consider cheatsheet for list methods)
def nextBody(snake: Snake, isEating: Boolean): List[Node] =
  val newBody = snake.head :: snake.body
  if isEating then newBody
  else newBody.dropRight(1)

// student
def eatsFruit(snake: Snake, fruit: Fruit): Boolean =
  fruit.x == snake.head.x && fruit.y == snake.head.y

def nextWorld(world: World, input: UserInput): World | GameOver.type =
  val World(
    snake @ Snake(direction, head, _),
    fruit,
    height,
    width
  ) = world

  input match
    case UserInput.Pause       => world
    case UserInput.Reset       => GameOver
    case _ if bitItself(snake) => GameOver
    case _ =>
      val isEating = eatsFruit(snake, fruit)

      val newFruit =
        if isEating then Fruit.createRandom(width, height) else fruit

      val newDirection = nextDirection(direction, input)
      val newHead = nextHead(head, newDirection, width, height)
      val newBody = nextBody(snake, isEating)

      val newSnake = Snake(newDirection, newHead, newBody)

      world.copy(snake = newSnake, fruit = newFruit)

end nextWorld

/*
 * end of workshop. BUT time for expansion. Possible ideas:
 - Better graphics: images, colors, text, effects
 - Richer game engine: accelerate after fruit, fruits with different weight,
                       walls etc
 - toggle pause
 - context parameter for canvas context
 - make snake always have a head?
 */
