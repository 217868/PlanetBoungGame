package logic;

import logic.data.planetmodels.Planet;
import logic.data.planetmodels.PlanetFactory;
import logic.data.shipmodels.Ship;

public class GameData {

    private Ship ship;
    private Planet planet;
    private PlanetExplorationLogic exLogic;

    GameData() {
        ship = null;
        generateNextPlanet();
        exLogic = null;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public PlanetExplorationLogic getExLogic() {
        return exLogic;
    }

    public void setExLogic(PlanetExplorationLogic exLogic) {
        this.exLogic = exLogic;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void generateNextPlanet() {
        planet = PlanetFactory.createRandomPlanet();
    }
}
