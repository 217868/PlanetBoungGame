import logic.Dice;
import logic.data.Resource;
import logic.data.planetmodels.*;
import logic.data.shipmodels.ResourceType;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Alien alien = new Alien(AlienType.BLACK, 0, 0);
        System.out.println(alien.getAttackDeathStatistic().getDeaths().toString());
    }
}
