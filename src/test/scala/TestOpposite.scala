import Direction.*

// > testOnly TestOpposite
class TestOpposite extends munit.FunSuite:

  test("opposite UP") {
    assertEquals(opposite(Up), Down)
  }
  test("opposite Down") {
    assertEquals(opposite(Down), Up)
  }
  test("opposite Left") {
    assertEquals(opposite(Left), Right)
  }
  test("opposite Right") {
    assertEquals(opposite(Right), Left)
  }
