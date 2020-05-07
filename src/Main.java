import logic.Dice;
import logic.data.Resource;
import logic.data.planetmodels.PlanetType;
import logic.data.planetmodels.ResourceToPlanetMap;

public class Main {

    public static void main(String[] args) {
        ResourceToPlanetMap resourceToPlanetMap = new ResourceToPlanetMap();
        for (Resource resource: resourceToPlanetMap.getResourcesForPlanetType(PlanetType.BLUE)) {
            System.out.println(resource.getResourceType().toString());
        }
    }
}
