package logic.data.shipmodels;

public class CargoSystem implements ShipSystem {
    private boolean isAvailable;
    private boolean isMilitary;
    private int currentMaxResourceAmount;

    private int blackResourceAmount;
    private int redResourceAmount;
    private int blueResourceAmount;
    private int greenResourceAmount;

    public CargoSystem(boolean isMilitary) {
        this.isMilitary = isMilitary;

        currentMaxResourceAmount = 6;

        blackResourceAmount = 0;
        redResourceAmount = 0;
        blueResourceAmount = 0;
        greenResourceAmount = 0;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCurrentMaxResourceAmount() {
        return currentMaxResourceAmount;
    }

    public int getBlackResourceAmount() {
        return blackResourceAmount;
    }

    public int getRedResourceAmount() {
        return redResourceAmount;
    }

    public int getBlueResourceAmount() {
        return blueResourceAmount;
    }

    public int getGreenResourceAmount() {
        return greenResourceAmount;
    }

    public void payBlackResource(int amount) {
        if (blackResourceAmount - amount < 0) return; //TODO: exception if insufficient amount of resources
        blackResourceAmount -= amount;
    }

    public void payRedResource(int amount) {
        if (redResourceAmount - amount < 0) return; //TODO: exception if insufficient amount of resources
        redResourceAmount -= amount;
    }

    public void payBlueResource(int amount) {
        if (blueResourceAmount - amount < 0) return; //TODO: exception if insufficient amount of resources
        blueResourceAmount -= amount;
    }

    public void payGreenResource(int amount) {
        if (greenResourceAmount - amount < 0) return; //TODO: exception if insufficient amount of resources
        greenResourceAmount -= amount;
    }

    public void payAllResources (int amount) {
        if (
                greenResourceAmount - amount < 0 ||
                blueResourceAmount - amount < 0 ||
                redResourceAmount - amount < 0 ||
                blackResourceAmount - amount < 0
        ) return; //TODO: exception if insufficient amount of resources
        greenResourceAmount -= amount;
        blueResourceAmount -= amount;
        redResourceAmount -= amount;
        blackResourceAmount -= amount;

    }

    public void upgradeCargoSystem() {
        if (isMilitary && currentMaxResourceAmount == 12) return;
        if (!isMilitary && currentMaxResourceAmount == 24) return;
        //TODO: add proper exceptions if requirements not met

        currentMaxResourceAmount *= 2;
    }
}
