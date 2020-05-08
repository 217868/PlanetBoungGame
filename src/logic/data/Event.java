package logic.data;

import logic.Dice;
import logic.data.shipmodels.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class Event {

    EventTypes eventType;

    public Event(EventTypes eventType) {
        this.eventType = eventType;
    }

    public Event() {
        this.eventType = EventTypes.randomEvent();
    }

    public void runSpecificEvent(Ship ship) {
        switch(this.eventType) {

            case CREW_DEATH:
                ship.killOneCrewMember();
                break;
            case SALVAGE_SHIP:
                int amount = Dice.throwd6();
                ship.getCargoSystem().addRandomResource(amount);
                break;
            case CARGO_LOSS:
                int rand = ThreadLocalRandom.current().nextInt(0, 3);
                int amount2 = Dice.throwd3();
                switch (rand) {
                    case 0:
                        ship.getCargoSystem().loseBlackResource(amount2);
                        break;
                    case 1:
                        ship.getCargoSystem().loseBlueResource(amount2);
                        break;
                    case 2:
                        ship.getCargoSystem().loseRedResource(amount2);
                        break;
                    case 3:
                        ship.getCargoSystem().loseGreenResource(amount2);
                        break;
                }
                break;
            case FUEL_LOSS:
                ship.getFuelSystem().spendFuel(1);
                break;
            case NO_EVENT:
                break;
            case CREW_RESCUE:
                ship.hireOneCrewMember();
                break;
        }
    }

}
