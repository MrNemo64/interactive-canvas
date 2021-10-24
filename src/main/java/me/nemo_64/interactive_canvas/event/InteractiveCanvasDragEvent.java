package me.nemo_64.interactive_canvas.event;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import me.nemo_64.interactive_canvas.InteractiveCanvas;

public class InteractiveCanvasDragEvent extends InteractiveCanvasClickEvent {

	private final Point toScreenLocation;
	private final Point2D.Float toWorldLocation;

	public InteractiveCanvasDragEvent(InteractiveCanvas canvas, Point startScreenLocation, Float startWorldLocation,
			Point toScreenLocation, Float toWorldLocation, int button, int modifiers, int clickCount) {
		super(canvas, startScreenLocation, startWorldLocation, button, modifiers, clickCount);
		this.toScreenLocation = toScreenLocation;
		this.toWorldLocation = toWorldLocation;
	}

	/**
	 * @return the end screen location
	 */
	public Point getToScreenLocation() {
		return new Point(toScreenLocation);
	}

	/**
	 * @return the end world location
	 */
	public Point2D.Float getToWorldLocation() {
		return new Point2D.Float(toWorldLocation.x, toWorldLocation.y);
	}

	/**
	 * @return the start screen locarion
	 */
	@Override
	public Point getScreenLocation() {
		return super.getScreenLocation();
	}

	/**
	 * @return the start world locarion
	 */
	@Override
	public Float getWorldLocation() {
		return super.getWorldLocation();
	}

	/**
	 * @return the start screen location
	 */
	public Point getFromScreenLocation() {
		return getScreenLocation();
	}

	/**
	 * @return the start world locarion
	 */
	public Point2D.Float getFromWorldLocation() {
		return getWorldLocation();
	}

	public double screenDistance() {
		return getScreenLocation().distance(getToScreenLocation());
	}

	public double worldDistance() {
		return getWorldLocation().distance(getToWorldLocation());
	}

}
