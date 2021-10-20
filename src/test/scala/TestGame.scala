import Direction.*

class TestGame extends munit.FunSuite:

  val initWorld = World(snake(Right, 0 -> 0), Fruit(4, 0), 30, 30)

  def ouroboros = snake(Up, 6 -> 13, 6 -> 14, 5 -> 14, 5 -> 13, 6 -> 13)
  def safe = snake(Down, 6 -> 15, 6 -> 14, 5 -> 14, 5 -> 13, 6 -> 13)

  test("snake bit itself implies game over") {
    val w1 = initWorld.copy(snake = ouroboros)
    val w2 = initWorld.copy(snake = safe)
    assertEquals(nextWorld(w1, UserInput.Empty), GameOver)
    assertNotEquals(nextWorld(w2, UserInput.Empty), GameOver: (World | GameOver.type))
  }
