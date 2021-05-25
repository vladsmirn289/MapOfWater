import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 {
    private int[][] blockHeights;

    private enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST,
        NONE
    }

    public Task2(int[][] blockHeights) {
        this.blockHeights = blockHeights;
    }

    public int[][] waterHeightsByBlocks() {
        int width = blockHeights.length;
        int length = blockHeights[0].length;
        int[][] waterHeights = new int[blockHeights.length][blockHeights[0].length];
        int[][] oldBlockHeights;

        do {
            oldBlockHeights = deepCopy(blockHeights);
            int newHeight;

            for (int x = 1; x < width - 1; ++x) {
                for (int y = 1; y < length - 1; ++y) {
                    int currentHeight = blockHeights[x][y];
                    List<Integer> listOfBlockHeights = listOfAroundBlockHeights(x, y);

                    int firstMin = listOfBlockHeights.get(0);
                    int secondMin;
                    if (listOfBlockHeights.size() > 1) {
                        secondMin = listOfBlockHeights.get(1);
                    } else {
                        secondMin = firstMin;
                    }

                    if (currentHeight == 0 || listOfBlockHeights.get(0) == 0) {
                        waterHeights[x][y] += 0;
                    } else if (currentHeight == firstMin) {
                        if (!isPartOfLake(x, y, Direction.NONE)) {
                            waterHeights[x][y] += 0;
                        } else {
                            newHeight = secondMin - currentHeight + blockHeights[x][y];
                            waterHeights[x][y] = newHeight;
                            blockHeights[x][y] = newHeight;
                        }
                    } else if (currentHeight > firstMin) {
                        waterHeights[x][y] += 0;
                    } else {
                        newHeight = firstMin - currentHeight + blockHeights[x][y];
                        waterHeights[x][y] = newHeight;
                        blockHeights[x][y] = newHeight;
                    }
                }
            }
        } while (!isMapsEquals(oldBlockHeights, blockHeights));

        return waterHeights;
    }

    private List<Integer> listOfAroundBlockHeights(int x, int y) {
        /*
                    n = north
                    s = south
                    w = west
                    e = east
        */
        int n = blockHeights[x-1][y];
        int e = blockHeights[x][y+1];
        int s = blockHeights[x+1][y];
        int w = blockHeights[x][y-1];

        List<Integer> aroundBlockHeights = new ArrayList<>(Arrays.asList(n, e, s, w));
        return aroundBlockHeights.stream()
                .distinct().sorted()
                .collect(Collectors.toList());
    }

    private boolean isPartOfLake(int x, int y, Direction from) {
        if (x == 0 || y == 0 || x == blockHeights.length-1 || y == blockHeights[0].length-1) {
            return false;
        }

        int n = blockHeights[x-1][y];
        int e = blockHeights[x][y+1];
        int s = blockHeights[x+1][y];
        int w = blockHeights[x][y-1];
        int currentHeight = blockHeights[x][y];

        if (n <= currentHeight && !from.equals(Direction.NORTH)) {
            if (!isPartOfLake(x - 1, y, Direction.SOUTH)) {
                return false;
            }
        }

        if (e <= currentHeight && !from.equals(Direction.EAST)) {
            if (!isPartOfLake(x, y + 1, Direction.WEST)) {
                return false;
            }
        }

        if (s <= currentHeight && !from.equals(Direction.SOUTH)) {
            if (!isPartOfLake(x + 1, y, Direction.NORTH)) {
                return false;
            }
        }

        if (w <= currentHeight && !from.equals(Direction.WEST)) {
            if (!isPartOfLake(x, y - 1, Direction.EAST)) {
                return false;
            }
        }

        return true;
    }

    private int[][] deepCopy(int[][] array) {
        int[][] newArray = new int[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int k = 0; k < array[i].length; ++k) {
                newArray[i][k] = array[i][k];
            }
        }

        return newArray;
    }

    private boolean isMapsEquals(int[][] firstMap, int[][] secondMap) {
        if (firstMap.length != secondMap.length) {
            return false;
        }

        for (int i = 0; i < firstMap.length; ++i) {
            for (int k = 0; k < firstMap[i].length; ++k) {
                if (firstMap[i][k] != secondMap[i][k]) {
                    return false;
                }
            }
        }

        return true;
    }

    public int[][] getBlockHeights() {
        return blockHeights;
    }

    public void setBlockHeights(int[][] blockHeights) {
        this.blockHeights = blockHeights;
    }

    public static void main(String[] args) {
        Tests.testCases();
    }
}
