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

// ******** Part 1 - growing the snake, based on if it ate the fruit ********

/** Tests if the snake is eating the fruit.
  *
  * If the fruit's underlying block and snake's head have an equal position, then the snake eats the fruit.
  *
  * hint: The snake's head is the first element of its `body` field. You can find `List` methods (such as finding the
  * first element) described in the "Introduction to Scala" page of the website.
  */
def eatsFruit(snake: Snake, fruit: Fruit): Boolean =
  fruit.block == snake.body.head

/** Returns the tail of the snake in the next frame of the game.
  *
  * If the snake is not eating a fruit, then its body will lose its rightmost Block, otherwise its body will become its
  * next tail.
  *
  * hint: look up "If expressions" in the "Introduction to Scala" page of the website.
  */
def nextTail(snake: Snake, isEating: Boolean): List[Block] =
  if isEating then snake.body
  else snake.body.dropRight(1)

// ******** Part 2 - Reacting to player's input, changing the snake's direction ********

/** Returns the direction that is opposite to the provided direction, e.g. The opposite of up is down.
  *
  * hint: look up "Pattern matching" in the "Introduction to Scala" page of the website.
  */
def opposite(direction: Direction): Direction =
  direction match
    case Direction.Up    => Direction.Down
    case Direction.Down  => Direction.Up
    case Direction.Left  => Direction.Right
    case Direction.Right => Direction.Left

/** Returns the new direction that the snake will face, depending on if input was provided.
  *
  * If an input direction was provided, then that will become the new direction, as long as the direction is valid;
  * otherwise we retain the current direction.
  *
  * hint: you will need to pattern match on `inputDirection`. A snake cannot turn into its own body.
  */
def nextDirection(currentDirection: Direction, inputDirection: Option[Direction]): Direction =
  inputDirection match
    case Some(newDirection) =>
      if newDirection == opposite(currentDirection) then currentDirection
      else newDirection
    case None =>
      currentDirection

// ******** Part 3 - Moving the snake's head, based on its new direction ********

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
  * The snakes next head should be translated by one block in the direction indicated, considering the world's
  * boundaries.
  *
  * hint: the rules for movement are given in the workshop instructions.
  */
def nextHead(snake: Snake, nextDirection: Direction, worldSize: Size): Block =
  val head = snake.body.head
  nextDirection match
    case Direction.Up    => Block(x = head.x, y = wrapY(worldSize, head.y - 1))
    case Direction.Down  => Block(x = head.x, y = wrapY(worldSize, head.y + 1))
    case Direction.Left  => Block(x = wrapX(worldSize, head.x - 1), y = head.y)
    case Direction.Right => Block(x = wrapX(worldSize, head.x + 1), y = head.y)

// ******** Part 4 - Updating the snake, based on user input and if it ate the fruit ********

/** Returns the snake in the next frame of the game.
  *
  * The updated snake will have a new direction, given an optional input direction; a new head, given its new direction;
  * and a new tail, given whether or not the snake is eating a fruit.
  *
  * hint: use the functions you implemented above to compute the new attributes of the snake.
  */
def nextSnake(snake: Snake, inputDirection: Option[Direction], isEating: Boolean, worldSize: Size): Snake =
  val newDir = nextDirection(snake.direction, inputDirection)
  val newHead = nextHead(snake, newDir, worldSize)
  val newTail = nextTail(snake, isEating)
  Snake(newDir, newHead :: newTail)

// ******** Part 5 - Updating the world, based on user input ********

/** Returns a new fruit with a random position, fitting within the boundary of the world.
  */
def createRandomFruit(worldSize: Size): Fruit =
  val x = Random.nextInt(worldSize.width)
  val y = Random.nextInt(worldSize.height)
  Fruit(Block(x, y))

/** Returns the world in the next frame of the game.
  *
  * The updated world will have a new fruit, and a new snake.
  *
  * The attributes of the new snake depend on the input direction and if it ate the fruit. The new fruit will either
  * remain in the same position if it was not eaten, otherwise it should have a random next position.
  *
  * hint: use the `createRandomFruit` method when a new position is needed for the fruit. Use the functions you
  * implemented above to test if the fruit was eaten, and to create the new snake.
  */
def nextWorld(world: World, inputDirection: Option[Direction]): World =
  val isEating: Boolean = eatsFruit(world.snake, world.fruit)
  val newSnake: Snake = nextSnake(world.snake, inputDirection, isEating, world.size)
  val newFruit: Fruit = if isEating then createRandomFruit(world.size) else world.fruit
  World(newSnake, newFruit, world.size)

// ******** Part 6 - Updating the game, based on user input ********

/** Tests if the snake's head has collided with the rest of its body.
  *
  * If the snake's tail contains the snake's head, then the snake has bit itself.
  *
  * hint: Lookup the available `List` methods in the "Introduction to Scala" page of the website.
  */
def bitItself(snake: Snake): Boolean =
  snake.body.tail.contains(snake.body.head)

/** Returns either the next world of the current game, or GameOver, by inspecting the user input.
  *
  * Game Over states (where `GameOver` should be returned):
  *   - if the snake is currently biting itself, (i.e. before considering any possible moves for the snake),
  *   - if the user pressed the reset key.
  *
  * Game play states (where an updated `world` should be returned):
  *   - if the user pressed the pause key, then `world` remains unchanged,
  *   - if the user pressed an arrow key, then use the direction provided to update `world`,
  *   - if the user provided no input, then update `world` using an empty direction.
  */
def updateGame(world: World, input: Option[UserInput]): World | GameOver.type =
  input match
    case anyInput if bitItself(world.snake) => GameOver
    case Some(UserInput.Reset)              => GameOver
    case Some(UserInput.Pause)              => world
    case Some(UserInput.Arrow(direction))   => nextWorld(world, Some(direction))
    case None                               => nextWorld(world, None)

// ******** END ********
