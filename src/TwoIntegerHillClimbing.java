import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

public class TwoIntegerHillClimbing {

    private static Function<int[], Integer> distance = (int[] parameters) -> Math.abs(parameters[0] - 2) + Math.abs(parameters[0] * parameters[1] - 46);

    public static int[] twoIntegerHillClimbing() {
        int lowestDistance = Integer.MAX_VALUE;
        int[] current = {0, 0};

        while (distance.apply(current) != 0) {
            if (distance.apply(current) < lowestDistance) {
                lowestDistance = distance.apply(current);
            }
            int[] bestNeighbor =
                    bestNeighbor(getNeighbors(current));

            System.out.println("neighbors = " + Arrays.deepToString(getNeighbors(current)));
            System.out.println("bestNeighbor = " + Arrays.deepToString(new int[][]{bestNeighbor}));
            System.out.println("distance = " + distance.apply(bestNeighbor));

            if (distance.apply(bestNeighbor) < lowestDistance) {
                current = bestNeighbor;
            }
        }

        return current;
    }

    private static int[][] getNeighbors(int[] current) {
        int x = current[0], y = current[1];
        return new int[][]{{x + 1, y}, {x, y + 1}, {x + 1, y + 1}, {x - 1, y}, {x, y - 1}, {x - 1, y - 1}};
    }

    private static int[] bestNeighbor(int[][] neighbors) {
        return Arrays.stream(neighbors)
                .min(Comparator.comparing(neighbor -> distance.apply(neighbor)))
                .get();
    }

    public static void main(String[] args) {
        //This has an endless loop. No result.
        System.out.println(twoIntegerHillClimbing());
    }
}
