import Direction.*

class TestEatsFruit extends munit.FunSuite:
  val snake = Snake(Left, List(Block(2, 0), Block(1, 0), Block(0, 0)))

  test("snake collides when head touches fruit") {
    testEatsFruit(Fruit(Block(2, 0)), snake, didEat = true)
    testEatsFruit(
      Fruit(Block(29, 29)),
      Snake(Left, List(Block(29, 29), Block(29, 0), Block(0, 0))),
      didEat = true
    )
  }

  test("snake doesn't eat fruit") {
    testEatsFruit(Fruit(Block(15, 15)), snake, didEat = false)
  }
  test("snake does not eat when any part of its body touches the fruit") {
    testEatsFruit(Fruit(Block(3, 4)), Snake(Left, List(Block(4, 4), Block(3, 4))), didEat = false)
    testEatsFruit(
      Fruit(Block(0, 4)),
      Snake(Left, List(Block(0, 0), Block(29, 0), Block(29, 29))),
      didEat = false
    )
    testEatsFruit(fruit = Fruit(Block(1, 2)), Snake(Left, List(Block(0, 2), Block(1, 2), Block(1, 1))), didEat = false)
  }

  inline def testEatsFruit(fruit: Fruit, snake: Snake, didEat: Boolean) =
    assertEquals(eatsFruit(snake, fruit), didEat)
