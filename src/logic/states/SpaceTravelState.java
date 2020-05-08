package logic.states;

import logic.GameData;
import logic.data.shipmodels.Ship;

public class SpaceTravelState extends StateAdapter {
    public SpaceTravelState(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState goToSpaceStation(){
        if(!getGameData().getPlanet().hasSpaceStation()) return new SpaceTravelState(getGameData());

        getGameData().getPlanet().getSpaceStation().dockShip(getGameData().getShip());
        return new AtSpaceStationState(getGameData());
    }

    @Override
    public IState goToPlanet(){
        // Probably planet argument needed?
        return new AtPlanetState(getGameData());
    }

    @Override
    public IState gameStatusCheck(int artifactNumber, int fuelAmount, int crewMembersAmount){
        if(artifactNumber >= 5 && fuelAmount > 0 && crewMembersAmount > 0) return new GameWonState(getGameData());
        else if(artifactNumber < 5 && fuelAmount > 0 && crewMembersAmount > 0) return this;
        else return new GameLostState(getGameData());
    }
}
