package logic.states;

public class AtSpaceStationState extends StateAdapter {
    @Override
    public IState goToSpaceTravel(){
        return new SpaceTravelState();
    }
}
