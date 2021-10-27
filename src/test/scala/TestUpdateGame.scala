import Direction.*

// > testOnly TestUpdateGame
class TestUpdateGame extends munit.FunSuite:
  val worldSize: Size = Size(30, 30)
  val snake = Snake(Right, List(Block(0, 0)))
  val fruit = Fruit(Block(4, 0))
  val world = World(snake, fruit, worldSize)

  test("game is paused when user input is Pause") {
    val updated = updateGame(world, Some(UserInput.Pause))
    assertEquals(updated, world) // no change
  }
  test("game is gameOver when user input is Reset") {
    val updated = updateGame(world, Some(UserInput.Reset))
    assertEquals(updated, GameOver) //gameover to reset the game
  }
  test("game is gameOver when snake bitItself") {
    val snakeThatBitItself = Snake(Up, List(Block(6, 13), Block(6, 14), Block(5, 14), Block(5, 13), Block(6, 13)))
    val world = World(snakeThatBitItself, fruit, worldSize)
    val updated = updateGame(world, None)
    assertEquals(updated, GameOver) // game over when bitItself
    val updated2 = updateGame(world, Some(UserInput.Arrow(Up)))
    assertEquals(updated2, GameOver) // game over when bitItself
    val updated3 = updateGame(world, Some(UserInput.Pause))
    assertEquals(updated2, GameOver) // game over when bitItself
  }
  test("next world without user input") {
    val updated = updateGame(world, None)
    val expected = nextWorld(world, None)
    assertEquals(updated, expected)
  }
  test("next world with user input") {
    val updated = updateGame(world, Some(UserInput.Arrow(Down)))
    val expected = nextWorld(world, Some(Down))
    assertEquals(updated, expected)
  }
