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

    public void travelCost(){
        int probability = Dice.throwd8();
        if(probability == 1) {
            getGameData().getShip().getFuelSystem().spendFuel(3);
            getGameData().getShip().getShieldSystem().spendShield(2);
            if(getGameData().getShip().getCrewAmount() < 6){
                getGameData().getShip().getFuelSystem().spendFuel(1);
                getGameData().getShip().getShieldSystem().spendShield(2);
            }
        }
        else getGameData().getShip().getFuelSystem().spendFuel(1);

    }


}
