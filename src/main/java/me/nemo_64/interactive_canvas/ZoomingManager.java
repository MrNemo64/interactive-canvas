package me.nemo_64.interactive_canvas;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

class ZoomingManager implements MouseWheelListener {

	private final InteractiveCanvas canvas;

	float scaleX;
	float scaleY;

	public ZoomingManager(InteractiveCanvas canvas) {
		this.canvas = canvas;
	}

	void initVars() {
		scaleX = 1f;
		scaleY = 1f;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		Point2D.Float mouseBeforeZoom = canvas.screenToWorld(mouseX, mouseY);

		float scroll = (float) e.getPreciseWheelRotation();
		if (scroll < 0) {
			scaleX *= 1.115f;
			scaleY *= 1.115f;
		} else {
			scaleX *= 0.885f;
			scaleY *= 0.885f;
		}

		Point2D.Float mouseAfterZoom = canvas.screenToWorld(mouseX, mouseY);

		canvas.panningManager.offsetX += (mouseBeforeZoom.x - mouseAfterZoom.x);
		canvas.panningManager.offsetY += (mouseBeforeZoom.y - mouseAfterZoom.y);
	}

}
