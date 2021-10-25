import scala.util.Random
import scala.deriving.Mirror
import scala.compiletime.constValue

import Direction.*

class TestMovement extends munit.FunSuite:

  val initWorld = World(snake(Right, 0 -> 0), Fruit(4, 0), 30, 30)
  val snakeLength4 = snake(Down, 1 -> 1, 2 -> 1, 3 -> 1, 4 -> 1)

  test("move 1 square in current direction when empty input") {
    testOneMoveSnake(None)(
      before = snake(Right, 0 -> 0),
      after = snake(Right, 1 -> 0)
    )
    testOneMoveSnake(None)(
      before = snake(Down, 0 -> 0),
      after = snake(Down, 0 -> 1)
    )
    testOneMoveSnake(None)(
      before = snake(Left, 0 -> 0),
      after = snake(Left, 29 -> 0)
    )
    testOneMoveSnake(None)(
      before = snake(Up, 0 -> 0),
      after = snake(Up, 0 -> 29)
    )
  }

  test("move 1 square down when down arrow") {
    testOneMoveSnake(Some(UserInput.Arrow(Down)))(
      before = snake(Right, 0 -> 0),
      after = snake(Down, 0 -> 1)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Down)))(
      before = snake(Down, 0 -> 0),
      after = snake(Down, 0 -> 1)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Down)))(
      before = snake(Left, 0 -> 0),
      after = snake(Down, 0 -> 1)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Down)))(
      before = snake(Up, 0 -> 0),
      after = snake(Down, 0 -> 1)
    )
  }

  test("move 1 square up when up arrow") {
    testOneMoveSnake(Some(UserInput.Arrow(Up)))(
      before = snake(Right, 0 -> 0),
      after = snake(Up, 0 -> 29)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Up)))(
      before = snake(Down, 0 -> 0),
      after = snake(Up, 0 -> 29)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Up)))(
      before = snake(Left, 0 -> 0),
      after = snake(Up, 0 -> 29)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Up)))(
      before = snake(Up, 0 -> 0),
      after = snake(Up, 0 -> 29)
    )
  }

  test("move 1 square left when left arrow") {
    testOneMoveSnake(Some(UserInput.Arrow(Left)))(
      before = snake(Right, 0 -> 0),
      after = snake(Left, 29 -> 0)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Left)))(
      before = snake(Down, 0 -> 0),
      after = snake(Left, 29 -> 0)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Left)))(
      before = snake(Left, 0 -> 0),
      after = snake(Left, 29 -> 0)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Left)))(
      before = snake(Up, 0 -> 0),
      after = snake(Left, 29 -> 0)
    )
  }

  test("move 1 square right when right arrow") {
    testOneMoveSnake(Some(UserInput.Arrow(Right)))(
      before = snake(Right, 0 -> 0),
      after = snake(Right, 1 -> 0)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Right)))(
      before = snake(Down, 0 -> 0),
      after = snake(Right, 1 -> 0)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Right)))(
      before = snake(Left, 0 -> 0),
      after = snake(Right, 1 -> 0)
    )
    testOneMoveSnake(Some(UserInput.Arrow(Right)))(
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
    val world0 = initWorld.copy(snake = snake, fruit = Fruit(-1, -1)) // non-existant fruit
    val worlds = LazyList.unfold((world0, moves)) { (w, ms) =>
      ms match
        case m :: ms1 =>
          nextWorld(w, Some(m)) match
            case GameOver => None // there was a snake-eat-self
            case w1: World =>
              Some((w1, (w1, ms1)))
        case _ => None
    }
    worlds

  inline def validateMoves(snake: Snake, moves: List[UserInput]) =
    val worlds = sequenceMoves(snake, moves)
    worlds.zipWithIndex.foreach { (w, i) =>
      if !validSnake(w) then
        fail(s"snake at move $i is not valid [${w.snake}]")
    }

  inline def testOneMoveSnake(input: Option[UserInput])(before: Snake, after: Snake) =
    val world1 = initWorld.copy(snake = before)
    val world2 = initWorld.copy(snake = after)
    assertEquals(nextWorld(world1, input), world2)
