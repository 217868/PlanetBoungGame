package logic.data.shipmodels;

public class FuelSystem implements ShipSystem {
    private boolean isAvailable;
    private boolean isMilitary;
    private int maxFuelAmount;
    private int fuelAmount;

    public FuelSystem(boolean isMilitary) {
        this.isMilitary = isMilitary;

        if (isMilitary) maxFuelAmount = 27;
        else maxFuelAmount = 45;

        fuelAmount = maxFuelAmount;
    }
}
