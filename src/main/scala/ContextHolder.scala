import org.scalajs.dom.CanvasRenderingContext2D

/** Provides safe management of the rendering context */
class ContextHolder(ctx: CanvasRenderingContext2D):

  def use(op: CanvasRenderingContext2D => Unit): Unit =
    try
      ctx.save()
      op(ctx)
    finally ctx.restore()
