
public class Box implements Asset {
    private Position pos;


    public Box(int x, int y) {
        this.pos = new Position(x,y);
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

    public boolean atGoal(Position goal) {
        return this.pos.compareCoordinate(this.pos,goal);
    }
}
