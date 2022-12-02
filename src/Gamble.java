import java.util.*;

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
    int totalWonLostAmount = 0;
    int luckiestDay;
    int unLuckiestDay;
    HashMap<String, ArrayList<Integer>> daysWonLost;
    HashMap<Integer, Integer> dayAndAmountWon;

    public Gamble() {
        stake = 100;
        bet = 1;
        MIN_STAKE = stake * 50 / 100;
        MAX_STAKE = stake + MIN_STAKE;
        daysWonLost = new HashMap<>();
        daysWonLost.put(DAYS_WON_KEY, new ArrayList<>());
        daysWonLost.put(DAYS_LOST_KEY, new ArrayList<>());
        dayAndAmountWon = new HashMap<>();
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
        printMonthResult();
    }

    private void printMonthResult() {
        System.out.println("Total won days in month = " + monthlyWonDays + "\nWon days are as follows : ");
        System.out.println(daysWonLost.get(DAYS_WON_KEY));
        System.out.println("Total lost days in month = " + monthlyLostDays + "\nLost days are as follows :");
        System.out.println(daysWonLost.get(DAYS_LOST_KEY));

        if (totalWonLostAmount > 0) {
            System.out.println("You won by amount = " + totalWonLostAmount);
            luckiestDay = 1;
            unLuckiestDay = 1;
            int maxWon = dayAndAmountWon.get(1);
            int maxLost = maxWon;

            for (Map.Entry<Integer, Integer> entry : dayAndAmountWon.entrySet()) {
                if (entry.getValue() > maxWon) {
                    maxWon = entry.getValue();
                    luckiestDay = entry.getKey();
                }
                if (entry.getValue() < maxLost) {
                    maxLost = entry.getValue();
                    unLuckiestDay = entry.getKey();
                }
                System.out.println(entry.getKey() +" -> "+ entry.getValue());
            }

            System.out.println("Luckiest day = "+luckiestDay);
            System.out.println("Unluckiest day = "+unLuckiestDay);

        } else
            System.out.println("Ooh.... you lost all amount");
    }

    private void dailyWinLoss(int day, int currentDayStake) {
        int wonOrLostStake = Math.abs(currentDayStake - stake);
        if (currentDayStake == MAX_STAKE) {
            daysWonLost.get(DAYS_WON_KEY).add(day);
            monthlyWonDays++;
            totalWonLostAmount += wonOrLostStake;
            dayAndAmountWon.put(day, totalWonLostAmount);
        } else {
            daysWonLost.get(DAYS_LOST_KEY).add(day);
            monthlyLostDays++;
            totalWonLostAmount -= wonOrLostStake;
            dayAndAmountWon.put(day, totalWonLostAmount);
        }
    }
}
