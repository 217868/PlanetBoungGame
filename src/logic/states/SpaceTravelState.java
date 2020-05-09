package logic.states;

import logic.Dice;
import logic.GameData;
import logic.data.Event;
import logic.data.shipmodels.Ship;

public class SpaceTravelState extends StateAdapter {
    public SpaceTravelState(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState goToSpaceStation(){
        if(!getGameData().getPlanet().hasSpaceStation() || getGameData().getPlanet().getSpaceStation().isAlreadyVisited()) return new SpaceTravelState(getGameData());

        getGameData().getPlanet().getSpaceStation().dockShip(getGameData().getShip());
        return new AtSpaceStationState(getGameData());
    }

    @Override
    public IState goToPlanet(){
        if(!getGameData().getPlanet().hasResources()) return this;
        if(!getGameData().getShip().isExplorationOfficerAvailable()) return this;
            return new AtPlanetState(getGameData());
    }

    @Override
    public IState goToNextRegion() {
        travelCost();
        if (getGameData().getShip().getAmountOfArtifacts() >= 5) return new GameWonState(getGameData());

        Event event = new Event();
        event.runSpecificEvent(getGameData().getShip());

        if (getGameData().getShip().getFuelSystem().getFuelAmount() == 0 || getGameData().getShip().getCrewAmount() == 0) return new GameLostState(getGameData());

        getGameData().generateNextPlanet();
        return new SpaceTravelState(getGameData());
    }

    @Override
    public IState produce(String type, int amount){
        if(!getGameData().getShip().getCargoSystem().isAvailable()) return this;

        switch(type) {
            case "ammo":
                getGameData().getShip().getResourceConverter().produceAmmo(amount);
            case "fuel":
                getGameData().getShip().getResourceConverter().produceFuel(amount);
            case "shield":
                getGameData().getShip().getResourceConverter().produceShield(amount);
        }

        return this;
    }

    public void travelCost(){
        int probability = Dice.throwd8();
        if(probability == 1) {
            getGameData().getShip().getFuelSystem().spendFuel(3);
            getGameData().getShip().getShieldSystem().spendShield(2);
            if(!getGameData().getShip().isShieldOfficerAvailable()){
                getGameData().getShip().getFuelSystem().spendFuel(1);
                getGameData().getShip().getShieldSystem().spendShield(2);
            }
        }
        else getGameData().getShip().getFuelSystem().spendFuel(1);

    }


}
