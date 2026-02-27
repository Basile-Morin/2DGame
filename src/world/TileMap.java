package world;

public final class TileMap {
    private int cols;
    private int rows;
    private int[][] grid;

    public TileMap(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.grid = new int[cols][rows];
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
    }

}