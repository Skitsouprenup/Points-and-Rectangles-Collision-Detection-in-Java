import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
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

public class RectToRectAABB {
	PointObject ro1;
	Rectangle2D r1,r2;
	boolean xCollide,yCollide;
	
	public static void main(String[] args) {
		new RectToRectAABB();
	}
	
	public RectToRectAABB() {
		
		xCollide = false;
		yCollide = false;
		ro1 = new PointObject(0f,0f);
		r1 = new Rectangle2D.Float();
		r2 = new Rectangle2D.Float(150f,200f,80f,50f);
		
		EventQueue.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				JFrame jf = new JFrame("RectToRectAABB");
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
		//Intersection
		/* 
		if(ro1.getX() < r2.getX() + r2.getWidth() && 
		   ro1.getX() + r1.getWidth() > r2.getX()) xCollide = true;
		else xCollide = false;
		
		if(ro1.getY() < r2.getY() + r2.getHeight() && 
		   ro1.getY() + r1.getHeight() > r2.getY()) yCollide = true;
		else yCollide = false;
		*/
		
		//Contains
		if(ro1.getX() + r1.getWidth() <= r2.getX() + r2.getWidth() &&
		   ro1.getX() >= r2.getX()) xCollide = true;
		else xCollide = false;
		
		if(ro1.getY() + r1.getHeight() <= r2.getY() + r2.getHeight() &&
		   r2.getY() <= ro1.getY()) yCollide = true;
		else yCollide = false;
		
		//Point Collision
		/*
		if(ro1.getX() < r2.getX() + r2.getWidth() && 
		   ro1.getX() > r2.getX()) xCollide = true;
		else xCollide = false;
		
		if(ro1.getY() < r2.getY() + r2.getHeight() && 
		   ro1.getY() > r2.getY()) yCollide = true;
		else yCollide = false;
		*/
	}
	
	void drawObjects(Graphics2D g2d){
		//System.out.println("drawing objects...");
		g2d.setPaint(Color.GREEN);
		//if(xCollide && yCollide) g2d.setPaint(Color.RED);
		g2d.fill(r2);
		if(xCollide && yCollide) g2d.setPaint(Color.RED);
		else g2d.setPaint(Color.YELLOW);
		r1.setRect(ro1.getX(), ro1.getY(), 40f, 40f);
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
			g2d.setPaint(Color.BLACK);
			g2d.fillRect(0,0,getWidth(),getHeight());
			drawObjects(g2d);
			g2d.setPaint(Color.WHITE);
			g2d.drawString("Mouse-controlled square will turn red once there's a collision",40f,20f);
			g2d.drawString("xCollide: "+xCollide +", yCollide: " + yCollide,40f,40f);
			//g2d.drawString("Stationary square will turn red once there's a collision",60f,20f);
			g2d.dispose();
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
	
	class MouseMotion implements MouseMotionListener {
	
		@Override
		public void mouseDragged(MouseEvent e){}
	
		@Override
		public void mouseMoved(MouseEvent e){
			ro1.setX(e.getX());
			ro1.setY(e.getY());
		}
	}
	
}

