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

public class RectRectAvg {
	PointObject ro1;
	Rectangle2D r1,r2;
	boolean isColliding;
	
	public static void main(String[] args) {
		new RectRectAvg();
	}
	
	public RectRectAvg() {
		
		isColliding = false;
		ro1 = new PointObject(0f,0f);
		r1 = new Rectangle2D.Float();
		r2 = new Rectangle2D.Float(150f,200f,80f,50f);
		
		EventQueue.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				JFrame jf = new JFrame("RectRectAvg");
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
		//alternative for getting average (tedious)
		/*
		float r1_half_x = r1.getWidth() * 0.5f;
		float r2_half_x = r2.getWidth() * 0.5f;
		float r1_half_y = r1.getHeight() * 0.5f;
		float r2_half_y = r2.getHeight() * 0.5f;
		float avg_width = r1_half_x + r2_half_x;
		float avg_height = r1_half_y + r2_half_y;
		*/
		double r1_center_x = r1.getX() + r1.getWidth() * 0.5;
		double r1_center_y = r1.getY() + r1.getHeight() * 0.5;
		double r2_center_x = r2.getX() + r2.getWidth() * 0.5;
		double r2_center_y = r2.getY() + r2.getHeight() * 0.5;
		double avgWidth = ( r1.getWidth() + r2.getWidth() ) * 0.5;
		double avgHeight = ( r1.getHeight() + r2.getHeight() ) * 0.5;
		double dst_x = Math.abs(r1_center_x - r2_center_x);
		double dst_y = Math.abs(r1_center_y - r2_center_y);
		
		if(dst_x < avgWidth && dst_y < avgHeight) isColliding = true;
		else isColliding = false;
	}
	
	void drawObjects(Graphics2D g2d){
		//System.out.println("drawing objects...");
		g2d.setPaint(Color.GREEN);
		g2d.fill(r2);
		if(isColliding) g2d.setPaint(Color.RED);
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

