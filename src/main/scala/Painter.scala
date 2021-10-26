class Painter(scale: Int):

  def paintWorld(world: World, holder: ContextHolder): Unit =
    val World(snake, fruit, dim) = world
    holder.use(_.clearRect(0, 0, dim.height * scale, dim.width * scale))
    paintFruit(fruit, holder)
    paintSnake(snake, holder)

  private def paintFruit(fruit: Fruit, holder: ContextHolder): Unit =
    val Fruit(Node(x, y)) = fruit
    holder.use { ctx =>
      ctx.fillStyle = "#4cafab"
      ctx.fillRect(x * scale, y * scale, scale, scale)
    }

  private def paintSnake(snake: Snake, holder: ContextHolder): Unit =
    holder.use { ctx =>
      ctx.fillStyle = "#FFFFFF"
      for Node(x, y) <- snake.body do ctx.fillRect(x * scale, y * scale, scale, scale)
    }
