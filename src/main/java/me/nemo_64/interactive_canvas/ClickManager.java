package me.nemo_64.interactive_canvas;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import me.nemo_64.interactive_canvas.event.InteractiveCanvasClickEvent;
import me.nemo_64.interactive_canvas.event.InteractiveCanvasDragEvent;

class ClickManager implements MouseListener, MouseMotionListener {

	private final InteractiveCanvas canvas;

	private Point2D.Float startWorld;
	private Point startScreen;

	public ClickManager(InteractiveCanvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Util.isPanningButton(e))
			return;
		boolean back = true;
		Point2D.Float point = canvas.screenToWorld(e.getX(), e.getY());
		InteractiveCanvasClickEvent event = new InteractiveCanvasClickEvent(canvas, new Point(e.getX(), e.getY()),
				new Point2D.Float(point.x, point.y), e.getButton(), e.getModifiersEx(), e.getClickCount());
		for (int i = 0; i < canvas.interactables.size(); i++) {
			Interactable interactable = canvas.interactables.get(i);
			if (interactable.contains(new Point2D.Float(point.x, point.y))) {
				interactable.onClick(event);
				back = false;
			}
		}
		if (back) {
			canvas.onBackgroundClicked(event);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (Util.isPanningButton(e))
			return;
		Point endScreenPoint = new Point(e.getX(), e.getY());
		Point2D.Float endWorldPoint = canvas.screenToWorld(e.getX(), e.getY());
		InteractiveCanvasDragEvent event = new InteractiveCanvasDragEvent(canvas, startScreen, startWorld, endScreenPoint,
				endWorldPoint, e.getButton(), e.getModifiersEx(), e.getClickCount());

		for (int i = 0; i < canvas.interactables.size(); i++) {
			Interactable interactable = canvas.interactables.get(i);
			boolean cStart = interactable.contains(startWorld);
			boolean cEnd = interactable.contains(endWorldPoint);

			if (!(cStart || cEnd)) {
				// nothing
			} else if (cStart && cEnd) {
				interactable.onDragInside(event);
			} else if (cStart && !cEnd) {
				interactable.onDragOuto(event);
			} else if (!cStart && cEnd) {
				interactable.onDragInto(event);
			}
		}

		startScreen = new Point(e.getX(), e.getY());
		startWorld = canvas.screenToWorld(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Util.isPanningButton(e))
			return;
		startScreen = new Point(e.getX(), e.getY());
		startWorld = canvas.screenToWorld(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	void initVars() {}

}
