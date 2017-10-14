import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTTGame
{
	private boolean gameWon = false;
	private int[][] buttons = new int[3][3];
	private TheScreen screen;
	
	
	
	public void startGame()
	{
		startNew();
		
	}
		
	
	private void startNew()
	{
			screen = new TheScreen();
			setScreen();
			screen.resetScreen();
			screen.cmdNew.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
					{
						JOptionPane.showMessageDialog( null, "reset");
						restartgame();
					}

					private void restartgame() 
					{
					
						getGame();
						
					}
				});
			getGame();

	}
	
	private void setScreen()
	{
		JFrame frame = new JFrame("X - O Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
			
		frame.add(screen);
		
	}
	
	
	
	
	private void clearButt()
	{
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{
				buttons[i][j] = 9;
			}
		}
	}
	
	private void getGame()
	{
		
		
		clearButt();
		screen.resetScreen();
		
		int countTurn = 0;
		
		while ((!gameWon)&&(countTurn<9))
		{
			int[] UserVal = new int[2];
			
			UserVal = getUserTurn();
			
						
			int row, col;
			row=UserVal[0];
			col=UserVal[1];
			if (row==5)
			{
				return;
			}
			if (row ==-1)//if input contain too many values
			{
				JOptionPane.showMessageDialog( null, "Invalid input #1, try something else");
			}
			else if((row>=0)&&(row<=2)&&(col>=0)&&(col<=2)) //if values are in range
			{
				int val;
				
				val=getBoxValue(row,col);
				
				
				if (val==9) //if the player can choose this box
				{
					buttons[row][col] = 3;
					screen.setColor(row, col, buttons[row][col]);
					 countTurn++;
					
					 gameWon=checkWin(row, col,buttons[row][col]);
					 
					if (gameWon) 
						{
						JOptionPane.showMessageDialog( null, "you won!.");
						}
					

					if  ((countTurn<8) && (!gameWon))
					{
						countTurn++;
						getComputerTurn();
						if (gameWon) 
						{
						JOptionPane.showMessageDialog( null, "you lose!.");
						}
						
					}
				}
				else
				{
					JOptionPane.showMessageDialog( null, "This Box is used, please try something else.");
				}
			}
			else //if values is out of range
			{	
				JOptionPane.showMessageDialog( null, "Invalid input #2, try something else");
			}
		}
	}
	

	private int[] getUserTurn()
	{
		
		String userInput= JOptionPane.showInputDialog(null,
				 "Please type row number, colume number",
				 	"the number should be 0-2",
				 		JOptionPane.QUESTION_MESSAGE);

		
		int l=9;
		String[] choice = new String[l];
		int[] userVal = new int[2];
		
		for (int i=0; i<l; i++)
		{
			choice[i]="null";
		}
		
		int count=0;
		if (userInput==null)
		{
			userVal[0]=5;
			return userVal;
		}
		for(String retval: userInput.split(","))
		{
			choice[count] = retval;
			count++;
		}
		if (choice[2]!="null") //if input contain too many values
		{
			
			userVal[0]=-1;
			return userVal;
		}
		
		userVal[0]=Integer.valueOf(choice[0]);
		userVal[1]=Integer.valueOf(choice[1]);
		
		return userVal;
	}
	private void getComputerTurn()//something wrong - always pick 00
	{
		boolean change = false;
		boolean status = false;
		
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{
				if (buttons[i][j]==9)
					{					
						buttons[i][j]=0;
						status = checkWin(i,j,buttons[i][j]);
					
						if (status==true)
						{
							buttons[i][j]=0;
							gameWon = true;
							change = true;
							screen.setColor(i, j, buttons[i][j]);
							return;
							
						}
						else //status==false
						{
							buttons[i][j]=3;
							boolean status2=checkWin(i,j,buttons[i][j]);
							if (status2)
							{
								buttons[i][j]=0;
								change = true;
								screen.setColor(i, j, buttons[i][j]);
								return;
							}
							else
							{
								buttons[i][j]=9;
							}
						}
					}	
			}
		}
		if (!change) //if the computer and the player not going to win
		{
			for (int i=0; i<3; i++)
			{
				for (int j=0; j<3; j++)
				{
					if (buttons[i][j]==9)
					{
						buttons[i][j]=0;
						screen.setColor(i, j, buttons[i][j]);
						return;						
					}
				}
			}
		
		}
	}

	
	private int getBoxValue(int i, int j)//return box value
	{
		return buttons[i][j];
	}


	
	
	private boolean checkWin(int row, int col, int val) //check if the player win
    {
      if(buttons[row][0]==buttons[row][1]&& buttons[row][1]==buttons[row][2])
        {
         return true;    
        }
      
      else  if(buttons[0][col]==buttons[1][col]&& buttons[1][col]==buttons[2][col])
       {
          return true;
       }
      
      else if (!((col!=1 && row==1)||(col==1 && row!=1))) //check if row or col=/=1 or otherwise
      {			//check the diagonals option- it's not the most efficient but it's have less "checking" if-s
     	if ((buttons[0][0]==buttons[1][1]&& buttons[1][1]==buttons[2][2]&&buttons[1][1]==val)||
    			(buttons[0][2]==buttons[1][1]&& buttons[1][1]==buttons[2][0]&&buttons[1][1]==val))
    		       {
    		          return true;
    		       }  	
      }
	return false;
    }
	
}
