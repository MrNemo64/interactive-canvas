package me.nemo_64.interactive_canvas;

import java.awt.geom.Rectangle2D;

public interface Drawable {

	public void draw(CanvasGraphics g);

	public boolean intersects(Rectangle2D s);

}
