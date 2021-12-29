import java.util.ArrayList;

/**
 * A class defines a two-dimensional grid world made by Tiles
 */
public class Map implements Displayable{
    private int w;
    private int h;
    private Tile[][] map;

    public Map(int w, int h) {
        this.w = w;
        this.h = h;
        initMap();
    }

    private void initMap() {
        map = new Tile[getH()][getW()];
        for (int i = 0; i < getH(); i++) {
            for (int j = 0; j < getW(); j++) {
                map[i][j] = new Tile();
            }
        }
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    public Tile getTile(int i, int j) {
        return map[i][j];
    }

    // add a component to a specified tile
    public void add(int i, int j, WorldComponent c) {
        getTile(i, j).addComponents(c);
    }

    // transform the information  to arraylist type to displayed into a GUI by LayOutManager
    public ArrayList<String> toStringList() {
        ArrayList<String> view = new ArrayList<>();
        String boarder = "_".repeat(getW() * 4 + 1);
        for (int h = 0; h < getH(); h++) {
            view.add(boarder);
            String line = "| ";
            for (int w = 0; w < getW(); w++) {
                line += getTile(w, h).toString() + " | ";
            }
            view.add(line);
        }
        view.add(boarder);
        return view;
    }

}
