import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.concurrent.TimeUnit;
// btn size = 160,116
public class GameWindow extends JFrame {
	int gameCount=1;
    int b=0;
    int p1,p2;
    boolean won=false,p1turn=true;
	Container C;
	JPanel title_Panel, game_Panel, bottom_Panel;
	Button  Restart , Exit ;
	Button []btns = new Button[9];        // 9 btns array for game
	int []pos = {2,2,2,2,2,2,2,2,2};              // Array to store value of 0 and 1
	
	Label Player1,Player2,Player1_Level,Player2_Level,Round_Level,Round,Status;
	
	ImageIcon title_icon = new ImageIcon("files/Logo.png");  // LOGO image
	
	String BeepSound = "files/Beep.wav";   //Sound File
	
	BorderLayout borderLayout = new BorderLayout();      //Layouts
	GridLayout gridLayout_game = new GridLayout(3,3);
	GridLayout gridLayout_bottom = new GridLayout(2,3);
	FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
	GridBagLayout gridbagLayout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED);  //Border
	BevelBorder bevelBorder2 = new BevelBorder(BevelBorder.LOWERED);
	LineBorder lineBorder = new LineBorder(Color.white);
	
	Font f = new Font("Calibri",Font.BOLD,25);  // Font
	Font f1 = new Font("Arial",Font.BOLD,100);
	
	Color mettalic = new Color(170,169,173);  //Color
	
	Action ac = new Action();
	Mouse mo = new Mouse();
	
	GameWindow(){
    	   this.setBounds(500,100,500,500);
    	   this.setTitle("Tic Tac Toe");
    	   this.setIconImage(title_icon.getImage());
    	   this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	   this.setResizable(false);
    	   C = this.getContentPane();
    	   C.setLayout(borderLayout);
    	   GUI();
    	   C.add(title_Panel,BorderLayout.NORTH);
    	   C.add(game_Panel,BorderLayout.CENTER);
    	   C.add(bottom_Panel,BorderLayout.SOUTH);
    	      
    	   this.setVisible(true);
    	   
       }
	
	public void GUI() {
		
		Player1 = new Label("Player1",f,bevelBorder2);
		Player2 = new Label("Player2",f,bevelBorder2);
		Player1_Level = new Label("3",f,bevelBorder2);
		Player2_Level = new Label("3",f,bevelBorder2);
		Status = new Label("Player 1 Turn ",f,bevelBorder2);
		
		Status.setForeground(Color.blue);
		p1 = Integer.parseInt(Player1_Level.getText());
		p2 = Integer.parseInt(Player2_Level.getText());
		
		
		Round = new Label("Round",f,bevelBorder2);
		Round_Level = new Label("1",f,bevelBorder2);
		
		title_Panel = new JPanel();
		title_Panel.setLayout(gridbagLayout);
		title_Panel.setBorder(lineBorder);
		
		game_Panel = new JPanel();
		game_Panel.setLayout(gridLayout_game);
		game_Panel.setBorder(bevelBorder);
		
		bottom_Panel = new JPanel();
		bottom_Panel.setLayout(gridLayout_bottom);
		
		Restart = new Button("  Restart  ","Restart",bevelBorder,mettalic);
		Exit = new Button("   Exit   ","Exit",bevelBorder,mettalic);
	    Restart.addActionListener(ac);
	    Restart.addMouseListener(mo);
		Exit.addActionListener(ac);
		Exit.addMouseListener(mo);
		
		
		for(int i=0 ; i < 9 ; i++) {                             // Game Array btns
			Button btn= new Button(" ","btn",bevelBorder,mettalic);
			btns[i]=btn;
			btn.setFont(f1);
			btn.addActionListener(ac);
			btn.addMouseListener(mo);
			game_Panel.add(btn);
			btn.setName(String.valueOf(i));
			
		}
		
        titleCons(0,0,1);
		title_Panel.add(Restart,gbc);
		titleCons(1,0,2);
		title_Panel.add(Status,gbc);
		titleCons(3,0,1);
		title_Panel.add(Exit,gbc);
		
		bottom_Panel.add(Player1);
		bottom_Panel.add(Round);		
		bottom_Panel.add(Player2);
		bottom_Panel.add(Player1_Level);
		bottom_Panel.add(Round_Level);
		bottom_Panel.add(Player2_Level);
	}
		
	
class Action implements ActionListener{
		
