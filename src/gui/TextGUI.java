package gui;

import logic.GameLogic;
import logic.data.Resource;
import logic.data.shipmodels.ResourceType;
import logic.states.*;

import java.util.Scanner;

public class TextGUI {

    GameLogic gl;
    boolean exit;
    public TextGUI() {
        gl = new GameLogic();
        exit = false;
    }

    public void run() {
        while(!exit){
            if(gl.getState() instanceof ShipSelectionState) shipSelectionGUI();
            else if(gl.getState() instanceof SpaceTravelState) spaceTravelGUI();
            else if(gl.getState() instanceof AtPlanetState) atPlanetGUI();
            else if(gl.getState() instanceof AtSpaceStationState) atSpaceStationGUI();
            else if(gl.getState() instanceof WaitingForReturnConfirmationState) waitingForReturnConfirmationGUI();
            else if(gl.getState() instanceof GameWonState) gameWonGUI();
            else if(gl.getState() instanceof GameLostState) gameLostGUI();
        }
    }

    private void shipSelectionGUI(){
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to Planet Bound!\n");
        System.out.println("Select type of your ship:");
        System.out.println("1. Military");
        System.out.println("2. Mining");
        System.out.println("0. Exit");

        int selection = clearBufforAndGetInput(s);
        switch(selection) {
            case 1:
                gl.chooseShip(true);
                break;
            case 2:
                gl.chooseShip(false);
                break;
            case 0:
                exit = true;
                break;
        }
    }
    private void spaceTravelGUI(){
        Scanner s = new Scanner(System.in);
        System.out.println("You are in space.");
        System.out.println("1. Explore planet");
        System.out.println("2. Travel to next region");
        if (gl.getGameData().getPlanet().hasSpaceStation()) System.out.println("3. Go to Space Station");
        System.out.println("0. Exit");

        int selection = clearBufforAndGetInput(s);
        switch(selection) {
            case 1:
                gl.explorePlanet();
                break;
            case 2:
                gl.goToNextRegion();
                break;
            case 3:
                if (gl.getGameData().getPlanet().hasSpaceStation()) gl.goToSpaceStation();
                break;
            case 0:
                exit = true;
                break;
        }
    }
    private void atPlanetGUI(){
        Scanner s = new Scanner(System.in);
        System.out.println("You are at the planet surface.");
        System.out.println("Planet has resource type " + gl.getGameData().getExLogic().getResource().getResourceType() + "\n");

        System.out.print("\n");
        gl.getGameData().getExLogic().drawGrid();
        System.out.print("\n");

        System.out.println("w - Move up");
        System.out.println("a - Move left");
        System.out.println("s - Move down");
        System.out.println("d - Move right");
        System.out.println("0. Exit");

        char selection = s.next().charAt(0);
        switch(selection) {
            case 'w':
                gl.goUp();
                break;
            case 'a':
                gl.goLeft();
                break;
            case 's':
                gl.goDown();
                break;
            case 'd':
                gl.goRight();
                break;
            case '0':
                exit = true;
                break;
        }

    }
    /*
    upgradeCargo();
    IState convertResource(Resource from, Resource to);
    IState hireCrew();
    IState upgradeWeaponSystem();
    IState replenishArmor();
    IState buyNewDrone();
     */
    private void atSpaceStationGUI(){
        Scanner s = new Scanner(System.in);
        System.out.println("You are at Space Station. You can perfom one action:\n");
        System.out.println("1. Upgrade cargo");
        System.out.println("2. Convert resource");
        System.out.println("3. Hire new crew member");
        System.out.println("4. Upgrade weapon system");
        System.out.println("5. Replenish armor");
        System.out.println("6. Buy a new drone");
        System.out.println("0. Exit");

        int selection = clearBufforAndGetInput(s);
        switch(selection) {
            case 1:
                gl.upgradeCargo();
                break;
            case 2:
                System.out.println("From resource:");
                Resource r1 = getResources();
                System.out.println("To resource:");
                Resource r2 = getResources();
                gl.convertResource(r1, r2);
                break;
            case 3:
                gl.hireCrew();
                break;
            case 4:
                gl.upgradeWeaponSystem();
                break;
            case 5:
                gl.replenishArmor();
                break;
            case 6:
                gl.buyNewDrone();
                break;
            case 0:
                exit = true;
                break;
        }
    }
    private void waitingForReturnConfirmationGUI(){
        System.out.println("Are you sure you want to return?");
        if (gl.getGameData().getExLogic().isResourceInShip())
            System.out.println("You have gathered resources\n");
        else System.out.println("You have not gathered any resources\n");

        Scanner s = new Scanner(System.in);
        System.out.println("1. Stay on the planet");
        System.out.println("2. Return to the ship");
        System.out.println("0. Exit");

        int selection = clearBufforAndGetInput(s);
        switch(selection) {
            case 1:
                gl.declineReturn();
                break;
            case 2:
                gl.acceptReturn();
                break;
            case 0:
                exit = true;
                break;
        }
    }
    private void gameWonGUI(){
        System.out.println("You have won the game!:\n");
        gameOverGUI();
    }
    private void gameLostGUI(){
        System.out.println("You have lost the game.:\n");
        gameOverGUI();
    }

    private void gameOverGUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("1. Play again");
        System.out.println("0. Exit");

        int selection = clearBufforAndGetInput(s);
        switch(selection) {
            case 1:
                gl.playAgain();
                break;
            case 0:
                exit = true;
                break;
        }
    }

    private int clearBufforAndGetInput(Scanner s) {
        while (!s.hasNextInt()) {
            s.next();
        }
        return s.nextInt();
    }

    private Resource getResources() {
        System.out.println("Available resources: 1. BLACK, 2. BLUE, 3. RED, 4.GREEN");
        Scanner s = new Scanner(System.in);
        int selection = clearBufforAndGetInput(s);
        switch(selection) {
            case 1:
                return new Resource(ResourceType.BLACK);
            case 2:
                return new Resource(ResourceType.BLUE);
            case 3:
                return new Resource(ResourceType.RED);
            case 4:
                return new Resource(ResourceType.GREEN);
            default:
                return new Resource(ResourceType.BLACK);

        }
    }

}
