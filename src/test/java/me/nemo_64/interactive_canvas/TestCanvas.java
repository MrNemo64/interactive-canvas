package me.nemo_64.interactive_canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import me.nemo_64.interactive_canvas.event.InteractiveCanvasClickEvent;
import me.nemo_64.interactive_canvas.event.InteractiveCanvasDragEvent;

public class TestCanvas {

	boolean stop = false;

	public static void main(String[] args) {
		new TestCanvas().testCanvas();
	}

	public void testCanvas() {
		List<Sphere> list = new ArrayList<>();
		JFrame frame = new JFrame();
		InteractiveCanvas canvas = new InteractiveCanvas() {
			@Override
			public void onBackgroundClicked(InteractiveCanvasClickEvent e) {
				list.forEach((s) -> s.h = false);
			}
		};
		canvas.setPreferredSize(new Dimension(400, 400));
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
		canvas.initVars();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == 'a') {
					stop = true;
				} else if (e.getKeyChar() == 'q') {
					stop = false;
				}
			}
		});
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Sphere s = new Sphere(i, j, 1);
				list.add(s);
				canvas.addDrawableAndInteractable(s);
			}
		}
	}

	class Sphere implements Drawable, Interactable {

		private Ellipse2D.Float s;
		private boolean h = false, r = false;

		public Sphere(int i, int j, int r) {
			s = new Ellipse2D.Float(i, j, r, r);
		}

		@Override
		public void draw(CanvasGraphics g) {
			if (r || stop) {
				r = true;
				g.setColor(Color.RED);
			}
			if (h) {
				g.fillEllipse(s.x, s.y, s.width, s.height);
			} else {
				g.drawEllipse(s.x, s.y, s.width, s.height);
			}
			if (r) {
				g.setColor(Color.WHITE);
			}
		}

		@Override
		public boolean intersects(Rectangle2D s) {
			return this.s.intersects(s);
		}

		@Override
		public boolean contains(Point2D point) {
			return this.s.contains(point);
		}

		@Override
		public void onClick(InteractiveCanvasClickEvent e) {
			h = true;
		}

		@Override
		public void onDragInto(InteractiveCanvasDragEvent e) {
			System.out.println("drag into");
		}

		@Override
		public void onDragOuto(InteractiveCanvasDragEvent e) {
			System.out.println("drag outo");
		}

		@Override
		public void onDragInside(InteractiveCanvasDragEvent e) {
			System.out.println("drag inside");
		}

	}
}