		Action(){
			
		}
    
public void actionPerformed(ActionEvent e) {
		
	String a = e.getActionCommand();
	
	Button currentButton = (Button)e.getSource();     // Creating obj of the Source
	
	

	
	switch(a) {
	case("Exit"):  System.exit(0); break;
	case("Restart"): Restart(); break;
	case("btn"):  
	{
		String c = currentButton.getText(); 
		String name = currentButton.getName();
	    int n = Integer.parseInt(name);
	    Status.setText("Player 1 Turn");
	   
	    ///To play game until results 
	    
		if(c==" " && b<4) {                  ///Conditon 1 btn is empty and used btns are less then 5
			if(p1turn) {
			currentButton.setText("O");
			pos[n]=0;
			Status.setText("Player 2 Turn");
			p1turn=false;
			
			}
			else {
				currentButton.setText("X");
				pos[n]=1; Status.setText("Player 1 Turn");p1turn=true;}
			
		b++;
			}
		else if(c == " " && b>=4 && b<9) {     ///Conditon 2 btn is empty and used btns are greater than 5
			
			if(p1turn) { currentButton.setText("O"); 	pos[n]=0; Status.setText("Player 2 Turn");p1turn=false;}
			
			else {currentButton.setText("X");pos[n]=1; Status.setText("Player 1 Turn");p1turn=true;}
						
			if(win(0)) {wonTheGame(0);}
			
			else if(win(1)){wonTheGame(1);}	
			
		    b++;
			}
		
		if(b==9 && !won){                    //all btns are pressed
			if(win(0)) { wonTheGame(0);}
			
			else if(win(1)){wonTheGame(1);}
			
		    else {wonTheGame(-1);}
				       }
		
		
		//// Someone won or match is draw  Next Round
		
		if(won) {                             
			gameCount++;
			Round_Level.setText(gameCount+"");
			for(int j=0;j<9;j++) {
				pos[j] = 2;
				btns[j].setText(" ");}
				b=0;
			won=false;
			/*if(win(0)) {JOptionPane.showMessageDialog(new JFrame(),"Player 1 WON this Round");}
			else if(win(1)) {JOptionPane.showMessageDialog(new JFrame(),"Player 2 WON this Round");}
			else {JOptionPane.showMessageDialog(new JFrame(),"This Round is Draw");}  */
			for(int i=0;i<9;i++) {
				btns[i].setBackground(mettalic);
			}
			}
			}
		if(gameCount==4) {
			
			if(p1>p2) {JOptionPane.showMessageDialog(new JFrame(),"Player 1 WON this Game"); }
			else if(p2>p1) {JOptionPane.showMessageDialog(new JFrame(),"Player 2 WON this Game");}
			else {JOptionPane.showMessageDialog(new JFrame(),"This Game is Draw");}
			new playSound(BeepSound);
			int o = JOptionPane.showConfirmDialog(new JFrame(), "Do you want to play again");
			if(o==JOptionPane.OK_OPTION){Restart();}
			else if(o == JOptionPane.NO_OPTION || o==JOptionPane.CANCEL_OPTION) {System.exit(0);}
			
			}
	}
			
		}
		
	}	
public void wonTheGame(int a) {
	
	if(a==0) {
		//System.out.println("Player1");
		p2--;
		Player2_Level.setText(p2 + "");
		won=true;
		Status.setText("Player 1 WIN");new playSound(BeepSound);
		JOptionPane.showMessageDialog(new JFrame(),"Player 1 WON this Round");
	}
	
	else if(a==1) {
		//System.out.println("Player2");
		won=true;
		p1--;
		Player1_Level.setText(p1+"");
		Status.setText("Player 2 Turn");new playSound(BeepSound);
		JOptionPane.showMessageDialog(new JFrame(),"Player 2 WON this Round");
		}
	else {
		//System.out.println("Its a Draw");
	     won=true;
	     p2--;p1--;
	     Player2_Level.setText(p2+"");
	     Player1_Level.setText(p1+"");
	     Status.setText("It's a Draw");new playSound(BeepSound);
	     JOptionPane.showMessageDialog(new JFrame(),"This Round is Draw");
	
	}
}

public boolean isEqual(int a,int b,int c,int d) {
	if(pos[b] == pos[c] && pos[c] == pos[d] && pos[d] == a) {
		btns[b].setBackground(Color.blue);
		btns[c].setBackground(Color.blue);
		btns[d].setBackground(Color.blue);
		return true;
	}
	else
		return false;
}

public boolean win(int a) {
	boolean A = isEqual(a,0,1,2);
	boolean B = isEqual(a,3,4,5);
	boolean C = isEqual(a,6,7,8);
	boolean D = isEqual(a,0,3,6);
	boolean E = isEqual(a,1,4,7);
	boolean F = isEqual(a,2,5,8);
	boolean G = isEqual(a,0,4,8);
	boolean H = isEqual(a,6,4,2);
	
	if(A || B || C || D || E || F || G || H) {
		return true;
	}
	else 
		return false;
	
	
}

public void Restart() {
	
	this.setVisible(false);
	new GameWindow();
}
	
public void titleCons(int a,int b,int c) {
	gbc.gridx =a;
	gbc.gridy = b;
	gbc.gridwidth = c;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.fill = GridBagConstraints.VERTICAL;
	gbc.weightx = 0.5;
	gbc.weighty = 0.5;
}
}


