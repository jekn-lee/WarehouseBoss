
public class Box implements Asset {
    private Box box;
    private Position pos;


    public Box() {
        box = new Box();
    }
    @Override
    public Position getPosition() {
        return box.pos;
    }

    @Override
    public void setPosition(Position p) {
        box.pos = p;
    }

    @Override
    public void move(String direction) {
        box.pos.changeCoordinate(direction);
    }

    public boolean atGoal(Position goal) {
        return box.pos.compareCoordinate(box.pos,goal);
    }
}
