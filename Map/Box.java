
public class Box implements Asset {
    private Position pos;
    private boolean isAtGoal;


    public Box(int x, int y, boolean g) {
        this.pos = new Position(x,y);
        this.isAtGoal = g;
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
        if (this.isAtGoal) {
            this.isAtGoal = false;
        }
        this.pos.changeCoordinate(direction);
        System.out.println("Moved box to (" + this.pos.getX() + "," + this.pos.getY() + ")");
    }
    
    //
    public Position clonePosition() {
    	Position p = new Position(this.pos);
    	return p;
    }
    //

    public boolean atGoal(Position goal) {
    	return this.pos.compareCoordinate(goal);
        //return this.pos.compareCoordinate(this.pos,goal);
    }

    public boolean getGoalState() {
        return this.isAtGoal;
    }

    public void setGoalSate(boolean g) {
        this.isAtGoal = g;
    }
}