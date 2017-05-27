import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.Timer;

/**
 * GameBoard class which manages all states of the game
 * @author Kevin z3418497 mahima z5113392
 */
public class GameBoard extends JPanel {

	/**
	 * Default Serial
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Declare all static ints here 
	 */
    //For difficulty selection
    private final int EASY = 1;
    private final int MEDIUM= 2;
    private final int HARD = 3;
    private final int RANDOM = 4;
	//For collision checking
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;
    //Direction Check
    private final int LEFT = 1;
    private final int RIGHT = 3;
    private final int UP = 2;
    private final int BOTTOM = 4;
    //For display 
    private final int OFFSET_X = 18;
    private final int OFFSET_Y = 40;
    //Use enum to define state of display
	private STATE state;
	/**
	 * Declare all objects used by Game 
	 */
	private int difficulty;
	private int predefNum;
	private int undosLeft;
	private boolean completed;
    //score counter based on moves
    private int moves;
    private int wallBreaks;
    private String score;
    private String breaks;
	//Width and Height of the full display
    private final int FR_WIDTH = 800;
    private final int FR_HEIGHT = 850;
	//Width and Height of each asset 
	private int assetWidth;
	private int assetHeight;
	//Arrays tracking placement of assets
	private ArrayList<Boundary> boundaries;
	private ArrayList<BoxDis> boxes;
	private ArrayList<GoalDis> goals;
	private ArrayList<Integer> mapRep;
	private PlayerDis player;
	//Map object for game map generation
	private MapSystem currMap;
	private MapSystem replayMap;
	//All the display classes
	private Menu menu;
    private HowTo howTo;
    private Difficulty diff;
    private Win win;
    private Selection select;
    //Images used here
	private Image undo;
	private Image reset;
	private Image home;


    private Timer timer;
    private int animCounter;
   // private boolean firstM;
    private boolean animating;
    private boolean leg;

    private int lastK = 0;
	
