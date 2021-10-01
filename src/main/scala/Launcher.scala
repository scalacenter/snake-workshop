@main def run =
  var nextDir: Direction = Direction.Right
  val initWorld = World(Snake(nextDir, Node(0, 0) :: Nil), Fruit(3, 0), 30, 30)
  val drawer = Draw(10)
  var world = initWorld

  import org.scalajs.dom.{window, document}
  import org.scalajs.dom.html.Canvas
  import org.scalajs.dom.raw.{KeyboardEvent, CanvasRenderingContext2D}

  window.addEventListener[KeyboardEvent](
    "keydown",
    keybEvent =>
      nextDir = keybEvent.key match
        case "ArrowDown"  => Direction.Down
        case "ArrowUp"    => Direction.Up
        case "ArrowLeft"  => Direction.Left
        case "ArrowRight" => Direction.Right
        case _            => nextDir
  )

  window.setInterval(
    () =>
      val canvas = document.getElementById("canvas").asInstanceOf[Canvas]
      val ctx = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

      world = onTick(world, Some(UserInput.Arrow(nextDir))) match
        case GameOver => initWorld
        case w: World => w

      println(world)

      drawer.drawWorld(world, ctx),
    250
  )
