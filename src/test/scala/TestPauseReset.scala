import Direction.*

class TestPauseReset extends munit.FunSuite:

  val baseWorld = World(snake(Right, 0 -> 0), Fruit(4, 0), 30, 30)

  test("pause input implies no change") {
    assertEquals(nextWorld(baseWorld, pause), baseWorld)
  }

  test("reset input implies game over") {
    assertEquals(nextWorld(baseWorld, reset), GameOver)
  }
