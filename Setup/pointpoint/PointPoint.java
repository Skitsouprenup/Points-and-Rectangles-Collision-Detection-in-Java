import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.EventQueue;

public class PointPoint {
	
	
	public static void main(String[] args) {
		new PointPoint();
	}
	
	public PointPoint() {
		EventQueue.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				JFrame jf = new JFrame("PointPoint");
				Panel pnl = new Panel();
				pnl.addMouseMotionListener(new MouseMotion());
				jf.add(pnl);
				jf.pack();
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setLocationRelativeTo(null);
				jf.setVisible(true);
			}
			
		});
		
	}
	
	void updateData(){
		//System.out.println("updating...");
	}
	
	void drawObjects(Graphics2D g2d){
		//System.out.println("drawing objects...");
	}
	
	class Panel extends JPanel {
		
		Panel(){
			Timer timer = new Timer(16, new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent e){
					updateData();
					repaint();
				}
			});
			timer.start();
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(400,400);
		}
		
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0,0,getWidth(),getHeight());
			drawObjects(g2d);
			g2d.dispose();
		}
	}
	
	class MouseMotion implements MouseMotionListener {
	
		@Override
		public void mouseDragged(MouseEvent e){}
	
		@Override
		public void mouseMoved(MouseEvent e){}
	}
	
}

