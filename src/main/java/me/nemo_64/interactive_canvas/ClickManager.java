package me.nemo_64.interactive_canvas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.SwingUtilities;

class ClickManager implements MouseListener {

	private final InteractiveCanvas canvas;

	public ClickManager(InteractiveCanvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!SwingUtilities.isLeftMouseButton(e))
			return;
		boolean back = true;
		Point2D.Float point = canvas.screenToWorld(e.getX(), e.getY());
		for (int i = 0; i < canvas.drawables.size(); i++) {
			Drawable d = canvas.drawables.get(i);
			if (d.contains(new Point2D.Float(point.x, point.y))) {
				d.onClick(e);
				back = false;
			}
		}
		if (back) {
			canvas.onBackgroundClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	void initVars() {}

}
