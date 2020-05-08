package logic.states;

import logic.GameData;

public class AtPlanetState extends StateAdapter {
    public AtPlanetState(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState goToSpaceTravel(){
        return new SpaceTravelState(getGameData());
    }
}
