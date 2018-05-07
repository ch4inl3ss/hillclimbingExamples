import java.util.function.Function;

public class SingleIntegerHillClimbing {

    private static Function<Integer, Integer> distance
            = x -> Math.abs(x * 2 - 46);

    public static int simpleIntegerHillClimbing() {
        int bestValue = Integer.MAX_VALUE;
        int currentValue = 0;

        while (distance.apply(currentValue) != 0) {
            if (distance.apply(currentValue) < bestValue) {
                bestValue = distance.apply(currentValue);
            }
            if (distance.apply(currentValue + 1) < bestValue) {
                currentValue += 1;
            }
            if (distance.apply(currentValue - 1) < bestValue) {
                currentValue -= 1;
            }
        }

        return currentValue;
    }

    public static void main(String[] args) {
        System.out.println(simpleIntegerHillClimbing());
    }
}
