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

// ******** Part 1 - updating the snake's tail ********

/** Tests if the snake is eating the fruit.
  *
  * If the fruit and snake's head have an equal position, then the snake eats the fruit.
  *
  * hint: Use the `head` method on the `body` field of snake to get the head of the snake. The positition of the snake's
  * head is the head of the snake. The position of the fruit is accessed from the `position` field. We can test that two
  * nodes are equal using `==`.
  */
def eatsFruit(snake: Snake, fruit: Fruit): Boolean =
  fruit.block == snake.body.head

/** Returns the tail of the snake in the next frame of the game.
  *
  * If the snake is eating a fruit, then its current body will become its next tail, otherwise return the current body
  * with its rightmost block removed.
  *
  * hint: ???
  */
def nextTail(snake: Snake, isEating: Boolean): List[Block] =
  if isEating then snake.body
  else snake.body.dropRight(1)

// ******** Part 2 - change direction based on user input ********

/** Returns the direction that is opposite to the given direction
  *
  *   - The opposite of up is down
  *   - The opposite of down is up
  *   - The opposite of left is right
  *   - The opposite of right is left
  *
  * hint: ??? (use pattern match with literal pattern)
  */
def opposite(direction: Direction): Direction =
  direction match
    case Direction.Up    => Direction.Down
    case Direction.Down  => Direction.Up
    case Direction.Left  => Direction.Right
    case Direction.Right => Direction.Left

/** Returns the new direction that the snake will face, depending on if input was provided
  *
  * If the `inputDirection` is `None`, then return the `currentDirection`; otherwise if the `inputDirection` is
  * `Some(newDirection)` and `newDirection` is not the opposite of `currentDirection`, then return the `newDirection`,
  * else return the `currentDirection`
  *
  * hint: ??? (use opposite)
  */
def nextDirection(currentDirection: Direction, inputDirection: Option[Direction]): Direction =
  inputDirection match
    case Some(newDirection) =>
      if newDirection == opposite(currentDirection) then currentDirection
      else newDirection
    case None =>
      currentDirection

// ******** Part 3 - updating the snake's head ********

/** Helper method to wrap an x-ordinate around the world boundary */
def wrapX(worldSize: Size, x: Int): Int =
  if x >= worldSize.width then 0
  else if x < 0 then worldSize.width - 1
  else x

/** Helper method to wrap a y-ordinate around the world boundary
  *
  * hint: follow the model of `wrapX` and instead compare against the height.
  */
def wrapY(worldSize: Size, y: Int): Int =
  if y >= worldSize.height then 0
  else if y < 0 then worldSize.height - 1
  else y

/** Returns the head of the snake in the next frame of the game.
  *
  * The snakes next head should be translated in either the x, or y axis by 1 block, in the direction indicated. If the
  * snakes head would go beyond any boundary of the world, it should instead wrap back to the 0 position of the axis it
  * is travelling into.
  *
  *   - up should move 0 in the x-axis and -1 in the y-axis
  *   - down should move 0 in the x-axis and +1 in the y-axis
  *   - left should move -1 in the x-axis and 0 in the y-axis
  *   - right should move +1 in the x-axis and 0 in the y-axis
  *
  * hint: the direction the snake's head should travel along is given by `nextDirection`. The snakes head is given by
  * the `head` method of the snake's `body` field. Pattern match on `nextDirection` and return a new `Block` that has
  * moved relative the the snakes's head in that direction by following the above rules. Use `wrapX` and `wrapY` to
  * ensure that the new `Block` does not move beyond the world boundary, given by `worldSize`.
  */
def nextHead(snake: Snake, nextDirection: Direction, worldSize: Size): Block =
  val head = snake.body.head
  nextDirection match
    case Direction.Up    => Block(x = head.x, y = wrapY(worldSize, head.y - 1))
    case Direction.Down  => Block(x = head.x, y = wrapY(worldSize, head.y + 1))
    case Direction.Left  => Block(x = wrapX(worldSize, head.x - 1), y = head.y)
    case Direction.Right => Block(x = wrapX(worldSize, head.x + 1), y = head.y)

// ******** Part 4 ********

/** Returns the snake in the next frame of the game.
  *
  * This function computes the new direction of the snake, considering an optional input direction; the new head of the
  * snake, given its new direction, and fitting within the world's boundary; and the new tail of the snake, which varies
  * depending on if the snake is eating a fruit or not.
  *
  * hint: ???
  */
def nextSnake(snake: Snake, inputDirection: Option[Direction], isEating: Boolean, worldSize: Size): Snake =
  val newDir = nextDirection(snake.direction, inputDirection)
  val newHead = nextHead(snake, newDir, worldSize)
  val newTail = nextTail(snake, isEating)
  Snake(newDir, newHead :: newTail)

// ******** Part 5 ********

def createRandomFruit(worldSize: Size): Fruit =
  val x = Random.nextInt(worldSize.width)
  val y = Random.nextInt(worldSize.height)
  Fruit(Block(x, y))

def nextWorld(world: World, inputDirection: Option[Direction]): World =
  val isEating: Boolean = eatsFruit(world.snake, world.fruit)
  val newSnake: Snake = nextSnake(world.snake, inputDirection, isEating, world.size)
  val newFruit: Fruit = if isEating then createRandomFruit(world.size) else world.fruit
  World(newSnake, newFruit, world.size)

// ******** Part 6 ********

/** Tests if the snakes head has collided with the rest of its body.
  *
  * If the snake's tail contains the snake's head, then the snake has bit itself.
  *
  * hint: Use the `head` and `tail` methods on `body` field of snake to get the head and tail of the snake.
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

// ******** END ********
