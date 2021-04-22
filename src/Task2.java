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

        for (int x = 1; x < width-1; ++x) {
            for (int y = 1; y < length-1; ++y) {
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
                    waterHeights[x][y] = 0;
                } else if (currentHeight == firstMin) {
                    if (isPartOfLake(x, y, Direction.NONE)) {
                        waterHeights[x][y] = 1;
                    } else {
                        waterHeights[x][y] = secondMin - currentHeight + 1;
                    }
                } else if (currentHeight > firstMin) {
                    waterHeights[x][y] = 0;
                } else {
                    waterHeights[x][y] = firstMin - currentHeight + 1;
                }
            }
        }

        return waterHeights;
    }

    public List<Integer> listOfAroundBlockHeights(int x, int y) {
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

    public boolean isPartOfLake(int x, int y, Direction from) {
        if (x == 0 || y == 0 || x == blockHeights.length-1 || y == blockHeights[0].length-1) {
            return true;
        }

        int n = blockHeights[x-1][y];
        int e = blockHeights[x][y+1];
        int s = blockHeights[x+1][y];
        int w = blockHeights[x][y-1];
        int currentHeight = blockHeights[x][y];

        if (n == currentHeight && !from.equals(Direction.NORTH)) {
            return isPartOfLake(x - 1, y, Direction.SOUTH);
        } else if (e == currentHeight && !from.equals(Direction.EAST)) {
            return isPartOfLake(x, y + 1, Direction.WEST);
        } else if (s == currentHeight && !from.equals(Direction.SOUTH)) {
            return isPartOfLake(x + 1, y, Direction.NORTH);
        } else if (w == currentHeight && !from.equals(Direction.WEST)) {
            return isPartOfLake(x, y - 1, Direction.EAST);
        } else {
            return false;
        }
    }

    public int[][] getBlockHeights() {
        return blockHeights;
    }

    public void setBlockHeights(int[][] blockHeights) {
        this.blockHeights = blockHeights;
    }

    public static void main(String[] args) {
        /* first output
            0, 0, 0, 0,
            0, 2, 2, 0,
            0, 0, 0, 0
        */
        int[][] blockHeights = { {1, 2, 2, 3},
                                 {2, 1, 1, 4},
                                 {4, 5, 6, 2} };
        Task2 task2 = new Task2(blockHeights);
        int[][] waterMap = task2.waterHeightsByBlocks();
        printArray(waterMap);

        /* second output
            0, 0, 0, 0,
            0, 2, 2, 0,
            0, 0, 0, 0
        */
        blockHeights = new int[][] { {1, 2, 2, 3},
                                     {2, 1, 0, 4},
                                     {4, 5, 6, 2} };
        task2.setBlockHeights(blockHeights);
        waterMap = task2.waterHeightsByBlocks();
        printArray(waterMap);

        /* third output
            0, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 0, 0
        */
        blockHeights = new int[][] { {1, 2, 2, 3},
                                     {2, 2, 3, 4},
                                     {4, 5, 6, 2} };
        task2.setBlockHeights(blockHeights);
        waterMap = task2.waterHeightsByBlocks();
        printArray(waterMap);

        /* third output
            0, 0, 0, 0,
            0, 2, 0, 0,
            0, 0, 0, 0
        */
        blockHeights = new int[][] { {1, 3, 2, 3},
                                     {3, 2, 3, 4},
                                     {4, 5, 6, 2} };
        task2.setBlockHeights(blockHeights);
        waterMap = task2.waterHeightsByBlocks();
        printArray(waterMap);
    }

    public static void printArray(int[][] array) {
        StringBuilder output = new StringBuilder();
        for (int x = 0; x < array.length; ++x) {
            for (int y = 0; y < array[0].length; ++y) {
                output.append(array[x][y]).append(", ");
            }
            output.append("\n");
        }

        output.deleteCharAt(output.length()-3); //remove last comma
        System.out.println(output.toString());
    }
}
