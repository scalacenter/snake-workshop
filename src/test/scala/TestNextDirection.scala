import Direction.*

class TestNextDirection extends munit.FunSuite:

  test("nextDirection when no user input") {
    assertEquals(nextDirection(Up, None), Up)
    assertEquals(nextDirection(Down, None), Down)
    assertEquals(nextDirection(Left, None), Left)
    assertEquals(nextDirection(Right, None), Right)
  }
  test("nextDirection when there is a user input direction") {
    // Up
    assertEquals(nextDirection(Up, Some(Left)), Left)
    assertEquals(nextDirection(Up, Some(Right)), Right)
    assertEquals(nextDirection(Up, Some(Up)), Up)
    // right
    assertEquals(nextDirection(Right, Some(Up)), Up)
    assertEquals(nextDirection(Right, Some(Down)), Down)
    assertEquals(nextDirection(Right, Some(Right)), Right)
    // Left
    assertEquals(nextDirection(Left, Some(Up)), Up)
    assertEquals(nextDirection(Left, Some(Down)), Down)
    assertEquals(nextDirection(Left, Some(Left)), Left)
    // down
    assertEquals(nextDirection(Down, Some(Left)), Left)
    assertEquals(nextDirection(Down, Some(Right)), Right)
    assertEquals(nextDirection(Down, Some(Down)), Down)
  }
  test("nextDirection when there is an opposite user input direction") {
    // Up
    assertEquals(nextDirection(Up, Some(Down)), Up)
    assertEquals(nextDirection(Down, Some(Up)), Down)
    assertEquals(nextDirection(Left, Some(Right)), Left)
    assertEquals(nextDirection(Right, Some(Left)), Right)
  }
