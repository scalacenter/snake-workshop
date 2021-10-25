import scala.util.Random

case class Fruit(position: Node)

object Fruit:
  def createRandom(dimension: World.Dimension): Fruit =
    Fruit(
      Node(
        Random.nextInt(dimension.height),
        Random.nextInt(dimension.width)
      )
    )

enum Direction:

  case Up, Down, Left, Right

  // student
  def opposite: Direction =
    this match
      case Up    => Down
      case Down  => Up
      case Left  => Right
      case Right => Left

  // student
  def nextDirection(inputDirectionOpt: Option[Direction]): Direction =
    inputDirectionOpt
      .filter(input => input != opposite)
      .getOrElse(this)

case class Node(x: Int, y: Int)

case class Snake(direction: Direction, head: Node, body: List[Node]):

  def move(inputDirection: Option[Direction], dimension: World.Dimension, isEating: Boolean): Snake =
    val newDir = direction.nextDirection(inputDirection)
    val newHead = nextHead(newDir, dimension)
    val newBody = nextBody(isEating)
    Snake(newDir, newHead, newBody)

  // student
  def eatsFruit(fruit: Fruit): Boolean =
    fruit.position == head

  // student
  def bitItself: Boolean =
    body.contains(head)

  // student
  private def nextHead(nextDirection: Direction, dimension: World.Dimension): Node =
    nextDirection match
      case Direction.Up    => head.copy(y = dimension.wrapY(head.y - 1))
      case Direction.Down  => head.copy(y = dimension.wrapY(head.y + 1))
      case Direction.Left  => head.copy(x = dimension.wrapX(head.x - 1))
      case Direction.Right => head.copy(x = dimension.wrapX(head.x + 1))

  // student (consider cheatsheet for list methods)
  private def nextBody(isEating: Boolean): List[Node] =
    val newBody = head :: body
    if isEating then newBody
    else newBody.dropRight(1)

case class World(snake: Snake, fruit: Fruit, dimension: World.Dimension):
  def nextWorld(inputDirection: Option[Direction], snake: Snake, fruit: Fruit): World =
    val isEating = snake.eatsFruit(fruit)
    val newSnake = snake.move(inputDirection, dimension, isEating)
    val newFruit = if isEating then Fruit.createRandom(dimension) else fruit
    this.copy(snake = newSnake, fruit = newFruit)

  def score: Int = snake.body.length - 1

object World:
  case class Dimension(height: Int, width: Int):
    // student
    def wrapX(x: Int) =
      if x >= width then 0
      else if x < 0 then width - 1
      else x

    // student
    def wrapY(y: Int) =
      if y >= height then 0
      else if y < 0 then height - 1
      else y

case object GameOver

enum UserInput:
  case Arrow(direction: Direction) // An arrow key was pressed
  case Pause // The pause key was pressed
  case Reset // The reset key was pressed

def nextWorld(world: World, input: Option[UserInput]): World | GameOver.type =
  val World(snake, fruit, _) = world

  input match
    case Some(UserInput.Pause) => world
    case Some(UserInput.Reset) => GameOver
    case _ if snake.bitItself  => GameOver
    case Some(UserInput.Arrow(direction)) =>
      world.nextWorld(Some(direction), snake, fruit)
    case None => world.nextWorld(None, snake, fruit)

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
