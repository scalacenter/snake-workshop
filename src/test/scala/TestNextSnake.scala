import Direction.*

// > testOnly TestNextSnake
class TestNextSnake extends munit.FunSuite:
  val worldSize = Size(30, 30)
  val snake = Snake(Left, List(Block(0, 0), Block(0, 1)))

  test("next Snake when the snake is not eating and no user input direction") {
    val notEating = false
    val noUserInput = None
    val next = nextSnake(snake, noUserInput, notEating, worldSize)
    val expected = Snake(Left, List(Block(29, 0), Block(0, 0)))
    assertEquals(next, expected)
  }
  test("next Snake when the snake is not eating and with user input direction") {
    val notEating = false
    val userInput = Up
    val next = nextSnake(snake, Some(userInput), notEating, worldSize)
    val expected = Snake(Up, List(Block(0, 29), Block(0, 0)))
    assertEquals(next, expected)
  }
  test("next Snake when the snake is eating and no user input direction") {
    val isEating = true
    val noUserInput = None
    val next = nextSnake(snake, noUserInput, isEating, worldSize)
    val expected = Snake(Left, List(Block(29, 0), Block(0, 0), Block(0, 1)))
    assertEquals(next, expected)
  }
  test("next Snake when the snake is eating and with user input direction") {
    val notEating = true
    val userInput = Up
    val next = nextSnake(snake, Some(userInput), notEating, worldSize)
    val expected = Snake(Up, List(Block(0, 29), Block(0, 0), Block(0, 1)))
    assertEquals(next, expected)
  }

