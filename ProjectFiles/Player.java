public class Player implements Asset  {
    private Player player;
    private Position pos;


    public Player() {
        player = new Player();
    }

    @Override
    public Position getPosition() {
        return player.pos;
    }

    @Override
    public void setPosition(Position p) {
        player.pos = p;
    }

    @Override
    public void move(String direction) {
        player.pos.changeCoordinate(direction);
    }
}
