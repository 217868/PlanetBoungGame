package logic;

import logic.data.shipmodels.MilitaryShip;
import logic.data.shipmodels.Ship;
import logic.states.IState;
import logic.states.ShipSelectionState;

public class GameLogic {

    private IState state;

    private GameData gameData;

    public GameLogic() {
        gameData = new GameData();
        state = new ShipSelectionState(gameData);

    }

    public IState getState() {
        return this.state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public void chooseShip(boolean isMilitary) {
        this.setState(this.state.selectShip(isMilitary));
    }

    public boolean isSpaceStationAvailable() {
        return gameData.getPlanet().hasSpaceStation();
    }

    public void goToSpaceStation() {
        this.setState(this.state.goToSpaceStation());
    }
    

    public GameData getGameData() {
        return gameData;
    }


}