	/**
	 * Constructor for GameBoard
	 */
	public GameBoard(){
    	this.addKeyListener(new KeyInput(this));
    	this.addMouseListener(new MouseInput(this));
    	this.setFocusable(true);
    	this.initStandardBoard();
        animCounter = 0;
        animating = false;
        this.leg = false;
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               ;
                animCounter++;
                leg = !leg;
                movePlayerR();
                System.out.println(animCounter);
                repaint();
            }
        });

    }

    private void movePlayerR() {


        switch (this.lastK) {
            case RIGHT:
                this.player.animate(this.lastK,this.leg);
                this.player.move(10,0);
                break;
            case LEFT:
                this.player.animate(this.lastK,this.leg);
                this.player.move(-10,0);
                break;
            case UP:
                this.player.animate(this.lastK,this.leg);
                this.player.move(0,-10);
                break;
            case BOTTOM:
                this.player.animate(this.lastK,this.leg);
                this.player.move(0,10);
                break;

        }

        if (animCounter == 5) {
            animCounter = 0;
            timer.stop();
            this.animating =false;
            this.player.setRot(this.lastK);
        }


    }

    private void initStandardBoard(){
		this.difficulty = EASY;
		this.state = STATE.MENU;
		this.completed = false;
		
		this.assetWidth = 50;
		this.assetHeight = 50;

        this.moves = 0;
        
        this.score = "Moves: " + this.moves;
        this.breaks = "Hedge cuts: " + this.wallBreaks;
        this.undosLeft = 0;
		this.boundaries = new ArrayList<Boundary>();
		this.boxes = new ArrayList<BoxDis>();
		this.goals = new ArrayList<GoalDis>();

		this.player = new PlayerDis(0,0);
		
		this.menu = new Menu(this);
        this.howTo = new HowTo(this);
        this.diff = new Difficulty(this);
        this.win = new Win(this);
        this.select = new Selection(this);
        
		ImageIcon  undoImg = new ImageIcon("button_undo.png");
		this.undo = undoImg.getImage();
		
		ImageIcon  resetImg = new ImageIcon("button_reset.png");
		this.reset = resetImg.getImage();
		
        ImageIcon homeImg = new ImageIcon("home_icon.png");
        this.home = homeImg.getImage();
	}
	
	private void positionAssets(){
        //Set starting width x and height y 
        int x = OFFSET_X;
        int y = OFFSET_Y + this.assetHeight;

        Boundary boundaryB;
        BoxDis b;
        GoalDis g;
        
        //Clear any currently held assets
        this.boundaries.clear();
        this.goals.clear();
        this.boxes.clear();

        //iterate through the mapRep array and set the sizes of 
        //all the assets
        for (int i = 0; i < this.mapRep.size(); i++) {

            int item = this.mapRep.get(i);
            if (item == 6) {
                y += this.assetHeight;
                x = OFFSET_X;
            } else if (item == 1) {
                boundaryB = new Boundary(x, y);
                boundaryB.scaleImage((int) (this.assetWidth*1.15), (int) (this.assetHeight*1.15));
                this.boundaries.add(boundaryB);
                x += this.assetWidth;
            } else if (item == 3) {
                b = new BoxDis(x, y);
                b.scaleImage(this.assetWidth, this.assetHeight);
                this.boxes.add(b);
                x += this.assetWidth;
            } else if (item == 4) {
                g = new GoalDis(x, y);
                g.scaleImage(this.assetWidth, this.assetHeight);
                this.goals.add(g);
                x += this.assetWidth;
            } else if (item == 2) {
                this.player.setX(x);
                this.player.setY(y);
                this.player.scaleImage(this.assetWidth, this.assetHeight);
                x += this.assetWidth;
            } else if (item == 0) {
                x += this.assetWidth;
            }
            //this.frameHeight = y + this.assetHeight;
        }
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(this.state == STATE.MENU){
        	buildMenu(g);
        }else if(this.state == STATE.HOWTOPLAY){
        	buildHowToPlay(g);
        }else if(this.state == STATE.SELECTION){
        	buildSelection(g);
        }else if(this.state == STATE.DIFFICULTY){
        	buildDifficulty(g);
        }else if(this.state == STATE.INGAME){
        	buildGame(g);
        } else if(this.state == STATE.WIN){
            buildWin(g);
        }
    }
    /**
     * Construct the menu display
     * @param g current Graphics
     */
    public void buildMenu(Graphics g){
    	//Set up basic display first
    	g.setColor(new Color(90, 221, 248));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    	this.menu.render(g);
    }
    /**
     * Construct the How to Play display
     * @param g current Graphics
     */
    public void buildHowToPlay(Graphics g){
    	//Set up basic display first
        g.setColor(new Color(100,100,100));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.howTo.render(g);
    }

    public void buildWin(Graphics g){
        //Set up basic display first
        g.setColor(new Color(0, 194, 200));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.win.render(g);
    }
    /**
     * Construct the Rules display
     * @param g current Graphics
     */    
    public void buildSelection(Graphics g){
    	//Set up basic display first
    	g.setColor(new Color(0, 194, 200));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.select.render(g);
    }
    /**
     * Construct the Difficulty display
     * @param g current Graphics
     */
    public void buildDifficulty(Graphics g){
    	//Set up basic display first
        g.setColor(new Color(100,100,100));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.diff.render(g);
    }
    /**
     * Construct the Game display
     * @param g current Graphics
     */
    public void buildGame(Graphics g){
        //Graphics2D g2d = (Graphics2D) g;
    	//Set up basic display first
        g.setColor(new Color(84, 157, 55));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
   
        ArrayList<AssetDis> items = new ArrayList<AssetDis>();
        items.addAll(this.boundaries);
        items.addAll(this.goals);
        items.addAll(this.boxes);
        if (this.moves == 0) {
            items.add(this.player);
        } else {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        Font fnt0 = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);


        for (int i = 0; i < items.size(); i++) {

            AssetDis item = (AssetDis) items.get(i);

            if ((item instanceof PlayerDis)
                    || (item instanceof BoxDis)) {
                g.drawImage(item.getImage(), item.getX(), item.getY(), this);
            } else {
                g.drawImage(item.getImage(), item.getX(), item.getY(), this);
            }

            if (this.completed) {
                g.setColor(new Color(0, 0, 0));
                this.state = STATE.WIN;
                repaint();
             //   g.drawString("Completed", 25, 20);
            }

        }

        g.drawString(this.score, 20,40);
        g.drawString(this.breaks, 150,40);
        if(undoPossible()){
        	g.drawImage(this.undo, 450, 10, 100, 50, this);
        }
		g.drawImage(this.reset, 575, 10,100, 50, this);
		g.drawImage(this.home,700,10,50,50,this);

    }
 
	/**
	 * @return width of final board
	 */
	public int getGameBoardWidth(){
		return FR_WIDTH;
	}
    /**
     * @return height of final board
     */
	public int getGameBoardHeight(){
		return FR_HEIGHT;
	}
	/**
	 * @return current enum state of display
	 */
    public STATE getState() {
		return this.state;
	}
    /**
     * Set display state of game
     * @param state to change to
     */
	public void setState(STATE state) {
		this.state = state;
	}
	/**
     * @return boolean if game is completed
     */
    public boolean getCompleted(){
    	return this.completed;
    }
    /**
     * Check if current game is completed 
     */
    public void isCompleted() {

        int num = boxes.size();
        int compl = 0;

        for (int i = 0; i < num; i++) {
            BoxDis box = (BoxDis) this.boxes.get(i);
            for (int j = 0; j < num; j++) {
                GoalDis goal = (GoalDis) this.goals.get(j);
                if (box.getX() == goal.getX()
                        && box.getY() == goal.getY()) {
                    compl += 1;
                }
            }
        }

        if (compl == num) {
            completed = true;
            repaint();
        }
    }
    /**
     * Update GameBoard when key e is pressed
     * @param e key pressed
     */

	public void keyPressed(KeyEvent e) throws InterruptedException {
		if(this.state == STATE.INGAME && !this.animating){
	        if (this.completed) {
	            //resetGame();
	        }
	        int key = e.getKeyCode();
	        if (key == KeyEvent.VK_LEFT) {
                this.lastK = LEFT;
                this.player.setRot(LEFT);
	            if (checkBoundaryCollision(player,
	                    LEFT_COLLISION)) {
	                return;
	            }
	
	            if (checkBoxCollision(LEFT_COLLISION)) {
	                return;
	            }
                this.animating = true;
                timer.start();
	           // player.move(-this.assetWidth, 0);
                this.moves++;
                this.currMap.getMapRep().updateMove("Left");
                this.undosLeft++;
	            //player.move(-SPACE/8, 0);
	
	        } else if (key == KeyEvent.VK_RIGHT) {
                    this.lastK = RIGHT;
                this.player.setRot(RIGHT);
                if (checkBoundaryCollision(player,
                        RIGHT_COLLISION)) {
                    return;
                }

                if (checkBoxCollision(RIGHT_COLLISION)) {
                    return;
                }
                this.animating = true;
                timer.start();
                this.moves++;
                this.currMap.getMapRep().updateMove("Right");
                this.undosLeft++;
	            //player.move(SPACE/8, 0);
	
	        } else if (key == KeyEvent.VK_UP) {
                this.lastK = UP;
                this.player.setRot(UP);
	            if (checkBoundaryCollision(player,
	                    TOP_COLLISION)) {
	                return;
	            }
	
	            if (checkBoxCollision(TOP_COLLISION)) {
	                return;
	            }
	
	           // player.move(0, -this.assetHeight);
                this.animating = true;
                timer.start();
                this.moves++;
                this.currMap.getMapRep().updateMove("Up");
                this.undosLeft++;
	            //player.move(0, -SPACE/8);
	
	        } else if (key == KeyEvent.VK_DOWN) {
                this.lastK = BOTTOM;
                this.player.setRot(BOTTOM);
	            if (checkBoundaryCollision(player,
	                    BOTTOM_COLLISION)) {
	                return;
	            }
	
	            if (checkBoxCollision(BOTTOM_COLLISION)) {
	                return;
	            }
	
	            //player.move(0, this.assetHeight);
                this.animating = true;
                timer.start();
                this.moves++;
                this.currMap.getMapRep().updateMove("Down");
                this.undosLeft++;
	            //player.move(0, SPACE/8);
	
	        } else if (key == KeyEvent.VK_SPACE) {
                if (checkBoundaryCollision(player,
                        LEFT_COLLISION) && this.wallBreaks > 0 && this.lastK == LEFT) {
                    delLeft();
                    this.undosLeft = -1000;
                } else if (checkBoundaryCollision(player,TOP_COLLISION) && this.wallBreaks > 0 && this.lastK == UP) {
                    delTop();
                    this.undosLeft = -1000;
                } else if (checkBoundaryCollision(player,RIGHT_COLLISION) && this.wallBreaks > 0 && this.lastK == RIGHT) {
                    delRight();
                    this.undosLeft = -1000;
                } else if (checkBoundaryCollision(player,BOTTOM_COLLISION) && this.wallBreaks > 0 && this.lastK == BOTTOM) {
                    delBottom();
                    this.undosLeft = -1000;
                }


            }
            this.score = "Moves: " + this.moves;
            this.breaks = "Hedge cuts: " + this.wallBreaks;
            repaint();
		}
	}

    private void delLeft() {
        System.out.println("BEFORE DEL: " + this.boundaries.size());
        int i = 0;
        for (i = 0; i < this.boundaries.size(); i++) {
            Boundary wall = this.boundaries.get(i);
            if (this.player.isLeftCollision(wall)) {
                if (edge(wall)) {
                    this.boundaries.remove(wall);
                    this.wallBreaks--;
                    this.moves++;
                    System.out.println("AFTER DEL: " + this.boundaries.size());
                }
                //break;
            }
        }

    }
    private void delTop() {
        System.out.println("BEFORE DEL: " + this.boundaries.size());
        int i = 0;
        for (i = 0; i < this.boundaries.size(); i++) {
            Boundary wall = this.boundaries.get(i);
            if (this.player.isTopCollision(wall)) {
                if (edge(wall)) {
                    this.boundaries.remove(wall);
                    this.wallBreaks--;
                    this.moves++;
                    System.out.println("AFTER DEL: " + this.boundaries.size());
                    //break;
                }
            }
        }

    }
    private void delRight() {
        System.out.println("BEFORE DEL: " + this.boundaries.size());
        int i = 0;
        for (i = 0; i < this.boundaries.size(); i++) {
            Boundary wall = this.boundaries.get(i);
            if (this.player.isRightCollision(wall)) {
                if (edge(wall)) {
                    this.boundaries.remove(wall);
                    this.wallBreaks--;
                    this.moves++;
                    System.out.println("AFTER DEL: " + this.boundaries.size());

                    //break;
                }

            }
        }

    }
    private void delBottom() {
        System.out.println("BEFORE DEL: " + this.boundaries.size());
        System.out.println("hhhhhh: " + this.getGameBoardHeight());
        int i = 0;
        for (i = 0; i < this.boundaries.size(); i++) {
            Boundary wall = this.boundaries.get(i);
            if (this.player.isBottomCollision(wall)) {
                if (edge(wall)) {
                    System.out.println("wwwwwww: " + (wall.getY() + assetHeight));
                    this.boundaries.remove(wall);
                    this.wallBreaks--;
                    this.moves++;
                    System.out.println("AFTER DEL: " + this.boundaries.size());
                    //break;
                }
            }
        }

    }
    public boolean edge(Boundary wall) {
        if (wall.getY() + assetHeight + 60 < this.getGameBoardHeight() && wall.getY() > 100) {
            if ((wall.getX() + assetWidth) < this.getGameBoardWidth() && wall.getX() > 50) {
                return true; //not on edge of map
            }
        }
        return false;
    }
    public void yo() throws InterruptedException {
        player.move(5, 0);
    }
    /**
     * Update GameBoard when key e is released
     * @param e key released
     */	
	public void keyReleased(KeyEvent e){
		repaint();
	}
	private boolean checkBoundaryCollision(AssetDis obj, int type) {

    	ArrayList<Boundary> walls = this.boundaries;
    	
        if (type == LEFT_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
                Boundary wall = (Boundary) walls.get(i);
                if (obj.isLeftCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == RIGHT_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
            	Boundary wall = (Boundary) walls.get(i);
                if (obj.isRightCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
            	Boundary wall = (Boundary) walls.get(i);
                if (obj.isTopCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == BOTTOM_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
            	Boundary wall = (Boundary) walls.get(i);
                if (obj.isBottomCollision(wall)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean checkBoxCollision(int type) {

    	ArrayList<BoxDis> boxes = this.boxes;
    	
        if (type == LEFT_COLLISION) {

            for (int i = 0; i < boxes.size(); i++) {

                BoxDis box = (BoxDis) boxes.get(i);
                if (player.isLeftCollision(box)) {

                    for (int j=0; j < boxes.size(); j++) {
                        BoxDis item = (BoxDis) boxes.get(j);
                        if (!box.equals(item)) {
                            if (box.isLeftCollision(item)) {
                                return true;
                            }
                        }
                        if (checkBoundaryCollision(box,
                                LEFT_COLLISION)) {
                            return true;
                        }
                    }
                    box.move(-this.assetWidth, 0);
                    isCompleted();
                }
            }
            return false;

        } else if (type == RIGHT_COLLISION) {

            for (int i = 0; i < boxes.size(); i++) {

                BoxDis box = (BoxDis) boxes.get(i);
                if (player.isRightCollision(box)) {
                    for (int j=0; j < boxes.size(); j++) {

                        BoxDis item = (BoxDis) boxes.get(j);
                        if (!box.equals(item)) {
                            if (box.isRightCollision(item)) {
                                return true;
                            }
                        }
                        if (checkBoundaryCollision(box,
                                RIGHT_COLLISION)) {
                            return true;
                        }
                    }
                    box.move(this.assetWidth, 0);
                    isCompleted();                  
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {

            for (int i = 0; i < boxes.size(); i++) {

                BoxDis box = (BoxDis) boxes.get(i);
                if (player.isTopCollision(box)) {
                    for (int j = 0; j < boxes.size(); j++) {

                        BoxDis item = (BoxDis) boxes.get(j);
                        if (!box.equals(item)) {
                            if (box.isTopCollision(item)) {
                                return true;
                            }
                        }
                        if (checkBoundaryCollision(box,
                                TOP_COLLISION)) {
                            return true;
                        }
                    }
                    box.move(0, -this.assetHeight);
                    isCompleted();
                }
            }

            return false;

        } else if (type == BOTTOM_COLLISION) {
        
            for (int i = 0; i < boxes.size(); i++) {

                BoxDis box = (BoxDis) boxes.get(i);
                if (player.isBottomCollision(box)) {
                    for (int j = 0; j < boxes.size(); j++) {

                        BoxDis item = (BoxDis) boxes.get(j);
                        if (!box.equals(item)) {
                            if (box.isBottomCollision(item)) {
                                return true;
                            }
                        }
                        if (checkBoundaryCollision(box,
                                BOTTOM_COLLISION)) {
                            return true;
                        }
                    }
                    box.move(0, this.assetHeight);
                    isCompleted();
                }
            }
        }

        return false;
    }
    
    public void rerun(){
    	resetMap();
    	//TODO
    }

    public void resetGame() {
        this.state = STATE.INGAME;
        this.completed = false;

        this.assetWidth = 50;
        this.assetHeight = 50;

        switch (this.difficulty) {
            case EASY:
                this.wallBreaks = 3;
                break;
            case MEDIUM:
                this.wallBreaks = 2;
                break;
            case HARD:
                this.wallBreaks = 1;
                break;
        }

        this.moves = 0;
        this.score = "Moves: " + this.moves;

        this.breaks = "Hedge cuts: " + this.wallBreaks;
        this.undosLeft = 0;

        this.boundaries.clear();
        this.boxes.clear();
        this.goals.clear();

        this.player = new PlayerDis(0,0);

        this.menu = new Menu(this);
        this.howTo = new HowTo(this);
        this.win = new Win(this);
        this.select = new Selection(this);
        
        resetMap();

    }

    public int getScore() {
        return this.moves;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int d) {
        this.difficulty = d;
    }
    
    public void setPredefNum(int num){
    	this.predefNum = num;
    }
    
    public boolean undoPossible(){
    	return (this.undosLeft>0);
    }
    
    public void undo(){
    	this.undosLeft--;
    	this.mapRep = this.currMap.undoMove();
    	positionAssets();
    	//TODO
    }
    
    public void setMap(){
    	if(this.predefNum != RANDOM){
	    	switch(this.difficulty){
	    	case EASY:
	    		this.currMap = new MapSystem(this.difficulty,this.predefNum);
	    		this.replayMap = new MapSystem(this.difficulty,this.predefNum);
	    		this.wallBreaks = 3;
	    		break;
	    	case MEDIUM:
	    		this.currMap = new MapSystem(this.difficulty,this.predefNum);
	    		this.replayMap = new MapSystem(this.difficulty,this.predefNum);
	    		this.wallBreaks = 2;
	    		break;
	    	case HARD:
	    		this.currMap = new MapSystem(this.difficulty,this.predefNum);
	    		this.replayMap = new MapSystem(this.difficulty,this.predefNum);
	    		this.wallBreaks = 1;
	    		break;
	    	}
    	}else{
    		switch(this.difficulty){
	    	case EASY:
	    		this.currMap = new MapSystem(7,7,1);
	    		this.replayMap = new MapSystem(7,7,1);
	    		this.wallBreaks = 3;
	    		break;
	    	case MEDIUM:
	    		this.currMap = new MapSystem(10,10,2);
	    		this.replayMap = new MapSystem(10,10,2);
	    		this.wallBreaks = 2;
	    		break;
	    	case HARD:
	    		this.currMap = new MapSystem(13,7,3);
	    		this.replayMap = new MapSystem(13,7,3);
	    		this.wallBreaks = 1;
	    		break;
	    	}
    	}
    	
    	/*Map map = new Map(this.currMap.getMapRep().getMapRep(), this.currMap.getMapRep().getPlayerPosX(),
    			           this.currMap.getMapRep().getPlayerPosY());
    	this.replayMap.setMapRep(map);
    	*/
    	this.mapRep = this.currMap.getMapRep().getArrayRep();
    	/*
    	switch(this.predefNum){
    	case 1:
    		this.mapRep = this.currMap.getPredefined(difficulty, 1);
    		break;
    	case 2:
    		this.mapRep = this.currMap.getPredefined(difficulty, 2);
    		break;
    	case 3: 
    		this.mapRep = this.currMap.getPredefined(difficulty, 3);
    		break;
    	case RANDOM:
    		this.mapRep = this.currMap.getMapRep().getArrayRep();
    		break;
    	}*/
    	positionAssets();
    }
    
    public void resetMap(){
    	Map newMap = new Map(this.replayMap.getMapRep().getMapRep(), this.replayMap.getMapRep().getPlayerPosX(),
    						this.replayMap.getMapRep().getPlayerPosY());
    	this.currMap.setMapRep(newMap);
    	
    	switch(this.predefNum){
    	case 1:
    		this.mapRep = this.currMap.getPredefined(difficulty, 1);
    		break;
    	case 2:
    		this.mapRep = this.currMap.getPredefined(difficulty, 2);
    		break;
    	case 3: 
    		this.mapRep = this.currMap.getPredefined(difficulty, 3);
    		break;
    	case RANDOM:
    		this.mapRep = this.currMap.getMapRep().getArrayRep();
    		break;
    	}
    	positionAssets();
    }
}

