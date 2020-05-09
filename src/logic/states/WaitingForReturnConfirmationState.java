package logic.states;

import logic.Dice;
import logic.GameData;
import logic.data.shipmodels.ResourceType;

public class WaitingForReturnConfirmationState extends StateAdapter {
    private IState previousState;

    public WaitingForReturnConfirmationState(GameData gameData, IState previousState) {
        super(gameData);
        this.previousState = previousState;
    }

    @Override
    public IState acceptReturn() {
        getGameData().getShip().getFuelSystem().spendFuel(1);
        if(!getGameData().getExLogic().isResourceInDrone()) return new SpaceTravelState(getGameData());

        if(getGameData().getExLogic().getResource().getResourceType() != ResourceType.PINK){
                int amountOfResource = Dice.throwd6();
            switch (getGameData().getExLogic().getResource().getResourceType()) {
                case BLACK:
                    getGameData().getShip().getCargoSystem().addBlackResource(amountOfResource);
                    break;
                case BLUE:
                    getGameData().getShip().getCargoSystem().addBlueResource(amountOfResource);
                    break;
                case GREEN:
                    getGameData().getShip().getCargoSystem().addGreenResource(amountOfResource);
                    break;
                case RED:
                    getGameData().getShip().getCargoSystem().addRedResource(amountOfResource);
                    break;
            }
            getGameData().getPlanet().deleteRandomResource();
        }
        else getGameData().getShip().addArtifact();
        return new SpaceTravelState(getGameData());
    }

    @Override
    public IState declineReturn() {
        return previousState;
    }

}
