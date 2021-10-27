import Direction.*

// > testOnly TestBitItself
class TestBitItself extends munit.FunSuite:

  test("one node snake can't bit itself") {
    assertEquals(bitItself(Snake(Left, List(Block(0, 0)))), false)
  }

  test("snake didnt bit itself") {
    assertEquals(bitItself(Snake(Left, List(Block(0, 0)))), false)
    val snake = Snake(Down, List(Block(6, 15), Block(6, 14), Block(5, 14), Block(5, 13), Block(6, 13)))
    assertEquals(bitItself(snake), false)
  }

  test("snake did bit itself") {
    def snake = Snake(Up, List(Block(6, 13), Block(6, 14), Block(5, 14), Block(5, 13), Block(6, 13)))
    assertEquals(bitItself(snake), true)
  }
