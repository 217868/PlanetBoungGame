package logic.data.shipmodels;

public class WeaponSystem implements ShipSystem {
    private boolean isAvailable;
    private boolean isMilitary;
    private int maxWeapons;
    private int weapons;

    public WeaponSystem(boolean isMilitary) {
        this.isMilitary = isMilitary;

        maxWeapons = 9;
        weapons = 9;
    }

    public void upgradeWeaponSystem() {
        if (isMilitary && maxWeapons == 18) return;
        if (!isMilitary) return;

        maxWeapons *= 2;
    }
}
