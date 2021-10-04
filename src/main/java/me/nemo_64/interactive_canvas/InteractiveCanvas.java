package me.nemo_64.interactive_canvas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class InteractiveCanvas extends JComponent {

	private static final long serialVersionUID = 8313047413598853397L;

	private final List<Drawable> drawables = new ArrayList<>();
	private final CanvasGraphics graphics = new CanvasGraphics(this);

	private float offsetX;
	private float offsetY;
	private float scaleX;
	private float scaleY;

	private float startPanX;
	private float startPanY;

	private MouseAdapter listener;

	public void initVars() {
		offsetX = -getScreenWidth() / 2f;
		offsetY = -getScreenHeight() / 2f;
		scaleX = 1f;
		scaleY = 1f;

		startPanX = 0f;
		startPanY = 0f;

		listener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isMiddleMouseButton(e)) {
					startPanX = e.getX();
					startPanY = e.getY();
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (SwingUtilities.isMiddleMouseButton(e)) {
					float mouseX = e.getX();
					float mouseY = e.getY();

					offsetX -= (mouseX - startPanX) / scaleX;
					offsetY -= (mouseY - startPanY) / scaleY;

					startPanX = e.getX();
					startPanY = e.getY();
				}
			}

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();

				Point2D.Float mouseBeforeZoom = screenToWorld(mouseX, mouseY);

				float scroll = (float) e.getPreciseWheelRotation();
				if (scroll < 0) {
					scaleX *= 1.115f;
					scaleY *= 1.115f;
				} else {
					scaleX *= 0.885f;
					scaleY *= 0.885f;
				}

				Point2D.Float mouseAfterZoom = screenToWorld(mouseX, mouseY);

				offsetX += (mouseBeforeZoom.x - mouseAfterZoom.x);
				offsetY += (mouseBeforeZoom.y - mouseAfterZoom.y);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!SwingUtilities.isLeftMouseButton(e))
					return;
				boolean back = true;
				Point2D.Float point = screenToWorld(e.getX(), e.getY());
				for (int i = 0; i < drawables.size(); i++) {
					Drawable d = drawables.get(i);
					if (d.contains(new Point2D.Float(point.x, point.y))) {
						d.onClick(e);
						back = false;
					}
				}
				if (back) {
					onBackgroundClicked(e);
				}
			}
		};

		addMouseListener(listener);
		addMouseMotionListener(listener);
		addMouseWheelListener(listener);
	}

	@Override
	protected void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		graphics.setGraphics(g, getScreenWidth(), getScreenHeight());

		Rectangle2D.Float screen = new Rectangle2D.Float(offsetX, offsetY, getScreenWidth() / scaleX,
				getScreenHeight() / scaleY);
		for (int i = 0; i < drawables.size(); i++) {
			Drawable d = drawables.get(i);
			if (d.intersects(screen.getBounds2D())) {
				d.draw(graphics);
			}
		}

		repaint();
	}

	public void onBackgroundClicked(MouseEvent e) {}

	Point worldToScreen(float worldX, float worldY) {
		int screenX = (int) ((worldX - offsetX) * scaleX);
		int screenY = (int) ((worldY - offsetY) * scaleY);
		return new Point(screenX, screenY);
	}

	Point2D.Float screenToWorld(int screenX, int screenY) {
		float worldX = ((float) screenX / scaleX) + offsetX;
		float worldY = ((float) screenY / scaleY) + offsetY;
		return new Point2D.Float(worldX, worldY);
	}

	public int getScreenWidth() {
		return getWidth();
	}

	public int getScreenHeight() {
		return getHeight();
	}

	public boolean addDrawable(Drawable drawable) {
		return this.drawables.add(drawable);
	}

	public boolean removeDrawable(Drawable drawable) {
		return this.drawables.remove(drawable);
	}

	public Drawable removeDrawable(int pos) {
		return this.drawables.remove(pos);
	}

	public int drawablesSize() {
		return this.drawables.size();
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}
}
