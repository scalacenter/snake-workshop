import Direction.*

class TestGame extends munit.FunSuite:

  val baseWorld = World(snake(Right, 0 -> 0), Fruit(Node(4, 0)), World.Dimension(30, 30))

  def ouroboros = snake(Up, 6 -> 13, 6 -> 14, 5 -> 14, 5 -> 13, 6 -> 13)
  def safe = snake(Down, 6 -> 15, 6 -> 14, 5 -> 14, 5 -> 13, 6 -> 13)

  test("snake bit itself implies game over") {
    val w = baseWorld.copy(snake = ouroboros)
    assertEquals(nextWorld(w, noAction), GameOver)
  }

  test("snake did not bite itself implies not game over") {
    val w = baseWorld.copy(snake = safe)
    assertNotEquals(nextWorld(w, noAction), GameOver: (World | GameOver.type))
  }
