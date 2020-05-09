package logic.data.shipmodels;

public class ShieldSystem implements ShipSystem {
    private boolean isAvailable;
    private boolean isMilitary;
    private int maxShieldsAmount;
    private int shieldsAmount;

    public ShieldSystem(boolean isMilitary) {
        this.isMilitary = isMilitary;

        if (isMilitary) maxShieldsAmount = 18;
        else maxShieldsAmount = 9;

        shieldsAmount = maxShieldsAmount;
    }

    public void replenishArmor() {
        shieldsAmount = maxShieldsAmount;
    }

    public int getMaxShieldsAmount() {
        return maxShieldsAmount;
    }

    public int getShieldsAmount() {
        return shieldsAmount;
    }

    public void spendShield(int amount){
        shieldsAmount -= amount;
    }
}
