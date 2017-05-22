public class Position {

    private int x;
    private int y;


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //
    public Position(Position p) {
    	this.x = p.x;
    	this.y = p.y;
    }

    //
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /*public boolean compareCoordinate(Position a, Position b) {
        if (a.x == b.x && a.y == b.y) {
            return  true;
        } else {
            return false;
        }
    }*/
    
    public boolean compareCoordinate(Position p) {
    	if (this.x == p.x && this.y == p.y) {
    		return true;
    	} else {
    		return false;
    	}
    }

    public void changeCoordinate(String dir) {
        switch(dir){
            case "Left":
                this.x--;
                break;
            case "Right":
                this.x++;
                break;
            case "Up":
                this.y--;
                break;
            case "Down":
                this.y++;
                break;
        }
    }
}