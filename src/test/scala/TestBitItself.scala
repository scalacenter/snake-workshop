import Direction.*

class TestBitItself extends munit.FunSuite:

  val initWorld = World(snake(Right, 0 -> 0), Fruit(4, 0), 30, 30)

  def ouroboros = snake(Up, 6 -> 13, 6 -> 14, 5 -> 14, 5 -> 13, 6 -> 13)
  def safe = snake(Down, 6 -> 15, 6 -> 14, 5 -> 14, 5 -> 13, 6 -> 13)

  test("snake with length > 4 can eat its own tail") {
    testBitItself(ouroboros, didBite = true)
  }

  test("snake can only eat its own tail if its head overlaps") {
    testBitItself(safe, didBite = false)
  }

  test("snake <= 4 can't bite itself") {
    val ouroboros1 = snake(Up, 6 -> 13)
    val ouroboros2 = snake(Up, 6 -> 13, 6 -> 13)
    val ouroboros3 = snake(Left, 6 -> 13, 6 -> 13, 6 -> 12)
    val ouroboros4 = snake(Down, 6 -> 13, 6 -> 13, 5 -> 13, 4 -> 13)
    testBitItself(ouroboros1, didBite = false)
    testBitItself(ouroboros2, didBite = false)
    testBitItself(ouroboros3, didBite = false)
    testBitItself(ouroboros4, didBite = false)
  }

  inline def testBitItself(snake: Snake, didBite: Boolean) =
    assertEquals(bitItself(snake), didBite)
