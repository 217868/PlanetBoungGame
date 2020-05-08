package logic.data.planetmodels;

import logic.data.Resource;
import logic.data.shipmodels.ResourceType;

import java.util.ArrayList;

public class Planet {
    PlanetType planetType;
    ArrayList<Resource> resources;
    SpaceStation spaceStation;

    public Planet(PlanetType planetType){
        this.planetType = planetType;
        this.resources = ResourceToPlanetMap.getResourcesForPlanetType(planetType);
        this.spaceStation = null;
    }

    public Planet(PlanetType planetType, SpaceStation spaceStation){
        this.planetType = planetType;
        this.resources = ResourceToPlanetMap.getResourcesForPlanetType(planetType);
        this.spaceStation = spaceStation;
    }

    public PlanetType getPlanetType() {
        return planetType;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void deleteResource(ResourceType resourceType){
        for (Resource r : resources) {
            if(r.getResourceType() == resourceType)
                resources.remove(r);
        }
    }

    public boolean hasSpaceStation(){
        if(spaceStation == null) return false;
        return true;
    }
}
