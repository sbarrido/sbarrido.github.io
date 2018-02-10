## Spiral Generator (Graphics and Graphics2D)

```
/**
 * @author Sam Barrido/sbarrido
 * Last Edited: Feb. 4, 2018
 * Lab 4
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
public class SpiralGenerator
{
	// final variables
	// defines ultimate width and height of spiral
	private static final int WIDTH = 200;
	private static final int HEIGHT = 200;
	
	// method to create a spiral
	public void draw(Graphics g)
	{
		// recovers use of Graphics2D
		Graphics2D g2 = (Graphics2D) g;

		// variables to create 2 points
		// points will be used to create a line
		int x1 = WIDTH;
		int y1 = 0;
		Point2D.Double from = new Point2D.Double(x1, y1);
		int x2 = x1;
		int y2 = HEIGHT;
		Point2D.Double to = new Point2D.Double(x2, y2);
		
		// Loops through to repeatedly create 4 lines
		// uses 2 points to create each line
		// 10 repetitions is used to cover entire 200x200 space
		int i;
		int j;
		for(j = 0; j < 10; j++)
		{
			// increments by multiples of 10 each rotation
			int increment = j * 10;
			for(i = 0; i < 1; i++)
			{
				// ================ Right Vertical Segment=============
				x1 = WIDTH - increment;
				y1 = 0 + increment - 10; 	// i cheated here... to ensure
											// second Right Vertical goes to 0
											// this means first Right Vertical actually
											// starts 10 units above frame
				from.setLocation(x1, y1);
				
				x2 = x1;
				y2 = HEIGHT - increment;
				to.setLocation(x2, y2);
				
				Line2D.Double segment1 = new Line2D.Double(from, to);
				g2.draw(segment1);
				// ===================== END Right Vertical Segment=============
				
				// =================== Bottom Horizontal Segment ===============
				x1 = x2;
				y1 = y2;
				from.setLocation(x1, y1);
				
				x2 = 0 + increment;
				y2 = HEIGHT - increment;
				to.setLocation(x2, y2);
				
				Line2D.Double segment2 = new Line2D.Double(from, to);
				g2.draw(segment2);
				
				// ================= END Bottom Horizontal Segment ===============
				
				// ==================== Left Vertical Segment===================
				x1 = x2;
				y1 = y2;
				from.setLocation(x1,  y1);
				
				x2 = 0 + increment;
				y2 = 0 + increment;
				to.setLocation(x2, y2);
				
				Line2D.Double segment3 = new Line2D.Double(from, to);
				g2.draw(segment3);
				
				// ===================== END Right Vertical Segment ============
				
				// ================== Top Horizontal Segment=================
				x1 = x2;
				y1 = y2;
				from.setLocation(x1,  y1);
				
				x2 = WIDTH - increment - 10;
				y2 = 0 + increment;
				to.setLocation(x2, y2);
				
				Line2D.Double segment4 = new Line2D.Double(from, to);
				g2.draw(segment4);
				// ====================END Top Horizontal Segment============
			}
		}      
	}
	public static void main(String[] args)
	{
		
	}
}
```

```
/**
 * @author Sam Barrido/sbarrido
 * Last Edited: Feb. 4, 2018
 * Lab 4
 */

import java.awt.Graphics;
import javax.swing.*;

public class SpiralGeneratorViewer
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      final int FRAME_WIDTH = 400;
      final int FRAME_HEIGHT = 400;
      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setTitle("SpiralGeneratorViewer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JComponent component = new JComponent()
      {
         public void paintComponent(Graphics graph)
         {
            SpiralGenerator spiral = new SpiralGenerator();
            spiral.draw(graph);
         }
      };
      frame.add(component);
      frame.setVisible(true);
   }
}
```
