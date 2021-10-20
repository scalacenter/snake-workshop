import Direction.*

class TestDirection extends munit.FunSuite:

  val initWorld = World(snake(Right, 0 -> 0), Fruit(4, 0), 30, 30)

  inline def testOneMoveDirection(input: UserInput)(before: Direction, after: Direction) =
    assertEquals(nextDirection(before, input), after)

  test("continue move when empty input") {
    testOneMoveDirection(UserInput.Empty)(before = Up, after = Up)
    testOneMoveDirection(UserInput.Empty)(before = Left, after = Left)
    testOneMoveDirection(UserInput.Empty)(before = Right, after = Right)
    testOneMoveDirection(UserInput.Empty)(before = Down, after = Down)
  }

  test("move down when down arrow") {
    testOneMoveDirection(UserInput.Arrow(Down))(before = Right, after = Down)
    testOneMoveDirection(UserInput.Arrow(Down))(before = Left, after = Down)
    testOneMoveDirection(UserInput.Arrow(Down))(before = Up, after = Down)
    testOneMoveDirection(UserInput.Arrow(Down))(before = Down, after = Down)
  }

  test("move up when up arrow") {
    testOneMoveDirection(UserInput.Arrow(Up))(before = Right, after = Up)
    testOneMoveDirection(UserInput.Arrow(Up))(before = Left, after = Up)
    testOneMoveDirection(UserInput.Arrow(Up))(before = Up, after = Up)
    testOneMoveDirection(UserInput.Arrow(Up))(before = Down, after = Up)
  }

  test("move left when left arrow") {
    testOneMoveDirection(UserInput.Arrow(Left))(before = Right, after = Left)
    testOneMoveDirection(UserInput.Arrow(Left))(before = Left, after = Left)
    testOneMoveDirection(UserInput.Arrow(Left))(before = Up, after = Left)
    testOneMoveDirection(UserInput.Arrow(Left))(before = Down, after = Left)
  }

  test("move right when right arrow") {
    testOneMoveDirection(UserInput.Arrow(Right))(before = Right, after = Right)
    testOneMoveDirection(UserInput.Arrow(Right))(before = Left, after = Right)
    testOneMoveDirection(UserInput.Arrow(Right))(before = Up, after = Right)
    testOneMoveDirection(UserInput.Arrow(Right))(before = Down, after = Right)
  }
