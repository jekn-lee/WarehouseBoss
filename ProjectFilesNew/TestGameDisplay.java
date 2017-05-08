import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.ArrayList;
import javax.swing.*;

public class TestGameDisplay extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    ImageIcon box;
    ImageIcon player;
    ImageIcon boundary;
    ImageIcon filledGoal;
    ImageIcon emptyGoal;
    ImageIcon empty;
    
	public TestGameDisplay(){
	    this.box = new ImageIcon("box.png");
	    this.player = new ImageIcon("player.png");
	    this.boundary = new ImageIcon("boundary.png");
	    this.filledGoal = new ImageIcon("filledGoal.png");
	    this.emptyGoal = new ImageIcon("emptyGoal.png");
	    this.empty = new ImageIcon("empty.png");
	    addKeyListener(this);
	    setFocusable(true);
	}
	public void displayCurrent(ArrayList<Integer> map){
		prepareGUI(map);
	}
	private void prepareGUI(ArrayList<Integer> map){
	    JFrame mainFrame = new JFrame("Game Display");
	    mainFrame.setSize(800,800);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setBackground(Color.blue);
	    
	    BoxLayout boxLayout = new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
	    
	    JPanel controlPanel = new JPanel();
	    controlPanel.setLayout(new FlowLayout());
	    controlPanel.setSize(800, 100);
	    controlPanel.add(new JButton("Home"));
	    controlPanel.add(new JButton("Rules"));
	    controlPanel.add(new JButton("Undo"));
	    
	    JPanel gamePanel = new JPanel();
	    gamePanel.setLayout(new GridLayout(6,6));
	    gamePanel.setSize(800, 700);
	    renewGamePanel(gamePanel,map);
	   
	    mainFrame.setLayout(boxLayout);
	    mainFrame.add(controlPanel);
	    mainFrame.add(gamePanel);
	    mainFrame.setVisible(true);
	}
	
	private void renewGamePanel(JPanel view, ArrayList<Integer> map){
	    
	    Iterator<Integer> iter = map.iterator();
	    while(iter.hasNext()){
	    	int current = iter.next();
	    	switch(current){
	    	case 0:
	    		view.add(new JLabel(this.empty));
	    		break;
	    	case 1:
	    		view.add(new JLabel(this.boundary));
	    		break;
	    	case 2:
	    		view.add(new JLabel(this.player));
	    		break;
	    	case 3:
	    		view.add(new JLabel(this.box));
	    		break;
	    	case 4:
	    		view.add(new JLabel(this.emptyGoal));
	    		break;
	    	case 5:
	    		view.add(new JLabel(this.filledGoal));
	    		break;
	    	}
	    }
	}

	/*
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_DOWN){
			this.ex.displayCurrent(test.getCurrMap("Down"));
		} else if (code == KeyEvent.VK_UP){
			this.ex.displayCurrent(test.getCurrMap("Up"));
		} else if (code == KeyEvent.VK_LEFT){
			this.ex.displayCurrent(test.getCurrMap("Left"));
		} else if (code == KeyEvent.VK_RIGHT){
			this.ex.displayCurrent(test.getCurrMap("Right"));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	*/
}
