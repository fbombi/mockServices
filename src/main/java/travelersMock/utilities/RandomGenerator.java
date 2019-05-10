package travelersMock.utilities;


public class RandomGenerator {
    public static int generateRandomNumber(int range, int from) {
        return ((int) (Math.random() * range) + from);
    }


}

