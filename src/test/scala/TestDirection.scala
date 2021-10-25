import Direction.*

class TestDirection extends munit.FunSuite:

  inline def testOneMoveDirection(input: Option[UserInput])(before: Direction, after: Direction) =
    assertEquals(nextDirection(before, input), after)

  test("continue move when empty input") {
    testOneMoveDirection(noAction)(before = Up, after = Up)
    testOneMoveDirection(noAction)(before = Left, after = Left)
    testOneMoveDirection(noAction)(before = Right, after = Right)
    testOneMoveDirection(noAction)(before = Down, after = Down)
  }

  test("move down when down arrow") {
    testOneMoveDirection(arrow(Down))(before = Right, after = Down)
    testOneMoveDirection(arrow(Down))(before = Left, after = Down)
    testOneMoveDirection(arrow(Down))(before = Up, after = Down)
    testOneMoveDirection(arrow(Down))(before = Down, after = Down)
  }

  test("move up when up arrow") {
    testOneMoveDirection(arrow(Up))(before = Right, after = Up)
    testOneMoveDirection(arrow(Up))(before = Left, after = Up)
    testOneMoveDirection(arrow(Up))(before = Up, after = Up)
    testOneMoveDirection(arrow(Up))(before = Down, after = Up)
  }

  test("move left when left arrow") {
    testOneMoveDirection(arrow(Left))(before = Right, after = Left)
    testOneMoveDirection(arrow(Left))(before = Left, after = Left)
    testOneMoveDirection(arrow(Left))(before = Up, after = Left)
    testOneMoveDirection(arrow(Left))(before = Down, after = Left)
  }

  test("move right when right arrow") {
    testOneMoveDirection(arrow(Right))(before = Right, after = Right)
    testOneMoveDirection(arrow(Right))(before = Left, after = Right)
    testOneMoveDirection(arrow(Right))(before = Up, after = Right)
    testOneMoveDirection(arrow(Right))(before = Down, after = Right)
  }
