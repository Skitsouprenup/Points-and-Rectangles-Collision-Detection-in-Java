import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.EventQueue;

public class PointToPoint {
	
	PointObject p1,p2;
	Ellipse2D c1,c2;
	boolean isColliding;
	
	public static void main(String[] args) {
		new PointToPoint();
	}
	
	PointToPoint() {
		
		
		//Initialization
		c1 = new Ellipse2D.Float();
		c2 = new Ellipse2D.Float(150f,150f,10f,10f);
		p1 = new PointObject(0f,0f);
		p2 = new PointObject(155f,155f);
		isColliding = false;
		
		EventQueue.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				JFrame jf = new JFrame("PointToPoint");
				Panel pnl = new Panel();
				//add mousemotionlistener to the jpanel because we're drawing
				//our objects there. Otherwise, you will have a problem when
				//getting the exact x and y of the mouse pointer in the panel
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
		if( ( p1.getX() == p2.getX() ) && ( p1.getY() == p2.getY() ) ) isColliding = true;
		else isColliding = false;
	}
	
	void drawObjects(Graphics2D g2d){
		//System.out.println("drawing objects...");
		g2d.setPaint(Color.GREEN);
		g2d.fill(c2);
		g2d.setPaint(Color.GRAY);
		c1.setFrame(p1.getX() - 5f, p1.getY() -5f, 10f, 10f);
		g2d.fill(c1);
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
			if(isColliding) g2d.setPaint(Color.GRAY);
			else g2d.setPaint(Color.BLACK);
			g2d.fillRect(0,0,getWidth(),getHeight());
			g2d.setPaint(Color.WHITE);
			g2d.drawString("Try to match the coordinates(x,y)", 60, 20);
			g2d.drawString("of point one and point two for a collision to happen", 60, 40);
			g2d.drawString("If there's a collision, screen will turn gray", 60, 60);
			g2d.drawString("point1(x): " + p1.getX() + " point1(y): " + p1.getY(), 60, 80);
			g2d.drawString("point2(x): " + p2.getX() + " point2(y): " + p2.getY(), 60, 100);
			drawObjects(g2d);
			g2d.dispose();
		}
	}
	
	class MouseMotion implements MouseMotionListener {
	
		@Override
		public void mouseDragged(MouseEvent e){}
	
		@Override
		public void mouseMoved(MouseEvent e){
		p1.setX(e.getX());
		p1.setY(e.getY());
		}
    }
	
}

class PointObject
{
	private float x,y;
	
	PointObject(float x, float y)
	{
		this.x = x;
		this.y = y;
		
	}
	
	float getX(){return x;}
	float getY(){return y;}
	
	void setX(float x){this.x = x;}
	void setY(float y){this.y = y;}
}
