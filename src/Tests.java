public class Tests {
    public static void testCases() {
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
            0, 0, 0, 0,
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
            0, 0, 0, 0,
            0, 0, 0, 0
        */
        blockHeights = new int[][] { {1, 2, 2, 3},
                                     {2, 2, 3, 4},
                                     {4, 5, 6, 2} };
        task2.setBlockHeights(blockHeights);
        waterMap = task2.waterHeightsByBlocks();
        printArray(waterMap);

        /* fourth output
            0, 0, 0, 0,
            0, 3, 0, 0,
            0, 0, 0, 0
        */
        blockHeights = new int[][] { {1, 3, 2, 3},
                                     {3, 2, 3, 4},
                                     {4, 5, 6, 2} };
        task2.setBlockHeights(blockHeights);
        waterMap = task2.waterHeightsByBlocks();
        printArray(waterMap);

        /* fifth output
            0, 0, 0, 0, 0,
            0, 2, 2, 2, 0,
            0, 0, 0, 0, 0
        */
        blockHeights = new int[][] { {1, 2, 2, 3, 0},
                                     {2, 1, 1, 1, 3},
                                     {4, 5, 6, 3, 1} };
        task2.setBlockHeights(blockHeights);
        waterMap = task2.waterHeightsByBlocks();
        printArray(waterMap);

        /* sixth output
            0, 0, 0, 0,
            0, 3, 3, 0,
            0, 0, 3, 0,
            0, 0, 0, 0
        */
        blockHeights = new int[][] { {2, 3, 4, 5},
                                     {3, 1, 2, 4},
                                     {4, 3, 1, 4},
                                     {5, 5, 4, 3} };
        task2.setBlockHeights(blockHeights);
        waterMap = task2.waterHeightsByBlocks();
        printArray(waterMap);

        /* seventh output
            0, 0, 0, 0,
            0, 2, 0, 0,
            0, 0, 2, 0,
            0, 0, 0, 0
        */
        blockHeights = new int[][] { {2, 3, 4, 5},
                                     {2, 1, 2, 4},
                                     {4, 3, 1, 4},
                                     {5, 5, 4, 3} };
        task2.setBlockHeights(blockHeights);
        waterMap = task2.waterHeightsByBlocks();
        printArray(waterMap);

        /* eighth output
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 2, 0,
            0, 0, 0, 0
        */
        blockHeights = new int[][] { {2, 3, 4, 5},
                                     {1, 1, 2, 4},
                                     {4, 3, 1, 4},
                                     {5, 5, 4, 3} };
        task2.setBlockHeights(blockHeights);
        waterMap = task2.waterHeightsByBlocks();
        printArray(waterMap);

        /* ninth output
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0
        */
        blockHeights = new int[][] { {2, 3, 4, 5},
                                     {3, 1, 0, 4},
                                     {4, 3, 1, 4},
                                     {5, 5, 4, 3} };
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
