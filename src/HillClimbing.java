import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

public class HillClimbing {

    private static Function<int[], Integer> distance = (int[] parameters) -> Math.abs(parameters[0] - 2) + Math.abs(parameters[0] * parameters[1] - 46);

    public static int[] hillClimbing(int[] current) {
        int lowestDistance = Integer.MAX_VALUE;
        int[] bestNeighbor;
        int circles = 0;

        while (circles <= 2) {
            if (distance.apply(current) < lowestDistance) {
                lowestDistance = distance.apply(current);
            }
            if (distance.apply(current) == lowestDistance) {
                circles++;
            }
            bestNeighbor = bestNeighbor(getNeighbors(current));
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

    private static boolean circle(int[] current, int[] bestNeighbor) {
        return Arrays.deepEquals(new int[][]{current}, new int[][]{bestNeighbor});
    }


    public static void main(String[] args) {
        Random random = new Random();
        int[] bestResult = IntStream.range(0, 100000)
                .boxed()
                .map(i -> new int[]{random.nextInt(1000), random.nextInt(1000)})
                .map(HillClimbing::hillClimbing)
                .min(Comparator.comparing(result -> distance.apply(result)))
                .get();
        System.out.println("bestResult = " + Arrays.deepToString(new int[][]{bestResult}));
        System.out.println("distance = " + distance.apply(bestResult));
    }
}
