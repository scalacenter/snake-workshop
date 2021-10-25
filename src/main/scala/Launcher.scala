import org.scalajs.dom.{window, document, html}
import org.scalajs.dom.{KeyboardEvent, CanvasRenderingContext2D}

// the primary world state
def initWorld = World(
  snake = Snake(Direction.Right, Node(0, 0), Nil),
  fruit = Fruit(4, 0),
  width = 30,
  height = 30
)

// Converts keyboard input to UserInput
def inputFromKey(key: String, current: Option[UserInput]): Option[UserInput] =
  key match
    case "ArrowUp"    => Some(UserInput.Arrow(Direction.Up))
    case "ArrowDown"  => Some(UserInput.Arrow(Direction.Down))
    case "ArrowLeft"  => Some(UserInput.Arrow(Direction.Left))
    case "ArrowRight" => Some(UserInput.Arrow(Direction.Right))
    case "p"          => Some(UserInput.Pause)
    case "r"          => Some(UserInput.Reset)
    case _            => current

def nextGame(world: World, input: Option[UserInput]) =
  nextWorld(world, input) match
    case GameOver     => initWorld
    case world: World => world

def nextInput(input: Option[UserInput]) =
  if input == Some(UserInput.Pause) then input
  else None

def gameLoop(holder: ContextHolder) =
  // painter handles rendering to the canvas
  val painter = Painter(scale = 20)

  // mutable game state
  var world = initWorld
  var userInput = Option.empty[UserInput]

  // updates the game state, representing 1 tick of game-time
  def tick() =
    world = nextGame(world, userInput)
    userInput = nextInput(userInput)
    println(world) // print the world in the browser console
    painter.paintWorld(world, holder) // paint the world to the canvas element

  // every 250 ms, call the tick function
  window.setInterval(() => tick(), 250)

  // on each key press, potentially update the userInput
  window.addEventListener[KeyboardEvent](
    "keydown",
    event => userInput = inputFromKey(event.key, userInput)
  )

end gameLoop

@main def run =
  // helpers for the rendering context for the canvas element in index.html
  val canvas = document.getElementById("canvas").asInstanceOf[html.Canvas]
  val ctx = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
  val holder = ContextHolder(ctx)

  gameLoop(holder)
