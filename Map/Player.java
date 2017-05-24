
public class Player implements Asset  {
    private Position pos;


    public Player(int x, int y) {
        this.pos = new Position(x ,y);
    }

    @Override
    public Position getPosition() {
        return this.pos;
    }

    @Override
    public void setPosition(Position p) {
        this.pos = p;
    }

    @Override
    public void move(String direction) {
        this.pos.changeCoordinate(direction);
    }
}