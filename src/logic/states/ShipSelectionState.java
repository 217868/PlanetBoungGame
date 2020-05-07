package logic.states;

public class ShipSelectionState extends StateAdapter {
    @Override
    public IState selectShip(){
        return new SpaceTravelState();
    }
}
