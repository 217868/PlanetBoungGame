package logic.states;

public class GameWonState extends StateAdapter {
    @Override
    public IState playAgain(){
        return new ShipSelectionState();
    }
}
