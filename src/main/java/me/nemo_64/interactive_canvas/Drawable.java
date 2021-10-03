package me.nemo_64.interactive_canvas;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public interface Drawable {

	public void draw(CanvasGraphics g);

	public boolean intersects(Rectangle2D s);

	public boolean contains(Point2D point);

	public void onClick(MouseEvent e);
	
}
