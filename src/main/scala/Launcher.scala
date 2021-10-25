import org.scalajs.dom.{window, document, html}
import org.scalajs.dom.{KeyboardEvent, CanvasRenderingContext2D}

// the primary world state
def initWorld = World(
  snake = Snake(Direction.Right, Node(0, 0), Nil),
  fruit = Fruit(Node(4, 0)),
  dimension = World.Dimension(width = 30, height = 30)
)

// helpers to create user inputs
def arrow(dir: Direction) = Some(UserInput.Arrow(dir))
def pause = Some(UserInput.Pause)
def reset = Some(UserInput.Reset)
def noAction = Option.empty[UserInput]

// Converts keyboard input to UserInput
def inputFromKey(key: String, current: Option[UserInput]): Option[UserInput] =
  key match
    case "ArrowUp"    => arrow(Direction.Up)
    case "ArrowDown"  => arrow(Direction.Down)
    case "ArrowLeft"  => arrow(Direction.Left)
    case "ArrowRight" => arrow(Direction.Right)
    case "p"          => pause
    case "r"          => reset
    case _            => current

def nextGame(world: World, input: Option[UserInput]) =
  nextWorld(world, input) match
    case GameOver     => initWorld
    case world: World => world

def nextInput(input: Option[UserInput]) =
  if input == pause then input
  else noAction

def gameLoop(holder: ContextHolder) =
  // painter handles rendering to the canvas
  val painter = Painter(scale = 20)

  // mutable game state
  var world = initWorld
  var userInput = noAction

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
