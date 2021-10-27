class TestCreateRandomFruit extends munit.FunSuite:
  val worldSize = Size(30, 30)

  test("create random fruit inside the world size") {
    val fruits = for _ <- 1 to 50 yield createRandomFruit(worldSize)
    for fruit <- fruits do
      assert(fruit.block.x >= 0)
      assert(fruit.block.x < worldSize.width)
      assert(fruit.block.y >= 0)
      assert(fruit.block.y <= worldSize.height)
  }
