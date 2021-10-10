package me.nemo_64.interactive_canvas;

import java.awt.geom.Point2D;

import me.nemo_64.interactive_canvas.event.InteractiveCanvasClickEvent;
import me.nemo_64.interactive_canvas.event.InteractiveCanvasDragEvent;

public interface Interactable {

	public boolean contains(Point2D point);

	public void onClick(InteractiveCanvasClickEvent e);

	public void onDragInto(InteractiveCanvasDragEvent e);

	public void onDragOuto(InteractiveCanvasDragEvent e);

	public void onDragInside(InteractiveCanvasDragEvent e);

}
