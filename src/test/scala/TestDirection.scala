import Direction.*

class TestDirection extends munit.FunSuite:

  val initWorld = World(snake(Right, 0 -> 0), Fruit(4, 0), 30, 30)

  inline def testOneMoveDirection(input: Option[UserInput])(before: Direction, after: Direction) =
    assertEquals(nextDirection(before, input), after)

  test("continue move when empty input") {
    testOneMoveDirection(None)(before = Up, after = Up)
    testOneMoveDirection(None)(before = Left, after = Left)
    testOneMoveDirection(None)(before = Right, after = Right)
    testOneMoveDirection(None)(before = Down, after = Down)
  }

  test("move down when down arrow") {
    testOneMoveDirection(Some(UserInput.Arrow(Down)))(before = Right, after = Down)
    testOneMoveDirection(Some(UserInput.Arrow(Down)))(before = Left, after = Down)
    testOneMoveDirection(Some(UserInput.Arrow(Down)))(before = Up, after = Down)
    testOneMoveDirection(Some(UserInput.Arrow(Down)))(before = Down, after = Down)
  }

  test("move up when up arrow") {
    testOneMoveDirection(Some(UserInput.Arrow(Up)))(before = Right, after = Up)
    testOneMoveDirection(Some(UserInput.Arrow(Up)))(before = Left, after = Up)
    testOneMoveDirection(Some(UserInput.Arrow(Up)))(before = Up, after = Up)
    testOneMoveDirection(Some(UserInput.Arrow(Up)))(before = Down, after = Up)
  }

  test("move left when left arrow") {
    testOneMoveDirection(Some(UserInput.Arrow(Left)))(before = Right, after = Left)
    testOneMoveDirection(Some(UserInput.Arrow(Left)))(before = Left, after = Left)
    testOneMoveDirection(Some(UserInput.Arrow(Left)))(before = Up, after = Left)
    testOneMoveDirection(Some(UserInput.Arrow(Left)))(before = Down, after = Left)
  }

  test("move right when right arrow") {
    testOneMoveDirection(Some(UserInput.Arrow(Right)))(before = Right, after = Right)
    testOneMoveDirection(Some(UserInput.Arrow(Right)))(before = Left, after = Right)
    testOneMoveDirection(Some(UserInput.Arrow(Right)))(before = Up, after = Right)
    testOneMoveDirection(Some(UserInput.Arrow(Right)))(before = Down, after = Right)
  }
