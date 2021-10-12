package me.nemo_64.interactive_canvas;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

final class Util {

	public static boolean isPanningButton(MouseEvent e) {
		return SwingUtilities.isMiddleMouseButton(e);
	}
	
}