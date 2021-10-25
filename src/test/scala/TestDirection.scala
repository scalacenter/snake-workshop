import Direction.*

class TestDirection extends munit.FunSuite:

  inline def testOneMoveDirection(input: Option[Direction])(before: Direction, after: Direction) =
    assertEquals(before.nextDirection(input), after)

  test("continue move when empty input") {
    testOneMoveDirection(None)(before = Up, after = Up)
    testOneMoveDirection(None)(before = Left, after = Left)
    testOneMoveDirection(None)(before = Right, after = Right)
    testOneMoveDirection(None)(before = Down, after = Down)
  }

  test("move down when down arrow") {
    testOneMoveDirection(Some(Down))(before = Right, after = Down)
    testOneMoveDirection(Some(Down))(before = Left, after = Down)
    testOneMoveDirection(Some(Down))(before = Up, after = Up)
    testOneMoveDirection(Some(Down))(before = Down, after = Down)
  }

  test("move up when up arrow") {
    testOneMoveDirection(Some(Up))(before = Right, after = Up)
    testOneMoveDirection(Some(Up))(before = Left, after = Up)
    testOneMoveDirection(Some(Up))(before = Up, after = Up)
    testOneMoveDirection(Some(Up))(before = Down, after = Down)
  }

  test("move left when left arrow") {
    testOneMoveDirection(Some(Left))(before = Right, after = Right)
    testOneMoveDirection(Some(Left))(before = Left, after = Left)
    testOneMoveDirection(Some(Left))(before = Up, after = Left)
    testOneMoveDirection(Some(Left))(before = Down, after = Left)
  }

  test("move right when right arrow") {
    testOneMoveDirection(Some(Right))(before = Right, after = Right)
    testOneMoveDirection(Some(Right))(before = Left, after = Left)
    testOneMoveDirection(Some(Right))(before = Up, after = Right)
    testOneMoveDirection(Some(Right))(before = Down, after = Right)
  }
