package logic;

import logic.data.Resource;
import logic.data.planetmodels.Alien;
import logic.data.planetmodels.AlienFactory;
import logic.data.planetmodels.Planet;
import logic.data.shipmodels.Drone;
import logic.data.shipmodels.Ship;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PlanetExplorationLogic {
    private Drone drone;
    private Alien alien;
    private Planet planet;
    private int resourcePositionX;
    private int resourcePositionY;
    private Resource resource;
    private int alienWaitingCounter;
    private int droneInitialPositionX;
    private int droneInitialPositionY;
    private boolean hasResource;

    public PlanetExplorationLogic(/*Ship ship, Resource resource*/){
       // this.drone = ship.getDrone();
       // this.resource = resource;
        this.drone = new Drone();
        initiateCoordinates();
        this.droneInitialPositionX = drone.getX();
        this.droneInitialPositionY = drone.getY();
        this.hasResource = false;
        alienWaitingCounter = 0;
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
        if (drone.isDestroyed()) return;



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
        if(isResourceReached(drone.getX(), drone.getY())) resourceReached();
        if(isDroneBackInShip()) backInShip();

        if (alien != null) {

            if (!checkIfTheCoordinatesAreOutsideRange(drone.getX(), drone.getY(), alien.getX(), alien.getY()))
                initiateFight(true);
            if (alien != null) {
                moveAlien();
                if (!checkIfTheCoordinatesAreOutsideRange(alien.getX(), alien.getY(), drone.getX(), drone.getY()))
                    initiateFight(false);
            }
        }
        if (alien == null ){
            alienWaitingCounter--;
            if (alienWaitingCounter == 0) createNewAlien();
        }
    }

    private void createNewAlien() {
        int alienX;
        int alienY;

        do{
            alienX = Dice.throwd6();
            alienY = Dice.throwd6();
        } while(!checkIfTheCoordinatesAreOutsideRange(alienX, alienY, drone.getX(), drone.getY()) || !checkIfTheCoordinatesAreOutsideRange(resourcePositionX, resourcePositionY, alienX, alienY));

        this.alien = AlienFactory.createRandomAlien(alienX, alienY);
    }

    private void initiateFight(boolean doesDroneBeginFight) {
        int thrownValue = Dice.throwd6();
        if(!doesDroneBeginFight){
            if(alien.isDroneAttacked(thrownValue)){
                System.out.println("Causes attack on drone: "+alien.getAttackDeathStatistic().getAttacks().toString() + " Dice: " + thrownValue);
                drone.damageDrone();
                if (drone.isDestroyed()) droneDestroyed();
                System.out.println("Drone health: " + drone.getHealth());
            }
        }
        if (!drone.isDestroyed()) {
            while(true){
                thrownValue = Dice.throwd6();
                if(alien.isAlienDead(thrownValue)) {
                    System.out.println("Causes alien death: "+alien.getAttackDeathStatistic().getDeaths().toString() + " Dice: " + thrownValue);
                    alien.destroy();
                    alienDestroyed();
                    System.out.println("Alien dead");
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
        }

        System.out.println("Fight ended");

    }

    private void droneDestroyed() {
        //TODO: stopgame
    }

    private void alienDestroyed() {
        alien = null;
        alienWaitingCounter = Dice.throwd6();
        System.out.println("Moves before next alien arrives: " + alienWaitingCounter);
    }

    public void resourceReached(){
        System.out.println("Resource reached");
    }

    public void backInShip(){
        if(isResourceInShip()) System.out.println("Back in ship with a resource");
        else System.out.println("Back in ship without a resource");
    }

    public boolean isResourceReached(int droneX, int droneY){
        if(droneX == resourcePositionX && droneY == resourcePositionY){
            this.hasResource = true;
            return true;
        }
        else return false;
    }

    public boolean isDroneBackInShip(){
        if(drone.getX() == droneInitialPositionX && drone.getY() == droneInitialPositionY){
            if(isComeBackToShipConfirmed()) return true;
            else return false;
        }
        else return false;
    }

    public boolean isResourceInShip(){
        return hasResource;
    }

    public boolean isComeBackToShipConfirmed(){
        System.out.println("Do you wanna come back to the ship? y/n");
        Scanner scanner = new Scanner(System.in);
        char option = scanner.next().charAt(0);
        if(option == 'y') return true;
        else if(option == 'n') return false;
        return false;
    }

    public void testMethod() {
        Scanner scanner = new Scanner(System.in);
        char move;
        do{
            drawGrid();
            System.out.println("Drone move: ");
            move = scanner.next().charAt(0);
            if(move == 'w') moveDrone("up");
            else if(move == 'a') moveDrone("left");
            else if(move == 's') moveDrone("down");
            else if(move == 'd') moveDrone("right");
        } while(!isDroneBackInShip());

    }


    // Test method to draw grid
    private void drawGrid() {
        System.out.println("-------------");

        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                if (alien != null && alien.getX() == j && alien.getY() == i) System.out.print("a");
                else if (drone.getX() == j && drone.getY() == i) System.out.print("d");
                else if (resourcePositionX == j && resourcePositionY == i) System.out.print("r");
                else System.out.print("o");
            }
            System.out.print("\n");
        }

        System.out.println("-------------\n");
    }






}
