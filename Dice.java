import java.util.Random;

public class Dice {
    private Random randomGen = new Random();
    // private int count;
    private int sides;
    private int value;

    public Dice() {
        this.sides = 6;
    }

    public int getValue() {
        return this.value;
    }

    public int roll() {
        this.value = randomGen.nextInt(this.sides) + 1;
        return this.value;
    }
}