import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Gamble {
    public static final int WIN_BET = 1;
    public static final int MAX_DAYS = 20;
    public final int MAX_STAKE;
    public final int MIN_STAKE;
    public static final String DAYS_WON_KEY = "Won";
    public static final String DAYS_LOST_KEY = "Lost";
    int stake;
    int bet;
    int monthlyWonDays = 0;
    int monthlyLostDays = 0;
    int totalWonAmount = 0;
    int totalLostAmount = 0;
    HashMap<String, ArrayList<Integer>> daysWonLost;

    public Gamble() {
        stake = 100;
        bet = 1;
        MIN_STAKE = stake * 50 / 100;
        MAX_STAKE = stake + MIN_STAKE;
        daysWonLost = new HashMap<>();
        daysWonLost.put(DAYS_WON_KEY, new ArrayList<>());
        daysWonLost.put(DAYS_LOST_KEY, new ArrayList<>());
    }

    public void makeABet() {
        int betResult = (int) Math.floor(Math.random() * 2);
        if (betResult == WIN_BET)
            stake += 1;
        else
            stake -= 1;
    }

    public int playTillMaxStake() {
        while (stake < MAX_STAKE && stake > MIN_STAKE) {
            makeABet();
        }
        return stake;
    }

    public void playMaxDays() {
        int day = 0;
        int currentDayStake;
        while (day < MAX_DAYS) {
            currentDayStake = playTillMaxStake();
            day++;
            stake = 100;
            dailyWinLoss(day, currentDayStake);
        }
        System.out.println("Total won days in month = " + monthlyWonDays + " these are as follows : ");
        System.out.println(daysWonLost.get("Won"));
        System.out.println("Total lost days in month = " + monthlyLostDays + " these are as follows :");
        System.out.println(daysWonLost.get("Lost"));
        int wonByAmount = totalWonAmount - totalLostAmount;
        if (wonByAmount > 0)
            System.out.println("You won by amount = " + wonByAmount);
        else
            System.out.println("Ooh.... you lost all amount");
    }

    private void dailyWinLoss(int day, int currentDayStake) {
        int wonOrLostStake = Math.abs(currentDayStake - stake);
        if (currentDayStake == MAX_STAKE) {
            daysWonLost.get(DAYS_WON_KEY).add(day);
            monthlyWonDays++;
            totalWonAmount += wonOrLostStake;
        } else {
            daysWonLost.get(DAYS_LOST_KEY).add(day);
            monthlyLostDays++;
            totalLostAmount += wonOrLostStake;
        }
    }
}
