package logic.states;

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
        // Probably planet argument needed?
        return new AtPlanetState(getGameData());
    }

    @Override
    public IState goToNextRegion() {
        if (getGameData().getShip().getAmountOfArtifacts() >= 5) return new GameWonState(getGameData());

        Event event = new Event();
        event.runSpecificEvent(getGameData().getShip());

        if (getGameData().getShip().getFuelSystem().getFuelAmount() == 0 || getGameData().getShip().getCrewAmount() == 0) return new GameLostState(getGameData());

        getGameData().generateNextPlanet();
        return new SpaceTravelState(getGameData());
    }


}
