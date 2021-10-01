import org.scalajs.dom.raw.CanvasRenderingContext2D

class Draw(scale: Int):
  def drawFruit(f: Fruit, ctx: CanvasRenderingContext2D): Unit =
    ctx.fillStyle = "#4cafab"
    ctx.fillRect(f.x * scale, f.y * scale, scale, scale)

  def drawSnake(s: Snake, ctx: CanvasRenderingContext2D): Unit =
    ctx.fillStyle = "#FFFFFF"
    s.nodes.foreach(el =>
      ctx.fillRect(el.x * scale, el.y * scale, scale, scale)
    )

  def drawWorld(w: World, ctx: CanvasRenderingContext2D): Unit =
    ctx.clearRect(0, 0, w.height * scale, w.width * scale)
    drawFruit(w.fruit, ctx)
    drawSnake(w.snake, ctx)
