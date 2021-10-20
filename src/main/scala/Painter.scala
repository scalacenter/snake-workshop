
class Painter(scale: Int):

  private def paintFruit(fruit: Fruit, holder: ContextHolder): Unit =
    val Fruit(x, y) = fruit
    holder.use { ctx =>
      ctx.fillStyle = "#4cafab"
      ctx.fillRect(x * scale, y * scale, scale, scale)
    }

  private def paintSnake(snake: Snake, holder: ContextHolder): Unit =
    holder.use { ctx =>
      ctx.fillStyle = "#FFFFFF"
      for Node(x, y) <- (snake.head :: snake.body) do
        ctx.fillRect(x * scale, y * scale, scale, scale)
    }

  def paintWorld(world: World, holder: ContextHolder): Unit =
    val World(snake, fruit, height, width) = world
    holder.use { _.clearRect(0, 0, height * scale, width * scale) }
    paintFruit(fruit, holder)
    paintSnake(snake, holder)
