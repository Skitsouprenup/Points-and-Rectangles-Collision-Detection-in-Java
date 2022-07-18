import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
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

public class RectToRect {
	
	Point2D p1;
	Rectangle2D r1,r2;
	boolean isColliding;
	
	public static void main(String[] args) {
		new RectToRect();
	}
	
	public RectToRect() {
		
		p1 = new Point2D.Double(0f,0f);
		r1 = new Rectangle2D.Float();
		r2 = new Rectangle2D.Float(150f,200f,80f,50f);
		isColliding = false;
		
		EventQueue.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				JFrame jf = new JFrame("RectToRect");
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
		isColliding = r2.intersects(r1.getX(),r1.getY(),r1.getWidth(),r1.getHeight());
		//isColliding = r2.contains(r1.getX(),r1.getY(),r1.getWidth(),r1.getHeight());
		//isColliding = r2.contains(p1.getX(),p1.getY());
	}
	
	void drawObjects(Graphics2D g2d){
		//System.out.println("drawing objects...");
		g2d.setPaint(Color.GREEN);
		//if(isColliding) g2d.setPaint(Color.RED);
		g2d.fill(r2);
		if(isColliding) g2d.setPaint(Color.RED);
		else g2d.setPaint(Color.YELLOW);
		r1.setRect(p1.getX(), p1.getY(), 40f, 40f);
		g2d.fill(r1);
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
			g2d.setPaint(Color.WHITE);
			//g2d.drawString("Mouse-controlled square will turn read once there's a collision",40f,20f);
			g2d.drawString("Stationary square will turn red once there's a collision",60f,20f);
			g2d.dispose();
		}
	}
	
	class MouseMotion implements MouseMotionListener {
	
		@Override
		public void mouseDragged(MouseEvent e){}
	
		@Override
		public void mouseMoved(MouseEvent e){
			p1.setLocation(e.getX(),e.getY());
		}
	}
	
}

