package me.nemo_64.interactive_canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class TestCanvas {

	boolean stop = false;

	public static void main(String[] args) {
		new TestCanvas().testCanvas();
	}

	public void testCanvas() {
		JFrame frame = new JFrame();
		InteractiveCanvas canvas = new InteractiveCanvas();
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
				} else if(e.getKeyChar() == 'q') {
					stop = false;
				}
			}
		});
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				canvas.addDrawable(new Sphere(i, j, 1));
	}

	class Sphere implements Drawable {

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
		public void onClick(MouseEvent e) {
			h = true;
		}

	}
}
