import logic.Dice;
import logic.GameLogic;
import logic.PlanetExplorationLogic;
import logic.data.Resource;
import logic.data.planetmodels.*;
import logic.data.shipmodels.ResourceType;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic();
        gameLogic.chooseShip(true);
        System.out.print(gameLogic.getGameData().getShip());
    }
}
