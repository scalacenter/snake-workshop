import Direction.*

// > testOnly TestNextWorld
class TestNextWorld extends munit.FunSuite:

  val worldSize = Size(30, 30)
  val snake = Snake(Right, List(Block(1, 0), Block(0, 0)))

  test("world size should never change") {
    val inputDirection = None
    val fruit = Fruit(Block(0, 4))
    val world = World(snake, fruit, worldSize)
    val nextW = nextWorld(world, inputDirection)
    assertEquals(world.size, nextW.size)
  }

  test("if snake doesn't eat the fruit, the fruit remains the same") {
    val inputDirection = None
    val fruit = Fruit(Block(0, 4))
    val world = World(snake, fruit, worldSize)
    val nextW = nextWorld(world, inputDirection)
    assertEquals(nextW.fruit, fruit)
  }

  test("if snake eats the fruit, the fruit should be different") {
    val inputDirection = None
    val fruit = Fruit(Block(1, 0))
    val currentWorld = World(snake, fruit, worldSize)
    val nextW = nextWorld(currentWorld, inputDirection)
    assertNotEquals(nextW.fruit, fruit)
    assertEquals(nextW.snake.body.size, 3) // bigger body after eating a fruit
  }
  test("two iterations of nextWorld with user input direction") {
    val fruit = Fruit(Block(2, 0))
    val currentWorld = World(snake, fruit, worldSize)
    val nextWorld1 = nextWorld(currentWorld, inputDirection = Some(Left))
    assertEquals(nextWorld1.fruit, currentWorld.fruit)
    assertEquals(nextWorld1.snake, Snake(Right, List(Block(2, 0), Block(1, 0))))
    val nextWorld2 = nextWorld(nextWorld1, Some(Down))
    assertNotEquals(nextWorld2.fruit, fruit)
    assertEquals(nextWorld2.snake, Snake(Down, List(Block(2, 1), Block(2, 0), Block(1, 0))))
  }
