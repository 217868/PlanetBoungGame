package logic;

import logic.data.planetmodels.Planet;
import logic.data.planetmodels.PlanetFactory;
import logic.data.shipmodels.Ship;

public class GameData {

    private Ship ship;
    private Planet planet;

    GameData() {
        ship = null;
        generateNextPlanet();
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void generateNextPlanet() {
        planet = PlanetFactory.createRandomPlanet();
    }
}
