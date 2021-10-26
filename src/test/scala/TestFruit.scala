import Direction.*

class TestFruit extends munit.FunSuite:

  val baseWorld = World(snake(Right, 0 -> 0), Fruit(Node(4, 0)), Size(30, 30))

  test("snake collides when head touches fruit") {
    testEatsFruit(fruit = 3 -> 4, snake(Left, 3 -> 4), didEat = true)
    testEatsFruit(fruit = 11 -> 13, snake(Left, 11 -> 13), didEat = true)
    testEatsFruit(
      fruit = 29 -> 29,
      snake(Left, 29 -> 29, 0 -> 29),
      didEat = true
    )
  }

  test("snake does not eat when only tail touches fruit") {
    testEatsFruit(fruit = 3 -> 4, snake(Left, 2 -> 4, 3 -> 4), didEat = false)
    testEatsFruit(
      fruit = 29 -> 29,
      snake(Left, 0 -> 0, 29 -> 0, 29 -> 29),
      didEat = false
    )
  }

  test("when snake length 1 eats fruit, grow one node") {
    testOneMoveEatFruit(
      before = snake(Right, 4 -> 0),
      after = snake(Right, 5 -> 0, 4 -> 0)
    )
    testOneMoveEatFruit(
      before = snake(Up, 4 -> 0),
      after = snake(Up, 4 -> 29, 4 -> 0)
    )
    testOneMoveEatFruit(
      before = snake(Down, 4 -> 0),
      after = snake(Down, 4 -> 1, 4 -> 0)
    )
    testOneMoveEatFruit(
      before = snake(Left, 4 -> 0),
      after = snake(Left, 3 -> 0, 4 -> 0)
    )
  }

  test("when snake length 2 eats fruit, grow one node") {
    testOneMoveEatFruit(
      before = snake(Right, 4 -> 0, 4 -> 1),
      after = snake(Right, 5 -> 0, 4 -> 0, 4 -> 1)
    )
    testOneMoveEatFruit(
      before = snake(Up, 4 -> 0, 4 -> 1),
      after = snake(Up, 4 -> 29, 4 -> 0, 4 -> 1)
    )
    testOneMoveEatFruit(
      before = snake(Down, 4 -> 0, 3 -> 0),
      after = snake(Down, 4 -> 1, 4 -> 0, 3 -> 0)
    )
    testOneMoveEatFruit(
      before = snake(Left, 4 -> 0, 4 -> 1),
      after = snake(Left, 3 -> 0, 4 -> 0, 4 -> 1)
    )
  }

  test("when snake length 3 eats fruit, grow one node") {
    testOneMoveEatFruit(
      before = snake(Right, 4 -> 0, 4 -> 1, 4 -> 2),
      after = snake(Right, 5 -> 0, 4 -> 0, 4 -> 1, 4 -> 2)
    )
    testOneMoveEatFruit(
      before = snake(Up, 4 -> 0, 4 -> 1, 4 -> 2),
      after = snake(Up, 4 -> 29, 4 -> 0, 4 -> 1, 4 -> 2)
    )
    testOneMoveEatFruit(
      before = snake(Down, 4 -> 0, 3 -> 0, 2 -> 0),
      after = snake(Down, 4 -> 1, 4 -> 0, 3 -> 0, 2 -> 0)
    )
    testOneMoveEatFruit(
      before = snake(Left, 4 -> 0, 4 -> 1, 4 -> 2),
      after = snake(Left, 3 -> 0, 4 -> 0, 4 -> 1, 4 -> 2)
    )
  }

  inline def testOneMoveEatFruit(before: Snake, after: Snake) =
    val world1 = baseWorld.copy(snake = before)
    val world2 = nextFromInput(world1, None).asInstanceOf[World]
    assertEquals(world2.snake, after)

  inline def testEatsFruit(fruit: (Int, Int), snake: Snake, didEat: Boolean) =
    assertEquals(eatsFruit(snake, Fruit.apply(Node.apply.tupled(fruit))), didEat)
