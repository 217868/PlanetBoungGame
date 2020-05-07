package logic.states;

public class SpaceTravelState extends StateAdapter {
    @Override
    public IState goToSpaceStation(){
        // Planet argument needed with existing space station, if it exists, game is in this state
        return new AtSpaceStationState();
    }

    @Override
    public IState goToPlanet(){
        // Probably planet argument needed?
        return new AtPlanetState();
    }

    @Override
    public IState gameStatusCheck(int artifactNumber, int fuelAmount, int crewMembersAmount){
        if(artifactNumber >= 5 && fuelAmount > 0 && crewMembersAmount > 0) return new GameWonState();
        else if(artifactNumber < 5 && fuelAmount > 0 && crewMembersAmount > 0) return this;
        else return new GameLostState();
    }
}
