package me.nemo_64.interactive_canvas.event;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import me.nemo_64.interactive_canvas.InteractiveCanvas;

public class InteractiveCanvasClickEvent extends InteractiveCanvasEvent {

	private final Point screenLocation;
	private final Point2D.Float worldLocation;
	private final int button;
	private final int modifiers;
	private final int clickCount;

	public InteractiveCanvasClickEvent(InteractiveCanvas canvas, Point screenLocation, Float worldLocation, int button,
			int modifiers, int clickCount) {
		super(canvas);
		this.screenLocation = screenLocation;
		this.worldLocation = worldLocation;
		this.button = button;
		this.modifiers = modifiers;
		this.clickCount = clickCount;
	}

	/**
	 * @return the clickCount
	 */
	public int getClickCount() {
		return clickCount;
	}

	/**
	 * @return the screenLocation
	 */
	public Point getScreenLocation() {
		return new Point(screenLocation);
	}

	/**
	 * @return the worldLocation
	 */
	public Point2D.Float getWorldLocation() {
		return new Point2D.Float(worldLocation.x, worldLocation.y);
	}

	/**
	 * @return the button
	 */
	public int getButton() {
		return button;
	}

	/**
	 * @return the modifiers
	 */
	public int getModifiers() {
		return modifiers;
	}

}
