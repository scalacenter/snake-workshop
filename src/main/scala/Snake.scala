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

case class Snake(dir: Direction, nodes: List[Node])

case class World(snake: Snake, fruit: Fruit, height: Int, width: Int)

enum UserInput:
  case Arrow(direction: Direction)
  case Reset
  case Pause
/////

def score(world: World): Int = world.snake.nodes.length - 1

//// WORKSHOP PARTICIPANTS WORK HERE:

def bitItself(snake: Snake): Boolean =
  snake match
    case Snake(_, body) if body.length <= 4 => false
    case Snake(_, head :: tail) =>
      tail.exists(node => node == head)

// student
def wrap(x: Int, min: Int, max: Int) =
  if x >= max then min
  else if x < min then max - 1
  else x

case object GameOver
def onTick(world: World, userInput: Option[UserInput]): World | GameOver.type =
  val World(snake @ Snake(dir, nodes @ head :: _), fruit, height, width) = world

  val newDirection: Direction = userInput
    .flatMap {
      case UserInput.Arrow(dir) => Some(dir)
      case _                    => None
    }
    .getOrElse(dir)

  // student
  val newFruit =
    if fruit.x == head.x && fruit.y == head.y then
      Fruit.createRandom(width, height)
    else fruit

  // student
  val newTail =
    if newFruit == fruit then nodes.dropRight(1)
    else nodes

  // student
  val newHead = newDirection match
    case Direction.Up    => head.copy(y = wrap(head.y - 1, 0, world.height))
    case Direction.Down  => head.copy(y = wrap(head.y + 1, 0, world.height))
    case Direction.Left  => head.copy(x = wrap(head.x - 1, 0, world.width))
    case Direction.Right => head.copy(x = wrap(head.x + 1, 0, world.width))

  val newSnake = Snake(newDirection, newHead :: newTail)

  if bitItself(snake) then GameOver
  else
    println(newSnake)
    World(newSnake, newFruit, height, width)

/*
 * end of workshop. BUT time for expansion. Possible ideas:
 - Better graphics: images, colors, text, effects
 - Richer game engine: accelerate after fruit, fruits with different weight,
                       walls etc
 */
