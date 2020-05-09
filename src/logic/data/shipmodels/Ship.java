package logic.data.shipmodels;


public class Ship {
    private FuelSystem fuelSystem;
    private CargoSystem cargoSystem;
    private WeaponSystem weaponSystem;
    private ShieldSystem shieldSystem;

    private Drone drone;

    private int amountOfArtifacts;

    private int crewAmount;


    public Ship(FuelSystem fuelSystem, CargoSystem cargoSystem, WeaponSystem weaponSystem, ShieldSystem shieldSystem) {
        this.fuelSystem = fuelSystem;
        this.cargoSystem = cargoSystem;
        this.weaponSystem = weaponSystem;
        this.shieldSystem = shieldSystem;

        amountOfArtifacts = 0;
        crewAmount = 6;

        drone = new Drone();
    }

    public FuelSystem getFuelSystem() {
        return fuelSystem;
    }

    public CargoSystem getCargoSystem() {
        return cargoSystem;
    }

    public WeaponSystem getWeaponSystem() {
        return weaponSystem;
    }

    public ShieldSystem getShieldSystem() {
        return shieldSystem;
    }

    public int getAmountOfArtifacts() {
        return amountOfArtifacts;
    }

    public int getCrewAmount() {
        return crewAmount;
    }

    public void killOneCrewMember() {
        crewAmount--;
        switch (crewAmount){
            case 5:
                getCargoSystem().setAvailable(false);
                break;
            case 4:
                getWeaponSystem().setAvailable(false);
                break;
            case 3:
                getShieldSystem().setAvailable(false);
        }
        if (crewAmount == 0) endGame();
    }

    public void hireOneCrewMember() {
        if (crewAmount < 6) crewAmount++;
    }

    public void endGame() {
        //TODO: implement endgame
    }

    public Drone getDrone() {
        return drone;
    }

    public void addArtifact(){
        this.amountOfArtifacts++;
    }

    public void produceShield(int amount){
        //TODO exception
        if(!getCargoSystem().isAvailable()) return;
        if(getCargoSystem().getBlackResourceAmount() < amount || getCargoSystem().getGreenResourceAmount() < amount || getCargoSystem().getBlueResourceAmount() < amount) return;
        if(getShieldSystem().getShieldsAmount() + amount <= getShieldSystem().getMaxShieldsAmount()){
            getCargoSystem().payBlackResource(amount);
            getCargoSystem().payGreenResource(amount);
            getCargoSystem().payBlueResource(amount);
            getShieldSystem().addShield(amount);
        }
    }

    public void produceAmmo(int amount){
        //TODO exception
        if(!getCargoSystem().isAvailable()) return;
        if(getCargoSystem().getBlackResourceAmount() < amount || getCargoSystem().getBlueResourceAmount() < amount) return;
        if(getWeaponSystem().getWeapons() + amount <= getWeaponSystem().getMaxWeapons()){
            getCargoSystem().payBlackResource(amount);
            getCargoSystem().payBlueResource(amount);
            getWeaponSystem().addAmmo(amount);
        }
    }

    public void produceFuel(int amount){
        //TODO exception
        if(!getCargoSystem().isAvailable()) return;
        if(getCargoSystem().getBlackResourceAmount() < amount || getCargoSystem().getRedResourceAmount() < amount || getCargoSystem().getGreenResourceAmount() < amount ) return;
        if(getFuelSystem().getFuelAmount() + amount <= getFuelSystem().getMaxFuelAmount()){
            getCargoSystem().payBlackResource(amount);
            getCargoSystem().payRedResource(amount);
            getCargoSystem().payGreenResource(amount);
            getFuelSystem().addFuel(amount);
        }
    }

    public boolean isCargoOfficerAvailable(){
        if(crewAmount > 5) return true;
        return false;
    }

    public boolean isWeaponOfficerAvailable(){
        if(crewAmount > 4)return true;
        return false;
    }

    public boolean isShieldOfficerAvailable(){
        if(crewAmount > 3) return true;
        return false;
    }

    public boolean isExplorationOfficerAvailable(){
        if(crewAmount > 2) return  true;
        return false;
    }

    public boolean isNavigationOfficerAvailable(){
        if(crewAmount > 1) return true;
        return false;
    }

    public boolean isCaptainAvailable(){
        if(crewAmount > 0) return true;
        return false;
    }


}
