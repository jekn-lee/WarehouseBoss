
public class Position {
    
    private Position pos;
    private int x;
    private int y;


    public Position(int x, int y) {
        pos = new Position(x,y);
        pos.x = x;
        pos.y = y;
    }

    public int getX() {
        return pos.x;
    }

    public int getY() {
        return pos.y;
    }

    public boolean compareCoordinate(Position a, Position b) {
        if (a.x == b.x && a.y == b.y) {
            return  true;
        } else {
            return false;
        }
    }

    public void changeCoordinate(String dir) {
        switch(dir){
            case "Left":
                pos.x--;
                break;
            case "Right":
                pos.x++;
                break;
            case "Up":
                pos.y++;
                break;
            case "Down":
                pos.y--;
                break;
        }
    }
}

