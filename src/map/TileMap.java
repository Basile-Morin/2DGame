package map;

import java.util.HashSet;
import java.util.Set;

public final class TileMap {
    private int cols;
    private int rows;
    private int[][] grid;
    private final TileSet tileSet;

    public TileMap(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.grid = new int[cols][rows];
        this.tileSet = new TileSet();
    }

    public int[][] getMap() {
        return grid;
    }

    public int getCols() { return cols; }
    public int getRows() { return rows; }

    public void setMap(int[][] map) {
        this.grid = map;
        this.cols= map.length;
        this.rows= map[0].length;
        Set<Integer> checkedTiles = new HashSet<>();
        for (int i=0;i<cols;i++){
            for (int j=0;j<rows;j++){
                int tileNumber = grid[i][j];
                if(checkedTiles.contains(tileNumber)) continue;
                if (tileSet.getTile(tileNumber) == null){
                    grid[i][j]=0;
                }
                else checkedTiles.add(tileNumber);
            }
        }
    }

    public TileSet getTileSet() {
        return tileSet;
    }
}