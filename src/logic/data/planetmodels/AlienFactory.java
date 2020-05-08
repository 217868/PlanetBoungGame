package logic.data.planetmodels;

import logic.Dice;

import java.util.concurrent.ThreadLocalRandom;

public class AlienFactory {

    public static Alien createAlien(AlienType alienType, int x, int y) {
        return new Alien(alienType, x, y);
    }

    public static Alien createRandomAlien(int x, int y) {
        return new Alien(AlienType.randomAlienType(), x, y);
    }

    public static Alien createAlienWithRandomCoordinates(AlienType alienType) {
        int x = ThreadLocalRandom.current().nextInt(0, 6);
        int y = ThreadLocalRandom.current().nextInt(0, 6);

        return new Alien(alienType, x, y);
    }

    public static Alien createRandomAlienWithRandomCoordinates(){
        return createAlienWithRandomCoordinates(AlienType.randomAlienType());
    }


}
