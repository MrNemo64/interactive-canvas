package me.nemo_64.interactive_canvas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class PanningManager implements MouseMotionListener, MouseListener {

	private final InteractiveCanvas canvas;

	public PanningManager(InteractiveCanvas canvas) {
		this.canvas = canvas;
	}

	float offsetX;
	float offsetY;

	float startPanX;
	float startPanY;

	void initVars() {
		offsetX = -canvas.getScreenWidth() / 2f;
		offsetY = -canvas.getScreenHeight() / 2f;

		startPanX = 0f;
		startPanY = 0f;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (Util.isPanningButton(e)) {
			float mouseX = e.getX();
			float mouseY = e.getY();

			offsetX -= (mouseX - startPanX) / canvas.zoomingManager.scaleX;
			offsetY -= (mouseY - startPanY) / canvas.zoomingManager.scaleY;

			startPanX = e.getX();
			startPanY = e.getY();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Util.isPanningButton(e)) {
			startPanX = e.getX();
			startPanY = e.getY();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
