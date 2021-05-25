import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 {
    private int[][] blockHeights;
    private int width;
    private int length;
    private RiverChecker riverChecker;

    public Task2(int[][] blockHeights) {
        this.blockHeights = blockHeights;
        this.width = blockHeights.length;
        this.length = blockHeights[0].length;
        this.riverChecker = new RiverChecker(blockHeights);
    }

    public int[][] waterHeightsByBlocks() {
        int[][] waterHeights = new int[width][length];
        int[][] oldBlockHeights;

        do {
            oldBlockHeights = deepCopy(blockHeights);
            int newHeight;

            for (int x = 1; x < width - 1; ++x) {
                for (int y = 1; y < length - 1; ++y) {
                    int currentHeight = blockHeights[x][y];
                    List<Integer> listOfAroundBlockHeights = getAroundBlockHeights(new Coordinate(x, y));

                    int firstMin = listOfAroundBlockHeights.get(0);
                    int secondMin;
                    if (listOfAroundBlockHeights.size() > 1) {
                        secondMin = listOfAroundBlockHeights.get(1);
                    } else {
                        secondMin = firstMin;
                    }

                    boolean isHole = currentHeight == 0 || firstMin == 0;
                    boolean isFlatland = currentHeight == firstMin;
                    boolean isPeak = currentHeight > firstMin;
                    if (isHole) {
                        waterHeights[x][y] += 0;
                    } else if (isFlatland) {
                        //A river is a state when the flatland contains a way to the outer border. This leads water is falls down.
                        boolean isRiver = riverChecker.isPartOfRiver(new Coordinate(x, y), Direction.NONE);
                        if (isRiver) {
                            waterHeights[x][y] += 0;
                        } else {
                            newHeight = secondMin - currentHeight + blockHeights[x][y];
                            waterHeights[x][y] = newHeight;
                            blockHeights[x][y] = newHeight;
                        }
                    } else if (isPeak) {
                        waterHeights[x][y] += 0;
                    } else {
                        newHeight = firstMin - currentHeight + blockHeights[x][y];
                        waterHeights[x][y] = newHeight;
                        blockHeights[x][y] = newHeight;
                    }

                    riverChecker.resetCounter();
                }
            }
        } while (!isMapsEquals(oldBlockHeights, blockHeights));

        return waterHeights;
    }

    private List<Integer> getAroundBlockHeights(Coordinate coordinate) {
        /*
                    n = north
                    s = south
                    w = west
                    e = east
        */
        int x = coordinate.getX();
        int y = coordinate.getY();

        int n = blockHeights[x-1][y];
        int e = blockHeights[x][y+1];
        int s = blockHeights[x+1][y];
        int w = blockHeights[x][y-1];

        List<Integer> aroundBlockHeights = new ArrayList<>(Arrays.asList(n, e, s, w));
        return aroundBlockHeights.stream()
                .distinct().sorted()
                .collect(Collectors.toList());
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
        this.width = blockHeights.length;
        this.length = blockHeights[0].length;
        this.riverChecker.setBlockHeights(blockHeights);
    }

    public static void main(String[] args) {
        Tests.testCases();
    }
}
