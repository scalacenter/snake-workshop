class TestWrap extends munit.FunSuite:

  test("wrap x to 0 at or after max width") {
    val width = 23
    assert(wrap(22, 0, width) == 22) // below max, after min => No-op
    assert(wrap(23, 0, width) == 0) // at max => go to min
    assert(wrap(24, 0, width) == 0) // after max => to to min
  }

  test("wrap x to max below min width") {
    val width = 23
    assert(wrap(-1, 0, width) == 22) // below min => to to border
  }

  test("wrap y to 0 at or after max height") {
    val height = 19
    assert(wrap(18, 0, height) == 18) // below max, after min => No-op
    assert(wrap(19, 0, height) == 0) // at max => go to min
    assert(wrap(20, 0, height) == 0) // after max => to to min
  }

  test("wrap y to max below min height") {
    val height = 19
    assert(wrap(-1, 0, height) == 18) // below min => to to border
  }
