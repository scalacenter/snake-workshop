import Direction.*

class TestPauseReset extends munit.FunSuite:

  val initWorld = World(snake(Right, 0 -> 0), Fruit(4, 0), 30, 30)

  test("pause input implies no change") {
    assertEquals(nextWorld(initWorld, UserInput.Pause), initWorld)
  }

  test("reset input implies game over") {
    assertEquals(nextWorld(initWorld, UserInput.Reset), GameOver)
  }
