
public class Goal implements Asset {
    private Position pos;
    private boolean isSatisfied;

    public Goal(int x, int y, boolean b) {
        this.pos = new Position(x, y);
        this.isSatisfied = b;
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

    }

    public boolean getSatisfied() {
        return this.isSatisfied;
    }

    public void setSatisfied(boolean b) {
        this.isSatisfied = b;
    }

}
