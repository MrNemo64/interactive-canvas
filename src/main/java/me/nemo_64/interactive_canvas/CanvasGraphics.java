package me.nemo_64.interactive_canvas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class CanvasGraphics {

	private final InteractiveCanvas canvas;
	private Graphics2D g;

	private Color background = Color.BLACK;
	private Color current = Color.WHITE;

	public CanvasGraphics(InteractiveCanvas canvas, Graphics2D g) {
		this.canvas = canvas;
		this.g = g;
	}

	public CanvasGraphics(InteractiveCanvas canvas) {
		this(canvas, null);
	}

	public void drawLine(float sx, float sy, float ex, float ey) {
		if (g == null)
			return;
		Point startPoint = worldToScreen(sx, sy);
		Point endPoint = worldToScreen(ex, ey);
		g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
	}

	public void drawEllipse(float x, float y, float w, float h) {
		if (g == null)
			return;
		Point point = worldToScreen(x, y);
		g.drawOval(point.x, point.y, (int) (w * getScaleX()), (int) (h * getScaleY()));
	}

	public void fillEllipse(float x, float y, float w, float h) {
		if (g == null)
			return;
		Point point = worldToScreen(x, y);
		g.fillOval(point.x, point.y, (int) (w * getScaleX()), (int) (h * getScaleY()));
	}

	public void drawRectangle(float x, float y, float w, float h) {
		if (g == null)
			return;
		Point point = worldToScreen(x, y);
		g.drawRect(point.x, point.y, (int) (w * getScaleX()), (int) (h * getScaleY()));
	}

	public void fillRectangle(float x, float y, float w, float h) {
		if (g == null)
			return;
		Point point = worldToScreen(x, y);
		g.fillRect(point.x, point.y, (int) (w * getScaleX()), (int) (h * getScaleY()));
	}

	public void setColor(Color c) {
		if (g != null && c != null) {
			current = c;
			g.setColor(c);
		}
	}

	public void setBackground(Color background) {
		if (background != null)
			this.background = background;
	}

	void setGraphics(Graphics2D g, int w, int h) {
		this.g = g;
		g.setColor(background);
		g.fillRect(0, 0, w, h);
		g.setColor(current);
	}

	private Point worldToScreen(float worldX, float worldY) {
		return canvas.worldToScreen(worldX, worldY);
	}

	private float getScaleX() {
		return canvas.getScaleX();
	}

	private float getScaleY() {
		return canvas.getScaleY();
	}

}
