import scala.util.Random
import scala.deriving.Mirror
import scala.compiletime.constValue

import Direction.*

class TestMovement extends munit.FunSuite:

  val baseWorld = World(snake(Right, 0 -> 0), Fruit(Node(4, 0)), Size(30, 30))
  val snakeLength4 = snake(Down, 1 -> 1, 2 -> 1, 3 -> 1, 4 -> 1)

  test("can't reverse snake onto itself") {
    testOneMoveSnake(arrow(Down))(
      before = snake(Up, 0 -> 0),
      after = snake(Up, 0 -> 29)
    )
    testOneMoveSnake(arrow(Up))(
      before = snake(Down, 0 -> 0),
      after = snake(Down, 0 -> 1)
    )
    testOneMoveSnake(arrow(Left))(
      before = snake(Right, 0 -> 0),
      after = snake(Right, 1 -> 0)
    )
    testOneMoveSnake(arrow(Right))(
      before = snake(Left, 0 -> 0),
      after = snake(Left, 29 -> 0)
    )
  }

  test("move 1 square in current direction when empty input") {
    testOneMoveSnake(noAction)(
      before = snake(Right, 0 -> 0),
      after = snake(Right, 1 -> 0)
    )
    testOneMoveSnake(noAction)(
      before = snake(Down, 0 -> 0),
      after = snake(Down, 0 -> 1)
    )
    testOneMoveSnake(noAction)(
      before = snake(Left, 0 -> 0),
      after = snake(Left, 29 -> 0)
    )
    testOneMoveSnake(noAction)(
      before = snake(Up, 0 -> 0),
      after = snake(Up, 0 -> 29)
    )
  }

  test("move 1 square down when down arrow") {
    testOneMoveSnake(arrow(Down))(
      before = snake(Right, 0 -> 0),
      after = snake(Down, 0 -> 1)
    )
    testOneMoveSnake(arrow(Down))(
      before = snake(Down, 0 -> 0),
      after = snake(Down, 0 -> 1)
    )
    testOneMoveSnake(arrow(Down))(
      before = snake(Left, 0 -> 0),
      after = snake(Down, 0 -> 1)
    )
  }

  test("move 1 square up when up arrow") {
    testOneMoveSnake(arrow(Up))(
      before = snake(Right, 0 -> 0),
      after = snake(Up, 0 -> 29)
    )
    testOneMoveSnake(arrow(Up))(
      before = snake(Left, 0 -> 0),
      after = snake(Up, 0 -> 29)
    )
    testOneMoveSnake(arrow(Up))(
      before = snake(Up, 0 -> 0),
      after = snake(Up, 0 -> 29)
    )
  }

  test("move 1 square left when left arrow") {
    testOneMoveSnake(arrow(Left))(
      before = snake(Down, 0 -> 0),
      after = snake(Left, 29 -> 0)
    )
    testOneMoveSnake(arrow(Left))(
      before = snake(Left, 0 -> 0),
      after = snake(Left, 29 -> 0)
    )
    testOneMoveSnake(arrow(Left))(
      before = snake(Up, 0 -> 0),
      after = snake(Left, 29 -> 0)
    )
  }

  test("move 1 square right when right arrow") {
    testOneMoveSnake(arrow(Right))(
      before = snake(Right, 0 -> 0),
      after = snake(Right, 1 -> 0)
    )
    testOneMoveSnake(arrow(Right))(
      before = snake(Down, 0 -> 0),
      after = snake(Right, 1 -> 0)
    )
    testOneMoveSnake(arrow(Right))(
      before = snake(Up, 0 -> 0),
      after = snake(Right, 1 -> 0)
    )
  }

  test("snake length 4 remains valid after 100 moves") {
    validateMoves(snakeLength4, randomMoves(100))
  }

  def randomMoves(size: Int) = List.fill(size)(randomMove)

  def randomMove: UserInput =
    val mirror = summon[Mirror.SumOf[Direction]]
    val numberOfDirections = constValue[Tuple.Size[mirror.MirroredElemTypes]]
    val index = Random.nextInt(numberOfDirections)
    UserInput.Arrow(Direction.fromOrdinal(index))

  def sequenceMoves(snake: Snake, moves: List[UserInput]) =
    val world0 = baseWorld.copy(snake = snake, fruit = Fruit(Node(-1, -1))) // non-existant fruit
    val worlds = LazyList.unfold((world0, moves)) { (w, ms) =>
      ms match
        case m :: ms1 =>
          nextFromInput(w, Some(m)) match
            case GameOver => None // there was a snake-eat-self
            case w1: World =>
              Some((w1, (w1, ms1)))
        case _ => None
    }
    worlds

  inline def validateMoves(snake: Snake, moves: List[UserInput]) =
    val worlds = sequenceMoves(snake, moves)
    worlds.zipWithIndex.foreach { (w, i) =>
      if !validSnake(w) then fail(s"snake at move $i is not valid [${w.snake}]")
      if w.snake.body.length != snake.body.length then
        fail(s"snake unexpectedly gained or lost body nodes at move $i [${w.snake}]")
    }

  inline def testOneMoveSnake(input: Option[UserInput])(before: Snake, after: Snake) =
    val world1 = baseWorld.copy(snake = before)
    val world2 = baseWorld.copy(snake = after)
    assertEquals(nextFromInput(world1, input), world2)
