import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * MouseInput class to detect mouse presses/releases
 *
 *
 */
public class MouseInput implements MouseListener{
    //For difficulty selection
    private final int EASY = 1;
    private final int MEDIUM= 2;
    private final int HARD = 3;
	//Pointer to game for updating
	GameBoard game;
	/**
	 * Constructor for mouse input
	 * @param game currently running
	 */
	public MouseInput(GameBoard game){
		this.game = game;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Action when mouse is pressed
	 */
	public void mousePressed(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();
		STATE currState = game.getState();
		System.out.println("X mouse: " +mX+"Y mouse: " +mY);

		System.out.println(game.getGameBoardWidth()/2+150);
		System.out.println(game.getGameBoardHeight()/2+130);
		switch(currState){
		case MENU:
			if(mX<= game.getGameBoardWidth()/2+150 && mX>= game.getGameBoardWidth()/2-150){
				if(mY>= game.getGameBoardHeight()/2-170 && mY<= game.getGameBoardHeight()/2-110) {
					game.setState(STATE.DIFFICULTY);
					game.repaint();
				} else if(mY>=game.getGameBoardHeight()/2-20 && mY<= game.getGameBoardHeight()/2+130) {
					game.setState(STATE.HOWTOPLAY);
					game.repaint();
				} else if(mY>=game.getGameBoardHeight()/2+130 && mY<= game.getGameBoardHeight()/2+280) {
					System.exit(1);
				} 
			}
			break;
		case HOWTOPLAY:
			if(mX<= 125 && mX>= 50){
				if(mY<= 750 && mY>= 675) {
					game.setState(STATE.MENU);
					game.repaint();
				}
			}else if(mX>=game.getGameBoardWidth()/2+150 && mX<=game.getGameBoardWidth()/2+350){
				if(mY<= 755 && mY>= 675) {
					game.setState(STATE.DIFFICULTY);
					game.repaint();
				}
			}
			break;
		case SELECTION:
            if(mY>=game.getGameBoardHeight()/2-200 && mY<=game.getGameBoardHeight()/2){
                if(mX>= 50 && mX<= 250) {
                	game.setPredefNum(1);
                	game.setMap();
                    game.setState(STATE.INGAME);
                    game.repaint();
                } else if(mX>=300 && mX<= 500) {
                	game.setPredefNum(2);
                	game.setMap();
                    game.setState(STATE.INGAME);
                    game.repaint();
                } else if(mX>=550 && mX<= 750) {
                	game.setPredefNum(3);
                	game.setMap();
                    game.setState(STATE.INGAME);
                    game.repaint();
                }
            }else if(mX>=game.getGameBoardWidth()/2-150 && mX<=game.getGameBoardWidth()/2+150){
            	if(mY>=game.getGameBoardHeight()/2+130 && mY<=game.getGameBoardHeight()/2+210){
            		game.setPredefNum(4);
            		game.setMap();
            		game.setState(STATE.INGAME);
            		game.repaint();
            	}
            }else if(mX<= 125 && mX>= 50){
            	if(mY<= 750 && mY>= 675) {
            		game.setState(STATE.MENU);
            		game.repaint();
            	}
            }
			break;
		case DIFFICULTY:
            if(mX<= game.getGameBoardWidth()/2+150 && mX>= game.getGameBoardWidth()/2-150){
                if(mY>= game.getGameBoardHeight()/2-170 && mY<= game.getGameBoardHeight()/2-110) {
                    game.setDifficulty(EASY);
                    game.setState(STATE.SELECTION);
                    game.repaint();
                } else if(mY>=game.getGameBoardHeight()/2-20 && mY<= game.getGameBoardHeight()/2+130) {
                    game.setDifficulty(MEDIUM);
                    game.setState(STATE.SELECTION);
                    game.repaint();
                } else if(mY>=game.getGameBoardHeight()/2+130 && mY<= game.getGameBoardHeight()/2+280) {
                    game.setDifficulty(HARD);
                    game.setState(STATE.SELECTION);
                    game.repaint();
                }
            }else if(mX<= 125 && mX>= 50){
            	if(mY<= 750 && mY>= 675) {
            		game.setState(STATE.MENU);
            		game.repaint();
            }
        }
			break;
		case INGAME:
			if(mY<= 60 && mY>= 10) {
				if (mX>=700 && mX<=750) {
					game.resetGame();
					game.setState(STATE.MENU);
					game.repaint();
				} else if (mX>=575 && mX<=675) {
					game.resetGame();
					game.repaint();
				} else if(game.undoPossible()){
					if(mX>=450 && mX<=550){
						game.undo();
						game.repaint();
					}
				}
			}

			break;
		case WIN:
			if(mX<=game.getGameBoardWidth()/2+150 && mX>= game.getGameBoardWidth()/2-150) {
				if (mY <= game.getGameBoardHeight()/2+80 && mY >= game.getGameBoardHeight()/2) {
					game.rerun();
					game.setState(STATE.INGAME);
					game.repaint();
				}
			}else if(mX<= 125 && mX>= 50){
				if(mY<= 750 && mY>= 675) {
					game.setState(STATE.MENU);
					game.repaint();
				}
            }
			break;
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
