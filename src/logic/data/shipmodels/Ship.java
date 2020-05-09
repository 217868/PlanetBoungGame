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
}
