
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.awt.*;


public class mainWindow extends JFrame{
	
	Button Play , Options , Exit ;
	Container C;
	Label title,PlayerName;
	JFrame options;
	String Name="Player 1";
	
	ImageIcon title_icon = new ImageIcon("files/Logo.png");  // LOGO image
	
	BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED);  //Border
	BevelBorder bevelBorder2 = new BevelBorder(BevelBorder.LOWERED);
	
	Font f = new Font("Forte",Font.BOLD,25);  // Font
	Font f1 = new Font("Arial",Font.BOLD,25);
	
	Color mettalic = new Color(170,169,173);  //Color
	
	Action ac = new Action();
	Mouse mo = new Mouse();
	mainWindow(){
		this.setBounds(500,100,300,300);
 	   this.setTitle("Tick Tack Toe");
 	   this.setIconImage(title_icon.getImage());
 	   this.setDefaultCloseOperation(EXIT_ON_CLOSE);
 	   this.setBackground(Color.blue);
 	   this.setResizable(false);
 	   C = this.getContentPane();
 	   C.setLayout(null);
 	   C.setBackground(Color.white);
 	   GUI();
 	   C.add(Play);
 	   //C.add(Options);
 	   C.add(Exit);
 	   C.add(title);
 	   this.setVisible(true);
	}
	
	public void GUI(){
		Play = new Button("Play","P",bevelBorder,mettalic);
		Options = new Button("Options","O",bevelBorder,mettalic);
		Exit = new Button("Exit","E",bevelBorder,mettalic);
		Play.setFont(f);
		Options.setFont(f);
		Exit.setFont(f);
		
		title = new Label("Tic Tack Toe",f1,bevelBorder2);
		title.setBackground(Color.blue);
		title.setBounds(0,0,290,30);
		Play.setBounds(100,100,100,30);
		Exit.setBounds(100,200,100,30);
		
		Play.addActionListener(ac);
		Play.addMouseListener(mo);
		Options.addActionListener(ac);
		Exit.addActionListener(ac);
		Exit.addMouseListener(mo);
		
		
		
		}
	public void play() {
		this.setVisible(false);
		new GameWindow();
	}
	
	class Action implements ActionListener{
		Action(){}
		public void actionPerformed(ActionEvent e) {
			
			String a = e.getActionCommand();
			
			switch(a) {
			
			case("P"):  play(); break;
			case("E"): System.exit(0);
			}
			
		}
	}
	
	}
