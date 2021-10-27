import Direction.*

class TestNextTail extends munit.FunSuite:

  test("nexTail when snake didn't eat the fruit") {
    val notEaten = false
    testNexTail(notEaten, Snake(Right, List(Block(0, 0))), expectedNextTail = List())
    testNexTail(notEaten, Snake(Right, List(Block(1, 0), Block(0, 0))), expectedNextTail = List(Block(1, 0)))
    testNexTail(
      notEaten,
      Snake(Right, List(Block(2, 0), Block(1, 0), Block(0, 0))),
      expectedNextTail = List(Block(2, 0), Block(1, 0))
    )
  }
  test("snexTail when snake did eat the fruit") {
    val hasEaten = true
    testNexTail(hasEaten, Snake(Right, List(Block(0, 0))), expectedNextTail = List(Block(0, 0)))
    testNexTail(
      hasEaten,
      Snake(Right, List(Block(1, 0), Block(0, 0))),
      expectedNextTail = List(Block(1, 0), Block(0, 0))
    )
    testNexTail(
      hasEaten,
      Snake(Right, List(Block(2, 0), Block(1, 0), Block(0, 0))),
      expectedNextTail = List(Block(2, 0), Block(1, 0), Block(0, 0))
    )
  }

  inline def testNexTail(isEaten: Boolean, snake: Snake, expectedNextTail: List[Block]) =
    assertEquals(nextTail(snake, isEaten), expectedNextTail)
