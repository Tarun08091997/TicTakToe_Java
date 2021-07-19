import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class ExtraClasses {

}

class Mouse implements MouseListener{
	
	String clickSound = "files/Mouse_Click.wav";		
	
	public void  mouseEntered(MouseEvent m) { }

	public void mousePressed(MouseEvent m){ }
	public void mouseReleased(MouseEvent m) { }
	public void mouseExited(MouseEvent m) { }
    public void mouseClicked(MouseEvent m) 
    { 
    	new playSound(clickSound);
    }
	
    
}


class Button extends JButton{
	 Button(String a,String b,Border border,Color color){
		 this.setText(a);
		 this.setBorder(border);
		 this.setBackground(color);
		 this.setActionCommand(b);
	 }	 
}


class Label extends JLabel{ 
	Label(String a,Font f,Border border){
		this.setText(a);
	    this.setFont(f);
	    this.setBorder(border);
	    this.setHorizontalAlignment(CENTER);
	}
}

class playSound{
	Clip clip;
	playSound(String soundFile){
		this.setFile(soundFile);
		this.play();
		
	}
	public void setFile(String soundFile) {
    	try {
    		File file = new File(soundFile);
    		AudioInputStream sound = AudioSystem.getAudioInputStream(file);
    		clip = AudioSystem.getClip();
    		clip.open(sound);
    	}
    	catch(Exception e) {
    		
    	}
    }
    public void play() {
    	clip.setFramePosition(0);
    	clip.start();

    }	
}