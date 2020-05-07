package logic.states;

public class GameLostState extends StateAdapter{
    @Override
    public IState playAgain(){
        return new ShipSelectionState();
    }
}
