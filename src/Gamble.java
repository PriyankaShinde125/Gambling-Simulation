public class Gamble {
    public static final int WIN_BET = 1;
    int stake;
    int bet;

    public Gamble() {
        stake = 100;
        bet = 1;
    }

    public void makeABet() {
        int betResult = (int) Math.floor(Math.random() * 2);
        System.out.println(betResult == WIN_BET ? "You win $" + bet : "You loss $" + bet);
    }
}
