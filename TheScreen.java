import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class TheScreen extends JPanel 
{
		private JLabel[][] lblMarks;
		private JPanel grid = new JPanel();
		public JButton cmdNew;

		
		
		public TheScreen()
		{
			setScreen();			
		}
		
		private void setScreen()
		{
	
			grid.setLayout(new GridLayout(3,3,2,2));
			
			lblMarks = new JLabel[3][3];
			
			Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
		
			for(int i = 0 ; i < 3; i++)
			{
				
				for(int j = 0 ; j <3; j++)
				{
					lblMarks[i][j]=new JLabel(" ");
					lblMarks[i][j].setBorder(border);
					lblMarks[i][j].setOpaque(true);
					
				}
			}
			cmdNew = new JButton("New Game");
		
			this.setLayout(new BorderLayout());
			
			for (int i=0; i < 3; i++)
			{
				for(int j = 0 ; j <3; j++)
				{
					grid.add(lblMarks[i][j]);
				}
			}
			this.add(grid, BorderLayout.CENTER);
		
			this.add(cmdNew,  BorderLayout.SOUTH);
		
		}
		
		public void resetScreen()
		{
			for(int i = 0 ; i < 3; i++)
			{
				for(int j = 0 ; j <3; j++)
				{
					setColor(i,j,10);
					
				}
			}
		}
		
		public void setColor (int row, int col, int color)
		{
		
			if (color==3)
			{
				lblMarks[row][col].setBackground(Color.magenta);
				lblMarks[row][col].setText("    ");
				
			}
			else if (color==0)
			{
		
			lblMarks[row][col].setBackground(Color.BLACK);
			lblMarks[row][col].setText("    ");
			}
			
			else
			{
				lblMarks[row][col].setBackground(Color.GRAY);
				lblMarks[row][col].setText(row+" , "+col);
				lblMarks[row][col].setHorizontalAlignment(SwingConstants.CENTER);
				lblMarks[row][col].setVerticalAlignment(SwingConstants.CENTER);
			}
		}
	
}
