public class RiverChecker {
    private int[][] blockHeights;
    private static int stepsCounter;
    private int maxSteps;

    public RiverChecker(int[][] blockHeights) {
        this.blockHeights = blockHeights;
        this.maxSteps = blockHeights.length * blockHeights[0].length;
    }

    public boolean isPartOfRiver(Coordinate coordinate, Direction prev) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        ++stepsCounter;
        if (stepsCounter > maxSteps) {
            return false;
        }

        if (x == 0 || y == 0 || x == blockHeights.length-1 || y == blockHeights[0].length-1) {
            return true;
        }

        int n = blockHeights[x-1][y];
        int e = blockHeights[x][y+1];
        int s = blockHeights[x+1][y];
        int w = blockHeights[x][y-1];
        int currentHeight = blockHeights[x][y];

        if (n <= currentHeight && !prev.equals(Direction.NORTH)) {
            if (isPartOfRiver(new Coordinate(x - 1, y), Direction.SOUTH)) {
                return true;
            }
        }

        if (e <= currentHeight && !prev.equals(Direction.EAST)) {
            if (isPartOfRiver(new Coordinate(x, y + 1), Direction.WEST)) {
                return true;
            }
        }

        if (s <= currentHeight && !prev.equals(Direction.SOUTH)) {
            if (isPartOfRiver(new Coordinate(x + 1, y), Direction.NORTH)) {
                return true;
            }
        }

        if (w <= currentHeight && !prev.equals(Direction.WEST)) {
            if (isPartOfRiver(new Coordinate(x, y - 1), Direction.EAST)) {
                return true;
            }
        }

        return false;
    }

    public int[][] getBlockHeights() {
        return blockHeights;
    }

    public void setBlockHeights(int[][] blockHeights) {
        this.blockHeights = blockHeights;
        this.maxSteps = blockHeights.length * blockHeights[0].length;
    }

    public void resetCounter() {
        stepsCounter = 0;
    }
}
