class TestWrap extends munit.FunSuite:
  val size = Size(width = 23, height = 19)

  test("wrap x to 0 at or after max width") {
    val width = 23
    assert(wrapX(size, 22) == 22) // below max, after min => No-op
    assert(wrapX(size, 23) == 0) // at max => go to min
    assert(wrapX(size, 24) == 0) // after max => to to min
  }

  test("wrap x to max below min width") {
    assert(wrapX(size, -1) == 22) // below min => to to border
  }

  test("wrap y to 0 at or after max height") {
    assert(wrapY(size, 18) == 18) // below max, after min => No-op
    assert(wrapY(size, 19) == 0) // at max => go to min
    assert(wrapY(size, 20) == 0) // after max => to to min
  }

  test("wrap y to max below min height") {
    val height = 19
    assert(wrapY(size, -1) == 18) // below min => to to border
  }
