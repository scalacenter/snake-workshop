def body(nodes: (Int, Int)*) =
  (nodes map (Block(_, _))).toList

def snake(dir: Direction, head: (Int, Int), nodes: (Int, Int)*) =
  Snake(dir, body((head +: nodes)*))

// helpers to create user inputs
def arrow(dir: Direction) = Some(UserInput.Arrow(dir))
def pause = Some(UserInput.Pause)
def reset = Some(UserInput.Reset)
def noAction = Option.empty[UserInput]

def validSnake(world: World) =
  val snake = world.snake
  def areConnected(n1: Block, n2: Block) =
    val maxX = math.max(n1.x, n2.x)
    val minX = math.min(n1.x, n2.x)
    val maxY = math.max(n1.y, n2.y)
    val minY = math.min(n1.y, n2.y)
    val diffX = maxX - minX
    val diffY = maxY - minY
    def validX = diffX == 1 || minX == 0 && maxX == world.size.width - 1
    def validY = diffY == 1 || minY == 0 && maxY == world.size.height - 1
    validX ^ validY
  snake.body.sizeCompare(1) >= 0
  && snake.body.sliding(2).forall {
    case List(l, r) => areConnected(l, r)
    case _          => true
  }
