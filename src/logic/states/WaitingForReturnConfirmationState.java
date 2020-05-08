package logic.states;

import logic.GameData;

public class WaitingForReturnConfirmationState extends StateAdapter {
    public WaitingForReturnConfirmationState(GameData gameData) {
        super(gameData);
    }

    @Override
    public IState acceptReturn() {
        return goToSpaceTravel();
    }

    @Override
    public IState declineReturn() {
        return new AtPlanetState(getGameData());
    }
}
