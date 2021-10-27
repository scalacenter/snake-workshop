import Direction.*

class TestNextHead extends munit.FunSuite:

  val worldSize = Size(30, 30)

  test("next head when inside the worldsize") {
    val snake = Snake(Up, List(Block(15, 15)))
    assertEquals(nextHead(snake, Up, worldSize), expected = Block(15, 14))
    assertEquals(nextHead(snake, Down, worldSize), expected = Block(15, 16))
    assertEquals(nextHead(snake, Left, worldSize), expected = Block(14, 15))
    assertEquals(nextHead(snake, Right, worldSize), expected = Block(16, 15))
  }

  test("next head when crossing outside the worldsize") {
    val snake = Snake(Up, List(Block(0, 0)))
    assertEquals(nextHead(snake, Up, worldSize), expected = Block(0, 29))
    assertEquals(nextHead(snake, Down, worldSize), expected = Block(0, 1))
    assertEquals(nextHead(snake, Left, worldSize), expected = Block(29, 0))
    assertEquals(nextHead(snake, Right, worldSize), expected = Block(1, 0))
  }
