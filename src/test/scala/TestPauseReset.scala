import Direction.*

class TestPauseReset extends munit.FunSuite:

  val baseWorld = World(snake(Right, 0 -> 0), Fruit(Node(4, 0)), Size(30, 30))

  test("pause input implies no change") {
    assertEquals(nextFromInput(baseWorld, pause), baseWorld)
  }

  test("reset input implies game over") {
    assertEquals(nextFromInput(baseWorld, reset), GameOver)
  }
