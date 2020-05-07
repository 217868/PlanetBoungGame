package logic.states;

public class AtPlanetState extends StateAdapter {
    @Override
    public IState goToSpaceTravel(){
        return new SpaceTravelState();
    }
}
