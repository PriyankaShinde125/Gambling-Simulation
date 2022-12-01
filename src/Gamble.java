import java.util.Stack;

public class Gamble {
    public static final int WIN_BET = 1;
    public final int MAX_STAKE;
    public final int MIN_STAKE;
    int stake;
    int bet;

    public Gamble() {
        stake = 100;
        bet = 1;
        MIN_STAKE = stake * 50 / 100;
        MAX_STAKE = stake + MIN_STAKE;
    }

    public void makeABet() {
        int betResult = (int) Math.floor(Math.random() * 2);
        System.out.println(betResult == WIN_BET ? "You won $" + bet : "You lost $" + bet);
        if (betResult == WIN_BET)
            stake += 1;
        else
            stake -= 1;
    }

    public void playTillMaxStake() {
        while (stake < MAX_STAKE && stake > MIN_STAKE) {
            makeABet();
            System.out.println("Stake = " + stake);
        }
    }
}
