import javax.swing.JFrame;

public final class GameSystem extends JFrame {

    /**
	 * Default Serial
	 */
	private static final long serialVersionUID = 1L;
	
	private int offset;

    public GameSystem() {
        InitUI();
    }

    public void InitUI() {
        GameBoard board = new GameBoard();
        add(board);
        this.offset = board.getGameBoardWidth()/100;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board.getGameBoardWidth() + this.offset,
                board.getGameBoardHeight() + 2*(this.offset));
        setLocationRelativeTo(null);
        setTitle("Plantonia");
    }


    public static void main(String[] args) {

        GameSystem GameSystem = new GameSystem();
        GameSystem.setVisible(true);
    }
}


