package logic.states;

public class StateAdapter implements IState {
    @Override
    public IState selectShip() {
        return this;
    }

    @Override
    public IState goToSpaceStation() {
        return this;
    }

    @Override
    public IState goToSpaceTravel() {
        return this;
    }

    @Override
    public IState goToPlanet() {
        return this;
    }

    @Override
    public IState gameStatusCheck(int artifactNumber, int fuelAmount, int crewMembersAmount) {
        return this;
    }

    @Override
    public IState repeat() {
        return this;
    }

    @Override
    public IState playAgain() {
        return this;
    }
}
