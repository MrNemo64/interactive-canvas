package me.nemo_64.interactive_canvas.event;

import me.nemo_64.interactive_canvas.InteractiveCanvas;

public class InteractiveCanvasEvent {

	private final InteractiveCanvas canvas;

	public InteractiveCanvasEvent(InteractiveCanvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * @return the canvas
	 */
	public InteractiveCanvas getCanvas() {
		return canvas;
	}

}
