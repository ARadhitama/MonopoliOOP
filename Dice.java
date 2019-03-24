import java.util.Random;

public class Dice {
    Random randomGen = new Random();
    private int count = 0;
    private int sides;

    public Dice (int numSides) {
        this.sides = numSides;
    }

    public int roll () {
        int result = randomGen.nextInt(sides) + 1;
        return result;
    }
}