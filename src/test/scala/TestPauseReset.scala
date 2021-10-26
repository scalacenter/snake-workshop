import Direction.*

class TestPauseReset extends munit.FunSuite:

  val baseWorld = World(snake(Right, 0 -> 0), Fruit(Block(4, 0)), Size(30, 30))

  test("pause input implies no change") {
    assertEquals(updateGame(baseWorld, pause), baseWorld)
  }

  test("reset input implies game over") {
    assertEquals(updateGame(baseWorld, reset), GameOver)
  }
