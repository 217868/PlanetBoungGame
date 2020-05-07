package logic.data.planetmodels;

import logic.data.Resource;
import logic.data.shipmodels.Ship;

public class SpaceStation implements ISpaceStation {

    private Ship dockedShip;

    public SpaceStation(Ship dockedShip) {
        this.dockedShip = dockedShip;
    }


    @Override
    public void upgradeCargo() {
        try {
            dockedShip.getCargoSystem().upgradeCargoSystem();

            dockedShip.getCargoSystem().payAllResources(1);
        } catch (Exception e) {
            //TODO: handle
        }
    }

    @Override
    public void convertResource(Resource from, Resource to) {

    }

    @Override
    public void hireCrew() {
        try {
            dockedShip.hireOneCrewMember();

            dockedShip.getCargoSystem().payAllResources(1);
        } catch (Exception e) {
            //TODO: handle
        }
    }

    @Override
    public void upgradeWeaponSystem() {
        try {
            dockedShip.getWeaponSystem().upgradeWeaponSystem();

            dockedShip.getCargoSystem().payAllResources(2);
        } catch (Exception e) {
            //TODO: handle
        }
    }

    @Override
    public void replenishArmor() {
        try {
            dockedShip.getShieldSystem().replenishArmor();

            dockedShip.getCargoSystem().payAllResources(1);
        } catch (Exception e) {
            //TODO: handle
        }
    }

    @Override
    public void buyNewDrone() {
        try {
            dockedShip.getDrone().repairDrone();

            dockedShip.getCargoSystem().payAllResources(3);
        } catch (Exception e) {
            //TODO: handle
        }
    }
}
