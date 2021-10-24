package me.nemo_64.interactive_canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import me.nemo_64.interactive_canvas.event.InteractiveCanvasClickEvent;

public class InteractiveCanvas extends JComponent {

	private static final long serialVersionUID = 8313047413598853397L;

	final List<Drawable> drawables = new ArrayList<>();
	final List<Interactable> interactables = new ArrayList<>();
	private final CanvasGraphics graphics = new CanvasGraphics(this);

	ClickManager clickManager;
	ZoomingManager zoomingManager;
	PanningManager panningManager;

	public void initVars() {
		clickManager = new ClickManager(this);
		zoomingManager = new ZoomingManager(this);
		panningManager = new PanningManager(this);

		clickManager.initVars();
		zoomingManager.initVars();
		panningManager.initVars();

		addMouseListener(clickManager);
		addMouseMotionListener(clickManager);
		addMouseWheelListener(zoomingManager);
		addMouseListener(panningManager);
		addMouseMotionListener(panningManager);
	}

	@Override
	protected void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		graphics.setGraphics(g, getScreenWidth(), getScreenHeight());

		Rectangle2D.Float screen = new Rectangle2D.Float(panningManager.offsetX, panningManager.offsetY,
				getScreenWidth() / zoomingManager.scaleX, getScreenHeight() / zoomingManager.scaleY);
		for (int i = 0; i < drawables.size(); i++) {
			Drawable d = drawables.get(i);
			if (d.intersects(screen.getBounds2D())) {
				d.draw(graphics);
			}
		}

		repaint();
	}

	public void onBackgroundClicked(InteractiveCanvasClickEvent e) {}

	public void moveScreen(float moveX, float moveY) {
		panningManager.offsetX += moveX;
		panningManager.offsetY += moveY;
	}

	public void setScreenPosition(float x, float y) {
		panningManager.offsetX = x;
		panningManager.offsetY = y;
	}

	public void addZoom(float scaleX, float scaleY) {
		zoomingManager.scaleX *= scaleX;
		zoomingManager.scaleY *= scaleY;
		zoomingManager.checkValues();
	}

	public void setZoom(float scaleX, float scaleY) {
		zoomingManager.scaleX = scaleX;
		zoomingManager.scaleY = scaleY;
		zoomingManager.checkValues();
	}

	@Override
	public void setBackground(Color bg) {
		graphics.setBackground(bg);
	}

	Point worldToScreen(float worldX, float worldY) {
		int screenX = (int) ((worldX - panningManager.offsetX) * zoomingManager.scaleX);
		int screenY = (int) ((worldY - panningManager.offsetY) * zoomingManager.scaleY);
		return new Point(screenX, screenY);
	}

	Point2D.Float screenToWorld(int screenX, int screenY) {
		float worldX = ((float) screenX / zoomingManager.scaleX) + panningManager.offsetX;
		float worldY = ((float) screenY / zoomingManager.scaleY) + panningManager.offsetY;
		return new Point2D.Float(worldX, worldY);
	}

	public int getScreenWidth() {
		return getWidth();
	}

	public int getScreenHeight() {
		return getHeight();
	}

	public boolean addDrawableAndInteractable(Object inter) {
		if (!(inter instanceof Drawable && inter instanceof Interactable))
			throw new IllegalArgumentException("inter must implement Drawable and Interactable");
		return addDrawable((Drawable) inter) & addInteractable((Interactable) inter);
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

	public List<Drawable> getDrawables() {
		return drawables;
	}

	public List<Interactable> getInteractables() {
		return interactables;
	}

	public boolean addInteractable(Interactable interactable) {
		return this.interactables.add(interactable);
	}

	public boolean removeInteractable(Interactable interactable) {
		return this.interactables.remove(interactable);
	}

	public Interactable removeInteractable(int pos) {
		return this.interactables.remove(pos);
	}

	public int interactableSize() {
		return this.interactables.size();
	}

	public float getScaleX() {
		return zoomingManager.scaleX;
	}

	public float getScaleY() {
		return zoomingManager.scaleY;
	}
}
