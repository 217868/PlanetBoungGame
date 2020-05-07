package logic.states;

public interface IState {
    IState selectShip();
    IState goToSpaceStation();
    IState goToSpaceTravel();
    IState goToPlanet();
    IState gameStatusCheck(int artifactNumber, int fuelAmount, int crewMembersAmount);
    IState repeat();
    IState playAgain();
}
