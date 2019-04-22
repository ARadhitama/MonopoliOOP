import java.util.Random;

public class Dice {
    private int nth;
    private Random randomGen = new Random();
    private int sides;
    private int value;
    private MonopoliOOPUI mainPage;

    public Dice(int nth, MonopoliOOPUI mainPage) {
        this.nth = nth;
        this.sides = 6;
        this.value = randomGen.nextInt(this.sides) + 1;
        this.mainPage = mainPage;
    }

    public int getValue() {
        return this.value;
    }

    public int roll() {
        this.value = randomGen.nextInt(this.sides) + 1;


        if (this.nth == 1) {
            this.mainPage.setVisibleDice11(false);
            this.mainPage.setVisibleDice12(false);
            this.mainPage.setVisibleDice13(false);
            this.mainPage.setVisibleDice14(false);
            this.mainPage.setVisibleDice15(false);
            this.mainPage.setVisibleDice16(false);
            if (this.value == 1) {
                this.mainPage.setVisibleDice11(true);
            } else if (this.value == 2) {
                this.mainPage.setVisibleDice12(true);
            } else if (this.value == 3) {
                this.mainPage.setVisibleDice13(true);
            } else if (this.value == 4) {
                this.mainPage.setVisibleDice14(true);
            } else if (this.value == 5) {
                this.mainPage.setVisibleDice15(true);
            } else if (this.value == 6) {
                this.mainPage.setVisibleDice16(true);
            }
        } else {
            this.mainPage.setVisibleDice21(false);
            this.mainPage.setVisibleDice22(false);
            this.mainPage.setVisibleDice23(false);
            this.mainPage.setVisibleDice24(false);
            this.mainPage.setVisibleDice25(false);
            this.mainPage.setVisibleDice26(false);
            if (this.value == 1) {
                this.mainPage.setVisibleDice21(true);
            } else if (this.value == 2) {
                this.mainPage.setVisibleDice22(true);
            } else if (this.value == 3) {
                this.mainPage.setVisibleDice23(true);
            } else if (this.value == 4) {
                this.mainPage.setVisibleDice24(true);
            } else if (this.value == 5) {
                this.mainPage.setVisibleDice25(true);
            } else if (this.value == 6) {
                this.mainPage.setVisibleDice26(true);
            }
        }

        return this.value;
    }
}