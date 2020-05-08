package logic;

import logic.data.Resource;
import logic.data.planetmodels.Alien;
import logic.data.planetmodels.AlienFactory;
import logic.data.planetmodels.Planet;
import logic.data.shipmodels.Drone;
import logic.data.shipmodels.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class PlanetExplorationLogic {
    Drone drone;
    Alien alien;
    Planet planet;
    int resourcePositionX;
    int resourcePositionY;
    Resource resource;

    public PlanetExplorationLogic(/*Ship ship, Resource resource*/){
       // this.drone = ship.getDrone();
       // this.resource = resource;
        this.drone = new Drone();
        initiateCoordinates();
    }

    private void initiateCoordinates(){
        int droneX = Dice.throwd6();
        int droneY = Dice.throwd6();
        this.drone.deployDrone(droneX, droneY);

        int alienX;
        int alienY;

        do{
            alienX = Dice.throwd6();
            alienY = Dice.throwd6();
        } while(!checkIfTheCoordinatesAreOutsideRange(alienX, alienY, droneX, droneY));

        this.alien = AlienFactory.createRandomAlien(alienX, alienY);

        int resourceX;
        int resourceY;

        do{
            resourceX = Dice.throwd6();
            resourceY = Dice.throwd6();
        } while (!checkIfTheCoordinatesAreOutsideRange(resourceX, resourceY, droneX, droneY) || !checkIfTheCoordinatesAreOutsideRange(resourceX, resourceY, alienX, alienY));

        this.resourcePositionX = resourceX;
        this.resourcePositionY = resourceY;
    }

    public boolean checkIfTheCoordinatesAreOutsideRange(int x, int y, int excX, int excY){
        if(x == excX && y == excY) return false;
        else if(x == excX - 1 && y == excY) return false;
        else if(x == excX + 1 && y == excY) return false;
        else if(x == excX && y == excY - 1) return false;
        else if(x == excX && y == excY + 1) return false;
        else return true;
    }

    public void moveAlien(){
        int xDifference = (int) Math.signum(drone.getX() - alien.getX());
        int yDifference = (int) Math.signum(drone.getY() - alien.getY());

        if (xDifference != 0 && yDifference != 0) {
            int rand = Dice.throwd2();
            if(rand == 1) alien.setX(alien.getX() + xDifference);
            else alien.setY(alien.getY() + yDifference);
        }
        else if(xDifference == 0) alien.setY(alien.getY() + yDifference);
        else if(yDifference == 0) alien.setX(alien.getX() + xDifference);

    }

    public void moveDrone(String dir) {
        switch(dir) {
            case "up":
                drone.moveUp();
                break;
            case "down":
                drone.moveDown();
                break;
            case "left":
                drone.moveLeft();
                break;
            case "right":
                drone.moveRight();
                break;
        }
        if (!checkIfTheCoordinatesAreOutsideRange(drone.getX(), drone.getY(), alien.getX(), alien.getY())) initiateFight(true);
        moveAlien();
        if (!checkIfTheCoordinatesAreOutsideRange(alien.getX(), alien.getY(), drone.getX(), drone.getY())) initiateFight(false);

    }

    private void initiateFight(boolean doesDroneBeginFight) {
        int thrownValue = Dice.throwd6();
        if(!doesDroneBeginFight){
            if(alien.isDroneAttacked(thrownValue)){
                System.out.println("Causes attack on drone: "+alien.getAttackDeathStatistic().getAttacks().toString() + " Dice: " + thrownValue);
                drone.damageDrone();
                System.out.println("Drone health: " + drone.getHealth());
            }
        }
        if (!drone.isDestroyed())
            while(true){
                thrownValue = Dice.throwd6();
                if(alien.isAlienDead(thrownValue)) {
                    System.out.println("Causes alien death: "+alien.getAttackDeathStatistic().getDeaths().toString() + " Dice: " + thrownValue);
                    alien.destroy();
                    System.out.println("Alien dead: " + alien.isDestroyed());
                    break;
                }
                thrownValue = Dice.throwd6();
                if(alien.isDroneAttacked(thrownValue)){
                    System.out.println("Causes attack on drone: "+alien.getAttackDeathStatistic().getAttacks().toString() + " Dice: " + thrownValue);
                    drone.damageDrone();
                    System.out.println("Drone health: " + drone.getHealth());
                    if (drone.isDestroyed()) break;
                }

            }

        System.out.println("Fight ended");

    }

    public void testMethod() {
        moveDrone("down");
        moveDrone("left");
        moveDrone("down");
        moveDrone("left");
        moveDrone("down");
        moveDrone("left");
        moveDrone("down");
        moveDrone("left");
    }






}
