class TestWrap extends munit.FunSuite:
  val dimension = World.Dimension(19, 23)

  test("wrap x to 0 at or after max width") {
    val width = 23
    assert(dimension.wrapX(22) == 22) // below max, after min => No-op
    assert(dimension.wrapX(23) == 0) // at max => go to min
    assert(dimension.wrapX(24) == 0) // after max => to to min
  }

  test("wrap x to max below min width") {
    assert(dimension.wrapX(-1) == 22) // below min => to to border
  }

  test("wrap y to 0 at or after max height") {
    assert(dimension.wrapY(18) == 18) // below max, after min => No-op
    assert(dimension.wrapY(19) == 0) // at max => go to min
    assert(dimension.wrapY(20) == 0) // after max => to to min
  }

  test("wrap y to max below min height") {
    val height = 19
    assert(dimension.wrapY(-1) == 18) // below min => to to border
  }
